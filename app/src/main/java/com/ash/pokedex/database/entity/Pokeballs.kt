package com.ash.pokedex.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PokeBalls(
    @PrimaryKey
    val id: String,
    val name: String,
    val renameCount: Int
)
