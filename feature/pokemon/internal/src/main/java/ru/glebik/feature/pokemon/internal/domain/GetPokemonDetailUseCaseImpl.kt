package ru.glebik.feature.pokemon.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail
import ru.glebik.feature.pokemon.api.repository.PokemonRepository
import ru.glebik.feature.pokemon.api.usecase.GetPokemonDetailUseCase

class GetPokemonDetailUseCaseImpl(
    private val repository: PokemonRepository,
) : GetPokemonDetailUseCase {
    override suspend fun invoke(id: Int): ResultWrapper<PokemonDetail> =
        repository.getPokemonDetail(id)
}

