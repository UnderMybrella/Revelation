package org.abimon.revelation

import java.util.*

object Words {
    val RNG = Random()

    val EMOTIONS = arrayOf(
            "Happiness",
            "Sadness",
            "Anger",
            "Lust",
            "Luck",
            "Security",
            "Charming"
    )

    val PROJECTILE = arrayOf(
            "Arrow",
            "Missile",
            "Fireball",
            "Firebolt",
            "Witchbolt",
            "Crossbow Bolt"
    )

    val MOVEMENT_FORCE = arrayOf(
            "Attraction",
            "Repulsion",
            "Acceleration",
            "Deceleration",
            "Braking"
    )

    val CREATURE = arrayOf(
            "Giant",
            "Efreeti",
            "Frog"
    )

    val CREATURE_VARIANT = arrayOf(
            "Hill Giant",
            "Stone Giant",
            "Frost Giant",
            "Fire Giant",
            "Cloud Giant",
            "Storm Giant"
    )

    val STAT = arrayOf(
            "Strength",
            "Dexterity",
            "Consitution",
            "Intelligence",
            "Wisdom",
            "Charisma"
    )

    val SKILL = arrayOf(
            "Athletics",
            "Acrobatics",
            "Sleight of Hand",
            "Stealth",
            "Arcana",
            "History",
            "Investigation",
            "Nature",
            "Religion",
            "Animal Handling",
            "Insight",
            "Medicine",
            "Perception",
            "Survival",
            "Deception",
            "Intimidation",
            "Performance",
            "Persuasion"
    )

    val EFFECT = arrayOf(
            "Smoking",
            "Billowing",
            "Levitation",
            "Holding",
            "Devouring",
            "Snaring",
            "Seeing",
            "Blasting",
            "Trapping",
            "Rising",
            "Absorption",
            "Annihilation",
            "Answering",
            "Life Stealing",
            "Wounding",
            "Binding",
            "Warning",
            "Opening",
            "Mending"
    )

    val RACE = arrayOf(
            "Dwarven",
            "Elven"
    )

    val CLASS = arrayOf(
            "Barbarian",
            "Bard",
            "Cleric",
            "Druid",
            "Fighter",
            "Monk",
            "Paladin",
            "Ranger",
            "Rogue",
            "Sorcerer",
            "Warlock",
            "Wizard"
    )

    val PROFESSION = arrayOf(
            "Berserker",
            "Totem Warrior",
            "Champion",
            "Battle Master",
            "Eldritch Knight",
            "Hunter",
            "Beast Master",
            "Thief",
            "Assassin",
            "Arcane Trickster",
            "Charlatan"
    )

    val WEAPONS = arrayOf(
            "Club",
            "Dagger",
            "Greatclub",
            "Handaxe",
            "Javelin",
            "Light Hammer",
            "Mace",
            "Quarterstaff",
            "Sickle",
            "Spear",
            "Light Crossbow",
            "Dart",
            "Shortbow",
            "Sling",
            "Battleaxe",
            "Flail",
            "Glaive",
            "Greataxe",
            "Greatsword",
            "Halberd",
            "Lance",
            "Longsword",
            "Maul",
            "Morningstar",
            "Pike",
            "Rapier",
            "Scimitar",
            "Shortsword",
            "Trident",
            "War Pick",
            "Warhammer",
            "Whip",
            "Blowgun",
            "Hand Crossbow",
            "Heavy Crossbow",
            "Longbow",
            "Composite Bow",
            "Net"
    )

    val MATERIALS = arrayOf(
            "Adamantine",
            "Brass",
            "Bronze",
            "Crystal",
            "Force",
            "Iron",
            "Mithral",
            "Silver"
    )

    val PROPERTIES = arrayOf(
            "Immovable",
            "Invulnerable",
            "Vulnerable",
            "Portable",
            "Folding",
            "Sending",
            "Pure Good",
            "Ultimate Evil",
            "Clockwork"
    )

    val DAMAGE_MODIFIER = arrayOf(
            "Invulnerability",
            "Resistance",
            "Vulnerability"
    )

    val OBJECTS = arrayOf(
            "Bag",
            "Bead",
            "Beans",
            "Belt",
            "Boat",
            "Boots",
            "Bowl",
            "Bracers",
            "Brazier",
            "Broom",
            "Candle",
            "Cap",
            "Cape",
            "Carpet",
            "Censer",
            "Chime",
            "Circlet",
            "Cloak",
            "Clothes",
            "Cube",
            "Die",
            "Dice",
            "Fan",
            "Flask",
            "Helm",
            "Hole",
            "Horn",
            "Horseshoe",
            "Instrument",
            "Manual",
            "Mirror",
            "Necklace",
            "Pearl",
            "Quiver",
            "Robe",
            "Rod",
            "Saddle",
            "Sphere",
            "Stone",
            "Table",
            "Talisman",
            "Tome",
            "Well",
            "Wings"
    )

    val ELEMENTS = arrayOf(
            "Fire"
    )

    val NAMES = arrayOf(
            "Daern"
    )

    val MISC = arrayOf(
            "Speed"
    )

    val PLACES = arrayOf(
            "The Deep"
    )

    val emotion: String
        get() = EMOTIONS[RNG]

    val projectile: String
        get() = PROJECTILE[RNG]

    val movementForce: String
        get() = MOVEMENT_FORCE[RNG]

    val profession: String
        get() = PROFESSION[RNG]

    val race: String
        get() = RACE[RNG]

    val creature: String
        get() = CREATURE[RNG]

    val creatureVariant: String
        get() = CREATURE_VARIANT[RNG]

    val stat: String
        get() = STAT[RNG]

    val effect: String
        get() = EFFECT[RNG]

    val objectWord: String
        get() = OBJECTS[RNG]

    val property: String
        get() = PROPERTIES[RNG]

    val colour: String
        get() = "Blue"
}