package com.jolotan.waispublic.ui.styles

import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.border
import androidx.compose.foundation.style.contentPadding

object ContainerStyles {
    val main = Style {
        background(color = colors.background)
        contentColor(value = colors.onBackground)
    }

    val section = Style {
        shape(shapes.medium)
        background(colors.surfaceContainer)
        contentColor(colors.onSurface)
        contentPadding(value = dimensions.internalContainerPadding)
    }

    val image = Style {
        shape(shapes.extraExtraLarge)
        border(dimensions.imageBorder, colors.onBackground)
    }
}