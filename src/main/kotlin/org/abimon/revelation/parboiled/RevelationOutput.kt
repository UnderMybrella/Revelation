package org.abimon.revelation.parboiled

import org.abimon.revelation.RevelationOutputTemplate

class RevelationOutput {
    companion object {
        val RAW: RevelationOutputTemplate = { data ->
            buildString {
                if (data.raw.isNotBlank()) {
                    append(data.raw)
                }

                if (data.raw.isNotBlank() && data.values.isNotEmpty()) {
                    appendln()
                    appendln("------------")
                    appendln()
                }

                data.values.forEach { (key, value) ->
                    appendln("${key.capitalize()}: ${value.toString().capitalize()}")
                }
            }
        }

        val NPC: RevelationOutputTemplate = { data ->
            buildString {
                if (data.raw.isNotBlank()) {
                    appendln(data.raw)

                    appendln()
                    appendln("------------")
                    appendln()
                }

                val dataMap = data.values.groupBy { (key) -> key }
                        .mapValues { (_, list) -> list.map(Pair<*, *>::second) }

                appendln("Name:                     ${"name" from dataMap}")
                appendln("Race:                     ${"race" from dataMap}")
                appendln("Gender:                   ${"gender" from dataMap}")
                appendOp("Subrace:                  ${dataMap["elf subrace", "dwarf subrace", "halfling subrace", "gnome subrace", "dragonborn subrace"]}")
                appendln("Age:                      ${"age" from dataMap}")
                appendln("Alignment:                ${"alignment" from dataMap}")
                appendln("Married?                  ${"married" from dataMap}")
                appendln("Status:                   ${"status" from dataMap}")
                appendOp("Cause of Death:           ${"cauase of death" from dataMap}")
                appendOp("Occupation:               ${"occupation" from dataMap}")
                appendOp("Adventurer Class:         ${"adventurer class" from dataMap}")
                appendln()
                appendln("Appearance(s):            ${"appearance" from dataMap}")
                appendln("Talent(s):                ${"talent" from dataMap}")
                appendln("Mannerism(s):             ${"mannerism" from dataMap}")
                appendln("Interaction Trait(s):     ${"interaction trait" from dataMap}")
                appendln("Flaw(s):                  ${"flaw" from dataMap}")
                appendln("Bond(s):                  ${"bond" from dataMap}")

                appendln()

                appendln("Ideal(s):                 ${dataMap["good ideal", "evil ideal", "lawful ideal", "chaotic ideal", "neutral ideal", "other ideal"]}")

                appendln()

                appendOp("Parent:                   ${"parent" from dataMap}")
                appendOp("Half-Elf Parent:          ${"half-elf parent" from dataMap}")
                appendOp("Half-Orc Parent:          ${"half-orc parent" from dataMap}")
                appendOp("Tiefling Parent:          ${"tiefling parent" from dataMap}")
                appendOp("Birthplace:               ${"birthplace" from dataMap}")
                appendOp("Family:                   ${"family" from dataMap}")
                appendOp("Family Lifestyle:         ${"family lifestyle" from dataMap}")
                appendOp("Childhood Home:           ${"average childhood home" from dataMap}")
                appendOp("Childhood Home:           ${"childhood home" from dataMap}")
                appendOp("Childhood Memories:       ${"average childhood memories" from dataMap}")
                appendOp("Childhood Memories:       ${"childhood memories" from dataMap}")
                if ("number of siblings" in dataMap) {
                    appendln("Number of Siblings:       ${"number of siblings" from dataMap}")
                    appendOp("Number of Elf Siblings:   ${"number of elf siblings" from dataMap}")
                    appendOp("Number of Dwarf Siblings: ${"number of dwarf siblings" from dataMap}")
                } else if ("number of elf siblings" in dataMap) {
                    appendln("Number of Siblings:       ${"number of elf siblings" from dataMap}")
                    appendOp("Number of Dwarf Siblings: ${"number of dwarf siblings" from dataMap}")
                } else if ("number of dwarf siblings" in dataMap) {
                    appendln("Number of Siblings:       ${"number of dwarf siblings" from dataMap}")
                }

                appendOp("Birth Order:              ${"birth order" from dataMap}")
                appendOp("Absent Parent:            ${"absent parent" from dataMap}")
                appendOp("Absent Parents:           ${"absent parents" from dataMap}")

                appendln()

                appendOp("Stats:                    ${"stats" from dataMap}")

                if (("high stat" in dataMap || "low stat" in dataMap) || "stats" !in dataMap) {
                    appendln("High Stat(s):             ${"high stat" from dataMap}")
                    appendln("Low Stat(s):              ${"low stat" from dataMap}")
                }
            }
        }

        infix fun String.from(map: Map<String, List<Any?>>): String = map[this]?.joinToString() ?: "Unknown"
        operator fun Map<String, List<Any?>>.get(vararg keys: String): String = keys.mapNotNull(this::get).flatten().distinct().takeIf(List<*>::isNotEmpty)?.joinToString() ?: "Unknown"
        public inline fun StringBuilder.appendOp(value: String?): StringBuilder = if (value?.endsWith("Unknown") != false) this else append(value).appendln()
    }

    val values: MutableList<Pair<String, Any?>> = ArrayList()
    val raw: StringBuilder = StringBuilder()
    var format: RevelationOutputTemplate = RAW

    fun format(): String = format(this).trim()
    fun format(outputFormat: RevelationOutputTemplate = RAW): String = outputFormat(this).trim()
    fun add(value: Pair<String, Any?>) {
        values.add(value)
    }
}