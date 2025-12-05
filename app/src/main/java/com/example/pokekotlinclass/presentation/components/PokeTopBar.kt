package com.example.pokekotlinclass.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.pokekotlinclass.presentation.theme.Blue
import com.example.pokekotlinclass.presentation.theme.LightBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(content: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blue,
                    titleContentColor = LightBlue,
                ),
                title = {
                    Text(
                        text = "Poke List",
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        },
    ) { innerPadding ->
        content(innerPadding)
    }
}