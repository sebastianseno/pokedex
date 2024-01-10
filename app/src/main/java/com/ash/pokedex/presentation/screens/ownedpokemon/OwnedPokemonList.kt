package com.ash.pokedex.presentation.screens.ownedpokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ash.pokedex.presentation.components.cards.OwnedPokemonCards
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar
import com.ash.pokedex.viewmodel.OwnedPokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnedPokemonList(
    viewModel: OwnedPokemonListViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
    val ownedPokemon = viewModel.getOwnedPokemon.collectAsState(initial = null)
    Scaffold(
        topBar = {
            CenterTitleTopBar("Your Pokemon") {
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(vertical = padding.calculateTopPadding(), horizontal = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = {
                items(
                    items = ownedPokemon.value ?: emptyList(),
                    key = {
                        it.id
                    }) {
                    OwnedPokemonCards(
                        id = it.id,
                        pokemonName = it.name,
                        pokemonUrlImage = it.imageUrl
                    ) {
                        onClick(it.id)
                    }
                }
            })
    }
}