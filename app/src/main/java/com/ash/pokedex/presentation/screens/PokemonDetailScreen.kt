package com.ash.pokedex.presentation.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ash.pokedex.R
import com.ash.pokedex.presentation.components.topbar.CenterTitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    onBack: () -> Unit
) {
    Scaffold(topBar = {
        CenterTitleTopBar(pokemonName) {
            onBack()
        }
    }) {
        Column(Modifier.padding(it)) {
            Spacer(modifier = Modifier.height(24.dp))
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
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