package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Genera(
    @SerializedName("genus")
    val genus: String? = "",
    @SerializedName("language")
    val language: Language? = Language()
)