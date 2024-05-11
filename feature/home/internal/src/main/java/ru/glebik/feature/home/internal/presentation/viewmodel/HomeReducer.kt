package ru.glebik.feature.home.internal.presentation.viewmodel

import com.arkivanov.mvikotlin.core.store.Reducer

internal class HomeReducer : Reducer<HomeStore.State, HomeStoreFactory.Message> {

    override fun HomeStore.State.reduce(
        msg: HomeStoreFactory.Message,
    ) = when (msg) {
        is HomeStoreFactory.Message.SetError -> copy(
            isError = true,
            isLoading = false,
        )

        is HomeStoreFactory.Message.SetLoading -> copy(
            isLoading = true,
            isError = false,
        )

        is HomeStoreFactory.Message.SetPokemonList -> copy(
            isLoading = false,
            isError = false,
            pokemons = msg.list
        )

        is HomeStoreFactory.Message.SetCurrentPage -> copy(
            currentPage = msg.page
        )

    }
}