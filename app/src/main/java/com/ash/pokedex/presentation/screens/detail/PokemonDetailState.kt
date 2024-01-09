package com.ash.pokedex.presentation.screens.detail

import com.ash.pokedex.model.PokemonDetailResponse
import com.ash.pokedex.model.species.PokemonSpeciesResponse

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val data: PokemonDetailResponse? = PokemonDetailResponse(),
    val error: String = "",
)

data class PokemonSpeciesState(
    val isLoading: Boolean = false,
    val data: PokemonSpeciesResponse? = PokemonSpeciesResponse(),
    val error: String = "",
)
