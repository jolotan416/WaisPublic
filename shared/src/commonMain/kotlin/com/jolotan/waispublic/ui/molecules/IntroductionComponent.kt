package com.jolotan.waispublic.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import com.jolotan.waispublic.ui.atoms.StyledText
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.LocalCompact
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import waispublic.shared.generated.resources.Res
import waispublic.shared.generated.resources.github
import waispublic.shared.generated.resources.wais

@Composable
fun IntroductionComponent(modifier: Modifier = Modifier, style: Style = Style) {
    if (LocalCompact.current) {
        Column(
            modifier = modifier.styleable(style = style),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing)
        ) {
            IntroductionComponentImage(modifier = Modifier.width(AppTheme.dimensions.compactImageWidth))
            IntroductionComponentDetails(
                modifier = Modifier.fillMaxWidth(),
                textStyle = {
                    textAlign(TextAlign.Center)
                },
            )
        }
    } else {
        Row(
            modifier = modifier.styleable(style = style),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.rowHorizontalSpacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IntroductionComponentDetails(
                modifier = Modifier.weight(5f),
                textStyle = {
                    textAlign(TextAlign.Start)
                },
            )
            IntroductionComponentImage(modifier = Modifier.width(AppTheme.dimensions.imageWidth))
        }
    }
}

@Composable
fun IntroductionComponentDetails(
    modifier: Modifier = Modifier,
    style: Style = Style,
    textStyle: Style = Style,
) {
    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing),
        horizontalAlignment = if (LocalCompact.current) Alignment.CenterHorizontally else Alignment.Start
    ) {
        val locationModifier = if (LocalCompact.current) {
            Modifier
        } else {
            Modifier.fillMaxWidth()
        }

        StyledText(
            modifier = Modifier.fillMaxWidth(),
            text = "Wais: Budget Tracker App",
            style = AppTheme.styles.text.primaryText then textStyle
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.columnSmallVerticalSpacing))
        StyledText(
            modifier = Modifier.fillMaxWidth(),
            text = "Being wais with your money just got easy.",
            style = AppTheme.styles.text.sectionText then AppTheme.styles.text.headlineText then textStyle
        )
        Spacer(modifier = Modifier.height(AppTheme.dimensions.columnSmallVerticalSpacing))
        IntroductionComponentStatus(
            modifier = locationModifier,
            textStyle = textStyle,
            icon = Icons.Default.Build,
            text = "Currently in development"
        )
        IntroductionComponentSocialMediaButtons(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun IntroductionComponentStatus(
    modifier: Modifier = Modifier,
    style: Style = Style,
    textStyle: Style,
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = modifier.styleable(style = style),
        horizontalArrangement = Arrangement.spacedBy(
            AppTheme.dimensions.rowSmallHorizontalSpacing,
            if (LocalCompact.current) Alignment.CenterHorizontally else Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(AppTheme.dimensions.iconSize),
            imageVector = icon,
            contentDescription = null,
            tint = AppTheme.colors.onSurface
        )
        StyledText(
            modifier = if (LocalCompact.current) {
                Modifier
            } else {
                Modifier.weight(1f)
            },
            text = text,
            style = AppTheme.styles.text.sectionText then AppTheme.styles.text.bodyText then textStyle
        )
    }
}

@Composable
fun IntroductionComponentSocialMediaButtons(
    modifier: Modifier = Modifier,
    style: Style = Style,
) {
    val uriHandler = LocalUriHandler.current
    val socialMediaButtons = listOf(
        SocialMediaButton(
            icon = SocialMediaIcon.Drawable(Res.drawable.github),
            url = "https://github.com/jolotan416"
        ),
        SocialMediaButton(
            icon = SocialMediaIcon.Vector(Icons.Default.Email),
            url = "mailto:jolotan.support@gmail.com"
        ),
    )

    Row(
        modifier = modifier.styleable(style = style),
        horizontalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.rowHorizontalSpacing,
            alignment = if (LocalCompact.current) Alignment.CenterHorizontally else Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        socialMediaButtons.forEach { (icon, url) ->
            val iconModifier = Modifier.size(AppTheme.dimensions.iconSize)
                .clickable(onClick = { uriHandler.openUri(url) })
            val iconTint = AppTheme.colors.onSurface

            when (icon) {
                is SocialMediaIcon.Drawable -> {
                    Icon(
                        modifier = iconModifier,
                        painter = painterResource(icon.drawableResource),
                        tint = iconTint,
                        contentDescription = null
                    )
                }

                is SocialMediaIcon.Vector -> {
                    Icon(
                        modifier = iconModifier,
                        imageVector = icon.imageVector,
                        tint = iconTint,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun IntroductionComponentImage(modifier: Modifier = Modifier, style: Style = Style) {
    Image(
        modifier = modifier.aspectRatio(1f)
            .styleable(style = style),
        painter = painterResource(Res.drawable.wais),
        contentDescription = null
    )
}

data class SocialMediaButton(
    val icon: SocialMediaIcon,
    val url: String
)

sealed interface SocialMediaIcon {
    data class Drawable(val drawableResource: DrawableResource) : SocialMediaIcon
    data class Vector(val imageVector: ImageVector) : SocialMediaIcon
}