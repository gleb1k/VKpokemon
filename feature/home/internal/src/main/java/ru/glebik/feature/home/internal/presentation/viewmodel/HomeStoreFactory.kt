package ru.glebik.feature.home.internal.presentation.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kotlinx.collections.immutable.PersistentList
import ru.glebik.core.utils.ResultWrapper

internal class HomeStoreFactory(
    private val storeFactory: StoreFactory,
    private val getPokemonListUseCase: ru.glebik.feature.pokemon.api.usecase.GetPokemonListUseCase
) {
    fun create(): HomeStore = object :
        HomeStore,
        Store<HomeStore.Intent, HomeStore.State, Nothing> by storeFactory.create(
            name = HomeStore::class.simpleName,
            initialState = HomeStore.State(),
            bootstrapper = null,
            executorFactory = {
                HomeExecutor(
                    getPokemonListUseCase
                )
            },
            reducer = HomeReducer(),
        ) {}

    sealed interface Message {
        data class SetPokemonList(val list: PersistentList<ru.glebik.feature.pokemon.api.model.domain.PokemonSmall>) :
            Message
        data class SetCurrentPage(val page: Int) : Message
        data object SetLoading : Message
        data class SetError(val error: ResultWrapper.Failed) : Message
    }
}