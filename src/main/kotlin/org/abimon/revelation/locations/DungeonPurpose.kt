package org.abimon.revelation.locations

sealed class DungeonPurpose(val text: String) {

    object DEATH_TRAP: DungeonPurpose("Death Trap")
    object LAIR: DungeonPurpose("Lair")
    object MAZE: DungeonPurpose("Maze")
    object MINE: DungeonPurpose("Mine")
    object PLANAR_GATE: DungeonPurpose("Planar Gate")
    object STRONGHOLD: DungeonPurpose("Stronghold")
    object TEMPLE_OR_SHRINE: DungeonPurpose("Temple or Shrine")
    object TOMB: DungeonPurpose("Tomb")
    object TREASURE_VAULT: DungeonPurpose("Treasure Vault")

    override fun toString(): String = text
}