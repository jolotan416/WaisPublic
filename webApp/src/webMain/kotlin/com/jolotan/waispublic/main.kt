package com.jolotan.waispublic

import androidx.compose.foundation.ComposeFoundationFlags
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.jolotan.waispublic.ui.App

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
fun main() {
    ComposeFoundationFlags.isInheritedTextStyleEnabled = true
    ComposeViewport {
        App()
    }
}