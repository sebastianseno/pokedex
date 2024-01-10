package com.ash.pokedex.navigation

sealed class Screen(val route: String) {
     object PokemonList : Screen("pokemon_list_screen")
     object PokemonDetail : Screen("pokemon_detail_screen")
     object OwnedPokemonList : Screen("pokemon_owned_screen")
     object OwnedPokemonDetail : Screen("pokemon_owned_detail_screen")

}
