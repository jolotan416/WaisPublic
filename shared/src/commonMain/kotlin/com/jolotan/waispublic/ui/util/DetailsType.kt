package com.jolotan.waispublic.ui.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ContactSupport
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.Image
import androidx.compose.ui.graphics.vector.ImageVector

enum class DetailsType(val icon: ImageVector, val buttonText: String, val title: String) {
    Features(
        icon = Icons.Default.Construction,
        buttonText = "Features",
        title = "What can Wais do?"
    ),
    Screenshots(
        icon = Icons.Default.Image,
        buttonText = "Screenshots",
        title = "What does Wais look like?"
    ),
    Contact(
        icon = Icons.AutoMirrored.Default.ContactSupport,
        buttonText = "Contact",
        title = "Got a question?"
    )
}