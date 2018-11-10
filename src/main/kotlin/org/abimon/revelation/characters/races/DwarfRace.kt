package org.abimon.revelation.characters.races

import org.abimon.revelation.characters.Names

object DwarfRace : DnDRace {
    override val maleNames: Map<Int, String> = Names.DWARF_MALE
    override val femaleNames: Map<Int, String> = Names.DWARF_FEMALE
    override val surnames: Map<Int, String> = Names.DWARF_CLAN_NAME

    override val ageOfMaturity: Int = 50
    override val averageLifespan: Int = 350

    override val heightRange: IntRange = 46 .. 64
}