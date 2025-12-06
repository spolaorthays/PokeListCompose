package com.example.pokekotlinclass.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokekotlinclass.commons.constants.PokeStrings
import com.example.pokekotlinclass.domain.entities.PokeIdentificationEntity
import com.example.pokekotlinclass.domain.entities.PokeInitialEntity
import com.example.pokekotlinclass.domain.interactors.PokeInteractor
import com.example.pokekotlinclass.presentation.viewmodel.states.PokeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeViewModel @Inject constructor(private val interactor: PokeInteractor) : ViewModel() {

    val pokeState = MutableLiveData<PokeState>()
    val saveResults = MutableLiveData<PokeInitialEntity>()

    fun getInitialInformation() {
        viewModelScope.launch {
            try {
                pokeState.value = PokeState.Loading
                val result = interactor.getInitialInformation(offset = 0, limit = 1400)
                saveResults.value = result
                pokeState.value = PokeState.Success(entity = result, result.results)
            } catch (_: Exception) {
                pokeState.value = PokeState.Error(message = PokeStrings.listErrorMessage)
            }
        }
    }

    fun searchPokemonInCurrentList(
        pokemonName: String,
        currentList: List<PokeIdentificationEntity>
    ): String? {
        currentList.forEach { (pokeName, _) ->
            if (pokeName == pokemonName) {
                pokeState.value = PokeState.SearchSuccess(name = pokeName)
                return pokeName
            }
        }
        pokeState.value = PokeState.SearchError(message = PokeStrings.searchErrorMessage)
        return null
    }

    fun getPokemonDetails(pokemonName: String) {
        viewModelScope.launch {
            try {
                pokeState.value = PokeState.Loading
                val result = interactor.getPokemonDetails(pokemonName)
                pokeState.value = PokeState.DetailsSuccess(entity = result)
            } catch (_: Exception) {
                pokeState.value = PokeState.DetailsError(message = PokeStrings.detailsErrorMessage)
            }
        }
    }
}