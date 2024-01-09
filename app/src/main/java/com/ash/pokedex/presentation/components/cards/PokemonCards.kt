package com.ash.pokedex.presentation.components.cards

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ash.pokedex.R

@Composable
fun PokemonCards() {
    Surface(
        modifier = Modifier.size(104.dp, 108.dp),
        shadowElevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Row {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "#001")
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Bulbasur")
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}