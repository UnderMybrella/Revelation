package org.abimon.revelation.characters

class NPC(
        val appearance: MutableList<String> = arrayListOf(NPCTraits.apperance),
        val highAbilities: MutableList<String> = arrayListOf(NPCTraits.highAbility),
        val lowAbilities: MutableList<String> = arrayListOf(NPCTraits.lowAbility)
) {
}