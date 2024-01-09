package com.ash.pokedex.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ash.pokedex.presentation.screens.detail.PokemonDetailScreen
import com.ash.pokedex.presentation.screens.list.PokemonListScreen

fun NavGraphBuilder.addNavigationGraph(
    navController: NavController,
) {
    composable(Screen.PokemonList.route) { from ->
        PokemonListScreen { pokemonName ->
            navController.also {
                it.sendBundle("name", pokemonName)
            }.navigate(Screen.PokemonDetail.route)
        }
    }
    composable(Screen.PokemonDetail.route) { from ->
        val pokemonName = navController.receiveBundle<String>("name")

        PokemonDetailScreen(pokemonName = pokemonName.orEmpty()) {
            navController.popBackStack()
        }
    }
}
