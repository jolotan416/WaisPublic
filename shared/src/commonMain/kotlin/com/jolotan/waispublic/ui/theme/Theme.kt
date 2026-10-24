package com.jolotan.waispublic.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.ui.theme.AppTypography
import com.jolotan.waispublic.ui.Dimensions
import com.jolotan.waispublic.ui.styles.AppStyles
import com.jolotan.waispublic.ui.theme.AppTheme.Companion.LocalAppTheme

@Immutable
class AppTheme(
    val colors: ColorScheme = getColorScheme(false),
    val typography: Typography = Typography(),
    val shapes: Shapes = Shapes()
) {
    companion object {
        val colors: ColorScheme
            @Composable @ReadOnlyComposable
            get() = LocalAppTheme.current.colors

        val typography: Typography
            @Composable @ReadOnlyComposable
            get() = LocalAppTheme.current.typography

        val shapes: Shapes
            @Composable @ReadOnlyComposable
            get() = LocalAppTheme.current.shapes

        val dimensions: Dimensions = Dimensions

        val styles = AppStyles

        val LocalAppTheme: ProvidableCompositionLocal<AppTheme>
            get() = LocalAppThemeInstance
    }
}

internal val LocalAppThemeInstance =
    staticCompositionLocalOf { AppTheme() }

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = getColorScheme(isDarkTheme)
    val typography = AppTypography()
    val theme = AppTheme(colors = colors, typography = typography)

    CompositionLocalProvider(LocalAppTheme provides theme) {
        MaterialTheme(
            colorScheme = colors,
            typography = typography,
            content = content
        )
    }
}