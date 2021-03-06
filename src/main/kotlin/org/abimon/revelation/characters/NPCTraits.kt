package org.abimon.revelation.characters

import org.abimon.revelation.*
import java.util.concurrent.ThreadLocalRandom

object NPCTraits {
    @RevelationTable("appearance", "appearances", recommendedRoll = "1d20", category = "Detailed NPCs")
    val APPEARANCE = mapOf(
            1 to { NPCAppearanceTrait.DISTINCTIVE_JEWELRY },
            2 to { NPCAppearanceTrait.PIERCINGS },
            3 to { NPCAppearanceTrait.FLAMBOYANT_CLOTHES },
            4 to { NPCAppearanceTrait.FORMAL_CLOTHES },
            5 to { NPCAppearanceTrait.RAGGED_CLOTHES },
            6 to { NPCAppearanceTrait.PRONOUNCED_SCAR },
            7 to { NPCAppearanceTrait.MISSING_TEETH },
            8 to { NPCAppearanceTrait.MISSING_FINGERS },
            9 to NPCAppearanceTrait::UNUSUAL_EYE_COLOURS,
            10 to { NPCAppearanceTrait.TATTOOS },
            11 to { NPCAppearanceTrait.BIRTHMARK },
            12 to NPCAppearanceTrait::UNUSUAL_SKIN_COLOR,
            13 to { NPCAppearanceTrait.BALD },
            14 to { NPCAppearanceTrait.BRAIDED_BEARD },
            15 to NPCAppearanceTrait::UNUSUAL_HAIR_COLOR,
            16 to { NPCAppearanceTrait.NERVOUS_EYE },
            17 to { NPCAppearanceTrait.DISTINCTIVE_NOSE },
            18 to { NPCAppearanceTrait.DISTINCTIVE_POSTURE },
            19 to { NPCAppearanceTrait.EXCEPTIONALLY_BEAUTIFUL },
            20 to { NPCAppearanceTrait.EXCEPTIONALLY_UGLY }
    )

    @RevelationTable("high ability", "high abilities", recommendedRoll = "1d6", category = "Detailed NPCs")
    val HIGH_ABILITY = mapOf<Int, String>(
            1 to "Strength - powerful, brawny, strong as an ox",
            2 to "Dexterity - lithe, agile, graceful",
            3 to "Constitution - hardy, hale, healthy",
            4 to "Intelligence - studious, learned, inquisitive",
            5 to "Wisdom - perceptive, spiritual, insightful",
            6 to "Charisma - persuasive, forceful, born leader"
    )

    @RevelationTable("low ability", "low abilities", recommendedRoll = "1d6", category = "Detailed NPCs")
    val LOW_ABILITY = mapOf(
            1 to "Strength - feeble, scrawny",
            2 to "Dexterity - clumsy, fumbling",
            3 to "Consitution - sickly, pale",
            4 to "Intelligence - dim-witted, slow",
            5 to "Wisdom - oblivious, absentminded",
            6 to "Charisma - dull, boring"
    )

    @RevelationTable("talent", "talents", recommendedRoll = "1d20", category = "Detailed NPCs")
    val TALENTS = mapOf<Int, Pair<String, () -> String>>(
            1 to ("Plays a musical instrument" to ::noExtra),
            2 to ("Speaks several languages fluently" to ::languages),
            3 to ("Unbelievably lucky" to ::noExtra),
            4 to ("Perfect memory" to ::noExtra),
            5 to ("Great with animals" to ::noExtra),
            6 to ("Great with children" to ::noExtra),
            7 to ("Great at solving puzzles" to ::noExtra),
            8 to ("Great at one game" to ::noExtra),
            9 to ("Great at impersonations" to ::noExtra),
            10 to ("Draws beautifully" to ::noExtra),
            11 to ("Paints beautifully" to ::noExtra),
            12 to ("Sings beautifully" to ::noExtra),
            13 to ("Drinks everyone under the table" to ::noExtra),
            14 to ("Expert carmpenter" to ::noExtra),
            15 to ("Expert cook" to ::noExtra),
            16 to ("Expert dart thrower and rock skipper" to ::noExtra),
            17 to ("Expert juggler" to ::noExtra),
            18 to ("Skilled actor and master of disguise" to ::noExtra),
            19 to ("Skilled dancer" to ::noExtra),
            20 to ("Knows thieves' cant" to ::noExtra)
    )

