package org.abimon.revelation.parboiled

import org.parboiled.MatcherContext
import org.parboiled.matchers.AnyMatcher
import org.parboiled.support.Chars

object ParamMatcher : AnyMatcher() {
    override fun match(context: MatcherContext<*>): Boolean {
        return when (context.currentChar) {
            '"' -> context.inputBuffer.charAt(context.currentIndex - 1) == '\\'
            Chars.EOI -> false
            else -> super.match(context)
        }
    }
}