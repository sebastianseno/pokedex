package com.ash.pokedex.presentation.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ash.pokedex.R
import com.ash.pokedex.presentation.components.cards.PokemonCards
import com.ash.pokedex.viewmodel.PokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel = hiltViewModel(),
    onOwnedPokemon: () -> Unit,
    onClick: (String) -> Unit,
) {
    val state = viewModel.pokemonListState.collectAsLazyPagingItems()

    LaunchedEffect(key1 = Unit, block = {
        if (state.itemCount == 0) {
            viewModel.getPokemonList()
        }
    })
    Scaffold(modifier = Modifier, floatingActionButton = {
        FloatingActionButton(
            onClick = onOwnedPokemon::invoke,
            modifier = Modifier.size(80.dp),
            shape = RoundedCornerShape(100)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_monster_ball),
                contentDescription = null
            )
        }
    }) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            items(state.itemCount) { index ->
                PokemonCards(
                    pokemonName = state[index]?.name.orEmpty(),
                    pokemonIndex = index + 1,
                    onClick = { onClick(state[index]?.name.orEmpty()) }
                )
            }
        }
    }
}