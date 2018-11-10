package org.abimon.revelation.characters.genetics

open class HeightGene(name: String, dominance: Int): Gene(name, dominance) {
    companion object {
        val NAMES_DOMINANT = (0 until 5).map { i -> "[Height] Dominant $i" }.toTypedArray()
        val NAMES_RECESSIVE = (0 until 5).map { i -> "[Height] Recessive $i" }.toTypedArray()

        val DOMINANT = NAMES_DOMINANT.map { name -> HeightGene(name, true) }.toTypedArray()
        val RECESSIVE = NAMES_RECESSIVE.map { name -> HeightGene(name, false) }.toTypedArray()
    }

    constructor(name: String, isDominant: Boolean): this(name, if (isDominant) Gene.DOMINANT else Gene.RECESSIVE)
}