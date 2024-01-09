package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int? = null,
    @SerializedName("version")
    val version: Version? = null
)