    @RevelationTable("mannerism", "mannerisms", recommendedRoll = "1d20", category = "Detailed NPCs")
    val MANNERISMS = mapOf<Int, String>(
            1 to "Prone to singing, whistling, or humming quietly",
            2 to "Speaks in rhyme or some other peculiar way",
            3 to "Particularly low or high voice",
            4 to "Slurs words, lisps, or stutters",
            5 to "Enunciates overly clearly",
            6 to "Speaks loudly",
            7 to "Whispers",
            8 to "Uses flowery speech or long words",
            9 to "Frequently uses the wrong word",
            10 to "Uses colourful oaths and exclamations",
            11 to "Makes constant jokes or puns",
            12 to "Prone to predictions of doom",
            13 to "Fidgets",
            14 to "Squints",
            15 to "Stares into the distance",
            16 to "Chews something",
            17 to "Paces",
            18 to "Taps fingers",
            19 to "Bites fingernails",
            20 to "Twirls hair or tugs beard"
    )

    @RevelationTable("interaction trait", "interaction traits", recommendedRoll = "1d12", category = "Detailed NPCs")
    val INTERACTION_TRAITS = mapOf(
            1 to "Argumentative",
            2 to "Arrogant",
            3 to "Blustering",
            4 to "Rude",
            5 to "Curious",
            6 to "Friendly",
            7 to "Honest",
            8 to "Hot tempered",
            9 to "Irritable",
            10 to "Ponderous",
            11 to "Quiet",
            12 to "Suspicious"
    )

    @RevelationTable("good ideal", "good ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val GOOD_IDEALS = mapOf(
            1 to "Beauty",
            2 to "Charity",
            3 to "Greater Good",
            4 to "Life",
            5 to "Respect",
            6 to "Self-sacrifice"
    )

    @RevelationTable("evil ideal", "evil ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val EVIL_IDEALS = mapOf(
            1 to "Domination",
            2 to "Greed",
            3 to "Might",
            4 to "Pain",
            5 to "Retrbution",
            6 to "Slaughter"
    )

    @RevelationTable("lawful ideal", "lawful ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val LAWFUL_IDEALS = mapOf(
            1 to "Community",
            2 to "Fairness",
            3 to "Honour",
            4 to "Logic",
            5 to "Responsibility",
            6 to "Tradition"
    )

    @RevelationTable("chaotic ideal", "chaotic ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val CHAOTIC_IDEALS = mapOf(
            1 to "Change",
            2 to "Creativity",
            3 to "Freedom",
            4 to "Independence",
            5 to "No Limits",
            6 to "Whimsy"
    )

    @RevelationTable("neutral ideal", "neutral ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val NEUTRAL_IDEALS = mapOf(
            1 to "Balance",
            2 to "Knowledge",
            3 to "Live and Let Live",
            4 to "Moderation",
            5 to "Neutrality",
            6 to "People"
    )

    @RevelationTable("other ideal", "other ideals", recommendedRoll = "1d6", category = "Detailed NPCs")
    val OTHER_IDEALS = mapOf(
            1 to "Aspiration",
            2 to "Discovery",
            3 to "Glory",
            4 to "Nation",
            5 to "Redemption",
            6 to "Self-knowledge"
    )

    @RevelationTable("bond", "bonds", recommendedRoll = "1d10", category = "Detailed NPCs")
    val NPC_BONDS = mapOf(
            1 to { "Dedicated to fulfilling a personal life goal" },
            2 to { "Protective of close family members" },
            3 to { "Protective of colleagues or compatriots" },
            4 to { "Loyal to a benefactor, patron, or employer" },
            5 to { "Captivated by a romantic interest" },
            6 to { "Drawn to a special place" },
            7 to { "Protective of a sentimental keepsake" },
            8 to { "Protective of a valuable possession" },
            9 to { "Out for revenge" },
            10 to { "$npcBond & $npcBond"}
    )

