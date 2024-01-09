package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PokedexNumber(
    @SerializedName("entry_number")
    val entryNumber: Int? = null,
    @SerializedName("pokedex")
    val pokedex: Pokedex? = null
)