package ru.glebik.feature.home.internal.di

import cafe.adriel.voyager.core.registry.screenModule
import ru.glebik.core.navigation.SharedScreen
import ru.glebik.feature.home.internal.presentation.HomeScreen


val homeScreenModule = screenModule { register<SharedScreen.Home> { HomeScreen } }