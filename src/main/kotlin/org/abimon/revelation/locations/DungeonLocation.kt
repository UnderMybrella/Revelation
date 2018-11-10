package org.abimon.revelation.locations

sealed class DungeonLocation(val text: String) {
    override fun toString(): String = text

    object BUILDING_IN_A_CITY: DungeonLocation("A building in a city")
    object CATACOMBS_OR_SEWERS_BENEATH_CITY: DungeonLocation("Catacombs or sewers beneath a city")
    object BENEATH_FARMHOUSE: DungeonLocation("Beneath a farmhouse")
    object BENEATH_GRAVEYARD: DungeonLocation("Beneath a graveyard")
    object BENEATH_RUINED_CASTLE: DungeonLocation("Beneath a ruined castle")
    object BENEATH_RUINED_CITY: DungeonLocation("Beneath a ruined city")
    object BENEATH_TEMPLE: DungeonLocation("Beneath a ruined temple")
    object IN_CHASM: DungeonLocation("In a chasm")
    object CLIFF_FACE: DungeonLocation("In a cliff face")
    object DESERT: DungeonLocation("In a desert")
    object FOREST: DungeonLocation("In a forest")
    object GLACIER: DungeonLocation("In a glacier")
    object GORGE: DungeonLocation("In a gorge")
    object JUNGLE: DungeonLocation("In a jungle")
    object MOUNTAIN_PASS: DungeonLocation("In a mountain pass")
    object SWAMP: DungeonLocation("In a swamp")
    object MESA: DungeonLocation("Beneath or on top of a mesa")
    object SEA_CAVES: DungeonLocation("In sea caves")
    object SEVERAL_MESAS: DungeonLocation("In several connected mesas")
    object MOUNTAIN_PEAK: DungeonLocation("On a mountain peak")
    object PROMONTORY: DungeonLocation("Promontory")
    object ISLAND: DungeonLocation("On an island")
    object UNDERWATER: DungeonLocation("Underwater")

    class EXOTIC (val exoticLocation: ExoticLocation) : DungeonLocation("Exotic") {
        constructor(): this(AdventureEnvironments.exoticLocation)

        override fun toString(): String = "Exotic Location ($exoticLocation)"
    }
}