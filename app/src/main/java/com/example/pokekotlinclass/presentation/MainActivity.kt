package com.example.pokekotlinclass.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pokekotlinclass.presentation.components.LoadingComponent
import com.example.pokekotlinclass.presentation.components.PokeDetails
import com.example.pokekotlinclass.presentation.components.PokeList
import com.example.pokekotlinclass.presentation.components.SimpleTopAppBar
import com.example.pokekotlinclass.presentation.theme.DarkBlue
import com.example.pokekotlinclass.presentation.theme.PokeKotlinClassTheme
import com.example.pokekotlinclass.presentation.theme.SkyBlue
import com.example.pokekotlinclass.presentation.viewmodel.PokeViewModel
import com.example.pokekotlinclass.presentation.viewmodel.states.PokeState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: PokeViewModel by viewModels()

    var uiState by mutableStateOf<PokeState>(PokeState.Loading)

    var pokemonFound by mutableStateOf<String?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeKotlinClassTheme {
                SimpleTopAppBar(content = { innerPadding ->
                    val defaultDefinitions = Modifier
                        .fillMaxSize()
                        .background(color = SkyBlue)
                        .padding(innerPadding)

                    when (val state = uiState) {
                        PokeState.Loading -> {
                            LoadingComponent(
                                modifier = defaultDefinitions,
                                text = null
                            )
                        }

                        is PokeState.Error -> {
                            Box(
                                modifier = defaultDefinitions,
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "Erro: ${state.message}", color = Color.Red)
                            }
                        }

                        is PokeState.Success -> {
                            PokeList(
                                modifier = defaultDefinitions,
                                results = state.results,
                                onClick = { text ->
                                    pokemonFound = viewModel.searchPokemonInCurrentList(
                                        text,
                                        state.results
                                    )
                                }
                            )
                        }

                        is PokeState.SearchSuccess -> {
                            LoadingComponent(
                                modifier = defaultDefinitions,
                                text = "Buscando detalhes de $pokemonFound..."
                            )
                            pokemonFound?.let { name -> viewModel.getPokemonDetails(name) }
                        }

                        is PokeState.SearchError -> {
                            Column(modifier = defaultDefinitions) {
                                Text(text = state.message)
                                Text(text = "Deseja listar mais pokemons?")
                            }
                        }

                        is PokeState.DetailsSuccess -> {
                            PokeDetails(
                                modifier = defaultDefinitions,
                                details = state.entity,
                                onClickGoBack = {
                                    viewModel.pokeState.value = PokeState.Success(
                                        viewModel.saveResults.value!!,
                                        viewModel.saveResults.value!!.results,
                                    )
                                }
                            )
                        }

                        is PokeState.DetailsError -> {
                            Column(modifier = defaultDefinitions) {
                                Text(text = state.message)
                            }
                        }
                    }
                })
            }
        }
        observes(viewModel)
        viewModel.getInitialInformation()
    }

    private fun observes(viewModel: PokeViewModel) {
        with(viewModel) {
            pokeState.observe(this@MainActivity) { state ->
                uiState = state
            }
        }
    }
}