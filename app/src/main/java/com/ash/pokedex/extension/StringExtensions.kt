package com.ash.pokedex.extension

fun String.replaceNewlineWithSpace(): String {
    return this.replace("\n", " ")
}