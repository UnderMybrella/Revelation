package org.abimon.revelation.characters.genetics

import org.abimon.revelation.get
import java.security.SecureRandom
import java.util.*
import kotlin.collections.ArrayList

data class Genotype(val name: String, val alleles: Array<Pair<Gene, Gene>>) {
    companion object {
        val RNG = SecureRandom()

        val RACE = "Race"
        val HEIGHT = "Height"
    }

    val genes: Array<Gene> by lazy { alleles.flatMap { (a, b) -> listOf(a, b) }.toTypedArray() }

    val dominantCount = genes.count { gene -> gene.dominance and Gene.DOMINANT == Gene.DOMINANT }

    infix fun mixWith(genome: Genotype): Genotype {
        if (this.name != genome.name)
            throw IllegalArgumentException("Invalid genomes to mix! $genome and $this")
        val possibilities: Array<Array<Pair<Gene, Gene>>> = alleles.mapIndexed { index, (a1, b1) ->
            val (a2, b2) = genome.alleles[index]

            return@mapIndexed arrayOf(a1 to a2, a1 to b2, b1 to a2, b1 to b2)
        }.toTypedArray()

        return Genotype(name, possibilities.map { array -> array[RNG] }.toTypedArray())
    }

    fun possibleParentGenes(options: Array<Gene> = alleles.map { Gene.WILDCARD }.toTypedArray(), partner: Genotype? = null): List<Array<Pair<Gene, Gene>>> {
        if (partner != null && this.name != partner.name)
            throw IllegalArgumentException("Invalid genomes to mix! $partner and $this")

        return alleles.mapIndexed { index, (a, b) ->
            val combinations: MutableList<Pair<Gene, Gene>> = ArrayList()

            combinations.add(a to b)
            combinations.addAll(options.map { ao -> a to ao })
            combinations.addAll(options.map { bo -> b to bo })

            return@mapIndexed combinations.distinct().filter { (ao, bo) ->
                if (partner == null)
                    return@filter true

                val (ap, bp) = partner.alleles[index]

                if ((ap == a && ao == b) || (ao == a && ap == b)) {
                    return@filter true
                } else if ((ap == a && bo == b) || (bo == a && ap == b)) {
                    return@filter true
                } else if ((ao == a && bp == b) || (bp == a && ao == b)) {
                    return@filter true
                } else {
                    return@filter ((bp == a && bo == b) || (bo == a && bp == b))
                }
            }.toTypedArray()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Genotype) return false

        if (name != other.name) return false
        if (!Arrays.equals(alleles, other.alleles)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + Arrays.hashCode(alleles)
        return result
    }
}