    @RevelationTable("flaw", "flaws", recommendedRoll = "1d12", category = "Detailed NPCs")
    val NPC_FLAWS = mapOf(
            1 to "Forbidden love or susceptability to romance",
            2 to "Enjoys decadent pleasures",
            3 to "Arrogance",
            4 to "Envies another creature's possessions or station",
            5 to "Overpowering greed",
            6 to "Prone to rage",
            7 to "Has a powerful enemy",
            8 to "Specific phobia",
            9 to "Shameful or scandalous history",
            10 to "Secret crime or misdeed",
            11 to "Possession of forbidden lore",
            12 to "Foolhardy bravery"
    )

    @RevelationTable("gender", recommendedRoll = "2d4-1", category = "- Misc -")
    val GENDER = mapRangeOf(
            1 .. 3 to "Male",
            4 .. 6 to "Female",
            7 .. 7 to "Other"
    )

    val appearance: NPCAppearanceTrait
        get() = APPEARANCE[DiceSet.d20()]!!()

    val highAbility: String
        get() = HIGH_ABILITY[DiceSet.d6()]!!.let { score -> "$score (+${DiceSet.d4() + 1})"}

    val lowAbility: String
        get() = LOW_ABILITY[DiceSet.d6()]!!.let { score -> "$score (-${DiceSet.d4() + 1})"}

    val npcTalent: String
        get() = TALENTS[DiceSet.d20()]!!.format()

    val npcMannerism: String
        get() = MANNERISMS[DiceSet.d20()]!!

    val npcInteractionTrait: String
        get() = INTERACTION_TRAITS[DiceSet.d12()]!!

    val npcFlaws: String
        get() = NPC_FLAWS[DiceSet.d12()]!!

    val npcBond: String
        get() = NPC_BONDS[DiceSet.d10()]!!()

    val npcGoodIdeal: String
        get() = GOOD_IDEALS[DiceSet.d6()]!!

    val npcEvilIdeal: String
        get() = EVIL_IDEALS[DiceSet.d6()]!!

    val npcLawfulIdeal: String
        get() = LAWFUL_IDEALS[DiceSet.d6()]!!

    val npcChaoticIdeal: String
        get() = LAWFUL_IDEALS[DiceSet.d6()]!!

    val npcNeutralIdeal: String
        get() = NEUTRAL_IDEALS[DiceSet.d6()]!!

    val npcOtherIdeal: String
        get() = OTHER_IDEALS[DiceSet.d6()]!!

    val gender: String
        get() = GENDER[DiceSet.d4[2] - 1]!!

    fun age(race: String): Int {
        val range = when (race.toUpperCase()) {
            "DWARF" -> 50 .. 350
            "ELF" -> 100 .. 750
            "HALFLING" -> 20 .. 150
            "HUMAN" -> 18 .. 70
            "DRAGONBORN" -> 15 .. 80
            "GNOME" -> 40 .. 425
            "HALF-ELF" -> 20 .. 250
            "HALF-ORC" -> 14 .. 65
            "TIEFLING" -> 18 .. 80
            else -> 18 .. 60
        }

        return ThreadLocalRandom.current().nextInt(range.first, range.last)
    }

    fun name(race: String, gender: String): String {
        when (race.toUpperCase()) {
            "DWARF" -> {
                return when (gender.toUpperCase()) {
                    "MALE" -> "${Names.DWARF_MALE[DiceSet.d100()]!!} ${Names.DWARF_CLAN_NAME[DiceSet.d100()]!!}"
                    "FEMALE" -> "${Names.DWARF_FEMALE[DiceSet.d100()]!!} ${Names.DWARF_CLAN_NAME[DiceSet.d100()]!!}"
                    else -> "${if (DiceSet.d6() % 2 == 0) Names.DWARF_MALE[DiceSet.d100()]!! else Names.DWARF_FEMALE[DiceSet.d100()]!!} ${Names.DWARF_CLAN_NAME[DiceSet.d100()]!!}"
                }
            }
        }
        return race
    }
}