package org.abimon.revelation.characters

class NPC(
        val appearanceTraits: MutableList<NPCAppearanceTrait> = arrayListOf(NPCTraits.appearance),
        val highAbilities: MutableList<String> = arrayListOf(NPCTraits.highAbility),
        val lowAbilities: MutableList<String> = arrayListOf(NPCTraits.lowAbility)
) {
}