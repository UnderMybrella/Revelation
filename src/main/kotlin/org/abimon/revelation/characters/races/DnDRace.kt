package org.abimon.revelation.characters.races

interface DnDRace {
    val maleNames: Map<Int, String>
    val femaleNames: Map<Int, String>

    val maleChildNames: Map<Int, String>
        get() = maleNames
    val femaleChildNames: Map<Int, String>
        get() = femaleNames

    val surnames: Map<Int, String>

    val ageOfMaturity: Int
    val averageLifespan: Int

    val adultAgeRange: IntRange
        get() = ageOfMaturity .. averageLifespan

    /** In inches */
    val heightRange: IntRange
}