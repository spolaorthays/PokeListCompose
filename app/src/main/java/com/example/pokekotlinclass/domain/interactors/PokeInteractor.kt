package com.example.pokekotlinclass.domain.interactors

import com.example.pokekotlinclass.data.repository.PokeRepository
import com.example.pokekotlinclass.domain.entities.PokeDetailsEntity
import com.example.pokekotlinclass.domain.entities.PokeInitialEntity
import javax.inject.Inject

interface PokeInteractor {
    suspend fun getInitialInformation(offset: Int, limit: Int): PokeInitialEntity
    suspend fun getPokemonDetails(pokemonName: String): PokeDetailsEntity
}

class PokeInteractorImpl @Inject constructor(private val repository: PokeRepository) :
    PokeInteractor {
    override suspend fun getInitialInformation(offset: Int, limit: Int): PokeInitialEntity {
        return repository.getInitialInformation(offset, limit).toDomain()
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokeDetailsEntity {
        return repository.getPokemonDetails(pokemonName.lowercase()).toDomain()
    }
}