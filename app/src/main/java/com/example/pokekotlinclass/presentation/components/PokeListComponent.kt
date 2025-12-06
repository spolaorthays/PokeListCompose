package com.example.pokekotlinclass.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokekotlinclass.commons.toUpperFirstChar
import com.example.pokekotlinclass.domain.entities.PokeIdentificationEntity
import com.example.pokekotlinclass.presentation.theme.Blue
import com.example.pokekotlinclass.presentation.theme.LightBlue
import com.example.pokekotlinclass.presentation.theme.LightSkyBlue
import com.example.pokekotlinclass.presentation.theme.NavyBlue
import com.example.pokekotlinclass.presentation.theme.PokeKotlinClassTheme

@Composable
fun PokeList(
    modifier: Modifier = Modifier,
    results: List<PokeIdentificationEntity>,
    onClick: (text: String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    Column(modifier = modifier.padding(top = 16.dp)) {
        SearchComponent(
            text = text,
            onTextChange = { newText -> text = newText },
            onClick = onClick
        )
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
            items(results) { pokemon ->
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(),
                    onClick = { onClick(pokemon.name) },
                    colors = CardDefaults.cardColors(
                        containerColor = Blue
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally),
                        text = pokemon.name.toUpperFirstChar(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = LightBlue
                    )
                }
            }
        }
    }
}

@Composable
private fun SearchComponent(
    text: String,
    onTextChange: (String) -> Unit,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightSkyBlue,
                unfocusedContainerColor = LightBlue,
                errorContainerColor = Color.Red,
                focusedTextColor = Color.Black,
            ),
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = NavyBlue),
            onClick = { onClick(text) }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search action button",
                tint = LightSkyBlue
            )
        }
    }
}

@Composable
fun ButtonBack(modifier: Modifier = Modifier, onClickGoBack: () -> Unit) {
    Button(
        modifier = modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp),
        colors = ButtonDefaults.buttonColors(NavyBlue),
        onClick = { onClickGoBack() }) {
        Text(text = "Voltar para listagem", fontSize = 16.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun PokeListPreview() {
    PokeKotlinClassTheme {
        PokeList(
            results = listOf(PokeIdentificationEntity(name = "Pokemon", url = "uma url")),
            onClick = {}
        )
    }
}