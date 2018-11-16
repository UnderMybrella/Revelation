package org.abimon.revelation

import org.abimon.revelation.characters.XGtECharacterBuilding
import org.abimon.revelation.characters.genetics.Gene
import org.abimon.revelation.characters.genetics.Genotype
import org.abimon.revelation.characters.genetics.HeightGene
import org.abimon.revelation.characters.genetics.RacialGene
import org.abimon.revelation.characters.races.DwarfRace
import org.abimon.revelation.magicItems.categories.MagicItemCategory
import org.abimon.revelation.parboiled.RevelationParser
import java.util.concurrent.ThreadLocalRandom

object Revelation {
    @JvmStatic
    fun main(args: Array<String>) {
        val parser = RevelationParser()

        while (true) {
            print("> ")
            val line = readLine() ?: break
            val output = parser.parse(line)
            if (output == null)
                println("Unknown command: $line")
            else
                println(output.format())
        }
    }

    fun testFunc(): String = ""

    fun magicItems() {
        val names = ArrayList<String>()

        for (i in 0 until 1000) {
            val male = DiceSet.d4() % 2 == 0
            val age = ThreadLocalRandom.current().nextInt(DwarfRace.ageOfMaturity, DwarfRace.averageLifespan)

            val name = if(male) DwarfRace.maleNames[DiceSet.d100()]!! else DwarfRace.femaleNames[DiceSet.d100()]!!
            val clanName = DwarfRace.surnames[DiceSet.d100()]!!

            println(male)
            names.add("$name $clanName")
        }

        println(names.size)
        println(names.distinct().size)

        for (i in 0 until 10)
            println(MagicItemCategory.WONDROUS.gen())
    }

    @Suppress("UNCHECKED_CAST")
    fun familyTree() {
        val elfFather = Genotype(Genotype.HEIGHT, arrayOf(HeightGene.DOMINANT[0] to HeightGene.RECESSIVE[0], HeightGene.RECESSIVE[1] to HeightGene.DOMINANT[1], HeightGene.DOMINANT[2] to HeightGene.RECESSIVE[2]))
        val dwarfMother = Genotype(Genotype.HEIGHT, arrayOf(HeightGene.RECESSIVE[0] to HeightGene.RECESSIVE[0], HeightGene.RECESSIVE[1] to HeightGene.RECESSIVE[1], HeightGene.RECESSIVE[2] to HeightGene.RECESSIVE[2]))

        for (i in 0 until 0) {
            val child = elfFather mixWith dwarfMother
            val parentOptions = child.possibleParentGenes(HeightGene.DOMINANT.plus(HeightGene.RECESSIVE) as Array<Gene>, elfFather)

            println(parentOptions.mapIndexed { index, combos -> dwarfMother.alleles[index] in combos }.count(Boolean::not) == 0)
        }

        val child = Genotype(Genotype.RACE, arrayOf(RacialGene.MOUNTAIN_DWARF to RacialGene.MOUNTAIN_DWARF))
        val parentOptions = child.possibleParentGenes(RacialGene.ALL_RACES as Array<Gene>)

        println()
    }

    fun npc() {
        println(XGtECharacterBuilding.parents)
    }
}