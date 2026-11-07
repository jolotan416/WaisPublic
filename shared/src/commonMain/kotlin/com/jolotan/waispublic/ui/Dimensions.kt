package com.jolotan.waispublic.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Dimensions {
    // Container values
    private val columnVerticalSpacing = 16.dp
    private val desktopColumnVerticalSpacing = 32.dp
    private val internalContainerPadding: Dp = 20.dp
    private val desktopInternalContainerPadding: Dp = 60.dp

    val mainContainerPadding: Dp = 24.dp
    val columnVerticalSmallSpacing = 12.dp

    fun getInternalContainerPadding(isCompact: Boolean): Dp =
        if (isCompact) internalContainerPadding else desktopInternalContainerPadding

    fun getColumnVerticalSpacing(isCompact: Boolean): Dp =
        if (isCompact) columnVerticalSpacing else desktopColumnVerticalSpacing

    val rowHorizontalSpacing = 24.dp
    val rowSmallHorizontalSpacing = 12.dp

    val maxSectionWidth = 1500.dp

    // Image values
    val imageWidth = 320.dp
    val compactImageWidth = 120.dp
    val imageHeight = 240.dp
    val imageBorder = 4.dp

    val imageElevation = 2.dp

    val largeIconSize = 56.dp
    val iconSize = 28.dp
    val smallIconSize = 12.dp

    // Button values
    val buttonContainerHorizontalPadding: Dp = 20.dp
    val buttonContainerVerticalPadding: Dp = 16.dp
    val buttonElevationRadius: Dp = 1.dp
    val buttonElevationSpread: Dp = 0.dp
    val buttonElevationXOffset: Dp = 0.dp
    val buttonElevationYOffset: Dp = 2.dp
    val iconButtonSize: Dp = 40.dp
}