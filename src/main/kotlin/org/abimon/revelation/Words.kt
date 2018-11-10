package org.abimon.revelation

import java.util.*

object Words {
    val RNG = Random()

    interface Word {
        val text: String
    }

    abstract class VerbOfObject(override val text: String): Word {
        object GLEAMING: VerbOfObject("Gleaming")
        class OTHER(text: String): VerbOfObject(text)

        override fun toString(): String = text
    }

    abstract class MassVerbWithObject(override val text: String): Word {
        object NOURISHMENT: MassVerbWithObject("Nourishment")
        object REFRESHMENT: MassVerbWithObject("Refreshment")
        class OTHER(text: String): MassVerbWithObject(text)

        override fun toString(): String = text
    }

    abstract class AttributiveAdjective(override val text: String): Word {
        object FALSE: AttributiveAdjective("False")
        class OTHER(text: String): AttributiveAdjective(text)

        override fun toString(): String = text
    }

    val verbOfObjects: MutableList<() -> VerbOfObject> = arrayListOf(
            { VerbOfObject.GLEAMING }
    )

    val massVerbWithObjects: MutableList<() -> MassVerbWithObject> = arrayListOf(
            { MassVerbWithObject.NOURISHMENT },
            { MassVerbWithObject.REFRESHMENT }
    )

    fun massVerbWithObject(): MassVerbWithObject = massVerbWithObjects[RNG.nextInt(massVerbWithObjects.size)]()
}