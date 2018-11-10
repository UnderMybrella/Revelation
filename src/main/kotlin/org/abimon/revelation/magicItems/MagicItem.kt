package org.abimon.revelation.magicItems

import org.abimon.revelation.magicItems.categories.MagicItemCategory


data class MagicItem<T>(
        val category: MagicItemCategory<T>,
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