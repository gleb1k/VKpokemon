package ru.glebik.feature.pokemon.internal.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.api.PokemonApi
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall
import ru.glebik.feature.pokemon.api.repository.PokemonRepository

internal class PokemonRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val pokemonApi: PokemonApi,
) : PokemonRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getPokemonList(offset: Int): ResultWrapper<List<PokemonSmall>> =
        withContext(ioDispatcher) {
            runCatching {
                val pokemonsResponse =
                    pokemonApi.getPokemonList(offset).pokemonListResults ?: listOf()

                pokemonsResponse
                    .asFlow()
                    .flatMapMerge {
                        flow {
                            val spriteModel = pokemonApi.getPokemonSprite(it?.name ?: "null")
                            emit(spriteModel.toPokemonSmall())
                        }
                    }.toList()
                    .sortedBy { it.id }
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { ResultWrapper.Failed(it, it.message) }
            )
        }

    override suspend fun getPokemonDetail(id: Int): ResultWrapper<PokemonDetail> =
        withContext(ioDispatcher)
        {
            runCatching {
                pokemonApi.getPokemonDetail(id).toPokemonDetail()
            }.fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { ResultWrapper.Failed(it, it.message) }
            )
        }
}