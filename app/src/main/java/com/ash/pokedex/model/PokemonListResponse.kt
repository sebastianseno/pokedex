package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PokemonListResponse(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("next")
    val next: String? = null,
    @SerializedName("previous")
    val previous: String? = null,
    @SerializedName("results")
    val pokemonResults: List<PokemonResult> = emptyList()
)