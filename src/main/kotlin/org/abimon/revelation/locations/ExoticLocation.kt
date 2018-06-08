package org.abimon.revelation.locations

import org.abimon.revelation.DiceSet

sealed class ExoticLocation(val text: String) {
    override fun toString(): String = text

    object BRANCHES_TREE: ExoticLocation("Among the branches of a tree")
    object GEYSER: ExoticLocation("Around a geyser")
    object BEHIND_WATERFALL: ExoticLocation("Behind a waterfall")
    object BURIED_AVALANCHE: ExoticLocation("Buried in an avalanche")
    object BURIED_SANDSTORM: ExoticLocation("Buried in a sandstorm")
    object BURIED_VOLCANIC_ASH: ExoticLocation("Buried in volcanic ash")
    class CASTLE_IN_SWAMP: ExoticLocation("Castle or structure sunken in a swamp") { val castle: Boolean = DiceSet.d4() % 2 == 0 }
    class CASTLE_IN_SINKHOLE: ExoticLocation("Castle or structure at the bottom of a sinkhole") { val castle: Boolean = DiceSet.d4() % 2 == 0 }
    object FLOATING_SEA: ExoticLocation("Floating on the sea")
    object IN_METEORITE: ExoticLocation("In a meteorite")
    class ON_DEMIPLANE: ExoticLocation("On a demiplane or in a pocket dimension") { val demiplane: Boolean = DiceSet.d4() % 2 == 0 }
    object MAGICAL_CATASTROPHE: ExoticLocation("In an area devastated by a magical catastrophe")
    object CLOUD: ExoticLocation("On a cloud")
    object FEYWILD: ExoticLocation("In the Feywild")
    object SHADOWFELL: ExoticLocation("In the Shadowfell")
    object ISLAND_IN_UNDERGROUND_SEA: ExoticLocation("On an island in an underground sea")
    object VOLCANO: ExoticLocation("In a volcano")
    object CREATURE: ExoticLocation("On the back of a Gargantuan living creature")
    object DOME_OF_FORCE: ExoticLocation("Sealed inside a magical dome of force")
    object MANSION: ExoticLocation("Inside a Mordekainen's magnificent mansion")
}