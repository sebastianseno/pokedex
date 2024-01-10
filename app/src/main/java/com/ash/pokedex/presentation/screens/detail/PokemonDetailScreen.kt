package com.ash.pokedex.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ash.pokedex.R
import com.ash.pokedex.extension.isSuccessCatch
import com.ash.pokedex.extension.replaceNewlineWithSpace
import com.ash.pokedex.presentation.components.snackbar.SnackBarComponent
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar
import com.ash.pokedex.viewmodel.PokemonDetailViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    viewModel: PokemonDetailViewModel = hiltViewModel(),
    pokemonName: String,
    onBack: () -> Unit
) {
    LaunchedEffect(key1 = Unit, block = {
        viewModel.getPokemonDetail(pokemonName)
    })
    val scope = rememberCoroutineScope()
    val state by viewModel.pokemonDetailState.collectAsState(initial = PokemonDetailState())
    val speciesState by viewModel.pokemonSpeciesState.collectAsState(initial = PokemonSpeciesState())
    val painter =
        rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(LocalContext.current)
                .data(state.data?.sprites?.other?.officialArtwork?.frontDefault)
                .crossfade(false)
                .placeholder(drawableResId = R.drawable.ic_launcher_foreground)
                .build(),
            contentScale = ContentScale.Crop,
        )
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }

    Scaffold(
        snackbarHost = {
            SnackBarComponent(
                snackBarHostState = snackBarHostState.value,
            )
        },
        topBar = {
            CenterTitleTopBar(pokemonName) {
                onBack()
            }
        }, bottomBar = {
            Box(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    if (isSuccessCatch()) {
                        scope.launch {
                            snackBarHostState.value.showSnackbar(
                                "Gotcha!"
                            )
                            viewModel.insertNewPokemon(
                                pokemonName,
                                state.data?.sprites?.other?.officialArtwork?.frontDefault.orEmpty()
                            )
                        }
                    } else {
                        scope.launch {
                            snackBarHostState.value.showSnackbar(
                                "The $pokemonName break free!"
                            )
                        }
                    }

                }) {
                    Text(text = "Catch Pokemon")
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
            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                state.data?.types?.forEach {
                    ElevatedSuggestionChip(
                        onClick = { },
                        label = { Text(text = it.type?.name.orEmpty()) })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "About", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.height(48.dp)) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_weight),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = state.data?.weight.toString(), fontWeight = FontWeight.Bold)
                    }
                    Text(text = "Weight", fontWeight = FontWeight.Thin)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_ruler),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = state.data?.height.toString(), fontWeight = FontWeight.Bold)
                    }
                    Text(text = "Height", fontWeight = FontWeight.Thin)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    if (!state.data?.abilities.isNullOrEmpty()) {
                        Text(
                            text = state.data?.abilities?.get(0)?.ability?.name.toString(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(text = "Moves", fontWeight = FontWeight.Thin)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            if (!speciesState.data?.flavorTextEntries.isNullOrEmpty()) {
                Text(
                    text = speciesState.data?.flavorTextEntries?.get(0)?.flavorText?.replaceNewlineWithSpace()
                        ?: "",
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}