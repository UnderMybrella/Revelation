package org.abimon.revelation.parboiled

import com.jakewharton.fliptables.FlipTable
import org.abimon.revelation.*
import org.abimon.revelation.characters.NPCTraits
import org.abimon.revelation.characters.Names
import org.abimon.revelation.characters.VillainTraits
import org.abimon.revelation.characters.XGtECharacterBuilding
import org.abimon.revelation.iona.TempleOfForgottenRealms
import org.abimon.revelation.tables.DMWorkshop
import org.abimon.revelation.tables.RandomDungeons
import org.parboiled.Action
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.StringVar
import org.parboiled.support.Var

@BuildParseTree
open class RevelationParser(parboiledCreated: Boolean) : BaseParser<Any>() {
    companion object {
        operator fun invoke(): RevelationParser = Parboiled.createParser(RevelationParser::class.java, true)
    }

    open val whitespace = (Character.MIN_VALUE until Character.MAX_VALUE).filter { Character.isWhitespace(it) }.toCharArray()

    open fun WhitespaceCharacter(): Rule = AnyOf(whitespace)
    open fun OptionalWhitespace(): Rule = ZeroOrMore(WhitespaceCharacter())
    open fun Whitespace(): Rule = OneOrMore(WhitespaceCharacter())
    open fun InlineWhitespaceCharacter(): Rule = AnyOf(charArrayOf('\t', ' '))
    open fun InlineWhitespace(): Rule = OneOrMore(InlineWhitespaceCharacter())
    open fun OptionalInlineWhitespace(): Rule = ZeroOrMore(InlineWhitespaceCharacter())

    fun parse(input: String): RevelationOutput? {
        val header = ReportingParseRunner<Any>(Macros())
        val runner = ReportingParseRunner<Any>(CommandLine())

        val headerResult = header.run(input)

        if (headerResult.matched) {
            val result = runner.run(headerResult.valueStack.reversed().joinToString("\n"))

            if (result.matched) {
                val data = RevelationOutput()
                result.valueStack.toList().reversed().forEach { value ->
                    when (value) {
                        is RevelationFunc -> value(data)
                        is Pair<*, *> -> data.add(value.first.toString() to value.second)
                    }
                }

                return data
            } else {
                return null
            }
        } else {
            return null
        }
    }

    open val digitsLower = charArrayOf(
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z'
    )

    open val digitsUpper = charArrayOf(
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B',
            'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T',
            'U', 'V', 'W', 'X', 'Y', 'Z'
    )

    open fun Digit(): Rule = Digit(10)
    open fun Digit(base: Int): Rule = FirstOf(AnyOf(digitsLower.sliceArray(0 until base)), AnyOf(digitsUpper.sliceArray(0 until base)))

    open fun Macros(): Rule =
            Sequence(MacroLine(), ZeroOrMore(OptionalInlineWhitespace(), AnyOf(charArrayOf(';', '|', '\n')), OptionalInlineWhitespace(), MacroLine()))

    open val npcMacroGen = arrayOf(
            "appearance", "stats", "talent", "mannerism", "interaction trait", "flaw",
            "bond", "gender", "parent", "birthplace", "family", "family lifestyle",
            "number of siblings", "birth order", "alignment", "status", "occupation", "race", "married",
            "name", "age"
    )

    open val macros = mapOf(
            "npc" to buildString {
                appendln("change_format npc")
                appendln("gen ${npcMacroGen.joinToString { str -> "ifm:$str" }}")
                appendln("gen ifm:childhood home, ifm:childhood memories")
            },
            "social" to buildString {
                appendln("change_format social")
                appendln("gen ifm:argumentative, ifm:arrogant, ifm:blustering, ifm:curious, ifm:friendly, ifm:honest, ifm:hot-tempered, ifm:irritable, ifm:ponderous, ifm:quiet, ifm:suspicious")
            }
    )

    open fun MacroLine(): Rule = FirstOf(
            Sequence(
                    IgnoreCase("macro:"),
                    FirstOf(macros.keys.map(this::IgnoreCase).toTypedArray()),
                    Action<Any> { push(macros[match().toLowerCase()]) }
            ),
            Sequence(
                    OneOrMore(AllButMatcher(charArrayOf(';', '|', '\n'))),
                    Action<Any> { push(match()) }
            )
    )

    open fun Command(): Rule = FirstOf(
            Hello(),
            Gen(),
            Format(),
            Add(),
            Set(),
            Script(),
            RollCommand(),
            DumpTable()
    )

