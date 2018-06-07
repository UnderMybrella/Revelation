package org.abimon.revelation.magicItems.categories.armour

import org.abimon.revelation.magicItems.categories.ICategoryItemGen
import org.abimon.revelation.Words
import org.abimon.revelation.get

object Shield: ICategoryItemGen {
    override fun genName(): String = arrayOf(
            "Shield of ${Words.emotion}",
            "Shield of ${Words.projectile} ${Words.movementForce}",
            "Shield of the ${Words.profession}",
            "Shield of ${Words.race}kind",
            "Shield of ${Words.creatureVariant} ${Words.stat}",
            "Shield of ${Words.effect}",
            "Shield of ${Words.projectile} ${Words.effect}"
    )[Words.RNG]
}
