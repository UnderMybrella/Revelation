package org.abimon.revelation.characters

import org.abimon.revelation.*
import kotlin.math.ceil
import kotlin.math.roundToInt

object XGtECharacterBuilding {
    val PARENTS = mapRangeOf(
            1 .. 95 to "You know who your prents are or were.",
            96 .. 100 to "You do not know who your parents were"
    )

    val HALF_ELF_PARENTS = mapRangeOf(
            1 .. 5 to "One parent was an elf and the other was a human.",
            6 .. 6 to "One parent was an elf and the other was a half-elf",
            7 .. 7 to "One parent was a human and the other was a half-elf",
            8 .. 8 to "Both parents were half-elves"
    )

    val HALF_ORC_PARENTS = mapRangeOf(
            1 .. 3 to "One parent was an orc and the other was a human.",
            4 .. 5 to "One parent was an orc and the other was a half-orc.",
            6 .. 7 to "One parent was a human and the other was a half-orc.",
            8 .. 8 to "Both parents were half-orcs."
    )

    val TIEFLING_PARENTS = mapRangeOf(
            1 .. 4 to "Both parents were humans, their infernal heritage dormant until you came along.",
            5 .. 6 to "One parent was a tiefling and the other was a human.",
            7 .. 7 to "One parent was a tiefling and the other was a human.",
            8 .. 8 to "One parent was a human and the other was a devil."
    )

    val BIRTHPLACE = mapRangeOf(
            1 .. 50 to ("Home" to ::noExtra),
            51 .. 55 to ("Home of a family friend" to ::noExtra),
            56 .. 63 to ("Home of a healer or midwife" to ::noExtra),
            64 .. 65 to ("Carriage, cart, or wagon" to ::noExtra),
            66 .. 68 to ("Barn, shed, or other outbuilding" to ::noExtra),
            69 .. 70 to ("Cave" to ::noExtra),
            71 .. 72 to ("Field" to ::noExtra),
            73 .. 74 to ("Forest" to ::noExtra),
            75 .. 77 to ("Temple" to ::noExtra),
            78 .. 78 to ("Battlefield" to ::noExtra),
            79 .. 80 to ("Alley or street" to ::noExtra),
            81 .. 82 to ("Brothel, tavern, or inn" to ::noExtra),
            83 .. 84 to ("Castle, keep, tower, or palace" to ::noExtra),
            85 .. 85 to ("Sewer or rubbish heap" to ::noExtra),
            86 .. 88 to ("Among people of a different race" to ::race),
            89 .. 91 to ("On board a boat or a ship" to ::noExtra),
            92 .. 93 to ("In a prison or in the headquarters of a secret organisation" to ::noExtra),
            94 .. 95 to ("In a sage's laboratory" to ::noExtra),
            96 .. 96 to ("In the Feywild" to ::noExtra),
            97 .. 97 to ("In the Shadowfell" to ::noExtra),
            98 .. 98 to ("On the Astral Plane or the Ethereal Plane" to { if (DiceSet.d6() % 2 == 0) "Astral Plane" else "Ethereal Plane" }),
            99 .. 99 to ("On an Inner Plane of your choice" to ::innerPlane),
            100 .. 100 to ("On an Outer Plane of your choice" to ::outerPlane)
    )

    val NUMBER_OF_SIBLINGS = mapRangeOf(
            -2 .. 2 to { 0 },
            3 .. 4 to { ceil(DiceSet.d6().toFloat() / 2f).roundToInt() },
            5 .. 6 to { DiceSet.d4() + 1 },
            7 .. 8 to { DiceSet.d6() + 2 },
            9 .. 10 to { DiceSet.d8() + 3 }
    )

    val BIRTH_ORDER = mapRangeOf(
            2 .. 2 to "Twin, triplet, or quadruplet",
            3 .. 7 to "Older",
            8 .. 12 to "Younger"
    )

