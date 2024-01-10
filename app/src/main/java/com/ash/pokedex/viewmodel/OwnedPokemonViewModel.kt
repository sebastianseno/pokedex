package com.ash.pokedex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ash.pokedex.database.entity.PokeBalls
import com.ash.pokedex.presentation.screens.ownedpokemon.ReleasePokemonState
import com.ash.pokedex.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class OwnedPokemonViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {
     val ownedPokemon = MutableStateFlow<ReleasePokemonState>(ReleasePokemonState.StandBy)

    val getOwnedPokemon: Flow<List<PokeBalls>>
        get() = pokemonRepository.getOwnedPokemon()
    fun getOwnedPokemon(id: Int): Flow<PokeBalls> {
        return pokemonRepository.getOwnedPokemon(id)
    }
    fun updatePokemonData(
        id: Int,
        pokemonName: String,
        renameCounter: Int,
    ) {
        viewModelScope.launch {
            pokemonRepository.updatePokemonData(id, pokemonName, renameCounter)
        }
    }
     fun deleteOwnedPokemonById(id: Int) {
        viewModelScope.launch {
            ownedPokemon.value = ReleasePokemonState.Loading
            pokemonRepository.deleteOwnedPokemon(id)
            delay(2.seconds)
            ownedPokemon.value = ReleasePokemonState.Success
        }
    }
}