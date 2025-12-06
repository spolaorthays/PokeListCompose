package com.example.pokekotlinclass.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.pokekotlinclass.commons.toUpperFirstChar
import com.example.pokekotlinclass.domain.entities.PokeAbilityEntity
import com.example.pokekotlinclass.domain.entities.PokeDetailsEntity
import com.example.pokekotlinclass.domain.entities.PokeTypeEntity
import com.example.pokekotlinclass.presentation.theme.DarkBlue
import com.example.pokekotlinclass.presentation.theme.MidnightBlue
import com.example.pokekotlinclass.presentation.theme.LightPetrolBlue
import com.example.pokekotlinclass.presentation.theme.LightSteelBlue

@Composable
fun PokeDetails(
    modifier: Modifier = Modifier,
    details: PokeDetailsEntity,
    onClickGoBack: () -> Unit
) {
    Column(modifier = modifier) {
        PokeImage(
            modifier = Modifier.fillMaxWidth(),
            frontShinyUrl = details.sprites.frontShiny,
            frontDefaultUrl = details.sprites.frontDefault
        )
        BasicInformation(
            name = details.name,
            height = details.height,
            weight = details.weight
        )
        AbilitiesItems(abilities = details.abilities)
        TypeItems(types = details.types)
        ButtonBack(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClickGoBack = onClickGoBack
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokeImage(
    modifier: Modifier,
    frontShinyUrl: String?,
    frontDefaultUrl: String?
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        if (frontShinyUrl != null) {
            GlideImage(model = frontShinyUrl, contentDescription = "Imagem do Pokemon")
        } else {
            GlideImage(
                model = frontDefaultUrl,
                contentDescription = "Imagem do Pokemon"
            )
        }
    }
}

@Composable
fun SectionLabel(
    label: String
) {
    Text(text = label, fontSize = 20.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun BasicInformation(
    modifier: Modifier = Modifier,
    name: String,
    height: Int,
    weight: Int,
) {
    Column(modifier = modifier.padding(16.dp)) {
        SectionLabel(label = "Informações básicas:")
        LabelValue(label = "Nome: ", value = name.toUpperFirstChar())
        LabelValue(label = "Altura: ", value = height.toString())
        LabelValue(label = "Peso: ", value = weight.toString())
    }
}

@Composable
fun LabelValue(
    modifier: Modifier = Modifier,
    label: String,
    value: String
) {
    Row(modifier = modifier) {
        Text(text = label, fontWeight = FontWeight.Bold)
        Text(text = value)
    }
}

@Composable
fun AbilitiesItems(
    modifier: Modifier = Modifier,
    abilities: List<PokeAbilityEntity>
) {
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)) {
        SectionLabel(label = "Habilidades:")
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
            items(abilities) { ability ->
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(),
                    colors = CardDefaults.cardColors(
                        containerColor = DarkBlue
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        text = ability.name.toUpperFirstChar(),
                        textAlign = TextAlign.Center,
                        color = LightPetrolBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TypeItems(
    modifier: Modifier = Modifier,
    types: List<PokeTypeEntity>
) {
    Column(modifier = modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp, top = 8.dp)) {
        SectionLabel(label = "Tipos:")
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 150.dp)) {
            items(types) { type ->
                Card(
                    modifier = Modifier.padding(16.dp),
                    elevation = CardDefaults.cardElevation(),
                    colors = CardDefaults.cardColors(
                        containerColor = MidnightBlue
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        text = type.name.toUpperFirstChar(),
                        textAlign = TextAlign.Center,
                        color = LightSteelBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}