    val ABSENT_PARENT = mapOf<Int, DetailPair<String>>(
            1 to ("Your parent died" to this::causeOfDeath),
            2 to ("Your parent was imprisoned, enslaved, or otherwise taken away" to ::noExtra),
            3 to ("Your parent abandoned you" to ::noExtra),
            4 to ("Your parent disappeared to an unknown fate" to ::noExtra)
    )

    val FAMILY = mapRangeOf<DetailPair<String>>(
            1 .. 1 to ("None" to this::absentParents),
            2 .. 2 to ("Institution, such as an asylum" to this::absentParents),
            3 .. 3 to ("Temple" to this::absentParents),
            4 .. 5 to ("Orphanage" to this::absentParents),
            6 .. 7 to ("Guardian" to this::absentParents),
            8 .. 15 to ("Paternal or maternal aunt, uncle, or both; or extended family such as a tribe or clan" to this::absentParents),
            16 .. 25 to ("Paternal or maternal grandparent(s)" to this::absentParents),
            26 .. 35 to ("Adoptive family (same or different race)" to this::absentParents),
            36 .. 55 to ("Single father or stepfather" to this::absentMother),
            56 .. 75 to ("Single mother or stepmother" to this::absentFather),
            76 .. 100 to ("Mother and father" to ::noExtra)
    )

    val FAMILY_LIFESTYLE = mapRangeOf(
            3 .. 3 to "Wretched (-40)", //-40
            4 .. 5 to "Squalid (-20)", //-20
            6 .. 8 to "Poor (-10)", //-10
            9 .. 12 to "Modest (±0)", //0
            13 .. 15 to "Comfortable (+10)", //+10
            16 .. 17 to "Wealthy (+20)", //+20
            18 .. 18 to "Aristocratic (+40)" //+40
    )

    val CHILDHOOD_HOME = mapRangeOf(
            -40 .. 0 to "On the streets",
            1 .. 20 to "Rundown shack",
            21 .. 30 to "No permanent residence; you moved around a lot",
            31 .. 40 to "Encampment or village in the wilderness",
            41 .. 50 to "Apartment in a rundown neighborhood",
            51 .. 70 to "Small house",
            71 .. 90 to "Large house",
            91 .. 110 to "Mansion",
            111 .. 140 to "Palace or castle"
    )

    val CHILDHOOD_MEMORIES = mapRangeOf(
            -5 .. 3 to "I am still haunted by my childhood, when I was treated badly by my peers",
            4 .. 5 to "I spent most of my childhood alone, with no close friends",
            6 .. 8 to "Others saw me as being different or strange, and so I had few companions",
            9 .. 12 to "I had a few close friends and lived an ordinary childhood",
            13 .. 15 to "I had several friends, and my childhood was generally a happy one",
            16 .. 17 to "I always found it easy to make friends, and I loved being around people",
            18 .. 30 to "Everyone knew who I was, and I had friends everywhere I went"
    )

    /**
     * SECONDARY TABLES
     * These tables add detail to many of the results on the Life Events table. The tables are in alpahabetical order.
     * - Xanathar's Guide to Everything, page 70
     */

    /**
     * SUPPLIMENTAL TABLES
     */

    val ALIGNMENT = mapRangeOf(
            3 .. 3 to { if (DiceSet.d2() == 1) "Chaotic Evil" else "Chaotic Neutral" },
            4 .. 5 to { "Lawful Evil" },
            6 .. 8 to { "Neutral Evil" },
            9 .. 12 to { "Neutral" },
            13 .. 15 to { "Neutral Good" },
            16 .. 17 to { if (DiceSet.d2() == 1) "Lawful Good" else "Lawful Neutral" },
            18 .. 18 to { if (DiceSet.d2() == 1) "Chaotic Good" else "Chaotic Neutral" }
    )

