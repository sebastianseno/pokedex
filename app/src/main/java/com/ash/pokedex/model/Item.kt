package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Item(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null
)