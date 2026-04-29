@file:OptIn(ExperimentalMaterial3Api::class)

package com.runique.run.presentation.runoverview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.runique.core.presentation.designsystem.AnalyticsIcon
import com.runique.core.presentation.designsystem.LogoIcon
import com.runique.core.presentation.designsystem.LogoutIcon
import com.runique.core.presentation.designsystem.RunIcon
import com.runique.core.presentation.designsystem.RuniqueTheme
import com.runique.core.presentation.designsystem.components.RuniqueFloatingActionButton
import com.runique.core.presentation.designsystem.components.RuniqueScaffold
import com.runique.core.presentation.designsystem.components.RuniqueToolbar
import com.runique.core.presentation.designsystem.components.utils.DropDownItem
import com.runique.run.presentation.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun RunOverviewScreenRoot(
    viewModel: RunOverviewViewModel = koinViewModel(),
) {
    RunOverviewScreen(
        onAction = viewModel::onAction
    )
}

@Composable
fun RunOverviewScreen(
    onAction: (RunOverviewAction) -> Unit
) {
    val topAppBarState = rememberTopAppBarState()
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(
        topAppBarState
    )
    RuniqueScaffold(
        topAppBar = {
            RuniqueToolbar(
                showBackButton = false,
                title = stringResource(R.string.runique),
                scrollBehavior = scrollBehaviour,
                startContent = {
                    Icon(
                        imageVector = LogoIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                menuItems = listOf(
                    DropDownItem(
                        icon = AnalyticsIcon,
                        title = stringResource(R.string.analytics)
                    ),
                    DropDownItem(
                        icon = LogoutIcon,
                        title = stringResource(R.string.logout)
                    ),
                ),
                onMenuItemClick = { index ->
                    when (index) {
                        0 -> onAction(RunOverviewAction.OnAnalyticsClick)
                        1 -> onAction(RunOverviewAction.OnLogoutClick)
                    }
                }
            )
        },
        floatingActionButton = {
            RuniqueFloatingActionButton(
                onClick = { onAction(RunOverviewAction.OnStartClick) },
                icon = RunIcon
            )
        }
    ) { padding ->

    }
}


@Preview
@Composable
fun RunOverviewScreenPreview() {
    RuniqueTheme {
        RunOverviewScreen(
            onAction = {}
        )
    }
}
