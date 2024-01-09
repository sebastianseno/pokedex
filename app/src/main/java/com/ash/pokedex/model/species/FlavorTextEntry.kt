package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class FlavorTextEntry(
    @SerializedName("flavor_text")
    val flavorText: String? = null,
    @SerializedName("language")
    val language: Language? = null,
    @SerializedName("version")
    val version: Version? = null
)