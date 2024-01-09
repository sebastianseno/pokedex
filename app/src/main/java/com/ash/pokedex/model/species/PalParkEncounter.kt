package com.ash.pokedex.model.species


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PalParkEncounter(
    @SerializedName("area")
    val area: Area? = null,
    @SerializedName("base_score")
    val baseScore: Int? = null,
    @SerializedName("rate")
    val rate: Int? = null
)