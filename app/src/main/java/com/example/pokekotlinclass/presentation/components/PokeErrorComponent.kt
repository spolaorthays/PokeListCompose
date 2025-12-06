package com.example.pokekotlinclass.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokekotlinclass.presentation.theme.NavyBlue

@Composable
fun ErrorComponent(modifier: Modifier = Modifier, text: String, onClickGoBack: () -> Unit) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        Card(
            colors = CardDefaults.cardColors(NavyBlue)
        ) {
            Icon(imageVector = Icons.Filled.Close,
                contentDescription = "Error icon",
                tint = Color.Red
            )
        }
        Text(text = text, color = NavyBlue, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        ButtonBack(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClickGoBack = { onClickGoBack() }
        )
    }
}