    val CAUSE_OF_DEATH = mapRangeOf(
            1 .. 1 to "Unknown",
            2 .. 2 to "Murdered",
            3 .. 3 to "Killed in battle",
            4 .. 4 to "Accident related to class or occupation",
            5 .. 5 to "Accident unrelated to class or occupation",
            6 .. 7 to "Natural causes, such as disease or old age",
            8 .. 8 to "Apparent suicide",
            9 .. 9 to "Torn apart by an animal or a natural disaster",
            10 .. 10 to "Consumed by a monster",
            11 .. 11 to "Executed for a crime or tortured to death",
            12 .. 12 to "Bizarre event, such as being hit by a meteorite, struck down by an angry god, or killed by a hatching slaad egg"
    )

    val CLASS = mapRangeOf(
            1 .. 7 to "Barbarian",
            8 .. 14 to "Bard",
            15 .. 29 to "Cleric",
            30 .. 36 to "Druid",
            37 .. 52 to "Fighter",
            53 .. 58 to "Monk",
            59 .. 64 to "Paladin",
            65 .. 70 to "Ranger",
            71 .. 84 to "Rogue",
            85 .. 89 to "Sorcerer",
            90 .. 94 to "Warlock",
            95 .. 100 to "Wizard"
    )

    val OCCUPATION = mapRangeOf(
            1 .. 5 to "Academic",
            6 .. 10 to "Adventurer",
            11 .. 11 to "Aristocrat",
            12 .. 26 to "Artisan or Guild Member",
            27 .. 31 to "Criminal",
            32 .. 36 to "Entertainer",
            37 .. 38 to "Exile, hermit, or refugee",
            39 .. 43 to "Explorer or wanderer",
            44 .. 55 to "Farmer or herder",
            56 .. 60 to "Hunter or trapper",
            61 .. 75 to "Laborer",
            76 .. 80 to "Merchant",
            81 .. 85 to "Politician or bureaucrat",
            86 .. 90 to "Priest",
            91 .. 95 to "Sailor",
            96 .. 100 to "Soldier"
    )

    val ELF_SUBRACES = mapRangeOf(
            3 .. 3 to "Drow",
            4 .. 8 to "Wood Elf",
            9 .. 12 to "High Elf",
            13 .. 15 to "Sea Elf",
            16 .. 17 to "Shadar-Kai",
            18 .. 18 to "Drow"
    )

    val DWARF_SUBRACES = mapRangeOf(
            3 .. 4 to "Druegar",
            5 .. 8 to "Mountain Dwarf",
            9 .. 12 to "Hill Dwarf"
    )

    val HALFLING_SUBRACES = mapRangeOf(
            1 .. 2 to "Lightfoot",
            3 .. 3 to "Stout"
    )

    val GNOME_SUBRACES = mapRangeOf(
            1 .. 3 to "Rock Gnome",
            4 .. 4 to "Forest Gnome"
    )

    val DRAGONBORN_SUBRACES = mapOf(
            1 to "Black",
            2 to "Blue",
            3 to "Brass",
            4 to "Bronze",
            5 to "Copper",
            6 to "Gold",
            7 to "Green",
            8 to "Red",
            9 to "Silver",
            10 to "White"
    )

    val RACE = mapRangeOf<DetailPair<String>>(
            1 .. 40 to ("Human" to ::noExtra),
            41 .. 50 to ("Dwarf" to this::dwarfSubrace),
            51 .. 60 to ("Elf" to this::elfSubrace),
            61 .. 70 to ("Halfling" to this::halflingSubrace),
            71 .. 75 to ("Dragonborn" to this::dragonbornSubrace),
            76 .. 80 to ("Gnome" to this::gnomeSubrace),
            81 .. 85 to ("Half-Elf" to this::halfElfParents),
            86 .. 90 to ("Half-Orc" to this::halfOrcParents),
            91 .. 95 to ("Tiefling" to this::tieflingParents),
            96 .. 97 to ("Aasimar" to ::noExtra),
            98 .. 98 to ("Kenku" to ::noExtra),
            99 .. 99 to ("Tabaxi" to ::noExtra),
            100 .. 100 to ("DM's Choice" to ::noExtra)
    )

    val RELATIONSHIP = mapRangeOf(
            3 .. 4 to "Hostile",
            5 .. 10 to "Friendly",
            11 .. 12 to "Indifferent"
    )

