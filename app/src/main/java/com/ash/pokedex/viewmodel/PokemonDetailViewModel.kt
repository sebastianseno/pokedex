package com.ash.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ash.pokedex.globalstate.UiState
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.presentation.screens.detail.PokemonDetailState
import com.ash.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {


    private val _pokemonDetailState = MutableSharedFlow<PokemonDetailState>()
    val pokemonDetailState = _pokemonDetailState.asSharedFlow()

    fun getPokemonDetail(name: String) {
        pokemonRepository.getPokemonDetail(name).onEach { result ->
                when (result) {
                    is UiState.Loading -> {
                        _pokemonDetailState.emit(PokemonDetailState(isLoading = true))
                    }

                    is UiState.Success -> {
                        _pokemonDetailState.emit(PokemonDetailState(data = result.data))

                    }

                    is UiState.Error -> {
                        _pokemonDetailState.emit(PokemonDetailState(error = result.errorMessage.orEmpty()))

                    }
                }
            }.launchIn(viewModelScope)

    }
}