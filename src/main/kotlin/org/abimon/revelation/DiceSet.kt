package org.abimon.revelation

import java.util.*

object DiceSet {
    class Die(val sides: Int, val transform: (Int) -> Int = Int::toInt) {
        private val RANDOM: Random = Random()

        operator fun invoke(): Int = this[1]
        operator fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy { transform(RANDOM.nextInt(sides)) }
    }

    val d4 = Die(4)
    val d6 = Die(6)
    val d8 = Die(8)
    val d10 = Die(10)
    val d12 = Die(12)
    val d20 = Die(20)
    val percentile = Die(10) { num -> num * 10 }

    object d100 {
        operator fun invoke(): Int = this[1]
        operator fun get(num: Int): Int = (0 until num.coerceAtLeast(1)).sumBy {
            val ones = d10()
            val percent = percentile()

            if (ones == 10 && percent == 0)
                return@sumBy 100
            return@sumBy ones + percent
        }
    }
}