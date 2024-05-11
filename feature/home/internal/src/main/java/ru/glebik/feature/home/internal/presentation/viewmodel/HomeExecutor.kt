package ru.glebik.feature.home.internal.presentation.viewmodel

import kotlinx.collections.immutable.toPersistentList
import ru.glebik.core.presentation.BaseExecutor
import ru.glebik.core.utils.ResultWrapper

internal class HomeExecutor(
    private val getPokemonListUseCase: ru.glebik.feature.pokemon.api.usecase.GetPokemonListUseCase
) : BaseExecutor<HomeStore.Intent, Nothing, HomeStore.State, HomeStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: HomeStore.Intent,
        getState: () -> HomeStore.State,
    ) = when (intent) {
        is HomeStore.Intent.LoadPokemons -> loadPokemons(intent.page)
    }

    private suspend fun loadPokemons(page: Int) {
        dispatch(HomeStoreFactory.Message.SetLoading)
        val offset = 20 * (page - 1)

        when (val response = getPokemonListUseCase(offset)) {
            is ResultWrapper.Success -> {
                dispatch(
                    HomeStoreFactory.Message.SetPokemonList(response.data.toPersistentList())
                )
                dispatch(HomeStoreFactory.Message.SetCurrentPage(page))
            }

            is ResultWrapper.Failed -> dispatch(
                HomeStoreFactory.Message.SetError(
                    ResultWrapper.Failed(
                        response.exception,
                        response.errorMessage
                    )
                )
            )
        }
    }
}
