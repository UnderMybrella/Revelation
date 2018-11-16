package org.abimon.revelation

@Target(AnnotationTarget.FIELD)
annotation class RevelationTable(vararg val tags: String, val recommendedRoll: String = "1d100")