package com.example.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

var DarkColorScheme = darkColorScheme(
    primary = RuniqueGreen,
    onPrimary = RuniqueBlack,
    primaryContainer = RuniqueGreen30,
    onPrimaryContainer = RuniqueWhite,
    secondary = RuniqueWhite,
    onSecondary = RuniqueWhite,
    secondaryContainer = RuniqueGray40,
    onSecondaryContainer = RuniqueWhite,
    tertiary = RuniqueWhite,
    onTertiary = RuniqueWhite,
    tertiaryContainer = RuniqueDarkRed5,
    onTertiaryContainer = RuniqueWhite,
    error = RuniqueDarkRed,
    onError = RuniqueWhite,
    errorContainer = RuniqueDarkRed5,
    onErrorContainer = RuniqueWhite,
    background = RuniqueBlack,
    onBackground = RuniqueWhite,
    surface = RuniqueDarkGray,
    onSurface = RuniqueWhite,
    surfaceVariant = RuniqueGray,
)

@Composable
fun RuniqueTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
