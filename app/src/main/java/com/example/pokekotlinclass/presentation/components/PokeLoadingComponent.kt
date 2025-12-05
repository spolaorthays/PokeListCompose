package com.example.pokekotlinclass.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pokekotlinclass.presentation.theme.AnotherBlue
import com.example.pokekotlinclass.presentation.theme.DarkBlue

@Composable
fun LoadingComponent( modifier: Modifier = Modifier, text: String?) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = DarkBlue,
            trackColor = AnotherBlue
        )
        if (text != null) {
            Text(
                text = text,
                modifier = Modifier.padding(top = 50.dp)
            )
        }
    }
}