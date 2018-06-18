package org.abimon.revelation.locations

import org.abimon.revelation.CharacterClass
import org.abimon.revelation.DiceSet
import org.abimon.revelation.EnumAlignment
import org.abimon.revelation.mapRangeOf

object AdventureEnvironments {
    val DUNGEON_LOCATION = mapRangeOf(
            1 .. 4 to { DungeonLocation.BUILDING_IN_A_CITY },
            5 .. 8 to { DungeonLocation.CATACOMBS_OR_SEWERS_BENEATH_CITY },
            9 .. 12 to { DungeonLocation.BENEATH_FARMHOUSE },
            13 .. 16 to { DungeonLocation.BENEATH_GRAVEYARD },
            17 .. 22 to { DungeonLocation.BENEATH_RUINED_CASTLE },
            23 .. 26 to { DungeonLocation.BENEATH_RUINED_CITY },
            27 .. 30 to { DungeonLocation.BENEATH_TEMPLE },
            31 .. 34 to { DungeonLocation.IN_CHASM },
            35 .. 38 to { DungeonLocation.CLIFF_FACE },
            39 .. 42 to { DungeonLocation.DESERT },
            43 .. 46 to { DungeonLocation.FOREST },
            47 .. 50 to { DungeonLocation.GLACIER },
            51 .. 54 to { DungeonLocation.GORGE },
            55 .. 58 to { DungeonLocation.JUNGLE },
            59 .. 62 to { DungeonLocation.MOUNTAIN_PASS },
            63 .. 66 to { DungeonLocation.SWAMP },
            67 .. 70 to { DungeonLocation.MESA },
            71 .. 74 to { DungeonLocation.SEA_CAVES },
            75 .. 78 to { DungeonLocation.SEVERAL_MESAS },
            79 .. 82 to { DungeonLocation.MOUNTAIN_PEAK },
            83 .. 86 to { DungeonLocation.PROMONTORY },
            87 .. 90 to { DungeonLocation.ISLAND },
            91 .. 95 to { DungeonLocation.UNDERWATER },
            96 .. 100 to DungeonLocation::EXOTIC
    )

    val EXOTIC_LOCATIN = mapOf(
            1 to { ExoticLocation.BRANCHES_TREE },
            2 to { ExoticLocation.GEYSER },
            3 to { ExoticLocation.BEHIND_WATERFALL },
            4 to { ExoticLocation.BURIED_AVALANCHE },
            5 to { ExoticLocation.BURIED_SANDSTORM },
            6 to { ExoticLocation.BURIED_VOLCANIC_ASH },
            7 to ExoticLocation::CASTLE_IN_SWAMP,
            8 to ExoticLocation::CASTLE_IN_SINKHOLE,
            9 to { ExoticLocation.FLOATING_SEA },
            10 to { ExoticLocation.IN_METEORITE },
            11 to ExoticLocation::ON_DEMIPLANE,
            12 to { ExoticLocation.MAGICAL_CATASTROPHE },
            13 to { ExoticLocation.CLOUD },
            14 to { ExoticLocation.FEYWILD },
            15 to { ExoticLocation.SHADOWFELL },
            16 to { ExoticLocation.ISLAND_IN_UNDERGROUND_SEA },
            17 to { ExoticLocation.VOLCANO },
            18 to { ExoticLocation.CREATURE },
            19 to { ExoticLocation.DOME_OF_FORCE },
            20 to { ExoticLocation.MANSION }
    )

    val DUNGEON_CREATOR = mapRangeOf(
            1 .. 1 to { DungeonCreator.BEHOLDER },
            2 .. 4 to DungeonCreator::CULT,
            5 .. 8 to { DungeonCreator.DWARVES },
            9 .. 9 to { DungeonCreator.ELVES },
            10 .. 10 to { DungeonCreator.GIANTS },
            11 .. 11 to { DungeonCreator.HOBGOBLINGS },
            12 .. 15 to DungeonCreator::HUMANS,
            16 .. 16 to { DungeonCreator.KUO_TOA },
            17 .. 17 to { DungeonCreator.LICH },
            18 .. 18 to { DungeonCreator.MIND_FLAYERS },
            19 .. 19 to { DungeonCreator.YUAN_TI },
            20 .. 20 to { DungeonCreator.NONE }
    )

    val CULTS_AND_RELIGIOUS_GROUPS = mapRangeOf(
            1 .. 1 to { DungeonCreator.Cult.DEMON },
            2 .. 2 to { DungeonCreator.Cult.DEVIL },
            3 .. 4 to { DungeonCreator.Cult.AIR },
            5 .. 6 to { DungeonCreator.Cult.EARTH },
            7 .. 8 to { DungeonCreator.Cult.FIRE },
            9 .. 10 to { DungeonCreator.Cult.WATER },
            11 .. 15 to { DungeonCreator.Cult.EVIL },
            16 .. 17 to { DungeonCreator.Cult.GOOD },
            18 .. 20 to { DungeonCreator.Cult.NEUTRAL }
    )

