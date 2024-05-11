package ru.glebik.feature.home.internal.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowCircleLeft
import androidx.compose.material.icons.twotone.ArrowCircleRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.navigation.SharedScreen
import ru.glebik.core.widget.ErrorView
import ru.glebik.core.widget.LoadingView
import ru.glebik.core.widget.common.BaseAsyncImage
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.core.widget.common.CustomCard
import ru.glebik.feature.home.internal.presentation.component.HomeAppBar
import ru.glebik.feature.home.internal.presentation.viewmodel.HomeScreenModel
import ru.glebik.feature.home.internal.presentation.viewmodel.HomeStore
import ru.glebik.feature.pokemon.api.model.domain.PokemonSmall
import java.util.Locale

object HomeScreen : Screen {
    private fun readResolve(): Any = HomeScreen

    @Composable
    override fun Content() {
        HomeScreen()
    }

    @Composable
    private fun HomeScreen(
        viewModel: HomeScreenModel = getScreenModel()
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.state.collectAsStateWithLifecycle()

        if (state.isLoading)
            LoadingView()
        else if (state.isError)
            ErrorView { viewModel.loadPokemons(1) }
        else
            HomeView(
                state = state,
                navigator,
                viewModel
            )
    }

    @Composable
    private fun HomeView(
        state: HomeStore.State,
        navigator: Navigator,
        viewModel: HomeScreenModel
    ) {
        BaseSurface {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                HomeAppBar()

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(
                        horizontal = AppTheme.padding.baseMinus
                    ),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.padding.baseMinus),
                ) {
                    items(state.pokemons, key = { it.id }) {
                        PokemonItem(
                            item = it,
                            navigator = navigator,
                        )
                    }
                }
            }
            PaginationArrows(state, viewModel)
        }
    }

    @Composable
    private fun PaginationArrows(
        state: HomeStore.State,
        viewModel: HomeScreenModel,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = { viewModel.loadPokemons(state.currentPage - 1) }) {
                    Icon(
                        Icons.TwoTone.ArrowCircleLeft, null,
                        modifier = Modifier.fillMaxSize(),
                        tint = AppTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.small))
                Text(
                    text = state.currentPage.toString(),
                    style = AppTheme.typography.bodyBold,
                    color = AppTheme.colors.primary
                )
                Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.small))
                IconButton(onClick = { viewModel.loadPokemons(state.currentPage + 1) }) {
                    Icon(
                        Icons.TwoTone.ArrowCircleRight, null,
                        modifier = Modifier.fillMaxSize(),
                        tint = AppTheme.colors.primary
                    )
                }
            }
        }
    }

    @Composable
    private fun PokemonItem(
        item: PokemonSmall,
        navigator: Navigator,
    ) {
        val detailScreen = rememberScreen(SharedScreen.Detail(id = item.id))
        val mContext = LocalContext.current

        CustomCard(
            onSmallClick = {
                navigator.push(detailScreen)
            },
            onLongClick = {
                Toast.makeText(mContext, item.name, Toast.LENGTH_SHORT).show()
            },
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                BaseAsyncImage(
                    modifier = Modifier
                        .weight(0.25f),
                    model = item.frontDefaultImage ?: "",
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(AppTheme.padding.base)
                        .weight(0.75f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.ROOT
                            ) else it.toString()
                        },
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = AppTheme.typography.body
                    )
                }
            }
        }
    }
}


