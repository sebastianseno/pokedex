package com.ash.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ash.pokedex.globalstate.UiState
import com.ash.pokedex.model.PokemonDetailResponse
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.paging.PokemonListMediator
import com.ash.pokedex.services.PokemonServices
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val services: PokemonServices,

) {
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

    fun getPokemonDetail(name:String): Flow<UiState<PokemonDetailResponse>> {
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
}