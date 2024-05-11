package ru.glebik.feature.pokemon.internal.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.glebik.core.utils.di.CoroutineDispatchers
import ru.glebik.feature.pokemon.api.api.PokemonApi
import ru.glebik.feature.pokemon.api.repository.PokemonRepository
import ru.glebik.feature.pokemon.internal.domain.GetPokemonDetailUseCaseImpl
import ru.glebik.feature.pokemon.internal.domain.GetPokemonListUseCaseImpl

val pokemonModule = module {
    single<PokemonApi> {
        get<Retrofit>().create(PokemonApi::class.java)
    }
    factory<PokemonRepository> {
        ru.glebik.feature.pokemon.internal.data.PokemonRepositoryImpl(
            ioDispatcher = get(named(CoroutineDispatchers.IO)),
            pokemonApi = get(),
        )
    }

    factory<ru.glebik.feature.pokemon.api.usecase.GetPokemonListUseCase> {
        GetPokemonListUseCaseImpl(get())
    }

    factory<ru.glebik.feature.pokemon.api.usecase.GetPokemonDetailUseCase> {
        GetPokemonDetailUseCaseImpl(get())
    }
}