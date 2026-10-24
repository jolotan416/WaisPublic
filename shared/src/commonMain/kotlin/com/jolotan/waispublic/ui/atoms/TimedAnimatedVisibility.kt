package com.jolotan.waispublic.ui.atoms

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import kotlin.time.Duration

@Composable
fun TimedAnimatedVisibility(
    modifier: Modifier = Modifier,
    key: Any?,
    duration: Duration,
    isVisible: Boolean,
    updateVisibility: (Boolean) -> Unit,
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        enter = fadeIn() + expandVertically(),
        visible = isVisible,
        content = content
    )

    LaunchedEffect(key) {
        delay(duration)
        updateVisibility(true)
    }
}