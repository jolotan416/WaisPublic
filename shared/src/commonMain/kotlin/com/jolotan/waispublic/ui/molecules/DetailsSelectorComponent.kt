package com.jolotan.waispublic.ui.molecules

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.contentPadding
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jolotan.waispublic.ui.atoms.CustomButton
import com.jolotan.waispublic.ui.styles.colors
import com.jolotan.waispublic.ui.styles.dimensions
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.DetailsType
import com.jolotan.waispublic.ui.util.LocalCompact

@Composable
fun DetailsSelectorComponent(
    modifier: Modifier = Modifier,
    style: Style = Style,
    selectedDetailsType: DetailsType,
    selectLabel: (DetailsType) -> Unit
) {
    Row(
        modifier = modifier.styleable(style = style then Style {
            background(colors.background)
            contentPadding(vertical = dimensions.columnVerticalSpacing, horizontal = 0.dp)
        }),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.rowHorizontalSpacing)
    ) {
        val buttonModifier = Modifier.weight(1f)

        DetailsType.entries.forEach { label ->
            val buttonStyle = if (label == selectedDetailsType) {
                AppTheme.styles.button.primary
            } else {
                AppTheme.styles.button.secondary
            }

            CustomButton(
                modifier = buttonModifier,
                style = buttonStyle,
                textStyle = AppTheme.styles.text.largeButtonText,
                text = if (LocalCompact.current) null else label.buttonText,
                leftIcon = label.icon,
                iconContentDescription = label.buttonText,
                onButtonClick = { selectLabel(label) }
            )
        }
    }
}
