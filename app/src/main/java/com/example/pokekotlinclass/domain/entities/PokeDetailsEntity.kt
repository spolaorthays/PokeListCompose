package com.example.pokekotlinclass.domain.entities

data class PokeDetailsEntity(
    val abilities: List<PokeAbilityEntity>,
    val baseExperience: Int,
    val forms: List<PokeFormEntity>,
    val height: Int,
    val name: String,
    val order: Int,
    val species: PokeSpeciesEntity,
    val sprites: PokeSpritesEntity,
    val stats: List<PokeStatEntity>,
    val types: List<PokeTypeEntity>,
    val weight: Int
)

data class PokeAbilityEntity(
    val name: String,
    val url: String,
    val isHidden: Boolean,
    val slot: Int
)

data class PokeFormEntity(
    val name: String,
    val url: String
)

data class PokeSpeciesEntity(
    val name: String,
    val url: String
)

data class PokeSpritesEntity(
    val frontDefault: String?,
    val frontShiny: String?
)

data class PokeStatEntity(
    val baseStat: Int?,
    val effort: Int,
    val name: String,
    val url: String
)

data class PokeTypeEntity(
    val slot: Int,
    val name: String,
    val url: String
)