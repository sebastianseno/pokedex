package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class EvolutionChain(
    @SerializedName("url")
    val url: String? = null
)