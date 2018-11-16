package org.abimon.revelation.parboiled

import org.parboiled.Action
import org.parboiled.BaseParser
import org.parboiled.Parboiled
import org.parboiled.Rule
import org.parboiled.annotations.BuildParseTree
import org.parboiled.parserunners.ReportingParseRunner
import org.parboiled.support.Var
import java.math.BigDecimal

/**
 * We use this here as an alternative to a JSON library because we need it for literally two files
 */
@Suppress("UNUSED_PARAMETER")
@BuildParseTree
open class JsonParser(parboiledCreated: Boolean) : BaseParser<Any>() {
    companion object {
        operator fun invoke(): JsonParser = Parboiled.createParser(JsonParser::class.java, true)
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

    open val whitespace = (Character.MIN_VALUE until Character.MAX_VALUE).filter { Character.isWhitespace(it) }.toCharArray()

    open fun Digit(): Rule = Digit(10)
    open fun Digit(base: Int): Rule = FirstOf(AnyOf(digitsLower.sliceArray(0 until base)), AnyOf(digitsUpper.sliceArray(0 until base)))
    open fun WhitespaceCharacter(): Rule = AnyOf(whitespace)
    open fun OptionalWhitespace(): Rule = ZeroOrMore(WhitespaceCharacter())
    open fun InlineWhitespaceCharacter(): Rule = AnyOf(charArrayOf('\t', ' '))
    open fun InlineWhitespace(): Rule = OneOrMore(InlineWhitespaceCharacter())
    open fun OptionalInlineWhitespace(): Rule = ZeroOrMore(InlineWhitespaceCharacter())

    @Suppress("UNCHECKED_CAST")
    fun parse(string: String): Map<String, Any>? {
        val runner = ReportingParseRunner<Any>(JsonObject())
        val result = runner.run(string)

        return result.resultValue as? Map<String, Any>
    }

    open fun JsonObject(): Rule {
        val mapVar = Var<MutableMap<String, Any>>(HashMap())

        val pushToMap = Action<Any> {
            val kv = pop()

            if (kv is Pair<*, *>) {
                val (key, value) = kv

                if (key !is String)
                    return@Action false

                mapVar.get()[key] = value ?: return@Action true

                return@Action true
            }

            return@Action false
        }

        return Sequence(
                '{',
                Action<Any> { mapVar.get().clear(); true },
                Optional(
                        JsonKeyValuePair(),
                        pushToMap,
                        ZeroOrMore(
                                ',',
                                JsonKeyValuePair(),
                                pushToMap
                        )
                ),
                '}',
                Action<Any> { push(mapVar.get()) }
        )
    }

    open fun JsonArray(): Rule {
        val arrayVar = Var<MutableList<Any>>(ArrayList())

        return Sequence(
                '[',
                Action<Any> { arrayVar.get().clear(); true },
                Optional(
                        OptionalWhitespace(),
                        JsonValue(),
                        OptionalWhitespace(),
                        Action<Any> { arrayVar.get().add(pop()) },
                        ZeroOrMore(',', OptionalWhitespace(), JsonValue(), OptionalWhitespace(), Action<Any> { arrayVar.get().add(pop()) })
                ),
                Action<Any> { push(arrayVar.get()) },
                ']'
        )
    }

    open fun JsonKeyValuePair(): Rule {
        val key = Var<String>()
        val value = Var<Any>()

        return Sequence(
                OptionalWhitespace(),
                JsonString(),
                Action<Any> { key.set(pop() as String) },
                OptionalWhitespace(),
                ':',
                OptionalWhitespace(),
                JsonValue(),
                Action<Any> { value.set(pop()) },
                OptionalWhitespace(),
                Action<Any> { push(key.get() to value.get()) }
        )
    }

    open fun JsonString(): Rule {
        val str = Var<String>()

        return Sequence(
                "\"",
                Action<Any> { str.set("") },
                Optional(
                        OneOrMore(
                                FirstOf(
                                        Sequence(
                                                "\\",
                                                FirstOf(
                                                        Sequence(
                                                                FirstOf(
                                                                        "\"",
                                                                        "\\",
                                                                        "/",
                                                                        "b",
                                                                        "f",
                                                                        "n",
                                                                        "r",
                                                                        "t"
                                                                ),
                                                                Action<Any> {
                                                                    when (match()) {
                                                                        "\"" -> str.set(str.get() + "\"")
                                                                        "\\" -> str.set(str.get() + "\\")
                                                                        "/" -> str.set(str.get() + "/")
                                                                        "b" -> str.set(str.get() + "\b")
                                                                        "f" -> str.set(str.get() + 0xC.toChar())
                                                                        "n" -> str.set(str.get() + "\n")
                                                                        "r" -> str.set(str.get() + "\r")
                                                                        "t" -> str.set(str.get() + "\t")
                                                                    }

                                                                    return@Action true
                                                                }
                                                        ),
                                                        Sequence(
                                                                "u",
                                                                NTimes(4, Digit(16)),
                                                                Action<Any> { str.set(str.get() + match().toInt(16).toChar()) }
                                                        )
                                                )
                                        ),
                                        Sequence(
                                                AllButMatcher(charArrayOf('\\', '"')),
                                                Action<Any> { str.set(str.get() + match()) }
                                        )
                                )
                        )
                ),
                Action<Any> { push(str.get()) },
                "\""
        )
    }

    open fun JsonNumber(): Rule {
        val floating = Var<Boolean>()

        return Sequence(
                Sequence(
                        Action<Any> { floating.set(false) },
                        Optional("-"),
                        FirstOf(
                                "0",
                                Sequence(
                                        FirstOf("1", "2", "3", "4", "5", "6", "7", "8", "9"),
                                        OneOrMore(Digit())
                                )
                        ),
                        Optional(
                                '.',
                                OneOrMore(Digit()),
                                Action<Any> { floating.set(true) }
                        ),

                        Optional(
                                FirstOf('e', 'E'),
                                Optional(FirstOf('+', '-')),
                                OneOrMore(Digit()),
                                Action<Any> { floating.set(true) }
                        )
                ),

                Action<Any> {
                    val num = BigDecimal(match())

                    return@Action push(if (floating.get()) num.toDouble() else num.longValueExact())
                }
        )
    }

    open fun JsonValue(): Rule = FirstOf(
            JsonString(),
            JsonNumber(),

            JsonObject(),
            JsonArray(),

            Sequence(
                    "true",
                    push(true)
            ),
            Sequence(
                    "false",
                    push(false)
            ),
            Sequence(
                    "null",
                    push(null)
            )
    )
}