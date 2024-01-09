package com.ash.pokedex.presentation.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ash.pokedex.R
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar
import com.ash.pokedex.viewmodel.PokemonDetailViewModel
import com.ash.pokedex.viewmodel.PokemonListViewModel

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
    val state by viewModel.pokemonDetailState.collectAsState(initial = PokemonDetailState())
    val painter =
        rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(LocalContext.current)
                .data(state.data?.sprites?.frontDefault)
                .crossfade(false)
                .placeholder(drawableResId = R.drawable.ic_launcher_foreground)
                .build(),
            contentScale = ContentScale.Crop,
        )

    Scaffold(topBar = {
        CenterTitleTopBar(pokemonName) {
            onBack()
        }
    }) {
        Column(Modifier.padding(it), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier.size(200.dp),
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(24.dp))
            ElevatedSuggestionChip(onClick = { }, label = { Text(text = "") })
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "About", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(Modifier.height(48.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
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
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_weight),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "6.5", fontWeight = FontWeight.Bold)
                    }
                    Text(text = "Weight", fontWeight = FontWeight.Thin)
                }
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                    Row(modifier = Modifier) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_weight),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "6.5", fontWeight = FontWeight.Bold)
                    }
                    Text(text = "Weight", fontWeight = FontWeight.Thin)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Weight", fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}