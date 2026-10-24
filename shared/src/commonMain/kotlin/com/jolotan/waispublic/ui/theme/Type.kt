package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import waispublic.shared.generated.resources.Res
import waispublic.shared.generated.resources.jost_black
import waispublic.shared.generated.resources.jost_black_italic
import waispublic.shared.generated.resources.jost_bold
import waispublic.shared.generated.resources.jost_bold_italic
import waispublic.shared.generated.resources.jost_extra_bold
import waispublic.shared.generated.resources.jost_extra_bold_italic
import waispublic.shared.generated.resources.jost_extra_light
import waispublic.shared.generated.resources.jost_extra_light_italic
import waispublic.shared.generated.resources.jost_italic
import waispublic.shared.generated.resources.jost_light
import waispublic.shared.generated.resources.jost_light_italic
import waispublic.shared.generated.resources.jost_medium
import waispublic.shared.generated.resources.jost_medium_italic
import waispublic.shared.generated.resources.jost_regular
import waispublic.shared.generated.resources.jost_semi_bold
import waispublic.shared.generated.resources.jost_semi_bold_italic
import waispublic.shared.generated.resources.jost_thin
import waispublic.shared.generated.resources.jost_thin_italic
import waispublic.shared.generated.resources.lato_black
import waispublic.shared.generated.resources.lato_black_italic
import waispublic.shared.generated.resources.lato_bold
import waispublic.shared.generated.resources.lato_bold_italic
import waispublic.shared.generated.resources.lato_italic
import waispublic.shared.generated.resources.lato_light
import waispublic.shared.generated.resources.lato_light_italic
import waispublic.shared.generated.resources.lato_regular
import waispublic.shared.generated.resources.lato_thin
import waispublic.shared.generated.resources.lato_thin_italic

// Default Material 3 typography values
val baseline = Typography()

@Composable
fun displayFontFamily() = FontFamily(
    Font(Res.font.jost_black, weight = FontWeight.Black),
    Font(Res.font.jost_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(Res.font.jost_bold, weight = FontWeight.Bold),
    Font(Res.font.jost_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.jost_extra_bold, weight = FontWeight.ExtraBold),
    Font(Res.font.jost_extra_bold_italic, weight = FontWeight.ExtraBold, style = FontStyle.Italic),
    Font(Res.font.jost_extra_light, weight = FontWeight.ExtraLight),
    Font(
        Res.font.jost_extra_light_italic,
        weight = FontWeight.ExtraLight,
        style = FontStyle.Italic
    ),
    Font(Res.font.jost_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.jost_light, weight = FontWeight.Light),
    Font(Res.font.jost_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.jost_medium, weight = FontWeight.Medium),
    Font(Res.font.jost_medium_italic, weight = FontWeight.Medium, style = FontStyle.Italic),
    Font(Res.font.jost_regular, weight = FontWeight.Normal),
    Font(Res.font.jost_semi_bold, weight = FontWeight.SemiBold),
    Font(Res.font.jost_semi_bold_italic, weight = FontWeight.SemiBold, style = FontStyle.Italic),
    Font(Res.font.jost_thin, weight = FontWeight.Thin),
    Font(Res.font.jost_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

@Composable
fun bodyFontFamily() = FontFamily(
    Font(Res.font.lato_black, weight = FontWeight.Black),
    Font(Res.font.lato_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(Res.font.lato_bold, weight = FontWeight.Bold),
    Font(Res.font.lato_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(Res.font.lato_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(Res.font.lato_light, weight = FontWeight.Light),
    Font(Res.font.lato_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(Res.font.lato_regular, weight = FontWeight.Normal),
    Font(Res.font.lato_thin, weight = FontWeight.Thin),
    Font(Res.font.lato_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic),
)

@Composable
fun AppTypography(): Typography {
    val displayFontFamily = displayFontFamily()
    val bodyFontFamily = bodyFontFamily()

    return Typography(
        displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
    )
}

