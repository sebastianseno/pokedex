package com.ash.pokedex.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ash.pokedex.presentation.components.cards.PokemonCards
import com.ash.pokedex.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val state = viewModel.pokemonListState.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(
            items = state.itemSnapshotList,
            key = { index, key ->
                (key?.name + index.toString())
            },
        ) { index, data ->
            PokemonCards()
        }
    }

}