package ru.glebik.feature.pokemon.api.repository

import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall

interface PokemonRepository {

    suspend fun getPokemonList(offset: Int): ResultWrapper<List<PokemonSmall>>

    suspend fun getPokemonDetail(id: Int): ResultWrapper<PokemonDetail>

}