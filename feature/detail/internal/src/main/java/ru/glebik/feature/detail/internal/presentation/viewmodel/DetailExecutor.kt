package ru.glebik.feature.detail.internal.presentation.viewmodel

import ru.glebik.core.presentation.BaseExecutor
import ru.glebik.core.utils.ResultWrapper
import ru.glebik.feature.pokemon.api.usecase.GetPokemonDetailUseCase

internal class DetailExecutor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : BaseExecutor<DetailStore.Intent, Nothing, DetailStore.State, DetailStoreFactory.Message, Nothing>() {

    override suspend fun suspendExecuteIntent(
        intent: DetailStore.Intent,
        getState: () -> DetailStore.State,
    ) = when (intent) {
        is DetailStore.Intent.Load -> load(intent.id)
    }

    private suspend fun load(id: Int) {
        dispatch(DetailStoreFactory.Message.SetLoading)
        when (val response = getPokemonDetailUseCase(id)) {
            is ResultWrapper.Success -> {
                dispatch(
                    DetailStoreFactory.Message.SetPokemon(response.data)
                )
            }

            is ResultWrapper.Failed -> dispatch(
                DetailStoreFactory.Message.SetError(
                    ResultWrapper.Failed(
                        response.exception,
                        response.errorMessage
                    )
                )
            )
        }
    }

}