    val STATUS = mapRangeOf<DetailPair<String>>(
            3 .. 3 to ("Dead" to this::causeOfDeath),
            4 .. 5 to ("Missing or Unknown" to ::noExtra),
            6 .. 8 to ("Alive, but doing poorly due to injury, financial trouble, or relationship difficulties" to ::noExtra),
            9 .. 12 to ("Alive and well" to ::noExtra),
            13 .. 15 to ("Alive and quite successful" to ::noExtra),
            16 .. 17 to ("Alive and infamous" to ::noExtra),
            18 .. 18 to ("Alive and famous" to ::noExtra)
    )

    val parents: String
        get() = PARENTS[DiceSet.d100()]!!

    val halfElfParents: String
        get() = HALF_ELF_PARENTS[DiceSet.d8()]!!

    val halfOrcParents: String
        get() = HALF_ORC_PARENTS[DiceSet.d8()]!!

    val tieflingParents: String
        get() = TIEFLING_PARENTS[DiceSet.d8()]!!

    val birthplace: String
        get() = BIRTHPLACE[DiceSet.d100()]!!.format()

    val family: String
        get() = FAMILY[DiceSet.d100()]!!.format()

    val familyLifestyle: String
        get() = FAMILY_LIFESTYLE[DiceSet.d6[3]]!!.format()

    fun childhoodHome(lifestyle: Int): String = CHILDHOOD_HOME[DiceSet.d100() + lifestyle]!!
    fun childhoodMemories(charismaMod: Int): String = CHILDHOOD_MEMORIES[DiceSet.d6[3] + charismaMod]!!

    val averageChildhoodHome: String
        get() = childhoodHome(0)

    val averageChildhoodMemories: String
        get() = childhoodMemories(0)

    val numberOfSiblings: Int
        get() = NUMBER_OF_SIBLINGS[DiceSet.d10()]!!()

    val numberOfSiblingsLongevity: Int
        get() = NUMBER_OF_SIBLINGS[DiceSet.d10() - 2]!!()

    val birthOrder: String
        get() = BIRTH_ORDER[DiceSet.d6[2]]!!

    val absentParent: String
        get() = ABSENT_PARENT[DiceSet.d4()]!!.format()

    val absentMother: String
        get() = ABSENT_PARENT[DiceSet.d4()]!!.format().replace(" parent", " mother")

    val absentFather: String
        get() = ABSENT_PARENT[DiceSet.d4()]!!.format().replace(" parent", " father")

    val absentParents: String
        get() = "$absentMother; $absentFather"

    val alignment: String
        get() = ALIGNMENT[DiceSet.d6[3]]!!()

    val causeOfDeath: String
        get() = CAUSE_OF_DEATH[DiceSet.d12()]!!
    
    val adventurerClass: String
        get() = CLASS[DiceSet.d100()]!!

    val occupation: String
        get() = OCCUPATION[DiceSet.d100()]!!

    val race: String
        get() = RACE[DiceSet.d100()]!!.format()

    val relationship: String
        get() = RELATIONSHIP[DiceSet.d4[3]]!!

    val status: String
        get() = STATUS[DiceSet.d6[3]]!!.format()

    val elfSubrace: String
        get() = ELF_SUBRACES[DiceSet.d6[3]]!!

    val dwarfSubrace: String
        get() = DWARF_SUBRACES[DiceSet.d4[3]]!!

    val halflingSubrace: String
        get() = HALFLING_SUBRACES[DiceSet.d3()]!!

    val gnomeSubrace: String
        get() = GNOME_SUBRACES[DiceSet.d4()]!!

    val dragonbornSubrace: String
        get() = DRAGONBORN_SUBRACES[DiceSet.d10()]!!
/*
    val NAMES = mapOf(
            "MALE|DWARF" to Names.DWARF_MALE,
            "FEMALE|DWARF" to Names.DWARF_FEMALE
    )

    fun name(gender: String, race: String): String {
        val list = when ("$gender|$race".toUpperCase())
        return "No list for $gender $race's"
    }*/
}