package org.abimon.revelation.characters.genetics

open class Gene(val geneName: String, val dominance: Int) {
    companion object {
        val RECESSIVE = (1 shl 0)
        val DOMINANT = (1 shl 1)
        val NO_DOMINANCE = (1 shl 2)
        val CODOMINANCE = (1 shl 3)

        val wildcardGene = "[System] WILDCARD"
    }

    open val uid: String = geneName

    object RECESSIVE_WILDCARD: Gene(wildcardGene, RECESSIVE)
    object DOMINANT_WILDCARD: Gene(wildcardGene, DOMINANT)
    object WILDCARD: Gene(wildcardGene, 0)

    override fun toString(): String = "$geneName (0b${dominance.toString(2).padStart(4, '0')})"
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Gene) return false

        if (geneName == wildcardGene || other.geneName == wildcardGene || uid == wildcardGene || other.uid == wildcardGene) return true
        if (uid != other.uid) return false
        if (dominance != other.dominance) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uid.hashCode()
        result = 31 * result + dominance
        return result
    }
}