package org.abimon.revelation.iona

import org.abimon.revelation.DataMapObject
import org.abimon.revelation.DiceSet
import org.abimon.revelation.RevelationTable

object TempleOfForgottenRealms: DataMapObject("temple_of_forgotten_realms") {
    @RevelationTable("buff", "buffs", recommendedRoll = "1d10", category = "Temple of Forgotten Realms")
    val BUFFS = dataMap fromKey "buffs"
    @RevelationTable("buff", "buffs", recommendedRoll = "1d10", category = "Temple of Forgotten Realms")
    val DEBUFFS = dataMap fromKey "debuffs"
    @RevelationTable("buff", "buffs", recommendedRoll = "1d20", category = "Temple of Forgotten Realms")
    val ENVIRONMENTAL = dataMap fromKey "environmental"

    val buff: String
        get() = BUFFS[DiceSet.d10()]?.parseScript() ?: "Unknown"

    val debuff: String
        get() = DEBUFFS[DiceSet.d10()]?.parseScript() ?: "Unknown"

    val environmental: String
        get() = ENVIRONMENTAL[DiceSet.d20()]?.parseScript() ?: "Unknown"
}