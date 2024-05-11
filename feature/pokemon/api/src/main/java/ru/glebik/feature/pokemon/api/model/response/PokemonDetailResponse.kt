package ru.glebik.feature.pokemon.api.model.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail

@Serializable
data class PokemonDetailResponse(
    @SerialName("abilities")
    val abilities: List<AbilityItem>?,
    @SerialName("base_experience")
    val baseExperience: Int?,
    @SerialName("height")
    val height: Int?,
    @SerialName("id")
    val id: Int?,
    @SerialName("is_default")
    val isDefault: Boolean?,
    @SerialName("name")
    val name: String?,
    @SerialName("sprites")
    val spritesDetail: SpritesDetail?,
    @SerialName("weight")
    val weight: Int?
) {
    fun toPokemonDetail(): PokemonDetail = PokemonDetail(
        id = id ?: -1,
        name = name ?: "null",
        abilities = abilities?.map { it.ability?.name ?: "" } ?: listOf(),
        baseExperience = baseExperience ?: -1,
        height = height ?: -1,
        weight = weight ?: -1,
        isDefault = isDefault ?: false,
        images = spritesDetail?.getImageList() ?: listOf(),
        )
}

@Serializable
data class AbilityItem(
    @SerialName("ability")
    val ability: Ability?,
)

@Serializable
data class Ability(
    @SerialName("name")
    val name: String?,
)