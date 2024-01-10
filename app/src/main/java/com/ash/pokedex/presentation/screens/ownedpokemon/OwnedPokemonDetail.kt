package com.ash.pokedex.presentation.screens.ownedpokemon

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ash.pokedex.R
import com.ash.pokedex.extension.isPrime
import com.ash.pokedex.presentation.components.snackbar.SnackBarComponent
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar
import com.ash.pokedex.utils.PokemonRenamer
import com.ash.pokedex.viewmodel.OwnedPokemonViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OwnedPokemonDetail(
    viewModel: OwnedPokemonViewModel = hiltViewModel(),
    id: Int,
    onBack: () -> Unit = {}
) {
    val renamer = PokemonRenamer()
    val snackBarHostState = remember { mutableStateOf(SnackbarHostState()) }
    val pokemonDetailState by viewModel.getOwnedPokemon(id).collectAsState(initial = null)
    val scope = rememberCoroutineScope()

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

    val sheetState =
        rememberBottomSheetScaffoldState(
            bottomSheetState =
            SheetState(
                initialValue = SheetValue.Hidden,
                skipPartiallyExpanded = false
            ),
        )

    val keyword: MutableState<String> = remember { mutableStateOf("") }
    val renamePokemon: MutableState<String> = remember { mutableStateOf("") }
    val renameCountNumber: MutableState<Int> = remember { mutableIntStateOf(0) }
    val ownedPokemon = viewModel.ownedPokemon.collectAsState()

    fun collapseSheet() {
        scope.launch {
            sheetState.bottomSheetState.hide()
        }
    }

    fun openSheet() {
        scope.launch {
            sheetState.bottomSheetState.expand()
        }
    }
    
    LaunchedEffect(key1 = ownedPokemon.value, block = {
        if (ownedPokemon.value == ReleasePokemonState.Success){
            onBack()
        }
    })

    BackHandler(sheetState.bottomSheetState.isVisible) {
        collapseSheet()
    }

    BottomSheetScaffold(
        scaffoldState = sheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = keyword.value,
                    onValueChange = {
                        keyword.value = it
                    })
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = {
                    renamePokemon.value = renamer.renamePokemon(
                        keyword.value,
                        renameCount = pokemonDetailState?.renameCount ?: 0
                    )
                    renameCountNumber.value += 1
                    viewModel.updatePokemonData(
                        pokemonDetailState?.id ?: 0,
                        renamePokemon.value,
                        renameCountNumber.value
                    )
                    collapseSheet()
                }) {
                    Text(text = "Rename")
                }
            }

        }
    ) {
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
                        openSheet()
                    }) {
                        Text(text = "Rename Pokemon")
                    }
                    OutlinedButton(onClick = {
                        if (isPrime()) {
                            viewModel.deleteOwnedPokemonById(id)
                            scope.launch {
                                snackBarHostState.value.showSnackbar(
                                    "Success to Release Pokemon!"
                                )
                            }
                        } else {
                            scope.launch {
                                snackBarHostState.value.showSnackbar(
                                    "Failed to Release Pokemon"
                                )
                            }
                        }
                    }) {
                        Text(text = "Release Pokemon")
                    }
                }
            }) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = it.calculateTopPadding(), horizontal = 20.dp),
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
}