package com.ash.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.paging.PokemonListMediator
import com.ash.pokedex.services.PokemonServices
import kotlinx.coroutines.flow.Flow
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

}