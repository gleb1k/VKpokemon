package ru.glebik.feature.detail.internal.di

import cafe.adriel.voyager.core.registry.screenModule
import ru.glebik.core.navigation.SharedScreen
import ru.glebik.feature.detail.internal.presentation.DetailScreen

val detailScreenModule = screenModule {
    register<SharedScreen.Detail> { DetailScreen(it.id) }
}