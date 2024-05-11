package ru.glebik.feature.pokemon.api.model.domain

//В идеале эту модельку нужно в каждом фич модуле мапить под ui, но мне очень лень. Оверинжиниринг)
data class PokemonSmall(
    val id : Int,
    val name: String,
    val backDefaultImage: String?,
    val backShinyImage: String?,
    val frontDefaultImage: String?,
    val frontShinyImage: String?,
)
