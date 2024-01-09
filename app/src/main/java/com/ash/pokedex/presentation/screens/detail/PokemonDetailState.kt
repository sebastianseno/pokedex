package com.ash.pokedex.presentation.screens.detail

import com.ash.pokedex.model.PokemonDetailResponse

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val data: PokemonDetailResponse? = PokemonDetailResponse(),
    val error: String = "",
)
