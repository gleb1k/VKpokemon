package ru.glebik.feature.home.internal.presentation.viewmodel

import ru.glebik.core.presentation.MviScreenModel


class HomeScreenModel(
    private val store: HomeStore,
) : MviScreenModel<HomeStore.Intent, HomeStore.State, Nothing>(
    store
) {

    init {
        loadPokemons(store.state.currentPage)
    }

    fun loadPokemons(page: Int) {
        if (page <= 0)
            return
        store.accept(HomeStore.Intent.LoadPokemons(page))
    }

}
