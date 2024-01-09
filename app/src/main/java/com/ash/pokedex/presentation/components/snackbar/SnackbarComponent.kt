package com.ash.pokedex.presentation.components.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SnackBarComponent(
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState,
) {
    SnackbarHost(
        modifier = modifier,
        hostState = snackBarHostState,
    ) {
        ToastGreen(
            message = it.visuals.message
        )
    }
}


@Composable
@Preview("Green Toast")
fun ToastGreen(
    modifier: Modifier = Modifier,
    message: String = "Sample",
    elevation: Dp = 9.dp
) {
    Surface(
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 8.dp)
                .wrapContentWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.Filled.Info, contentDescription = null)
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = message)
        }
    }
}
