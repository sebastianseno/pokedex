package com.ash.pokedex.extension

import kotlin.random.Random

fun isSuccessCatch(): Boolean {
    return Random.nextFloat() < 0.5
}