    open fun CommandLine(): Rule =
            Sequence(Command(), ZeroOrMore(OptionalInlineWhitespace(), AnyOf(charArrayOf(';', '|', '\n')), OptionalInlineWhitespace(), Command()))

    open fun Hello(): Rule = Sequence(
            IgnoreCase("Hello"),
            Action<Any> { pushRunnable { println("Hello, World!") } }
    )

    open fun Gen(): Rule {
        val timesVar = Var<Int>(0)
        val actionVar = Var<Pair<String, () -> Any>>()
        val ifMissingVar = Var<Boolean>(false)
        val baseStrings = mapOf(
                "appearance" to NPCTraits::appearance,
                "high stat" to NPCTraits::highAbility,
                "low stat" to NPCTraits::lowAbility,
                "talent" to NPCTraits::npcTalent,
                "mannerism" to NPCTraits::npcMannerism,
                "interaction trait" to NPCTraits::npcInteractionTrait,
                "flaw" to NPCTraits::npcFlaws,
                "bond" to NPCTraits::npcBond,

                "good ideal" to NPCTraits::npcGoodIdeal,
                "evil ideal" to NPCTraits::npcEvilIdeal,
                "lawful ideal" to NPCTraits::npcLawfulIdeal,
                "chaotic ideal" to NPCTraits::npcChaoticIdeal,
                "neutral ideal" to NPCTraits::npcNeutralIdeal,
                "other ideal" to NPCTraits::npcOtherIdeal,

                "gender" to NPCTraits::gender,

                /** XGtE */
                "parent" to XGtECharacterBuilding::parents,
                "half-elf parent" to XGtECharacterBuilding::halfElfParents,
                "half-orc parent" to XGtECharacterBuilding::halfOrcParents,
                "tiefling parent" to XGtECharacterBuilding::tieflingParents,
                "birthplace" to XGtECharacterBuilding::birthplace,
                "family" to XGtECharacterBuilding::family,
                "family lifestyle" to XGtECharacterBuilding::familyLifestyle,
                "average childhood home" to XGtECharacterBuilding::averageChildhoodHome,
                "average childhood memories" to XGtECharacterBuilding::averageChildhoodMemories,
                "number of sibling" to XGtECharacterBuilding::numberOfSiblings,
                "number of elf sibling" to XGtECharacterBuilding::numberOfSiblingsLongevity,
                "number of dwarf sibling" to XGtECharacterBuilding::numberOfSiblingsLongevity,
                "birth order" to XGtECharacterBuilding::birthOrder,
                "absent parents" to XGtECharacterBuilding::absentParents,
                "absent parent" to XGtECharacterBuilding::absentParent,
                "alignment" to XGtECharacterBuilding::alignment,
                "cause of death" to XGtECharacterBuilding::causeOfDeath,
                "adventurer class" to XGtECharacterBuilding::adventurerClass,
                "occupation" to XGtECharacterBuilding::occupation,
                "race" to XGtECharacterBuilding::race,
                "relationship" to XGtECharacterBuilding::relationship,
                "status" to XGtECharacterBuilding::status,
                "elf subrace" to XGtECharacterBuilding::elfSubrace,
                "dwarf subrace" to XGtECharacterBuilding::dwarfSubrace,
                "halfling subrace" to XGtECharacterBuilding::halflingSubrace,
                "gnome subrace" to XGtECharacterBuilding::gnomeSubrace,
                "dragonborn subrace" to XGtECharacterBuilding::dragonbornSubrace,

                "adventure" to XGtECharacterBuilding::adventure,

                "married" to { if (DiceSet.d3() == 3) "Married" else "Single" },

                "inner_plane" to ::innerPlane,
                "outer_plane" to ::outerPlane,

                /** Villain Traits */
                "immortality_scheme" to VillainTraits::immortalityScheme,

                /** Iona */

                /** Temple of Forgotten Realms */
                "temple_buff" to TempleOfForgottenRealms::buff,
                "temple_debuff" to TempleOfForgottenRealms::debuff,
                "temple_environmental" to TempleOfForgottenRealms::environmental,

                /** Social */

                "argumentative" to DiceSet.d8::invoke,
                "arrogant" to DiceSet.d8::invoke,
                "blustering" to DiceSet.d8::invoke,
                "curious" to DiceSet.d8::invoke,
                "friendly" to DiceSet.d8::invoke,
                "honest" to DiceSet.d8::invoke,
                "hot-tempered" to DiceSet.d8::invoke,
                "irritable" to DiceSet.d8::invoke,
                "ponderous" to DiceSet.d8::invoke,
                "quiet" to DiceSet.d8::invoke,
                "suspicious" to DiceSet.d8::invoke
        )

        val generation = Sequence(
                Action<Any> { timesVar.set(1); actionVar.set("" to { "" }); ifMissingVar.set(false) },
                Optional(
                        "ifm:",
                        Action<Any> { ifMissingVar.set(true) }
                ),
                Optional(
                        Roll(),
                        Action<Any> { timesVar.set(pop().toString().toInt()) },
                        ':'
                ),
                FirstOf(
                        Sequence(
                                "stats",
                                Action<Any> { actionVar.set("stats" to { (0 until 6).map { (0 until 4).map { DiceSet.d6() }.sorted().drop(1).sum() }.sorted().joinToString() }) }
                        ),
                        Sequence(
                                "childhood home",
                                FirstOf(
                                        Sequence(
                                                OptionalInlineWhitespace(),
                                                '(',
                                                OptionalInlineWhitespace(),
                                                FirstOf(
                                                        Sequence(
                                                                '-',
                                                                OneOrMore(Digit()),
                                                                Action<Any> { (-match().toInt()).let { num -> actionVar.set("childhood home" to { XGtECharacterBuilding.childhoodHome(num) }) } }
                                                        ),
                                                        Sequence(
                                                                Optional('+'),
                                                                OneOrMore(Digit()),
                                                                Action<Any> { (match().toInt()).let { num -> actionVar.set("childhood home" to { XGtECharacterBuilding.childhoodHome(num) }) } }
                                                        ),
                                                        Sequence(
                                                                FirstOf(
                                                                        IgnoreCase("Wretched"),
                                                                        IgnoreCase("Squalid"),
                                                                        IgnoreCase("Poor"),
                                                                        IgnoreCase("Modest"),
                                                                        IgnoreCase("Comfortable"),
                                                                        IgnoreCase("Wealthy"),
                                                                        IgnoreCase("Aristocratic")
                                                                ),
                                                                Action<Any> {
                                                                    val modifier = when (match().toUpperCase()) {
                                                                        "WRETCHED" -> -40
                                                                        "SQUALID" -> -20
                                                                        "POOR" -> -10
                                                                        "MODEST" -> 0
                                                                        "COMFORTABLE" -> +10
                                                                        "WEALTHY" -> +20
                                                                        "ARISTOCRATIC" -> +40

                                                                        else -> 0
                                                                    }

                                                                    actionVar.set("childhood home" to { XGtECharacterBuilding.childhoodHome(modifier) })
                                                                }
                                                        )
                                                ),
                                                OptionalInlineWhitespace(),
                                                ')'
                                        ),
                                        Sequence(
                                                Action<Any> { context -> context.valueStack.any { value -> value is Pair<*, *> && value.first == "family lifestyle" } },
                                                Action<Any> { context ->
                                                    val modifier = when ((context.valueStack.first { value -> value is Pair<*, *> && value.first == "family lifestyle" } as Pair<*, *>).second.toString().toUpperCase().substringBefore(' ')) {
                                                        "WRETCHED" -> -40
                                                        "SQUALID" -> -20
                                                        "POOR" -> -10
                                                        "MODEST" -> 0
                                                        "COMFORTABLE" -> +10
                                                        "WEALTHY" -> +20
                                                        "ARISTOCRATIC" -> +40

                                                        else -> 0
                                                    }

                                                    actionVar.set("childhood home" to { XGtECharacterBuilding.childhoodHome(modifier) })
                                                }
                                        )
                                )
                        ),
                        Sequence(
                                "childhood memories",
                                OptionalInlineWhitespace(),
                                '(',
                                OptionalInlineWhitespace(),
                                FirstOf(
                                        Sequence(
                                                '-',
                                                OneOrMore(Digit()),
                                                Action<Any> { (-match().toInt()).let { num -> actionVar.set("childhood memories" to { XGtECharacterBuilding.childhoodMemories(num) }) } }
                                        ),
                                        Sequence(
                                                Optional('+'),
                                                OneOrMore(Digit()),
                                                Action<Any> { (match().toInt()).let { num -> actionVar.set("childhood memories" to { XGtECharacterBuilding.childhoodMemories(num) }) } }
                                        )
                                ),
                                OptionalInlineWhitespace(),
                                ')'
                        ),
                        Sequence(
                                "name",
                                FirstOf(
                                        Sequence(
                                                OptionalInlineWhitespace(),
                                                '(',
                                                OptionalInlineWhitespace(),
                                                FirstOf(
                                                        IgnoreCase("Dwarf"),
                                                        IgnoreCase("Elf"),
                                                        IgnoreCase("Halfling"),
                                                        IgnoreCase("Human"),
                                                        IgnoreCase("Dragonborn"),
                                                        IgnoreCase("Gnome"),
                                                        IgnoreCase("Half-Elf"),
                                                        IgnoreCase("Half-Orc"),
                                                        IgnoreCase("Tiefling")
                                                ),
                                                Action<Any> { actionVar.set(match() to {}) },
                                                OptionalInlineWhitespace(),
                                                ',',
                                                OptionalInlineWhitespace(),
                                                ParameterBut(')'),
                                                Action<Any> {
                                                    val name = NPCTraits.name(actionVar.get().first, pop() as String)
                                                    actionVar.set("name" to name::toString)
                                                },
                                                ')'
                                        ),
                                        Sequence(
                                                Action<Any> { context -> context.valueStack.any { value -> value is Pair<*, *> && value.first == "race" } && context.valueStack.any { value -> value is Pair<*, *> && value.first == "gender" } },
                                                Action<Any> { context ->
                                                    val name = NPCTraits.name(
                                                            (context.valueStack.first { value -> value is Pair<*, *> && value.first == "race" } as Pair<*, *>).second.toString().substringBefore(" ("),
                                                            (context.valueStack.first { value -> value is Pair<*, *> && value.first == "gender" } as Pair<*, *>).second.toString()
                                                    )

                                                    actionVar.set("name" to name::toString)
                                                }
                                        )
                                )
                        ),
                        Sequence(
                                "age",
                                FirstOf(
                                        Sequence(
                                                OptionalInlineWhitespace(),
                                                '(',
                                                OptionalInlineWhitespace(),
                                                Sequence(
                                                        FirstOf(
                                                                IgnoreCase("Dwarf"),
                                                                IgnoreCase("Elf"),
                                                                IgnoreCase("Halfling"),
                                                                IgnoreCase("Human"),
                                                                IgnoreCase("Dragonborn"),
                                                                IgnoreCase("Gnome"),
                                                                IgnoreCase("Half-Elf"),
                                                                IgnoreCase("Half-Orc"),
                                                                IgnoreCase("Tiefling")
                                                        ),
                                                        Action<Any> {
                                                            val age = NPCTraits.age(match())

                                                            actionVar.set("age" to { age })
                                                        }
                                                ),
                                                OptionalInlineWhitespace(),
                                                ')'
                                        ),
                                        Sequence(
                                                Action<Any> { context -> context.valueStack.any { value -> value is Pair<*, *> && value.first == "race" } },
                                                Action<Any> { context ->
                                                    val age = NPCTraits.age((context.valueStack.first { value -> value is Pair<*, *> && value.first == "race" } as Pair<*, *>).second.toString().substringBefore(" ("))

                                                    actionVar.set("age" to { age })
                                                }
                                        )
                                )
                        ),
                        FirstOf(baseStrings.entries.reversed().map { (key, func) -> Sequence(IgnoreCase(key), Optional('s'), Action<Any> { actionVar.set(key to func) }) }.toTypedArray())
                ),
                Action<Any> {
                    val (key, func) = actionVar.get()
                    if (ifMissingVar.get() && it.valueStack.any { value -> value is Pair<*, *> && value.first == key })
                        return@Action true

                    for (i in 0 until timesVar.get())
                        push(key to func())

                    return@Action true
                }
        )

        return Sequence(
                "gen",
                InlineWhitespace(),
                generation,
                ZeroOrMore(
                        OptionalInlineWhitespace(),
                        ',',
                        OptionalInlineWhitespace(),
                        generation
                )
        )
    }

