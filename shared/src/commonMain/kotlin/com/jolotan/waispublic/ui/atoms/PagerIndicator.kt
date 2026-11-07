package com.jolotan.waispublic.ui.atoms

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.jolotan.waispublic.ui.theme.AppTheme

@Composable
fun PagerIndicator(modifier: Modifier = Modifier, pagerState: PagerState) {
    val pageCount = pagerState.pageCount
    val currentPage = pagerState.currentPage

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.rowHorizontalSpacing,
            alignment = Alignment.CenterHorizontally
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(pageCount) { page ->
            AnimatedContent(targetState = currentPage == page, contentKey = { page }) {
                Box(
                    modifier = Modifier.height(AppTheme.dimensions.smallIconSize)
                        .aspectRatio(if (currentPage == page) 2f else 1f)
                        .clip(
                            shape = if (currentPage == page) {
                                AppTheme.shapes.small
                            } else {
                                CircleShape
                            }
                        )
                        .background(
                            color = if (currentPage == page) {
                                AppTheme.colors.primary
                            } else {
                                AppTheme.colors.surfaceVariant
                            }
                        )
                )
            }
        }
    }
}