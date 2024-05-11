package ru.glebik.feature.detail.internal.presentation.viewmodel

import com.arkivanov.mvikotlin.core.store.Store
import ru.glebik.feature.pokemon.api.model.domain.PokemonDetail

interface DetailStore : Store<DetailStore.Intent, DetailStore.State, Nothing> {

    data class State internal constructor(
        val pokemon: PokemonDetail? = null,

        val isLoading: Boolean = false,
        val isError: Boolean = false,
    )

    sealed interface Intent {
        data class Load(val id: Int) : Intent
    }
}


