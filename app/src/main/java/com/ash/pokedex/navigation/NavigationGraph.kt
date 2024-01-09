package com.ash.pokedex.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ash.pokedex.paging.Screen
import com.ash.pokedex.presentation.screens.PokemonListScreen

fun NavGraphBuilder.addNavigationGraph(
    navController: NavController,
) {
    composable(Screen.PokemonList.route) { from ->
        PokemonListScreen() {

        }
    }
    composable(Screen.PokemonDetail.route) { from ->
        PokemonListScreen() {
            navController.popBackStack()
        }
    }
}
