package com.example.pokekotlinclass.domain.entities

data class PokeInitialEntity (
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokeIdentificationEntity>
)

data class PokeIdentificationEntity(
    val name: String,
    val url: String
)