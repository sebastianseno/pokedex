package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Area(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)