    open val tableSource = arrayOf(NPCTraits, XGtECharacterBuilding, TempleOfForgottenRealms, VillainTraits, Names, DMWorkshop, RandomDungeons)

    open val tables: List<Pair<String, Map<Int, Any?>>> = tableSource.map(Any::findTables).flatten()
            .sortedBy { (key) -> key }.distinctBy { (key) -> key }.asReversed()

    open val tableNames: Map<String, List<Pair<String, String>>> = tableSource.map(Any::findTableNames).flatten()
            .groupBy { (_, tag) -> tag }
            .mapValues { (_, list) -> Triple(list[0].third, list[0].second, list[1].third) }
            .values.groupBy { (tag) -> tag }
            .mapValues { (_, list) ->
                list.map { (_, a, b) -> a to b }
                        .sortedBy { (key) -> key }.distinctBy { (key) -> key }.asReversed()
            }.toSortedMap(Comparator<String> { o1, o2 ->
                if (o1.startsWith("- "))
                    return@Comparator -1
                if (o2.startsWith("- "))
                    return@Comparator 1

                return@Comparator o1.compareTo(o2)
            })

    open fun RollCommand(): Rule {
        val rawVar = Var<Boolean>(false)

        return Sequence(
                "roll",
                InlineWhitespace(),
                FirstOf(
                        Sequence(
                                "raw:",
                                Action<Any> { rawVar.set(true) }
                        ),
                        Action<Any> { rawVar.set(false) }
                ),
                FirstOf(
                        Sequence(
                                Roll(),
                                Action<Any> {
                                    val string = buildString {
                                        if (rawVar.get())
                                            append(pop().toString())
                                        else
                                            appendln("You rolled a ${pop()}")
                                    }
                                    pushFunc { output -> output.raw.append(string) }
                                }
                        ),
                        FirstOf(tables.map { (key, imTheMap) ->
                            Sequence(
                                    IgnoreCase(key),
                                    ':',
                                    Roll(),
                                    Action<Any> { context ->
                                        val string = buildString {
                                            val resultNum = pop().toString().toIntOrNull() ?: 0
                                            var result = imTheMap[resultNum] ?: "Unknown"

                                            while (result is Function0<*>) {
                                                result = result() ?: "Unknown"
                                            }

                                            while (result.toString().startsWith("script:~[")) {
                                                result = parse(result.toString())?.format()?.trim() ?: "Unknown"
                                            }

                                            if (rawVar.get())
                                                append(result)
                                            else
                                                appendln("The result of rolling $resultNum on $key is: $result")
                                        }

                                        context.valueStack.push(RevelationFunc { output -> output.raw.append(string) })

                                        return@Action true
                                    }
                            )
                        }.toTypedArray())

                )
        )
    }

