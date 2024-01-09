package com.ash.pokedex.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenerationViii(
    @SerializedName("icons")
    val icons: Icons = Icons()
)