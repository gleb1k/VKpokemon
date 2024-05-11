package ru.glebik.feature.home.internal.presentation.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

interface HomeStore : Store<HomeStore.Intent, HomeStore.State, Nothing> {

    data class State internal constructor(
        val pokemons: PersistentList<ru.glebik.feature.pokemon.api.model.domain.PokemonSmall> = persistentListOf(),
        val currentPage: Int = 1,

        val querySearch: String = "",

        val isLoading: Boolean = false,
        val isError: Boolean = false,
    )

    sealed interface Intent {
        data class LoadPokemons(val page: Int) : Intent
    }
}