    open fun DumpTable(): Rule =
            Sequence(
                    "dump table ",
                    FirstOf(tables.map { (key, imTheMap) ->
                        Sequence(
                                IgnoreCase(key),
                                Action<Any> { context ->
                                    val string = buildString {
                                        val rows: MutableList<Array<String>> = ArrayList()
                                        imTheMap.keys.sorted().forEach { resultNum ->
                                            var result = imTheMap[resultNum] ?: "Unknown"

                                            while (result is Function0<*>) {
                                                result = result() ?: "Unknown"
                                            }

                                            while (result.toString().startsWith("script:~[")) {
                                                result = parse(result.toString())?.format()?.trim() ?: "Unknown"
                                            }

                                            rows.add(arrayOf(resultNum.toString(), result.toString()))
                                        }

                                        append(FlipTable.of(arrayOf("Roll", "Result"), rows.toTypedArray()))
                                    }

                                    context.valueStack.push(RevelationFunc { output -> output.raw.append(string) })
                                    return@Action true
                                }
                        )
                    }.toTypedArray())
            )

    open fun Add(): Rule {
        val valueVar = StringVar()

        return Sequence(
                IgnoreCase("Add"),
                " ",
                Parameter(),
                Action<Any> { valueVar.set(pop() as String) },
                " ",
                IgnoreCase("to"),
                " ",
                Parameter(),
                Action<Any> {
                    push((pop() as String) to valueVar.get())
                }
        )
    }

