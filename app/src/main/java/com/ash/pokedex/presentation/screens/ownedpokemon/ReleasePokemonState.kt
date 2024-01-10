package com.ash.pokedex.presentation.screens.ownedpokemon


sealed class ReleasePokemonState {
     object StandBy : ReleasePokemonState()

     object Loading : ReleasePokemonState()

     object Success : ReleasePokemonState()
}
