package ru.glebik.feature.pokemon.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall

@Serializable
data class PokemonSpritesResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("sprites")
    val spritesDetail: Sprites?
) {
    fun toPokemonSmall() : PokemonSmall = PokemonSmall(
        id, name,
        backDefaultImage = spritesDetail?.backDefault,
        backShinyImage = spritesDetail?.backShiny,
        frontDefaultImage = spritesDetail?.frontDefault,
        frontShinyImage = spritesDetail?.frontShiny,
    )
}

@Serializable
data class Sprites(
    @SerialName("back_default")
    val backDefault: String?,
    @SerialName("back_shiny")
    val backShiny: String?,
    @SerialName("front_default")
    val frontDefault: String?,
    @SerialName("front_shiny")
    val frontShiny: String?,
)