    open fun Set(): Rule {
        val keyVar = StringVar()

        return Sequence(
                IgnoreCase("Set"),
                " ",
                ParameterBut('='),
                Action<Any> { keyVar.set(pop() as String) },
                FirstOf(
                        Sequence(" ",
                                IgnoreCase("to"),
                                " "
                        ),
                        Sequence(
                                OptionalInlineWhitespace(),
                                '=',
                                OptionalInlineWhitespace()
                        )
                ),
                Parameter(),
                Action<Any> {
                    push(keyVar.get() to (pop() as String))
                }
        )
    }

    open fun Script(): Rule {
        val stringVar = StringVar()

        return Sequence(
                "script:",

                "~[",
                Action<Any> { stringVar.set("") },

                OneOrMore(
                        FirstOf(
                                Sequence(
                                        "{{",
                                        OptionalInlineWhitespace(),

                                        OneOrMore(AllButMatcher(charArrayOf('}', ']', '~'))),
                                        Action<Any> {
                                            RevelationParser().parse(match())?.format()?.trim()?.let(stringVar::append)
                                                    ?: false
                                        },

                                        OptionalInlineWhitespace(),
                                        "}}"
                                ),
                                Sequence(
                                        OneOrMore(AllButMatcher(charArrayOf('{', ']', '~'))),
                                        Action<Any> { stringVar.append(match()) }
                                )
                        )
                ),

                "]~",
                Action<Any> { pushFunc { output -> output.raw.appendln(stringVar.get()) } }
        )
    }

