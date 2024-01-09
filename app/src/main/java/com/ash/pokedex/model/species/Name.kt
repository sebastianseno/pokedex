package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Name(
    @SerializedName("language")
    val language: Language? = Language(),
    @SerializedName("name")
    val name: String? = ""
)