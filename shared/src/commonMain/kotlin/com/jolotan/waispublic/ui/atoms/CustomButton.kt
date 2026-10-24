package com.jolotan.waispublic.ui.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.rememberUpdatedStyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.jolotan.waispublic.ui.styles.ButtonStyle
import com.jolotan.waispublic.ui.styles.rememberThemedStyle
import com.jolotan.waispublic.ui.theme.AppTheme

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    style: ButtonStyle = AppTheme.styles.button.primary,
    textStyle: Style = Style,
    interactionSource: MutableInteractionSource? = null,
    text: String? = null,
    leftIcon: ImageVector? = null,
    iconContentDescription: String? = text,
    isEnabled: Boolean = true,
    onButtonClick: () -> Unit
) {
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val styleState = rememberUpdatedStyleState(interactionSource = interactionSource) {
        it.isEnabled = isEnabled
    }

    Row(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onButtonClick,
                enabled = isEnabled
            )
            .styleable(styleState, rememberThemedStyle(style.container)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.rowHorizontalSpacing,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        leftIcon?.let {
            Icon(
                imageVector = it,
                contentDescription = iconContentDescription,
                tint = style.contentColor(AppTheme.colors, styleState)
            )
        }
        text?.let {
            StyledText(
                text = it,
                style = style.content then textStyle,
                styleState = styleState
            )
        }
    }
}
