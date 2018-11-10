package org.abimon.revelation.characters

sealed class NPCAppearanceTrait(val text: String) {
    object DISTINCTIVE_JEWELRY: NPCAppearanceTrait("Distinctive jewelry; earrings, necklace, circlet, bracelets")
    object PIERCINGS: NPCAppearanceTrait("Piercings")
    object FLAMBOYANT_CLOTHES: NPCAppearanceTrait("Flamboyant or outlandish clothes")
    object FORMAL_CLOTHES: NPCAppearanceTrait("Formal, clean clothes")
    object RAGGED_CLOTHES: NPCAppearanceTrait("Ragged, dirty clothes")
    object PRONOUNCED_SCAR: NPCAppearanceTrait("Pronounced scar")
    object MISSING_TEETH: NPCAppearanceTrait("Missing teeth")
    object MISSING_FINGERS: NPCAppearanceTrait("Missing fingers")

    class UNUSUAL_EYE_COLOURS(val eyeOne: String, val eyeTwo: String): NPCAppearanceTrait("Unusual eye colour (or two different colours)") {
        constructor(eyeColor: String): this(eyeColor, eyeColor)
        constructor(): this("", "")

        override fun toString(): String = "Unusual eye colours ($eyeOne, $eyeTwo)"
    }

    object TATTOOS: NPCAppearanceTrait("Tattoos")
    object BIRTHMARK: NPCAppearanceTrait("Birthmark")
    class UNUSUAL_SKIN_COLOR(val skin: String): NPCAppearanceTrait("Unusual skin colour") {
        constructor(): this("")

        override fun toString(): String = "Unusual skin colour ($skin)"
    }

    object BALD: NPCAppearanceTrait("Bald")
    object BRAIDED_BEARD: NPCAppearanceTrait("Braided beard or hair")
    class UNUSUAL_HAIR_COLOR(val baseColor: String, val highlights: String?): NPCAppearanceTrait("Unusual hair colour") {
        constructor(baseColor: String): this(baseColor, null)
        constructor(): this("", null)

        override fun toString(): String = "Unusual hair colour ($baseColor${if (highlights != null) " with $highlights highlights" else ""})"
    }

    object NERVOUS_EYE: NPCAppearanceTrait("Nervous eye twitch")
    object DISTINCTIVE_NOSE: NPCAppearanceTrait("Distinctive nose")
    object DISTINCTIVE_POSTURE: NPCAppearanceTrait("Distinctive posture (crooked or rigid)")
    object EXCEPTIONALLY_BEAUTIFUL: NPCAppearanceTrait("Exceptionally beautiful")
    object EXCEPTIONALLY_UGLY: NPCAppearanceTrait("Exceptionally ugly")

    class OTHER(text: String): NPCAppearanceTrait(text)

    override fun toString(): String = text
}