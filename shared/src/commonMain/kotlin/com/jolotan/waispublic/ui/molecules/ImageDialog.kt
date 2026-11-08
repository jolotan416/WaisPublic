package com.jolotan.waispublic.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jolotan.waispublic.ui.atoms.StyledText
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.LocalCompact
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private const val ICON_BUTTON_ALPHA = 0.75f

@Composable
fun ImageDialog(
    images: List<DrawableResource>,
    selectedIndex: Int,
    onDismiss: () -> Unit
) {
    var selectedImageIndex by remember(selectedIndex) { mutableStateOf(selectedIndex) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .padding(AppTheme.dimensions.mainContainerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (LocalCompact.current) {
                ImageDialogColumn(
                    modifier = Modifier.fillMaxHeight(),
                    image = images[selectedImageIndex],
                    onDismiss = onDismiss
                )
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = if (LocalCompact.current) Arrangement.SpaceBetween
                else Arrangement.spacedBy(
                    AppTheme.dimensions.rowHorizontalSpacing,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageDialogNavigationButton(
                    modifier = Modifier.width(AppTheme.dimensions.iconButtonSize),
                    icon = Icons.Default.ArrowBackIosNew,
                    enabled = selectedImageIndex > 0,
                    onClick = {
                        if (selectedImageIndex > 0) {
                            --selectedImageIndex
                        }
                    }
                )

                if (!LocalCompact.current) {
                    ImageDialogColumn(
                        modifier = Modifier.fillMaxHeight(),
                        image = images[selectedImageIndex],
                        onDismiss = onDismiss
                    )
                }

                ImageDialogNavigationButton(
                    modifier = Modifier.width(AppTheme.dimensions.iconButtonSize),
                    icon = Icons.AutoMirrored.Default.ArrowForwardIos,
                    enabled = selectedImageIndex < images.lastIndex,
                    onClick = {
                        if (selectedImageIndex < images.lastIndex) {
                            ++selectedImageIndex
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ImageDialogNavigationButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean,
    onClick: () -> Unit
) {
    if (enabled) {
        IconButton(
            modifier = modifier,
            colors = IconButtonColors(
                containerColor = AppTheme.colors.surface.copy(alpha = ICON_BUTTON_ALPHA),
                contentColor = AppTheme.colors.onSurface.copy(alpha = ICON_BUTTON_ALPHA),
                disabledContainerColor = AppTheme.colors.surfaceVariant.copy(alpha = ICON_BUTTON_ALPHA),
                disabledContentColor = AppTheme.colors.onSurfaceVariant.copy(alpha = ICON_BUTTON_ALPHA)
            ),
            onClick = onClick
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = AppTheme.colors.onSurface
            )
        }
    } else {
        Spacer(modifier = modifier)
    }
}

@Composable
fun ImageDialogColumn(
    modifier: Modifier = Modifier,
    image: DrawableResource,
    onDismiss: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.getColumnVerticalSpacing(LocalCompact.current),
            alignment = Alignment.CenterVertically
        )
    ) {
        Image(
            modifier = Modifier.weight(1f),
            painter = painterResource(image),
            contentDescription = null
        )
        TextButton(onClick = onDismiss) {
            StyledText(style = AppTheme.styles.text.bodyText, text = "Close")
        }
    }
}