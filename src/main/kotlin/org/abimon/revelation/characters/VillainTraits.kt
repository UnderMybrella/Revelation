package org.abimon.revelation.characters

import org.abimon.revelation.DiceSet

object VillainTraits {
    val IMMORTALITY_SCHEME = mapOf(
            1 to "Acquire a legendary item to prolong life",
            2 to "Ascend to godhood",
            3 to "Become undead or obtain a younger body",
            4 to "Steal a planar creature's essence"
    )

    val INFLUENCE_SCHEME = mapOf(
            1 to "Seize a position of power or title",
            2 to "Win a contest or tournament",
            3 to "Win favour with a powerful individual",
            4 to "Place a pawn in a position of power"
    )

    val MAGIC_SCHEME = mapOf(
            1 to "Obtain an ancient artifact",
            2 to "Build a construct or magical device",
            3 to "Carry out a deity's wishes",
            4 to "Offer sacrifices to a deity",
            5 to "Contact a lost deity or power",
            6 to "Open a gate to another world"
    )

    val MAYHEM_SCHEME = mapOf(
            1 to "Fulfill an apocalyptic prophecy",
            2 to "Enact the vengeful will of a god or patron",
            3 to "Spread a vile contagion",
            4 to "Overthrow a government",
            5 to "Trigger a natural disaster",
            6 to "Utterly destroy a bloodline or clan"
    )

    val PASSION_SCHEME = mapOf(
            1 to "Prolong the life of a loved one",
            2 to "Prove worthy of another person's love",
            3 to "Raise or restore a dead loved one",
            4 to "Destroy rivals for another person's affection"
    )

    val POWER_SCHEME = mapOf(
            1 to "Conquer a region or incite a rebellion",
            2 to "Seize control of an army",
            3 to "Become the power behind the throne",
            4 to "Gain the favour of a ruler"
    )

    val REVENGE_SCHEME = mapOf(
            1 to "Avenge a past humiliation or insult",
            2 to "Avenge a past imprisonment or injury",
            3 to "Avenge the death of a loved one",
            4 to "Retrieve stolen property and punish the thief"
    )

    val WEALTH_SCHEME = mapOf(
            1 to "Control natural resources or trade",
            2 to "Marry into wealth",
            3 to "Plunder ancient ruins",
            4 to "Steal lands, goods, or money"
    )

    val SCHEMES = mapOf(
            1 to (DiceSet.d4 to IMMORTALITY_SCHEME),
            2 to (DiceSet.d4 to INFLUENCE_SCHEME),
            3 to (DiceSet.d6 to MAGIC_SCHEME),
            4 to (DiceSet.d6 to MAYHEM_SCHEME),
            5 to (DiceSet.d4 to PASSION_SCHEME),
            6 to (DiceSet.d4 to POWER_SCHEME),
            7 to (DiceSet.d4 to REVENGE_SCHEME),
            8 to (DiceSet.d4 to WEALTH_SCHEME)
    )

    val AGRICULTURAL_DEVASTATION_METHOD = mapOf(
            1 to "Blight",
            2 to "Crop failure",
            3 to "Drought",
            4 to "Famine"
    )

    val CAPTIVITY_OR_BRIBERY_METHOD = mapOf(
            1 to "Bribery",
            2 to "Enticement",
            3 to "Eviction",
            4 to "Imprisonment",
            5 to "Kidnapping",
            6 to "Legal intimidation",
            7 to "Press gangs",
            8 to "Shackling",
            9 to "Slavery",
            10 to "Threats or harassment"
    )

    val CONFIDENCE_SCAM_METHODS = mapOf(
            1 to "Breach of contract",
            2 to "Cheating",
            3 to "Fast talking",
            4 to "Fine print",
            5 to "Fraud or swindling",
            6 to "Quackery or tricks"
    )

    val DEFAMATION_METHODS = mapOf(
            1 to "Framing",
            2 to "Gossiping or slander",
            3 to "Humiliation",
            4 to "Libel or insults"
    )

    val EXECUTION_METHODS = mapOf(
            1 to "Beheading",
            2 to "Burning at the stake",
            3 to "Burying alive",
            4 to "Crucifixion",
            5 to "Drawing and quartering",
            6 to "Hanging",
            7 to "Impalement",
            8 to "Sacrifice (Living)"
    )

    val MAGICAL_MAYHEM_METHODS = mapOf(
            1 to "Hauntings",
            2 to "illusions",
            3 to "Infernal bargains",
            4 to "Mind control",
            5 to "Petrification",
            6 to "Raising or animating the dead",
            7 to "Summoning monsters",
            8 to "Weather control"
    )

    val MURDER_METHODS = mapOf(
            1 to "Assassination",
            2 to "Cannibalism",
            3 to "Dismemberment",
            4 to "Drowning",
            5 to "Electrocution",
            6 to "Euthanasia (involuntary)",
            7 to "Disease",
            8 to "Poisoning",
            9 to "Stabbing",
            10 to "Strangulation or suffocation"
    )

    val POLITICAL_METHODS = mapOf(
            1 to "Betrayal or treason",
            2 to "Conspiracy",
            3 to "Espionage or spying",
            4 to "Genocide",
            5 to "Oppression",
            6 to "Raising taxes"
    )

    val scheme: String
        get() = SCHEMES[DiceSet.d8()]!!.let { (die, scheme) -> scheme[die()]!! }
}