package org.abimon.revelation.tables

import org.abimon.revelation.DataMapObject
import org.abimon.revelation.RevelationTable

object DMWorkshop: DataMapObject("dm_workshop") {
    @RevelationTable("short term madness", "short-term madness", recommendedRoll = "1d100", category = "DM's Workshop")
    val SHORT_TERM_MADNESS = dataMap fromKey "short-term_madness"

    @RevelationTable("long term madness", "long-term madness", recommendedRoll = "1d100", category = "DM's Workshop")
    val LONG_TERM_MADNESS = dataMap fromKey "long-term_madness"

    @RevelationTable("indefinite madness", recommendedRoll = "1d100", category = "DM's Workshop")
    val INDEFINITE_MADNESS = dataMap fromKey "indefinite_madness"

    @RevelationTable("lingering injuries", recommendedRoll = "1d20", category = "DM's Workshop")
    val LINGERING_INJURIES = dataMap fromKey "lingering_injuries"

    @RevelationTable("system shock", recommendedRoll = "1d10", category = "DM's Workshop")
    val SYSTEM_SHOCK = dataMap fromKey "system_shock"
}