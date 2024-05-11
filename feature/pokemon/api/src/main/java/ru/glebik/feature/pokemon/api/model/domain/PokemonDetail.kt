package ru.glebik.feature.pokemon.api.model.domain

//В идеале эту модельку нужно в каждом фич модуле мапить под ui, но мне очень лень. Оверинжиниринг)
data class PokemonDetail(
    val id: Int,
    val name: String,
    val abilities: List<String>,
    val baseExperience: Int,
    val height: Int,
    val weight: Int,
    val isDefault: Boolean?,
    val images: List<String>,
    )
