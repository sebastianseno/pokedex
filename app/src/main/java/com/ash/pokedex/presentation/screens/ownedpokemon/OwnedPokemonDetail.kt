package com.ash.pokedex.presentation.screens.ownedpokemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ash.pokedex.R
import com.ash.pokedex.presentation.components.snackbar.SnackBarComponent
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar
import com.ash.pokedex.viewmodel.OwnedPokemonListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnedPokemonDetail(
    viewModel: OwnedPokemonListViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit = {}
) {
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }
    val pokemonDetailState by viewModel.getOwnedPokemon(id).collectAsState(initial = null)

    val painter =
        rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(LocalContext.current)
                .data(pokemonDetailState?.imageUrl)
                .crossfade(false)
                .placeholder(drawableResId = R.drawable.ic_launcher_foreground)
                .build(),
            contentScale = ContentScale.Crop,
        )
    Scaffold(
        snackbarHost = {
            SnackBarComponent(
                snackBarHostState = snackBarHostState.value,
            )
        },
        topBar = {
            CenterTitleTopBar("Pokemon Detail") {
                onBack()
            }
        }, bottomBar = {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {


                }) {
                    Text(text = "Rename Pokemon")
                }
                Button(onClick = {


                }) {
                    Text(text = "Release Pokemon")
                }
            }
        }) {
        Column(
            Modifier.padding(vertical = it.calculateTopPadding(), horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier.size(200.dp),
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = pokemonDetailState?.name.orEmpty())


        }
    }
}