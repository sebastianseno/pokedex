package com.ash.pokedex.presentation.components.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTitleTopBar(
    title: String = "",
    onBackClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        colors =
            TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
        title = { Text(text = title, color = Color.White) },
        navigationIcon = {
            Row(modifier = Modifier) {
                Spacer(modifier = Modifier.width(25.dp))
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                    modifier =
                        Modifier
                            .size(20.dp)
                            .clickable {
                                onBackClick()
                            },
                )
            }
        },
    )
}
