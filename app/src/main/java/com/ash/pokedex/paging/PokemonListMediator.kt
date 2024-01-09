package com.ash.pokedex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.services.PokemonServices
import retrofit2.HttpException
import java.io.IOException


class PokemonListMediator(
    private val services: PokemonServices
) : PagingSource<Int, PokemonResult>() {

    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        val pageIndex = params.key ?: 0
        return try {
            val response = services.getPokemonList(offset = pageIndex, limit = 10)
            val lastPage = response.next == null
            if (!lastPage) {
                LoadResult.Page(
                    data = response.pokemonResults,
                    prevKey = null,
                    nextKey = pageIndex + 10
                )
            } else {
                LoadResult.Page(
                    data = response.pokemonResults,
                    prevKey = null,
                    nextKey = null
                )
            }

        } catch (error: IOException) {
            return LoadResult.Error(error)
        } catch (error: HttpException) {
            return LoadResult.Error(error)
        }
    }
}
