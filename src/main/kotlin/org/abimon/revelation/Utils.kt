package org.abimon.revelation

import java.util.*

operator fun <T> Array<T>.get(rng: Random): T = this[rng.nextInt(this.size)]

