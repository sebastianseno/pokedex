package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class VersionDetail(
    @SerializedName("rarity")
    val rarity: Int? = 0,
    @SerializedName("version")
    val version: Version? = Version()
)