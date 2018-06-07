package org.abimon.revelation.magicItems.categories

import org.abimon.revelation.magicItems.categories.armour.Shield

enum class EnumMagicItemCategory(vararg val subItem: ICategoryItemGen) {
    ARMOUR(Shield),
    POTION,
    RING,
    ROD,
    SCROLL,
    STAFF,
    WAND,
    WEAPON,
    WONDROUS(WondrousItem)
}