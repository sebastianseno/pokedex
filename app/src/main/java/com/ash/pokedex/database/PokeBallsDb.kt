package com.ash.pokedex.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SimpleSQLiteQuery
import com.ash.pokedex.database.entity.PokeBalls

@Database(
    entities = [
        PokeBalls::class
    ],
    version = 1,
    exportSchema = false
)

abstract class PokeBallsDb : RoomDatabase() {

    fun clearAndResetAllTables() {
        // reset all auto-incrementalValues
        val query = SimpleSQLiteQuery("DELETE FROM sqlite_sequence")

        runInTransaction {
            clearAllTables()
            query(query)
        }
    }
}