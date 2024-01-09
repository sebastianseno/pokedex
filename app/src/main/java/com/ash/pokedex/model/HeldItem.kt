package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class HeldItem(
    @SerializedName("item")
    val item: Item? = Item(),
    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>? = listOf()
)