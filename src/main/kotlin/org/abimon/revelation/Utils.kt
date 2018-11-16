package org.abimon.revelation

import org.abimon.revelation.parboiled.RevelationOutput
import java.lang.reflect.Field
import java.util.*

typealias DetailPair<T> = Pair<T, () -> T>
typealias RevelationAction = () -> Unit
typealias RevelationOutputTemplate = (RevelationOutput) -> String
typealias BlankMap = Map<*, *>

fun Any?.findTables(): List<Pair<String, Map<Int, Any?>>> {
    val instance = (this?.instance() ?: return emptyList())

    return instance::class.java.declaredFields.mapNotNull { field ->
        val tags = field.annotations.mapNotNull { annotation -> if(annotation is RevelationTable) annotation.tags else null }.toTypedArray().flatten()

        if (tags.isNotEmpty())
            return@mapNotNull field.getMapFrom(instance).let { map -> tags.map { tag -> tag to map } }
        return@mapNotNull null
    }.flatten()
}

fun Any?.findTableNames(): List<Pair<String, String>> {
    val instance = (this?.instance() ?: return emptyList())

    return instance::class.java.declaredFields.mapNotNull { field ->
        val tags = field.annotations.mapNotNull { annotation -> if(annotation is RevelationTable) annotation.tags.map { tag -> tag to annotation.recommendedRoll } else null }.flatten()

        if (tags.isNotEmpty())
            return@mapNotNull tags
        return@mapNotNull null
    }.flatten()
}

fun Field.getMapFrom(instance: Any): Map<Int, Any?> {
    isAccessible = true

    return (this[instance] as BlankMap).mapKeys { (key) -> key.toString().toIntOrNull() ?: 0 }
}

public infix fun <T, R> Array<out T>.zipAll(other: Array<out R>): List<Pair<T, R>> {
    return flatMap { t -> other.map { r -> t to r } }
}

operator fun <T> Array<T>.get(rng: Random): T = this[rng.nextInt(this.size)]

fun <T> mapRangeOf(vararg params: Pair<IntRange, T>): Map<Int, T> = params.flatMap { (range, v) -> range.map { i -> i to v } }.toMap()

fun <T> mapOfNames(vararg names: T): Map<Int, T> = names.flatMap { str -> listOf(str, str) }.mapIndexed { index, s -> index + 1 to s }.toMap()

fun <T: Any> T.instance(): T = this

inline fun <reified T: Any> bruteForce(length: Int, charPool: Array<T>): List<Array<T>> = bruteForce(length, (0 until length).map { charPool }.toTypedArray())
inline fun <reified T: Any> bruteForce(length: Int, charPool: Array<Array<T>>): List<Array<T>> {
    val strs = ArrayList<Array<T>>()

    bruteForceRaw(length, charPool) { word -> if (word !in strs) strs.add(word) }

    return strs
}

inline fun <reified T: Any> bruteForce(length: Int, charPool: Array<T>, crossinline operate: (Array<T>) -> Unit) = bruteForceRaw(length, (0 until length).map { charPool }.toTypedArray(), operate)
inline fun <reified T: Any> bruteForceRaw(length: Int, charPool: Array<Array<T>>, crossinline operate: (Array<T>) -> Unit) = bruteForceRaw(length, charPool.map { it.size }.toIntArray()) { word -> operate(word.mapIndexed { index, i -> charPool[index][i] }.toTypedArray()) }
fun bruteForceRaw(length: Int, charPool: IntArray, operate: (IntArray) -> Unit) {
    val word = IntArray(length)
    val lastIndex = length - 1

    operate(word)
    loop@ while (word.filterIndexed { index, i -> i + 1 == charPool[index] }.count() < length) {
        word[lastIndex] += 1
        if (word[lastIndex] >= charPool[lastIndex]) {
            word[lastIndex] = 0

            for (i in lastIndex - 1 downTo 0) {
                word[i] += 1
                if (word[i] >= charPool[i]) {
                    word[i] = 0
                    continue
                }

                operate(word)
                continue@loop
            }
        } else
            operate(word)
    }
}

fun innerPlane(): String = when (DiceSet.d10()) {
    1 -> "Plane of Fire"
    2 -> "Plane of Fire"
    3 -> "Plane of Water"
    4 -> "Plane of Water"
    5 -> "Plane of Earth"
    6 -> "Plane of Earth"
    7 -> "Plane of Air"
    8 -> "Plane of Air"
    9 -> "Elemental Chaos"
    else -> "Material Plane"
}

fun outerPlane(): String = when (DiceSet.d4[5] - 4) {
    1 -> "Mount Celestia"
    2 -> "Bytopia"
    3 -> "Elysium"
    4 -> "The Beastlands"
    5 -> "Arborea"
    6 -> "Ysgard"
    7 -> "Limbo"
    8 -> "Pandemonium"
    9 -> "The Abyss"
    10 -> "Carceri"
    11 -> "Hades"
    12 -> "Gehenna"
    13 -> "The Nine Hells"
    14 -> "Acheron"
    15 -> "Mechanus"
    else -> "Arcadia"
}

fun race(): String = "FUCK YOU"
fun noExtra(): String = "N/a"
fun eyeColour(): String = if (DiceSet.d4() < 3) "" else " and "
fun languages(): String = (0 until DiceSet.d4() + 3).map { "" }.distinct().joinToString()

fun <T, U> Pair<T, () -> U>.format(toString: (T, U) -> String = { t, u -> if (u == "N/a") t.toString() else "$t ($u)" }): String = toString(first, second())