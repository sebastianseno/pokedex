package com.ash.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ash.pokedex.globalstate.UiState
import com.ash.pokedex.presentation.screens.detail.PokemonDetailState
import com.ash.pokedex.presentation.screens.detail.PokemonSpeciesState
import com.ash.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {


    private val _pokemonDetailState = MutableSharedFlow<PokemonDetailState>()
    val pokemonDetailState = _pokemonDetailState.asSharedFlow()

    private val _pokemonSpeciesState = MutableSharedFlow<PokemonSpeciesState>()
    val pokemonSpeciesState = _pokemonSpeciesState.asSharedFlow()

    fun getPokemonDetail(name: String) {
        pokemonRepository.getPokemonDetail(name).onEach { result ->
            when (result) {
                is UiState.Loading -> {
                    _pokemonDetailState.emit(PokemonDetailState(isLoading = true))
                }

                is UiState.Success -> {
                    _pokemonDetailState.emit(PokemonDetailState(data = result.data))
                    getPokemonSpecies((result.data?.id).toString() ?: "1")
                }

                is UiState.Error -> {
                    _pokemonDetailState.emit(PokemonDetailState(error = result.errorMessage.orEmpty()))

                }
            }
        }.launchIn(viewModelScope)

    }

    private fun getPokemonSpecies(id: String) {
        pokemonRepository.getPokemonSpecies(id).onEach { result ->
            when (result) {
                is UiState.Loading -> {
                    _pokemonSpeciesState.emit(PokemonSpeciesState(isLoading = true))
                }

                is UiState.Success -> {
                    _pokemonSpeciesState.emit(PokemonSpeciesState(data = result.data))

                }

                is UiState.Error -> {
                    _pokemonSpeciesState.emit(PokemonSpeciesState(error = result.errorMessage.orEmpty()))

                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertNewPokemon(
        pokemonName: String,
        pokemonUrl: String
    ) {
        viewModelScope.launch {
            pokemonRepository.insertNewPokemon(pokemonName, pokemonUrl)
        }
    }


}