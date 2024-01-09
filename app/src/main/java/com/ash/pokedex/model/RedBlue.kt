package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RedBlue(
    @SerializedName("back_default")
    val backDefault: String? = null,
    @SerializedName("back_gray")
    val backGray: String? = null,
    @SerializedName("back_transparent")
    val backTransparent: String? = null,
    @SerializedName("front_default")
    val frontDefault: String? = null,
    @SerializedName("front_gray")
    val frontGray: String? = null,
    @SerializedName("front_transparent")
    val frontTransparent: String? = null
)