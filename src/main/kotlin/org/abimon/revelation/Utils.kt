package org.abimon.revelation

import java.util.*

operator fun <T> Array<T>.get(rng: Random): T = this[rng.nextInt(this.size)]

fun <T> mapRangeOf(vararg params: Pair<IntRange, T>): Map<Int, T> = params.flatMap { (range, v) -> range.map { i -> i to v } }.toMap()

fun <T: Any> T.instance(): T = this