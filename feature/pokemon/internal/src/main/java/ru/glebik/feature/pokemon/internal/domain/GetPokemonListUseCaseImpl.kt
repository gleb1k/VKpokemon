package ru.glebik.feature.pokemon.internal.domain

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall
import ru.glebik.feature.pokemon.api.repository.PokemonRepository
import ru.glebik.feature.pokemon.api.usecase.GetPokemonListUseCase

class GetPokemonListUseCaseImpl(
    private val repository: ru.glebik.feature.pokemon.api.repository.PokemonRepository,
) : ru.glebik.feature.pokemon.api.usecase.GetPokemonListUseCase {
    override suspend fun invoke(offset: Int): ResultWrapper<List<ru.glebik.feature.pokemon.api.model.domain.PokemonSmall>> = repository.getPokemonList(offset)
}