    open fun Parameter(): Rule = FirstOf(
            Sequence(
                    '"',
                    OneOrMore(ParamMatcher),
                    Action<Any> { push(match()) },
                    '"'
            ),
            Sequence(
                    OneOrMore(AllButMatcher(whitespace)),
                    Action<Any> { push(match()) }
            )
    )

    open fun ParameterBut(vararg blacklist: Char): Rule = FirstOf(
            Sequence(
                    '"',
                    OneOrMore(ParamMatcher),
                    Action<Any> { push(match()) },
                    '"'
            ),
            Sequence(
                    OneOrMore(AllButMatcher(whitespace.plus(blacklist))),
                    Action<Any> { push(match()) }
            )
    )

    open fun Format(): Rule = Sequence(
            IgnoreCase("change_format"),
            " ",
            FirstOf(
                    Sequence(
                            "raw",
                            Action<Any> { pushFunc { data -> data.format = RevelationOutput.RAW } }
                    ),
                    Sequence(
                            "npc",
                            Action<Any> { pushFunc { data -> data.format = RevelationOutput.NPC } }
                    ),
                    Sequence(
                            "social",
                            Action<Any> { pushFunc { data -> data.format = RevelationOutput.SOCIAL } }
                    )
            )
    )

    fun pushRunnable(block: () -> Unit): Boolean = push(Runnable(block))
    fun pushFunc(block: (RevelationOutput) -> Unit): Boolean = push(RevelationFunc(block))
    fun Var<Runnable>.set(block: () -> Unit): Boolean = set(Runnable(block))

    open fun DieRoll(): Rule {
        val tmpVar = Var<Int>()

        return Sequence(
                OneOrMore(Digit()),
                Action<Any> { tmpVar.set(match().toIntOrNull() ?: 1) },
                Optional(
                        'd',
                        OneOrMore(Digit()),
                        Action<Any> {
                            val diceSides = match().toIntOrNull() ?: 4
                            val die: DiceSet.Die = when (diceSides) {
                                2 -> DiceSet.d2
                                3 -> DiceSet.d3
                                4 -> DiceSet.d4
                                6 -> DiceSet.d6
                                8 -> DiceSet.d8
                                10 -> DiceSet.d10
                                12 -> DiceSet.d12
                                20 -> DiceSet.d20
                                100 -> DiceSet.d100
                                else -> DiceSet.StandardDie(diceSides)
                            }

                            tmpVar.set(die[tmpVar.get()])
                        }
                ),
                Action<Any> { push(tmpVar.get()) }
        )
    }

    open fun Roll(): Rule {
        val resultVar = Var<Int>()
        return Sequence(
                DieRoll(),
                Action<Any> { resultVar.set(pop() as Int) },
                ZeroOrMore(
                        FirstOf(
                                Sequence(
                                        '+',
                                        DieRoll(),
                                        Action<Any> { resultVar.set(resultVar.get() + (pop() as Int)) }
                                ),
                                Sequence(
                                        '-',
                                        DieRoll(),
                                        Action<Any> { resultVar.set(resultVar.get() - (pop() as Int)) }
                                ),
                                Sequence(
                                        '*',
                                        DieRoll(),
                                        Action<Any> { resultVar.set(resultVar.get() * (pop() as Int)) }
                                ),
                                Sequence(
                                        '/',
                                        DieRoll(),
                                        Action<Any> { resultVar.set(resultVar.get() / (pop() as Int)) }
                                ),
                                Sequence(
                                        '%',
                                        DieRoll(),
                                        Action<Any> { resultVar.set(resultVar.get() % (pop() as Int)) }
                                )
                        )
                ),
                Action<Any> { push(resultVar.get()) }
        )
    }
}