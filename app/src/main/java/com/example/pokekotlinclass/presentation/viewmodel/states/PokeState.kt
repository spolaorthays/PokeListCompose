package com.example.pokekotlinclass.presentation.viewmodel.states

import com.example.pokekotlinclass.domain.entities.PokeDetailsEntity
import com.example.pokekotlinclass.domain.entities.PokeIdentificationEntity
import com.example.pokekotlinclass.domain.entities.PokeInitialEntity

sealed class PokeState {
    data class Success(
        val entity: PokeInitialEntity,
        val results: List<PokeIdentificationEntity>
    ) : PokeState()
    data class SearchSuccess(val name: String) : PokeState()

    data object Loading : PokeState()
    data class Error(val message: String) : PokeState()
    data class SearchError(val message: String) : PokeState()

    data class DetailsSuccess(
        val entity: PokeDetailsEntity,
    ) : PokeState()

    data class DetailsError(val message: String) : PokeState()
}