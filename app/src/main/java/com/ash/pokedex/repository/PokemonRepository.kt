package com.ash.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ash.pokedex.database.PokeBallsDb
import com.ash.pokedex.database.entity.PokeBalls
import com.ash.pokedex.globalstate.UiState
import com.ash.pokedex.model.PokemonDetailResponse
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.model.species.PokemonSpeciesResponse
import com.ash.pokedex.paging.PokemonListMediator
import com.ash.pokedex.services.PokemonServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val services: PokemonServices,
    database: PokeBallsDb

) {
    private val dao = database.pokeBallDao()

    fun getPokemonListMediator(): Flow<PagingData<PokemonResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                PokemonListMediator(
                    services,
                )
            },
        ).flow
    }

    fun getPokemonDetail(name: String): Flow<UiState<PokemonDetailResponse>> {
        return flow {
            emit(UiState.Loading())
            runCatching {
                services.getPokemonDetail(name)
            }.onSuccess {
                emit(UiState.Success(it))
            }.onFailure {
                emit(UiState.Error(message = it.message ?: "Something went wrong"))
            }
        }
    }

    fun getPokemonSpecies(id: String): Flow<UiState<PokemonSpeciesResponse>> {
        return flow {
            emit(UiState.Loading())
            runCatching {
                services.getPokemonSpecies(id)
            }.onSuccess {
                emit(UiState.Success(it))
            }.onFailure {
                emit(UiState.Error(message = it.message ?: "Something went wrong"))
            }
        }
    }

    suspend fun insertNewPokemon(
        pokemonName: String,
        pokemonUrl: String,
    ) = withContext(Dispatchers.IO) {
        dao.insertPokemon(
            PokeBalls(
                0,
                pokemonName,
                pokemonUrl,
                0
            )
        )
    }

    suspend fun updatePokemonData(
        id: Int,
        pokemonName: String,
        renameCounter: Int,
    ) = withContext(Dispatchers.IO) {
        dao.updatePokemonData(
            id,
            pokemonName,
            renameCounter

        )
    }

    fun getOwnedPokemon(): Flow<List<PokeBalls>> {
        return dao.getOwnedPokemon()
    }

    fun getOwnedPokemon(id: Int): Flow<PokeBalls> {
        return dao.getOwnedPokemon(id)
    }
    suspend fun deleteOwnedPokemon(id: Int) = withContext(Dispatchers.IO) {
        dao.deleteOwnedPokemon(id)
    }
}