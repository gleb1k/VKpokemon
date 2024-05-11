package ru.glebik.feature.detail.internal.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.core.widget.ErrorView
import ru.glebik.core.widget.LoadingView
import ru.glebik.core.widget.common.BaseAsyncImage
import ru.glebik.core.widget.common.BaseSurface
import ru.glebik.feature.detail.internal.R
import ru.glebik.feature.detail.internal.presentation.viewmodel.DetailScreenModel
import ru.glebik.feature.detail.internal.presentation.viewmodel.DetailStore
import java.util.Locale


data class DetailScreen(
    val id: Int
) : Screen {

    @Composable
    override fun Content() {
        DetailScreenImpl()
    }

    @Composable
    private fun DetailScreenImpl(
        viewModel: DetailScreenModel = getScreenModel()
    ) {
        val navigator = LocalNavigator.currentOrThrow
        val state by viewModel.state.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.load(id)
        }

        if (state.isLoading) LoadingView()
        else if (state.isError) ErrorView { viewModel.load(id) }
        else DetailView(
            state = state,
            navigator::pop,
        )
    }

    @Composable
    private fun DetailView(
        state: DetailStore.State,
        onBackClick: () -> Unit,
    ) {
        BaseSurface {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                ) {
                    ItemWithDesc(state = state)
                }
            }
            BackIcon(onBackClick)
        }
    }

    @Composable
    fun BackIcon(
        onBackClick: () -> Unit,
    ) {
        IconButton(
            onClick = onBackClick, modifier = Modifier.padding(vertical = AppTheme.padding.base)
        ) {
            Icon(
                painterResource(id = ru.glebik.core.widget.R.drawable.ic_back),
                null,
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ItemWithDesc(state: DetailStore.State) {
        val item = state.pokemon

        item?.let {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
            ) {
                item.images.let {

                    val pagerState = rememberPagerState(pageCount = {
                        item.images.size
                    })

                    HorizontalPager(
                        state = pagerState, modifier = Modifier.height(400.dp)
                    ) {
                        BaseAsyncImage(
                            model = item.images[it], modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = AppTheme.padding.base)
                ) {
                    Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.tiny))
                    DescItem(key = stringResource(id = R.string.desc_name),
                        value = " ${item.name.replaceFirstChar {
                            if (it.isLowerCase()) it.titlecase(
                                Locale.getDefault()
                            ) else it.toString()
                        }}")
                    DescItem(
                        key = stringResource(id = R.string.desc_height), value = " ${item.height} "
                    )
                    DescItem(
                        key = stringResource(id = R.string.desc_weight), value = " ${item.weight} "
                    )
                    DescItem(
                        key = stringResource(id = R.string.desc_is_default),
                        value = " ${item.isDefault} "
                    )
                    DescItem(
                        key = stringResource(id = R.string.desc_abilities), value = " ${
                            item.abilities.toString().replace("[", "").replace("]", "")
                        } "
                    )
                }
            }
        }

    }

    @Composable
    private fun DescItem(
        key: String,
        value: String,
    ) {
        Row {
            Text(text = key, style = AppTheme.typography.hintBold)
            Text(text = value, style = AppTheme.typography.hint)
        }
        Spacer(modifier = Modifier.padding(vertical = AppTheme.padding.tiny))
    }
}