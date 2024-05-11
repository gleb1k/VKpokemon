package ru.glebik.feature.pokemon.api.usecase

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail

interface GetPokemonDetailUseCase {
    suspend operator fun invoke(id: Int): ResultWrapper<PokemonDetail>
}

