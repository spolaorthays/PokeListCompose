package com.example.pokekotlinclass.data.repository

import com.example.pokekotlinclass.data.model.PokeDetailsResponse
import com.example.pokekotlinclass.data.model.PokeInitialInformation
import javax.inject.Inject

interface PokeRepository {
    suspend fun getInitialInformation(offset: Int, limit: Int): PokeInitialInformation
    suspend fun getPokemonDetails(pokemonName: String): PokeDetailsResponse
}


class PokeRepositoryImpl @Inject constructor(private val service: PokeService) : PokeRepository {

    override suspend fun getInitialInformation(offset: Int, limit: Int): PokeInitialInformation {
        return service.getInitialInformation(offset, limit)
    }

    override suspend fun getPokemonDetails(pokemonName: String): PokeDetailsResponse {
        return service.getPokemonDetails(pokemonName)
    }

}