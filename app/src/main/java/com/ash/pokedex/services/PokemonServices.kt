package com.ash.pokedex.services

import com.ash.pokedex.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonServices {

    @GET("v2/account/mobile/field-office/login")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int
        ): PokemonListResponse
}