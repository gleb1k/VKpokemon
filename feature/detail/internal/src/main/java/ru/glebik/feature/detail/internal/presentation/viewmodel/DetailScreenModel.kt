package ru.glebik.feature.detail.internal.presentation.viewmodel

import ru.glebik.core.presentation.MviScreenModel


class DetailScreenModel(
    private val store: DetailStore,
) : MviScreenModel<DetailStore.Intent, DetailStore.State, Nothing>(
    store
) {
    fun load(id: Int) = store.accept(DetailStore.Intent.Load(id))
}
