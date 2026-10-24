package com.jolotan.waispublic.ui.styles

import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleScope
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.jolotan.waispublic.ui.Dimensions
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.theme.AppTheme.Companion.LocalAppTheme

val StyleScope.colors: ColorScheme
    get() = LocalAppTheme.currentValue.colors

val StyleScope.typography: Typography
    get() = LocalAppTheme.currentValue.typography

val StyleScope.shapes: Shapes
    get() = LocalAppTheme.currentValue.shapes

val StyleScope.dimensions: Dimensions
    get() = AppTheme.dimensions

@Composable
fun rememberThemedStyle(style: Style): Style {
    val theme = LocalAppTheme.current
    return remember(style, theme) { Style { with(style) { applyStyle() } } }
}
