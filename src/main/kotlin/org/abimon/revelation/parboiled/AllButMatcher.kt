package org.abimon.revelation.parboiled

import org.parboiled.MatcherContext
import org.parboiled.matchers.AnyMatcher

class AllButMatcher(val blacklist: CharArray): AnyMatcher() {
    override fun match(context: MatcherContext<*>): Boolean {
        if(blacklist.contains(context.currentChar))
            return false
        return super.match(context)
    }
}