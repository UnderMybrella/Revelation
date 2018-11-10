package org.abimon.revelation.magicItems.categories

import org.abimon.revelation.Words

sealed class IWondrousItem {
    companion object: ICategoryItemGen<IWondrousItem> {
        override fun gen(): IWondrousItem = BeadOfMassVerbWithObject()
    }

    class BeadOfMassVerbWithObject(val verb: Words.MassVerbWithObject = Words.massVerbWithObject()): IWondrousItem() {
        override fun toString(): String = "Bead of $verb"
    }
}