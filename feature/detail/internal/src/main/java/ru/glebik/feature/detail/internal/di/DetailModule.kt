package ru.glebik.feature.detail.internal.di

import org.koin.dsl.module
import ru.glebik.feature.detail.internal.presentation.viewmodel.DetailScreenModel
import ru.glebik.feature.detail.internal.presentation.viewmodel.DetailStore
import ru.glebik.feature.detail.internal.presentation.viewmodel.DetailStoreFactory

val detailModule = module {

    factory<DetailStore> {
        DetailStoreFactory(
            storeFactory = get(), getPokemonDetailUseCase = get(),
        ).create()
    }

    factory<DetailScreenModel> { DetailScreenModel(get()) }

}