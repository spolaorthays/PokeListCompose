package com.example.pokekotlinclass.data.repository

import com.example.pokekotlinclass.data.model.PokeDetailsResponse
import com.example.pokekotlinclass.data.model.PokeInitialInformation
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeService {
    @GET("pokemon")
    suspend fun getInitialInformation(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokeInitialInformation

    @GET("pokemon/{name}")
    suspend fun getPokemonDetails(@Path("name") name: String): PokeDetailsResponse
}