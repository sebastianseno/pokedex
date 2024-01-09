package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue? = null,
    @SerializedName("yellow")
    val yellow: Yellow? = null
)