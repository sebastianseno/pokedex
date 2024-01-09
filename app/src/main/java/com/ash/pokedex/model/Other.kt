package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Other(
    @SerializedName("dream_world")
    val dreamWorld: DreamWorld? = null,
    @SerializedName("home")
    val home: Home? = null,
    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork? = null,
    @SerializedName("showdown")
    val showdown: Showdown? = null
)