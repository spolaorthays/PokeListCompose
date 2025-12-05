package com.example.pokekotlinclass.data.model

import android.util.Log
import com.example.pokekotlinclass.domain.entities.PokeAbilityEntity
import com.example.pokekotlinclass.domain.entities.PokeDetailsEntity
import com.example.pokekotlinclass.domain.entities.PokeFormEntity
import com.example.pokekotlinclass.domain.entities.PokeSpeciesEntity
import com.example.pokekotlinclass.domain.entities.PokeSpritesEntity
import com.example.pokekotlinclass.domain.entities.PokeStatEntity
import com.example.pokekotlinclass.domain.entities.PokeTypeEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeDetailsResponse(
    @param:Json(name = "abilities") val abilities: List<AbilitySlot>,
    @param:Json(name = "base_experience") val baseExperience: Int?,
    @param:Json(name = "forms") val forms: List<Form>,
    @param:Json(name = "height") val height: Int,
    @param:Json(name = "name") val name: String,
    @param:Json(name = "order") val order: Int,
    @param:Json(name = "species") val species: Species,
    @param:Json(name = "sprites") val sprites: Sprites,
    @param:Json(name = "stats") val stats: List<StatSlot>,
    @param:Json(name = "types") val types: List<TypeSlot>,
    @param:Json(name = "weight") val weight: Int
) {
    fun toDomain(): PokeDetailsEntity {
        return PokeDetailsEntity(
            abilities = abilities.map { abilitySlot ->
                abilitySlot.toDomain()
            },
            baseExperience = baseExperience ?: 0,
            forms = forms.map { form -> form.toDomain() },
            height = height,
            name = name,
            order = order,
            species = species.toDomain(),
            sprites = sprites.toDomain(),
            stats = stats.map { statSlot -> statSlot.toDomain() },
            types = types.map { typeSlot -> typeSlot.toDomain() },
            weight = weight
        )
    }
}

@JsonClass(generateAdapter = true)
data class AbilitySlot(
    @param:Json(name = "ability") val ability: Ability,
    @param:Json(name = "is_hidden") val isHidden: Boolean?,
    @param:Json(name = "slot") val slot: Int
) {
    fun toDomain(): PokeAbilityEntity {
        return PokeAbilityEntity(
            name = ability.name,
            url = ability.url,
            isHidden = isHidden ?: false,
            slot = slot
        )
    }
}

@JsonClass(generateAdapter = true)
data class Ability(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class Form(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "url") val url: String
) {
    fun toDomain(): PokeFormEntity {
        return PokeFormEntity(
            name = name,
            url = url
        )
    }
}

@JsonClass(generateAdapter = true)
data class Species(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "url") val url: String
) {
    fun toDomain(): PokeSpeciesEntity {
        return PokeSpeciesEntity(
            name = name,
            url = url
        )
    }
}

@JsonClass(generateAdapter = true)
data class Sprites(
    @param:Json(name = "other") val other: OtherSprites
) {
    fun toDomain(): PokeSpritesEntity {
        return PokeSpritesEntity(
            frontDefault = other.officialArtwork?.frontDefault,
            frontShiny = other.officialArtwork?.frontShiny
        )
    }
}

@JsonClass(generateAdapter = true)
data class OtherSprites(
    @param:Json(name = "official-artwork") val officialArtwork: OfficialArtwork?
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @param:Json(name = "front_default") val frontDefault: String,
    @param:Json(name = "front_shiny") val frontShiny: String
)

@JsonClass(generateAdapter = true)
data class StatSlot(
    @param:Json(name = "base_stat") val baseStat: Int?,
    @param:Json(name = "effort") val effort: Int,
    @param:Json(name = "stat") val stat: Stat
) {
    fun toDomain(): PokeStatEntity {
        return PokeStatEntity(
            baseStat = baseStat,
            effort = effort,
            name = stat.name,
            url = stat.url,
        )
    }
}

@JsonClass(generateAdapter = true)
data class Stat(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class TypeSlot(
    @param:Json(name = "slot") val slot: Int,
    @param:Json(name = "type") val type: Type
) {
    fun toDomain(): PokeTypeEntity {
        return PokeTypeEntity(
            slot = slot,
            name = type.name,
            url = type.url
        )
    }
}

@JsonClass(generateAdapter = true)
data class Type(
    @param:Json(name = "name") val name: String,
    @param:Json(name = "url") val url: String
)