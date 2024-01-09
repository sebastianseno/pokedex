package com.ash.pokedex.services

import com.ash.pokedex.model.PokemonDetailResponse
import com.ash.pokedex.model.PokemonListResponse
import com.ash.pokedex.model.species.PokemonSpeciesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonServices {

    @GET("v2/pokemon")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int
        ): PokemonListResponse

    @GET("v2/pokemon/{pokemonName}")
    suspend fun getPokemonDetail(
        @Path("pokemonName") pokemon: String,
    ): PokemonDetailResponse

    @GET("v2/pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: String,
    ): PokemonSpeciesResponse
}