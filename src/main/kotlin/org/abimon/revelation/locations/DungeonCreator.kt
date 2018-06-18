package org.abimon.revelation.locations

import org.abimon.revelation.CharacterClass
import org.abimon.revelation.EnumAlignment

sealed class DungeonCreator(val text: String) {
    object BEHOLDER: DungeonCreator("Beholder")
    class CULT: DungeonCreator("Cult") {
        val cult: Cult = AdventureEnvironments.cultOrReligiousGroup

        override fun toString(): String = "Cult or religious group ($cult)"
    }
    object DWARVES: DungeonCreator("Dwarves")
    object ELVES: DungeonCreator("Elves (including drow)")
    object GIANTS: DungeonCreator("Giants")
    object HOBGOBLINGS: DungeonCreator("Hobgoblins")
    class HUMANS: DungeonCreator("Humans") {
        val alignment: EnumAlignment = AdventureEnvironments.npcAlignment
        val npcClass: CharacterClass = AdventureEnvironments.npcClass

        override fun toString(): String = "Humans ($alignment $npcClass)"
    }
    object KUO_TOA: DungeonCreator("Kuo-toa")
    object LICH: DungeonCreator("Lich")
    object MIND_FLAYERS: DungeonCreator("Mind flayers")
    object YUAN_TI: DungeonCreator("Yuan-ti")
    object NONE: DungeonCreator("No creator (natural caverns")

    override fun toString(): String = text

    sealed class Cult(val text: String) {
        object DEMON: Cult("Demon-worshiping cult")
        object DEVIL: Cult("Devil-worshiping cult")
        object AIR: Cult("Elemental Air cult")
        object EARTH: Cult("Elemental Earth cult")
        object FIRE: Cult("Elemental Fire cult")
        object WATER: Cult("Elemental Water cult")
        object EVIL: Cult("Worshipers of an evil deity")
        object GOOD: Cult("Worshipers of a good deity")
        object NEUTRAL: Cult("Worshipers of a neutral deity")
    }
}