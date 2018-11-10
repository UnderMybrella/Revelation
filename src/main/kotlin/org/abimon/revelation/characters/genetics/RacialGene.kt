package org.abimon.revelation.characters.genetics

import java.util.*

abstract class RacialGene(val name: String) : Gene(name, Gene.NO_DOMINANCE) {
    companion object {
        val RACE_DWARF = "[Race] Dwarf"
        val RACE_ELF = "[Race] Elf"
        val RACE_HUMAN = "[Race] Human"
        val RACE_HALFLING = "[Race] Halfling"
        val RACE_DRAGONBORN = "[Race] Dragonborn"
        val RACE_GNOME = "[Race] Gnome"

        val ALL_DWARVES by lazy { mutableListOf(HILL_DWARF, MOUNTAIN_DWARF) }
        val ALL_ELVES by lazy { mutableListOf(HIGH_ELF, WOOD_ELF, DROW) }
        val ALL_HALFLINGS by lazy { mutableListOf(LIGHTFOOT_HALFLING, STOUT_HALFLING) }
        val ALL_HUMANS by lazy { mutableListOf(CALISHITE_HUMAN, CHONDATHAN_HUMAN, DAMARAN_HUMAN, ILLUSKAN_HUMAN, MULAN_HUMAN, RASHEMI_HUMAN, SHOU_HUMAN, TETHYRIAN_HUMAN, TURAMI_HUMAN) }
        val ALL_DRAGONBORNS by lazy { mutableListOf(BLACK_DRAGONBORN, BLUE_DRAGONBORN, BRASS_DRAGONBORN, BRONZE_DRAGONBORN, COPPER_DRAGONBORN, GOLD_DRAGONBORN, GREEN_DRAGONBORN, RED_DRAGONBORN, SILVER_DRAGONBORN, WHITE_DRAGONBORN) }
        val ALL_GNOMES by lazy { mutableListOf(FOREST_GNOME, ROCK_GNOME) }

        val ALL_RACES: Array<RacialGene>
            get() = ArrayList<RacialGene>().apply {
                addAll(ALL_DWARVES)
                addAll(ALL_ELVES)
                addAll(ALL_HALFLINGS)
                addAll(ALL_HUMANS)
                addAll(ALL_DRAGONBORNS)
                addAll(ALL_GNOMES)
            }.toTypedArray()
    }

    override val uid: String = UUID.randomUUID().toString()

    abstract class DWARF : RacialGene(RACE_DWARF)
    object HILL_DWARF : DWARF()
    object MOUNTAIN_DWARF : DWARF()

    abstract class ELF : RacialGene(RACE_ELF)
    object HIGH_ELF : ELF()
    object WOOD_ELF : ELF()
    object DROW : ELF()

    abstract class HALFLING : RacialGene(RACE_HALFLING)
    object LIGHTFOOT_HALFLING : HALFLING()
    object STOUT_HALFLING : HALFLING()

    abstract class HUMAN : RacialGene(RACE_HUMAN)
    object CALISHITE_HUMAN : HUMAN()
    object CHONDATHAN_HUMAN : HUMAN()
    object DAMARAN_HUMAN : HUMAN()
    object ILLUSKAN_HUMAN : HUMAN()
    object MULAN_HUMAN : HUMAN()
    object RASHEMI_HUMAN : HUMAN()
    object SHOU_HUMAN : HUMAN()
    object TETHYRIAN_HUMAN : HUMAN()
    object TURAMI_HUMAN : HUMAN()

    abstract class DRAGONBORN : RacialGene(RACE_DRAGONBORN)
    object BLACK_DRAGONBORN : DRAGONBORN()
    object BLUE_DRAGONBORN : DRAGONBORN()
    object BRASS_DRAGONBORN : DRAGONBORN()
    object BRONZE_DRAGONBORN : DRAGONBORN()
    object COPPER_DRAGONBORN : DRAGONBORN()
    object GOLD_DRAGONBORN : DRAGONBORN()
    object GREEN_DRAGONBORN : DRAGONBORN()
    object RED_DRAGONBORN : DRAGONBORN()
    object SILVER_DRAGONBORN : DRAGONBORN()
    object WHITE_DRAGONBORN : DRAGONBORN()

    abstract class GNOME : RacialGene(RACE_GNOME)
    object FOREST_GNOME : GNOME()
    object ROCK_GNOME : GNOME()
}