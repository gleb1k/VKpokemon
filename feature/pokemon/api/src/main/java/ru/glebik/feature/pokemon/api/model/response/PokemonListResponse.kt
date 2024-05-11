package ru.glebik.feature.pokemon.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    @SerialName("count")
    val count: Int?,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val pokemonListResults: List<PokemonListResult?>?
)

@Serializable
data class PokemonListResult(
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String
)