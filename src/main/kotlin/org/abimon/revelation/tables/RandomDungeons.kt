package org.abimon.revelation.tables

import org.abimon.revelation.DataMapObject
import org.abimon.revelation.RevelationTable

object RandomDungeons: DataMapObject("dungeons") {
	@RevelationTable("starting area", recommendedRoll = "1d10", category = "Random Dungeons | Starting Area")
	val startingArea = dataMap fromKey "starting_area"

	@RevelationTable("branching passage", recommendedRoll = "1d12", category = "Random Dungeons | Passages")
	val branchingPassage = dataMap fromKey "passage"

	@RevelationTable("chamber passage", recommendedRoll = "1d20", category = "Random Dungeons | Passages")
	val chamberPassage = dataMap fromKey "passage"

	@RevelationTable("passage width", recommendedRoll = "1d20", category = "Random Dungeons | Passages")
	val passageWidth = dataMap fromKey "passage_width"

	@RevelationTable("door type", recommendedRoll = "1d20", category = "Random Dungeons | Doors")
	val doorType = dataMap fromKey "door_type"

	@RevelationTable("beyond a door", recommendedRoll = "1d20", category = "Random Dungeons | Doors")
	val beyondADoor = dataMap fromKey "beyond_a_door"

    @RevelationTable("chamber", "chambers", recommendedRoll = "1d20", category = "Random Dungeons | Chambers")
    val chambers = dataMap fromKey "chambers"

    @RevelationTable("normal chamber exit", "normal chamber exits", recommendedRoll = "1d20", category = "Random Dungeons | Chambers")
    val normalChamberExits = dataMap fromKey "normal_chamber_exits"

    @RevelationTable("large chamber exit", "large chamber exits", recommendedRoll = "1d20", category = "Random Dungeons | Chambers")
    val largeChamberExits = dataMap fromKey "large_chamber_exits"

    @RevelationTable("exit location", recommendedRoll = "1d20", category = "Random Dungeons | Chambers")
    val exitLocation = dataMap fromKey "exit_location"

    @RevelationTable("exit type", recommendedRoll = "1d20", category = "Random Dungeons | Chambers")
    val exitType = dataMap fromKey "exit_type"

    @RevelationTable("stairs", recommendedRoll = "1d20", category = "Random Dungeons | Stairs")
    val stairs = dataMap fromKey "stairs"

    @RevelationTable("temple or shrine room", "temple or shrine rooms", "temple room", "temple rooms", "shrine room", "shrine rooms", recommendedRoll = "1d100", category = "Random Dungeons | Chamber Purpose")
    val templeOrShrineRooms = dataMap fromKey "temple_or_shrine_rooms"

    @RevelationTable("treasure vault room", "treasure vault rooms", recommendedRoll = "1d20", category = "Random Dungeons | Chamber Purpose")
    val treasureVaultRooms = dataMap fromKey "treasure_vault_rooms"

    @RevelationTable("general dungeon room", "general dungeon rooms", recommendedRoll = "1d100", category = "Random Dungeons | Chamber Purpose")
    val generalDungeonRooms = dataMap fromKey "general_dungeon_rooms"

    @RevelationTable("dungeon chamber contents", "chamber contents", recommendedRoll = "1d100", category = "Random Dungeons | Chamber Contents")
    val dungeonChamberContents = dataMap fromKey "dungeon_chamber_contents"

	@RevelationTable("monster motivation", "monster motivations", recommendedRoll = "1d20", category = "Random Dungeons | Monster Motivation")
    val monsterMotivation = dataMap fromKey "monster_motivation"

    @RevelationTable("dungeon hazard", "random dungeon hazard", "dungeon hazards", "random dungeon hazards", recommendedRoll = "1d20", category = "Random Dungeons | Random Dungeon Hazards")
    val dungeonHazard = dataMap fromKey "dungeon_hazard"

	@RevelationTable("obstacle", "obstacles", "dungeon obstacle", "dungeon obstacles", recommendedRoll = "1d20", category = "Random Dungeons | Random Obstacles")
	val dungeonObstacles = dataMap fromKey "dungeon_obstacle"

	@RevelationTable("dungeon trap trigger", "trap trigger", recommendedRoll = "1d6", category = "Random Dungeons | Random Traps")
	val dungeonTrapTrigger = dataMap fromKey "dungeon_trap_trigger"

	@RevelationTable("dungeon trap severity", "trap severity", recommendedRoll = "1d6", category = "Random Dungeons | Random Traps")
	val dungeonTrapSeverity = dataMap fromKey "dungeon_trap_severity"

	@RevelationTable("dungeon trap effect", "dungeon trap effects", "trap effect", "trap effects", recommendedRoll = "1d100", category = "Random Dungeons | Random Traps")
	val dungeonTrapEffects = dataMap fromKey "dungeon_trap_effects"

	@RevelationTable("trick object", "trick objects", recommendedRoll = "1d20", category = "Random Dungeons | Random Tricks")
	val trickObjects = dataMap fromKey "trick_object"

	@RevelationTable("tricks", "trick", "trick effect", "trick effects", recommendedRoll = "1d100", category = "Random Dungeons | Random Tricks")
	val tricks = dataMap fromKey "tricks"

	@RevelationTable("noise", "noises", recommendedRoll = "1d100", category = "Random Dungeons | Dungeon Dressing")
	val noises = dataMap fromKey "noises"

	@RevelationTable("air", "yes there is air", recommendedRoll = "1d100", category = "Random Dungeons | Dungeon Dressing")
	val air = dataMap fromKey "air"

	@RevelationTable("odor", "odour", "odors", "odours", recommendedRoll = "1d100", category = "Random Dungeons | Dungeon Dressing")
	val odors = dataMap fromKey "odors"

	@RevelationTable("general feature", "general features", recommendedRoll = "1d100", category = "Random Dungeons | Dungeon Dressing")
	val generalFeatures = dataMap fromKey "general_features"

	@RevelationTable("general furnishing", "general furnishings", recommendedRoll = "1d100", category = "Random Dungeons | Dungeon Dressing")
	val generalFurnishings = dataMap fromKey "general_furnishings"
}
