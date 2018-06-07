package org.abimon.revelation.magicItems.categories

import org.abimon.revelation.Words
import org.abimon.revelation.get

object WondrousItem: ICategoryItemGen {
    override fun genName(): String = arrayOf(
            "${Words.objectWord} of ${Words.effect}",
            "${Words.property} ${Words.objectWord}",
            "${Words.objectWord} of ${Words.emotion}",
            "${Words.objectWord} of ${Words.projectile} ${Words.movementForce}",
            "${Words.objectWord} of the ${Words.profession}",
            "${Words.objectWord} of ${Words.race}kind",
            "${Words.objectWord} of ${Words.creatureVariant} ${Words.stat}",
            "${Words.objectWord} of ${Words.projectile} ${Words.effect}"
    )[Words.RNG]
}