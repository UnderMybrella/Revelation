package org.abimon.revelation.locations

sealed class DungeonHistory(val text: String) {

    object ABANDONED_BY_CREATORS: DungeonHistory("Abandoned by creators")
    object ABANDONED_PLAGUE: DungeonHistory("Abandoned due to plague")
    object CONQUERED: DungeonHistory("Conquered by invaders")
    object CREATORS_DESTROYED_BY_RAIDERS: DungeonHistory("Creators destroyed by attacking raiders")
    object CREATORS_DESTROYED_BY_DISCOVERY: DungeonHistory("Creators destroyed by discovery made within the site")
    object CREATORS_DESTROYED_BY_INTERNAL: DungeonHistory("Creators destroyed by internal conflict")
    object CREATORS_DESTROYED_BY_MAGICAL: DungeonHistory("Creators destroyed by magical catastrophe")
    object CREATORS_DESTROYED_BY_DISASTER: DungeonHistory("Creators destroyed by natural disaster")
    object LOCATION_CURSED_BY_GODS: DungeonHistory("Location cursed by the gods and shunned")
    object ORIGINAL_CREATOR_IN_CONTROL: DungeonHistory("Original creator still in control")
    object OVERRUN_BY_PLANAR_CREATURES: DungeonHistory("Overrun by planar creatures")
    object SITE_OF_A_GREAT_MIRACLE: DungeonHistory("Site of a great miracle")

    override fun toString(): String = text
}