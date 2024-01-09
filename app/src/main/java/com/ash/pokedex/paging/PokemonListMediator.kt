package com.ash.pokedex.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.services.PokemonServices
import retrofit2.HttpException
import java.io.IOException

const val NETWORK_PAGE_SIZE = 500
private const val INITIAL_LOAD_SIZE = 1

class PokemonListMediator(
    private val services: PokemonServices,

    ) : PagingSource<Int, PokemonResult>() {
    override fun getRefreshKey(state: PagingState<Int, PokemonResult>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(other = 1) ?: anchorPage?.nextKey?.minus(other = 1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonResult> {
        return try {
            val position = params.key ?: INITIAL_LOAD_SIZE
            val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE

            val response = services.getPokemonList(
                offset = offset,
                limit = 10
            )
            val nextKey = if (response.next == null) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response.pokemonResults,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (error: IOException) {
            return LoadResult.Error(error)
        } catch (error: HttpException) {
            return LoadResult.Error(error)
        }
    }
}