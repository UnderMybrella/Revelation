package org.abimon.revelation.parboiled

data class RevelationFunc(val function: (RevelationOutput) -> Unit) {
    operator fun invoke(output: RevelationOutput) = function(output)
}