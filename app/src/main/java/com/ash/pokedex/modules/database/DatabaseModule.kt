package com.ash.pokedex.modules.database

import android.content.Context
import androidx.room.Room
import com.ash.pokedex.database.PokeBallsDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
	@Provides
	@Singleton
	fun providesRoom(@ApplicationContext context: Context): PokeBallsDb {
		return Room.databaseBuilder(context, PokeBallsDb::class.java, "PokeBalls.db")
			.fallbackToDestructiveMigration()
			.build()
	}
}