package com.jolotan.waispublic.ui.styles

import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleScope
import androidx.compose.foundation.style.StyleState
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.disabled
import androidx.compose.foundation.style.hovered
import androidx.compose.foundation.style.pressed
import androidx.compose.foundation.style.then
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset

class ButtonStyle(
    val container: Style,
    val contentColor: (colors: ColorScheme, state: StyleState) -> Color
) {
    val content: Style = TextStyles.buttonText then Style {
        contentColor(contentColor(colors, state))
    }
}

object ButtonStyles {
    private const val ELEVATION_ALPHA = 0.2f
    private const val DISABLED_CONTENT_ALPHA = 0.5f

    internal fun StyleScope.buttonDropShadow(shadowColor: Color) {
        dropShadow(
            Shadow(
                radius = dimensions.buttonElevationRadius,
                color = shadowColor,
                spread = dimensions.buttonElevationSpread,
                offset = DpOffset(
                    x = dimensions.buttonElevationXOffset,
                    y = dimensions.buttonElevationYOffset
                ),
                alpha = ELEVATION_ALPHA
            )
        )
    }

    private fun ColorScheme.buttonContentColor(
        state: StyleState,
        default: Color,
        hovered: Color,
        pressed: Color
    ): Color = when {
        !state.isEnabled -> onSurface.copy(alpha = DISABLED_CONTENT_ALPHA)
        state.isPressed -> pressed
        state.isHovered -> hovered
        else -> default
    }

    val default = Style {
        contentPadding(
            horizontal = dimensions.buttonContainerHorizontalPadding,
            vertical = dimensions.buttonContainerVerticalPadding
        )
        shape(shapes.medium)
    }

    val disabled = Style {
        disabled {
            background(colors.surfaceDim)
        }
    }

    val primary = ButtonStyle(
        container = default then Style {
            background(colors.primary)
            buttonDropShadow(colors.primary)

            hovered {
                background(colors.primaryContainer)
            }

            pressed {
                background(colors.onPrimaryFixed)
            }
        } then disabled,
        contentColor = { colors, state ->
            colors.buttonContentColor(
                state = state,
                default = colors.onPrimary,
                hovered = colors.primary,
                pressed = colors.primaryFixed
            )
        }
    )

    val secondary = ButtonStyle(
        container = default then Style {
            background(colors.secondaryContainer)
            buttonDropShadow(colors.secondary)

            hovered {
                background(colors.onSecondary)
            }

            pressed {
                background(colors.onSecondaryFixed)
            }
        } then disabled,
        contentColor = { colors, state ->
            colors.buttonContentColor(
                state = state,
                default = colors.onSecondaryContainer,
                hovered = colors.secondary,
                pressed = colors.secondaryFixed
            )
        }
    )
}
