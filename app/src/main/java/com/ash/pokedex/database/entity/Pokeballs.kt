package com.ash.pokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PokeBalls(
    @PrimaryKey(true)
    val id: Int,
    val name: String,
    val imageUrl: String,
    val renameCount: Int
)
