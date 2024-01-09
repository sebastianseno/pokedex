package com.ash.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ash.pokedex.model.PokemonResult
import com.ash.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    private val _pokemonListState = Channel<PagingData<PokemonResult>>()
    val pokemonListState = _pokemonListState.receiveAsFlow()

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonRepository.getPokemonListMediator(
            ).cachedIn(viewModelScope).collect {
                _pokemonListState.send(it)
            }
        }
    }
}