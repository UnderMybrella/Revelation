package org.abimon.revelation.characters

import org.abimon.revelation.DiceSet
import org.abimon.revelation.Words

object NPCTraits {
    val APPEARANCE = mapOf<Int, Pair<String, () -> String>>(
            1 to ("Distinctive jewelry; earrings, necklace, circlet, bracelets" to this::none),
            2 to ("Piercings" to this::none),
            3 to ("Flamboyant or outlandish clothes" to this::none),
            4 to ("Formal, clean clothes" to this::none),
            5 to ("Ragged, dirty clothes" to this::none),
            6 to ("Pronounced scar" to this::none),
            7 to ("Missing teeth" to this::none),
            8 to ("Missing fingers" to this ::none),
            9 to ("Unusual eye colour (or two different colours)" to this::eyeColour),
            10 to ("Tattoos" to this::none),
            11 to ("Birthmark" to this::none),
            12 to ("Unusual skin colour" to Words::colour),
            13 to ("Bald" to this::none),
            14 to ("Braided beard or hair" to this::none),
            15 to ("Unusual hair colour" to Words::colour),
            16 to ("Nervous eye twitch" to this::none),
            17 to ("Distinctive nose" to this::none),
            18 to ("Distinctive posture (crooked or rigid)" to this::none),
            19 to ("Exceptionally beautiful" to this::none),
            20 to ("Exceptionally ugly" to this::none)
    )

    val apperance: String
        get() = APPEARANCE[DiceSet.d20()]!!.let { (feature, option) -> "$feature (${option()})" }

    fun none(): String = "N/a"
    fun eyeColour(): String = if (DiceSet.d4() < 3) Words.colour else "${Words.colour} and ${Words.colour}"
}