package com.ash.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ash.pokedex.database.entity.PokeBalls
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeBallsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemon(obj: PokeBalls)
    @Query("SELECT * from PokeBalls")
    fun getOwnedPokemon(): Flow<List<PokeBalls>>
    @Query("SELECT * FROM PokeBalls WHERE id = :id")
    fun getOwnedPokemon(id: Int): Flow<PokeBalls>

}