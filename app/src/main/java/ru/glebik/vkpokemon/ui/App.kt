package ru.glebik.vkpokemon.ui

import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import ru.glebik.core.network.di.networkModule
import ru.glebik.core.presentation.di.presentationModule
import ru.glebik.core.utils.di.dispatchersModule
import ru.glebik.feature.detail.internal.di.detailModule
import ru.glebik.feature.detail.internal.di.detailScreenModule
import ru.glebik.feature.home.internal.di.homeModule
import ru.glebik.feature.home.internal.di.homeScreenModule
import ru.glebik.feature.pokemon.internal.di.pokemonModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ScreenRegistry {
            homeScreenModule()
            detailScreenModule()
        }

        startKoin {
            androidLogger()
            androidContext(this@App)

            modules(
                listOf(
                    dispatchersModule,
                    networkModule,
                    pokemonModule,
                    presentationModule,
                    homeModule,
                    detailModule,
                )
            )
        }

    }
}