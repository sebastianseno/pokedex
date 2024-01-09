package com.ash.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ash.pokedex.database.entity.PokeBalls

@Dao
interface PokeBallsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(obj: PokeBalls)

}