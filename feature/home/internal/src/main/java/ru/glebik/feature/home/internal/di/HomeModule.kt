package ru.glebik.feature.home.internal.di

import org.koin.dsl.module
import ru.glebik.feature.home.internal.presentation.viewmodel.HomeScreenModel
import ru.glebik.feature.home.internal.presentation.viewmodel.HomeStore
import ru.glebik.feature.home.internal.presentation.viewmodel.HomeStoreFactory

val homeModule = module {

    factory<HomeStore> {
        HomeStoreFactory(
            storeFactory = get(),
            get(),
        ).create()
    }

    factory<HomeScreenModel> { HomeScreenModel(get()) }

}
