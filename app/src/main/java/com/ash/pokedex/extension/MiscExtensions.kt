package com.ash.pokedex.extension

import kotlin.math.sqrt
import kotlin.random.Random

fun isSuccessCatch(): Boolean {
    return Random.nextFloat() < 0.5
}

fun isPrime(): Boolean {
    val num = Random.nextInt()
    if (num < 2) {
        return false
    }
    for (i in 2 until sqrt(num.toDouble()).toInt() + 1) {
        if (num % i == 0) {
            return false
        }
    }
    return true
}
