package org.abimon.revelation

import java.util.*
import kotlin.math.ceil
import kotlin.math.roundToInt

object DiceSet {
    abstract class Die {
        protected val RANDOM: Random = Random()

        operator fun invoke(): Int = this[1]
        operator fun invoke(num: Int): Int = this[num]
        abstract operator fun get(num: Int): Int
    }

    open class StandardDie(val sides: Int, val transform: (Int) -> Int = { roll -> roll + 1 }): Die() {
        override operator fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy { transform(RANDOM.nextInt(sides)) }
    }

    val d4: Die = StandardDie(4)
    val d6: Die = StandardDie(6)
    val d8: Die = StandardDie(8)
    val d10: Die = StandardDie(10)
    val d12: Die = StandardDie(12)
    val d20: Die  = StandardDie(20)
    val percentile: Die = StandardDie(10) { num -> num * 10 }

    val d2: Die = object: Die() {
        override fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy { ceil(d4().toDouble() / 2.0).roundToInt() }
    }
    val d3: Die = object: Die() {
        override fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy { ceil(d6().toDouble() / 2.0).roundToInt() }
    }
    val d100: Die = object: Die() {
        override operator fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy {
            val ones = d10()
            val percent = percentile()

            if (ones == 10 && percent == 0)
                return@sumBy 100
            return@sumBy ones + percent
        }
    }
}