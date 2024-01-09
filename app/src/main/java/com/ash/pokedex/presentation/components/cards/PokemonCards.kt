package com.ash.pokedex.presentation.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.ash.pokedex.R

@Composable
fun PokemonCards(
    pokemonName: String,
    pokemonIndex : Int,
    onClick : () -> Unit = {}
) {
    val pokemonImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonIndex}.png"
    val painter =
        rememberAsyncImagePainter(
            model =
            ImageRequest.Builder(LocalContext.current)
                .data(pokemonImage)
                .crossfade(false)
                .placeholder(drawableResId = R.drawable.ic_launcher_foreground)
                .build(),
            contentScale = ContentScale.Crop,
        )
    Surface(
        modifier = Modifier
            .size(104.dp, 108.dp)
            .clickable {
                onClick()
        },
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "#$pokemonIndex")
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                painter = painter,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = pokemonName)
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}