package com.ash.pokedex.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ash.pokedex.presentation.screens.detail.PokemonDetailScreen
import com.ash.pokedex.presentation.screens.list.PokemonListScreen
import com.ash.pokedex.presentation.screens.ownedpokemon.OwnedPokemonDetail
import com.ash.pokedex.presentation.screens.ownedpokemon.OwnedPokemonList

fun NavGraphBuilder.addNavigationGraph(
    navController: NavController,
) {
    composable(Screen.PokemonList.route) { from ->
        PokemonListScreen(onOwnedPokemon = {
            navController.navigate(Screen.OwnedPokemonList.route)
        }) { pokemonName ->
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
    composable(Screen.OwnedPokemonList.route) { from ->
        OwnedPokemonList {
            navController.also { nav ->
                nav.sendBundle("id", it)
            }.navigate(Screen.OwnedPokemonDetail.route)
        }
    }
    composable(Screen.OwnedPokemonDetail.route) { from ->
        val pokemonId = navController.receiveBundle<Int>("id")
        OwnedPokemonDetail(id = pokemonId ?: 0) {
            navController.popBackStack()
        }
    }
}
