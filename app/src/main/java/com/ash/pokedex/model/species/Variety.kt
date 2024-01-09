package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Variety(
    @SerializedName("is_default")
    val isDefault: Boolean? = null,
    @SerializedName("pokemon")
    val pokemon: Pokemon? = null
)