package com.ash.pokedex.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ash.pokedex.presentation.components.cards.PokemonCards
import com.ash.pokedex.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {
    val state = viewModel.pokemonListState.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit, block = {
        if (state.itemCount == 0) {
            viewModel.getPokemonList()
        }
    })
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()) {
        items(state.itemCount) { index ->
            PokemonCards(
                pokemonName = state[index]?.name.orEmpty(),
                pokemonIndex = index + 1,
                onClick = { onClick(state[index]?.name.orEmpty()) }
            )
        }
    }
}