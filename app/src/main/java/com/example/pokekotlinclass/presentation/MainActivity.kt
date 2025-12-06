package com.example.pokekotlinclass.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.pokekotlinclass.presentation.components.ErrorComponent
import com.example.pokekotlinclass.presentation.components.LoadingComponent
import com.example.pokekotlinclass.presentation.components.PokeDetails
import com.example.pokekotlinclass.presentation.components.PokeList
import com.example.pokekotlinclass.presentation.components.SimpleTopAppBar
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

                        is PokeState.Error -> {
                            ErrorComponent(
                                modifier = defaultDefinitions,
                                text = state.message,
                                onClickGoBack = { shouldShowList() }
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
                            ErrorComponent(
                                modifier = defaultDefinitions,
                                text = state.message,
                                onClickGoBack = { shouldShowList() }
                            )
                        }

                        is PokeState.DetailsSuccess -> {
                            PokeDetails(
                                modifier = defaultDefinitions,
                                details = state.entity,
                                onClickGoBack = {
                                    shouldShowList()
                                }
                            )
                        }

                        is PokeState.DetailsError -> {
                            ErrorComponent(
                                modifier = defaultDefinitions,
                                text = state.message,
                                onClickGoBack = { shouldShowList() }
                            )
                        }
                    }
                })
            }
        }
        observes()
        viewModel.getInitialInformation()
    }

    private fun observes() {
        with(viewModel) {
            pokeState.observe(this@MainActivity) { state ->
                uiState = state
            }
        }
    }

    private fun shouldShowList() {
        viewModel.pokeState.value = PokeState.Success(
            viewModel.saveResults.value!!,
            viewModel.saveResults.value!!.results,
        )
    }
}