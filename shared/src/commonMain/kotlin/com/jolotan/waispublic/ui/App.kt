package com.jolotan.waispublic.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.style.ExperimentalFoundationStyleApi
import androidx.compose.foundation.style.styleable
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfoV2
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass
import com.jolotan.waispublic.ui.atoms.StyledText
import com.jolotan.waispublic.ui.atoms.TimedAnimatedVisibility
import com.jolotan.waispublic.ui.molecules.DetailsComponent
import com.jolotan.waispublic.ui.molecules.DetailsSection
import com.jolotan.waispublic.ui.molecules.DetailsSelectorComponent
import com.jolotan.waispublic.ui.molecules.IntroductionComponent
import com.jolotan.waispublic.ui.molecules.SectionTextStyle
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.DetailsType
import com.jolotan.waispublic.ui.util.LocalCompact
import kotlin.time.Duration.Companion.milliseconds

private val VISIBILITY_ANIMATION_DURATION = 50.milliseconds

@OptIn(ExperimentalFoundationStyleApi::class)
@Composable
fun App(windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfoV2().windowSizeClass) {
    AppTheme {
        val isCompact =
            !windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_LARGE_LOWER_BOUND)
        val widthModifier =
            if (isCompact) {
                Modifier.fillMaxWidth()
            } else {
                Modifier.widthIn(max = AppTheme.dimensions.maxSectionWidth)
            }

        var isAnimatedVisibilityFinished by remember { mutableStateOf(false) }
        var selectedDetailsType by remember { mutableStateOf(DetailsType.entries.first()) }

        CompositionLocalProvider(LocalCompact provides isCompact) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .styleable(style = AppTheme.styles.container.main),
                verticalArrangement = Arrangement.spacedBy(
                    space = AppTheme.dimensions.columnVerticalSpacing,
                    alignment = Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.CenterHorizontally,
                contentPadding = PaddingValues(AppTheme.dimensions.mainContainerPadding)
            ) {
                item(key = "introduction") {
                    TimedAnimatedVisibility(
                        modifier = widthModifier,
                        key = Unit,
                        duration = VISIBILITY_ANIMATION_DURATION,
                        isVisible = isAnimatedVisibilityFinished,
                        updateVisibility = { isAnimatedVisibilityFinished = it }
                    ) {
                        IntroductionComponent(
                            modifier = Modifier.fillMaxWidth(),
                            style = AppTheme.styles.container.section,
                        )
                    }
                }

                item(key = "about") {
                    TimedAnimatedVisibility(
                        modifier = widthModifier,
                        key = Unit,
                        duration = VISIBILITY_ANIMATION_DURATION,
                        isVisible = isAnimatedVisibilityFinished,
                        updateVisibility = { isAnimatedVisibilityFinished = it }
                    ) {
                        DetailsSection(modifier = Modifier.fillMaxWidth(), title = "About Wais") {
                            StyledText(
                                modifier = Modifier.fillMaxWidth(),
                                style = SectionTextStyle(),
                                text = """
                                Wais is a budget tracking app that aims to simplify financial organization, especially for (but not limited to) Filipinos. It supports multiple profiles, automatic exchange rates, savings goals, budget allocations, and cash flow tracking, all packaged in one easy-to-use app.
                            """.trimIndent()
                            )
                        }
                    }
                }

                stickyHeader(key = DetailsType.entries) {
                    TimedAnimatedVisibility(
                        modifier = widthModifier,
                        key = Unit,
                        duration = VISIBILITY_ANIMATION_DURATION,
                        isVisible = isAnimatedVisibilityFinished,
                        updateVisibility = { isAnimatedVisibilityFinished = it }
                    ) {
                        DetailsSelectorComponent(
                            modifier = widthModifier,
                            selectedDetailsType = selectedDetailsType,
                            selectLabel = {
                                if (selectedDetailsType != it) {
                                    selectedDetailsType = it
                                }
                            }
                        )
                    }
                }

                item(key = "details") {
                    AnimatedContent(
                        modifier = widthModifier,
                        targetState = selectedDetailsType,
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut() using SizeTransform(clip = false)
                        }
                    ) { detailsType ->
                        DetailsComponent(
                            modifier = Modifier.fillMaxWidth(),
                            detailsType = detailsType
                        )
                    }
                }
            }
        }
    }
}