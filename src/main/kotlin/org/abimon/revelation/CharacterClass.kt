package org.abimon.revelation

sealed class CharacterClass(val name: String) {
    object BARBARIAN: CharacterClass("Barbarian")
    object BARD: CharacterClass("Bard")
    object CLERIC: CharacterClass("Cleric")
    object DRUID: CharacterClass("Druid")
    object FIGHTER: CharacterClass("Fighter")
    object MONK: CharacterClass("Monk")
    object PALADIN: CharacterClass("Paladin")
    object RANGER: CharacterClass("Ranger")
    object ROGUE: CharacterClass("Rogue")
    object SORCERER: CharacterClass("Sorcerer")
    object WARLOCK: CharacterClass("Warlock")
    object WIZARD: CharacterClass("Wizard")
}