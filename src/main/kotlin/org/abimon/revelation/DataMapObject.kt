package org.abimon.revelation

import org.abimon.revelation.parboiled.JsonParser
import org.abimon.revelation.parboiled.RevelationParser

open class DataMapObject(val dataName: String) {
    protected val parser = RevelationParser()
    protected val jsonParser = JsonParser()
    protected val dataMap = DataMapObject::class.java.classLoader.getResourceAsStream("$dataName.json").use { stream -> String(stream.readBytes()) }
            .let { lines -> jsonParser.parse(lines) }
            ?.let { map -> map.mapValues { (_, value) -> (value as? Map<*, *>)?.mapToRange() ?: value } } ?: emptyMap()

    fun BlankMap.mapToRange(): Map<Int, Any?> = flatMap { (key, value) ->
        val newValue = "script:~[$value]~"

        val strKey = key.toString()
        val intKey = strKey.toIntOrNull()

        if (intKey != null)
            return@flatMap listOf(intKey to newValue)

        if (".." in strKey)
            return@flatMap ((strKey.substringBefore("..").trim().toIntOrNull() ?: 0) .. (strKey.substringAfter("..").trim().toIntOrNull() ?: 0)).map { i -> i to newValue }
        if ("until" in strKey)
            return@flatMap ((strKey.substringBefore("until").trim().toIntOrNull() ?: 0) until (strKey.substringAfter("until").trim().toIntOrNull() ?: 0)).map { i -> i to newValue }

        return@flatMap listOf(-1 to value)
    }.toMap()
    fun BlankMap?.toDataMap(): Map<Int, String> = this?.mapNotNull { (key, value) -> (key as? Int)?.let { keyI -> (value as? String)?.let { valueS -> keyI to valueS } } }?.toMap() ?: emptyMap()

    infix fun Map<String, Any>.fromKey(key: String): Map<Int, String> = (this[key] as? BlankMap).toDataMap()
    fun String.parseScript(): String? = parser.parse(this)?.format()?.trim()
}