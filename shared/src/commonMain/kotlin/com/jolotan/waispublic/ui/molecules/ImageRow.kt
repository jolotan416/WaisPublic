package com.jolotan.waispublic.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.max
import com.jolotan.waispublic.ui.atoms.PagerIndicator
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.LocalCompact
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageRow(modifier: Modifier = Modifier, style: Style = Style, images: List<DrawableResource>) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { images.size })
    var selectedImageIndex by remember { mutableStateOf(-1) }
    val isAtStart by remember {
        derivedStateOf {
            val first = pagerState.layoutInfo.visiblePagesInfo.firstOrNull()
            first == null || (first.index == 0 && first.offset >= -1)
        }
    }
    val isAtEnd by remember {
        derivedStateOf {
            val info = pagerState.layoutInfo
            val last = info.visiblePagesInfo.lastOrNull()
            last == null || (
                    last.index == pagerState.pageCount - 1 &&
                            last.offset + info.pageSize <= info.viewportEndOffset - info.afterContentPadding + 1
                    )
        }
    }

    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(
            AppTheme.dimensions.getColumnVerticalSpacing(LocalCompact.current)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (!LocalCompact.current) {
                ImageRowNavigationButton(
                    modifier = Modifier.width(AppTheme.dimensions.iconButtonSize),
                    icon = Icons.Default.ArrowBackIosNew,
                    enabled = !isAtStart,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage - 1).coerceAtLeast(
                                    0
                                )
                            )
                        }
                    }
                )
            }

            BoxWithConstraints(modifier = Modifier.weight(1f)) {
                val pageWidth = AppTheme.dimensions.imageHeight
                val minPadding = AppTheme.dimensions.imageElevation
                val horizontalPadding = if (LocalCompact.current) {
                    max((maxWidth - pageWidth) / 2, minPadding)
                } else {
                    minPadding
                }

                HorizontalPager(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = horizontalPadding),
                    state = pagerState,
                    pageSize = PageSize.Fixed(pageWidth),
                    pageSpacing = AppTheme.dimensions.rowHorizontalSpacing,
                    snapPosition = if (LocalCompact.current) SnapPosition.Center else SnapPosition.Start
                ) { page ->
                    Image(
                        modifier = Modifier.fillMaxWidth()
                            .shadow(
                                elevation = AppTheme.dimensions.imageElevation,
                                shape = AppTheme.shapes.extraLarge
                            ).clickable(onClick = { selectedImageIndex = page }),
                        painter = painterResource(images[page]),
                        contentDescription = null
                    )
                }
            }

            if (!LocalCompact.current) {
                ImageRowNavigationButton(
                    modifier = Modifier.width(AppTheme.dimensions.iconButtonSize),
                    icon = Icons.AutoMirrored.Default.ArrowForwardIos,
                    enabled = !isAtEnd,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                (pagerState.currentPage + 1).coerceAtMost(
                                    images.lastIndex
                                )
                            )
                        }
                    }
                )
            }
        }

        if (LocalCompact.current) {
            PagerIndicator(modifier = Modifier.fillMaxWidth(), pagerState = pagerState)
        }
    }

    if (selectedImageIndex >= 0) {
        ImageDialog(
            images = images,
            selectedIndex = selectedImageIndex,
            onDismiss = { selectedImageIndex = -1 })
    }
}

@Composable
fun ImageRowNavigationButton(
    modifier: Modifier,
    icon: ImageVector,
    enabled: Boolean,
    onClick: () -> Unit
) {
    if (enabled) {
        IconButton(modifier = modifier, onClick = onClick) {
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