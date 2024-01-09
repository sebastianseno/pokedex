package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenerationIi(
    @SerializedName("crystal")
    val crystal: Crystal? = null,
    @SerializedName("gold")
    val gold: Gold? = null,
    @SerializedName("silver")
    val silver: Silver? = null
)