    val NPC_ALIGNMENT = mapRangeOf(
            1 .. 2 to EnumAlignment.LAWFUL_GOOD,
            3 .. 4 to EnumAlignment.NEUTRAL_GOOD,
            5 .. 6 to EnumAlignment.CHAOTIC_GOOD,
            7 .. 9 to EnumAlignment.LAWFUL_GOOD,
            10 .. 11 to EnumAlignment.NEUTRAL,
            12 .. 12 to EnumAlignment.CHAOTIC_NEUTRAL,
            13 .. 15 to EnumAlignment.LAWFUL_EVIL,
            16 .. 18 to EnumAlignment.NEUTRAL_EVIL,
            19 .. 20 to EnumAlignment.CHAOTIC_EVIL
    )

    val NPC_CLASS = mapRangeOf(
            1 .. 1 to { CharacterClass.BARBARIAN },
            2 .. 2 to { CharacterClass.BARD },
            3 .. 4 to { CharacterClass.CLERIC },
            5 .. 5 to { CharacterClass.DRUID },
            6 .. 7 to { CharacterClass.FIGHTER },
            8 .. 8 to { CharacterClass.MONK },
            9 .. 9 to { CharacterClass.PALADIN },
            10 .. 10 to { CharacterClass.RANGER },
            11 .. 14 to { CharacterClass.ROGUE },
            15 .. 15 to { CharacterClass.SORCERER },
            16 .. 16 to { CharacterClass.WARLOCK },
            17 .. 20 to { CharacterClass.WIZARD }
    )

    val DUNGEON_PURPOSE = mapRangeOf(
            1 .. 1 to { DungeonPurpose.DEATH_TRAP },
            2 .. 5 to { DungeonPurpose.LAIR },
            6 .. 6 to { DungeonPurpose.MAZE },
            7 .. 9 to { DungeonPurpose.MINE },
            10 .. 10 to { DungeonPurpose.PLANAR_GATE },
            11 .. 14 to { DungeonPurpose.STRONGHOLD },
            15 .. 17 to { DungeonPurpose.TEMPLE_OR_SHRINE },
            18 .. 19 to { DungeonPurpose.TOMB },
            20 .. 20 to { DungeonPurpose.TREASURE_VAULT }
    )

    val DUNGEON_HISTORY = mapRangeOf(
            1 .. 3 to { DungeonHistory.ABANDONED_BY_CREATORS },
            4 .. 4 to { DungeonHistory.ABANDONED_PLAGUE },
            5 .. 8 to { DungeonHistory.CONQUERED },
            9 .. 10 to { DungeonHistory.CREATORS_DESTROYED_BY_RAIDERS },
            11 .. 11 to { DungeonHistory.CREATORS_DESTROYED_BY_DISCOVERY },
            12 .. 12 to { DungeonHistory.CREATORS_DESTROYED_BY_INTERNAL },
            13 .. 13 to { DungeonHistory.CREATORS_DESTROYED_BY_MAGICAL },
            14 .. 15 to { DungeonHistory.CREATORS_DESTROYED_BY_DISASTER },
            16 .. 16 to  { DungeonHistory.LOCATION_CURSED_BY_GODS },
            17 .. 18 to { DungeonHistory.ORIGINAL_CREATOR_IN_CONTROL },
            19 .. 19 to { DungeonHistory.OVERRUN_BY_PLANAR_CREATURES },
            20 .. 20 to { DungeonHistory.SITE_OF_A_GREAT_MIRACLE }
    )

    val dungeonLocation: DungeonLocation
        get() = DUNGEON_LOCATION[DiceSet.d100()]!!()

    val exoticLocation: ExoticLocation
        get() = EXOTIC_LOCATIN[DiceSet.d20()]!!()

    val dungeonCreator: DungeonCreator
        get() = DUNGEON_CREATOR[DiceSet.d20()]!!()

    val dungeonHistory: DungeonHistory
        get() = DUNGEON_HISTORY[DiceSet.d20()]!!()

    val cultOrReligiousGroup: DungeonCreator.Cult
        get() = CULTS_AND_RELIGIOUS_GROUPS[DiceSet.d20()]!!()

    val npcAlignment: EnumAlignment
        get() = NPC_ALIGNMENT[DiceSet.d20()]!!

    val npcClass: CharacterClass
        get() = NPC_CLASS[DiceSet.d20()]!!()
}