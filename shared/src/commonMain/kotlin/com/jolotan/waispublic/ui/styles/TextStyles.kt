package com.jolotan.waispublic.ui.styles

import androidx.compose.foundation.style.Style
import androidx.compose.ui.text.style.TextAlign

object TextStyles {
    val primaryText = Style {
        textStyle(typography.displayLarge)
        contentColor(colors.primary)
    }

    val headlineText = Style {
        textStyle(typography.headlineMedium)
    }

    val bodyText = Style {
        textStyle(typography.bodyLarge)
        contentColor(colors.onBackground)
    }

    val helperText = Style {
        textStyle(typography.bodyMedium)
    }

    val sectionText = Style {
        contentColor(colors.onSurface)
    }

    // Buton buttonText styles
    val buttonText = Style {
        textStyle(typography.labelLarge)
        textAlign(TextAlign.Center)
    }

    val largeButtonText = Style {
        textStyle(typography.headlineSmall)
    }

    // Section buttonText styles
    val detailsTitleText = Style {
        textStyle(typography.headlineLarge)
        contentColor(colors.secondary)
    }

    val sectionHeaderText = Style {
        textStyle(typography.titleLarge)
        contentColor(colors.primary)
    }

    val subSectionText = Style {
        textStyle(typography.titleMedium)
    }
}