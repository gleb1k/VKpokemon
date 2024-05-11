package ru.glebik.feature.home.internal.presentation.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.glebik.core.designsystem.theme.AppTheme
import ru.glebik.feature.home.internal.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar() {
    TopAppBar(
        title = {
            Text(
                stringResource(id = R.string.pokemons),
                style = AppTheme.typography.topBar
            )
        },
        colors = TopAppBarColors(
            containerColor = AppTheme.colors.background,
            scrolledContainerColor = AppTheme.colors.background,
            navigationIconContentColor = AppTheme.colors.background,
            titleContentColor = AppTheme.colors.background,
            actionIconContentColor = AppTheme.colors.background,
        )
    )
}