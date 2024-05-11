package ru.glebik.feature.detail.internal.presentation.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.usecase.GetPokemonDetailUseCase

internal class DetailStoreFactory(
    private val storeFactory: StoreFactory,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) {
    fun create(): DetailStore = object :
        DetailStore,
        Store<DetailStore.Intent, DetailStore.State, Nothing> by storeFactory.create(
            name = DetailStore::class.simpleName,
            initialState = DetailStore.State(),
            bootstrapper = null,
            executorFactory = {
                DetailExecutor(
                    getPokemonDetailUseCase
                )
            },
            reducer = DetailReducer(),
        ) {}

    sealed interface Message {
        data object SetLoading : Message
        data class SetPokemon(
            val pokemon: ru.glebik.feature.pokemon.api.model.domain.PokemonDetail
        ) : Message

        data class SetError(val error: ResultWrapper.Failed) : Message
    }
}