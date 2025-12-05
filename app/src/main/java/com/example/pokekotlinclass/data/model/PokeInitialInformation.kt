package com.example.pokekotlinclass.data.model

import com.example.pokekotlinclass.domain.entities.PokeIdentificationEntity
import com.example.pokekotlinclass.domain.entities.PokeInitialEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeInitialInformation(
    @field:Json(name = "count")
    val count: Int,
    @field:Json(name = "next")
    val next: String?,
    @field:Json(name = "previous")
    val previous: String?,
    @field:Json(name = "results")
    val results: List<PokeIdentification>
) {
    fun toDomain(): PokeInitialEntity {
        return PokeInitialEntity(
            count = count,
            next = next,
            previous = previous,
            results = results.map { identification ->  identification.toDomain()}
        )
    }
}

@JsonClass(generateAdapter = true)
data class PokeIdentification(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String
) {
    fun toDomain(): PokeIdentificationEntity {
        return PokeIdentificationEntity(
            name = name,
            url = url,
        )
    }
}
