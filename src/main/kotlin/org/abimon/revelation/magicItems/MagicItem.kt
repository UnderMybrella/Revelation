package org.abimon.magicItemGen

import org.abimon.revelation.magicItems.categories.EnumMagicItemCategory

data class MagicItem(
        val category: EnumMagicItemCategory,
        val name: String,
        val requiresAttunement: Boolean,
        val activation: EnumActivation
) {
    override fun toString(): String = buildString {
        append("Name: ")
        appendln(name)

        append('*')
        append(category.name.toLowerCase().split("_").joinToString(" ") { word -> word.capitalize() })

        if(requiresAttunement)
            append(", requires attunement")
        appendln('*')

        append("Activation: ")
        append(activation.name.toLowerCase().split("_").joinToString(" ") { word -> word.capitalize() })
    }
}