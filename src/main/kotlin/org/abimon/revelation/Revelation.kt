package org.abimon.revelation

import org.abimon.revelation.characters.NPCTraits
import org.abimon.revelation.locations.AdventureEnvironments

object Revelation {
    @JvmStatic
    fun main(args: Array<String>) {
        println(NPCTraits.apperance)
        println(AdventureEnvironments.dungeonLocation)
        println((1 .. 1).map { "TEST" })
    }
}