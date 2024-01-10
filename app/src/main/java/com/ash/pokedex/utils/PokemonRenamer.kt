package com.ash.pokedex.utils

class PokemonRenamer {
    private val fibonacciSequence = generateFibonacciSequence()
    private fun generateFibonacciSequence(): Sequence<Int> = sequence {
        var a = 0
        var b = 1
        while (true) {
            yield(a)
            val temp = a
            a = b
            b += temp
        }
    }

    fun renamePokemon(originalName: String, renameCount: Int): String {
        val fibonacciNumber = fibonacciSequence.take(renameCount + 1).last()
        return "$originalName-$fibonacciNumber"
    }
}
