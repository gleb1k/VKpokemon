package ru.glebik.feature.pokemon.api.usecase

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall

interface GetPokemonListUseCase {
    suspend operator fun invoke(offset: Int): ResultWrapper<List<PokemonSmall>>
}