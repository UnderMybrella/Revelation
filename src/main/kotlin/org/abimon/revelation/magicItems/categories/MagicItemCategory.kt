package org.abimon.revelation.magicItems.categories

import java.util.*

sealed class MagicItemCategory<T>(val name: String, vararg val subItem: ICategoryItemGen<T>) {
    object ARMOUR: MagicItemCategory<Unit>("Armour")
    object POTION: MagicItemCategory<Unit>("Potion")
    object RING: MagicItemCategory<Unit>("Ring")
    object ROD: MagicItemCategory<Unit>("Rod")
    object SCROLL: MagicItemCategory<Unit>("Scroll")
    object STAFF: MagicItemCategory<Unit>("Stafff")
    object WAND: MagicItemCategory<Unit>("Wand")
    object WEAPON: MagicItemCategory<Unit>("Weapon")
    object WONDROUS: MagicItemCategory<IWondrousItem>("Wondrous Item", IWondrousItem)

    val rng = Random()

    fun gen(): T = subItem[rng.nextInt(subItem.size)].gen()
}