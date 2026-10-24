package com.jolotan.waispublic.ui.atoms

import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.StyleState
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorProducer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.jolotan.waispublic.ui.styles.rememberThemedStyle

@Composable
fun StyledText(
    text: String,
    modifier: Modifier = Modifier,
    style: Style = Style,
    styleState: StyleState? = null,
    textStyle: TextStyle = TextStyle.Default,
    color: ColorProducer? = null,
    overflow: TextOverflow = TextOverflow.Ellipsis,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
) {
    key(text) {
        BasicText(
            text = text,
            modifier = modifier.styleable(styleState = styleState, style = rememberThemedStyle(style)),
            style = textStyle,
            color = color,
            overflow = overflow,
            softWrap = softWrap,
            maxLines = maxLines,
            minLines = minLines,
        )
    }
}
