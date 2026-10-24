package com.jolotan.waispublic.ui.molecules

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.style.Style
import androidx.compose.foundation.style.styleable
import androidx.compose.foundation.style.then
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material.icons.filled.Wallet
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import com.jolotan.waispublic.ui.atoms.StyledText
import com.jolotan.waispublic.ui.molecules.SectionIcon.Drawable
import com.jolotan.waispublic.ui.theme.AppTheme
import com.jolotan.waispublic.ui.util.DetailsType
import com.jolotan.waispublic.ui.util.LocalCompact
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import waispublic.shared.generated.resources.Res
import waispublic.shared.generated.resources.github
import waispublic.shared.generated.resources.screenshot_accounts
import waispublic.shared.generated.resources.screenshot_budget
import waispublic.shared.generated.resources.screenshot_cashflow
import waispublic.shared.generated.resources.screenshot_dashboard
import waispublic.shared.generated.resources.screenshot_savings
import waispublic.shared.generated.resources.screenshot_transactions

private const val SCREENSHOT_ASPECT_RATIO = 1080f / 2364f

@Composable
fun DetailsComponent(
    modifier: Modifier = Modifier,
    style: Style = Style,
    detailsType: DetailsType,
) {
    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing)
    ) {
        StyledText(
            modifier = Modifier.fillMaxWidth(),
            style = AppTheme.styles.text.detailsTitleText then TextAlignmentStyle(),
            text = detailsType.title
        )

        DetailsSections(modifier = Modifier.fillMaxWidth(), detailsType = detailsType)
    }
}

@Composable
fun DetailsSections(
    modifier: Modifier = Modifier,
    style: Style = Style,
    detailsType: DetailsType
) {
    when (detailsType) {
        DetailsType.Features -> FeaturesDetailsSections(modifier, style)
        DetailsType.Screenshots -> ScreenshotsDetailsSections(modifier, style)
        DetailsType.Contact -> ContactDetailsSections(modifier, style)
    }
}

@Composable
fun FeaturesDetailsSections(modifier: Modifier = Modifier, style: Style = Style) {
    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing)
    ) {
        FeatureDetailsSection(
            title = "Multiple profiles",
            imageVector = Icons.Default.SwitchAccount,
            description = """
                Organize your money your way. Set up separate books for Personal, Household, or anything else, each with its own currency. When you move money between them, we'll record it on both sides so your numbers always add up.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Automatic exchange rates",
            imageVector = Icons.Default.CurrencyExchange,
            description = """
                Get daily exchange rates between 165 currencies, so you can see your finances in the currencies you prefer.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Budgets",
            imageVector = Icons.Default.Wallet,
            description = """
                Create your own categories and give each one a monthly or yearly budget. Your dashboard shows at a glance how much you've used.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Savings goals",
            imageVector = Icons.Default.Savings,
            description = """
                Plan your future with savings goals. Set priorities, link each goal to one or more accounts, and we'll let you know once you've reached it.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Cash flow",
            imageVector = Icons.Default.Payments,
            description = """
                See where your money comes from and where it goes with expense and income breakdowns by category.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Accounts",
            imageVector = Icons.Default.AccountBalance,
            description = """
                Manage your accounts your way. Give each bank account, e-wallet, and credit card its own currency, group them as you like, and see your total balance in your main currency.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Transactions",
            imageVector = Icons.AutoMirrored.Default.ReceiptLong,
            description = """
                Record your expenses, income and transfers and we'll keep them in an organized list you can browse by month or year. You can also schedule transactions that repeat, and we'll add them for you when they're due.
            """.trimIndent()
        )
        FeatureDetailsSection(
            title = "Privacy first",
            imageVector = Icons.Default.PrivacyTip,
            description = """
                Wais works offline with no account needed, so your financial data never leaves your device. Usage analytics and crash reports are off until you turn them on, and they never include your finances.
            """.trimIndent()
        )
    }
}

@Composable
fun FeatureDetailsSection(
    modifier: Modifier = Modifier,
    style: Style = Style,
    title: String,
    imageVector: ImageVector,
    description: String
) {
    DetailsSection(
        modifier = modifier.fillMaxWidth().styleable(style = style),
        title = title,
        sectionIcon = SectionIcon.Vector(imageVector)
    ) {
        StyledText(
            modifier = Modifier.fillMaxWidth(),
            style = SectionTextStyle(),
            text = description
        )
    }
}

@Composable
fun ScreenshotsDetailsSections(modifier: Modifier = Modifier, style: Style = Style) {
    val screenshots = listOf(
        Res.drawable.screenshot_dashboard to "Dashboard",
        Res.drawable.screenshot_accounts to "Accounts",
        Res.drawable.screenshot_transactions to "Transactions",
        Res.drawable.screenshot_savings to "Savings goals",
        Res.drawable.screenshot_cashflow to "Cash flow",
        Res.drawable.screenshot_budget to "Budget allocation",
    )

    FlowRow(
        modifier = modifier.styleable(style = style),
        horizontalArrangement = Arrangement.spacedBy(
            AppTheme.dimensions.rowHorizontalSpacing,
            Alignment.CenterHorizontally
        ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing)
    ) {
        screenshots.forEach { (image, caption) ->
            ScreenshotItem(
                modifier = if (LocalCompact.current) {
                    Modifier.fillMaxWidth()
                } else {
                    Modifier.width(AppTheme.dimensions.imageWidth)
                },
                image = image,
                caption = caption
            )
        }
    }
}

@Composable
fun ScreenshotItem(
    modifier: Modifier = Modifier,
    style: Style = Style,
    image: DrawableResource,
    caption: String
) {
    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnSmallVerticalSpacing),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.fillMaxWidth()
                .aspectRatio(SCREENSHOT_ASPECT_RATIO)
                .styleable(style = AppTheme.styles.container.image),
            painter = painterResource(image),
            contentScale = ContentScale.Fit,
            contentDescription = caption
        )
        StyledText(
            modifier = Modifier.fillMaxWidth(),
            style = AppTheme.styles.text.subSectionText then Style { textAlign(TextAlign.Center) },
            text = caption
        )
    }
}

@Composable
fun ContactDetailsSections(modifier: Modifier = Modifier, style: Style = Style) {
    Column(
        modifier = modifier.styleable(style = style),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing)
    ) {
        ContactDetailsSection(
            title = "Support",
            sectionIcon = SectionIcon.Vector(Icons.Default.Email),
            description = "Found a bug, need help with the app, or have an idea to make Wais better? We'd love to hear from you.",
            url = "mailto:jolotan.support@gmail.com"
        )
        ContactDetailsSection(
            title = "Privacy policy",
            sectionIcon = SectionIcon.Vector(Icons.Default.Policy),
            description = "Read how Wais handles your data.",
            url = "https://wais.jolotan.com/privacy.html"
        )
        ContactDetailsSection(
            title = "GitHub",
            sectionIcon = Drawable(Res.drawable.github),
            description = "The project overview, architecture notes and the code for this website.",
            url = "https://github.com/jolotan416/WaisPublic"
        )
    }
}

@Composable
fun ContactDetailsSection(
    modifier: Modifier = Modifier,
    style: Style = Style,
    title: String,
    sectionIcon: SectionIcon,
    description: String,
    url: String
) {
    val uriHandler = LocalUriHandler.current

    DetailsSection(
        modifier = modifier.fillMaxWidth()
            .clickable(onClick = { uriHandler.openUri(url) })
            .styleable(style = style),
        title = title,
        sectionIcon = sectionIcon
    ) {
        StyledText(
            modifier = Modifier.fillMaxWidth(),
            style = SectionTextStyle(),
            text = description
        )
    }
}

@Composable
fun DetailsSection(
    modifier: Modifier = Modifier,
    style: Style = Style,
    title: String,
    sectionIcon: SectionIcon? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier.styleable(style = style then AppTheme.styles.container.section),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.columnVerticalSpacing),
        horizontalAlignment = if (LocalCompact.current) Alignment.CenterHorizontally else Alignment.Start
    ) {
        if (LocalCompact.current) {
            DetailsSectionIcon(
                modifier = Modifier.size(AppTheme.dimensions.largeIconSize),
                sectionIcon = sectionIcon
            )
            StyledText(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                style = AppTheme.styles.text.sectionHeaderText then TextAlignmentStyle()
            )
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.rowHorizontalSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                DetailsSectionIcon(
                    modifier = Modifier.size(AppTheme.dimensions.largeIconSize),
                    sectionIcon = sectionIcon
                )
                StyledText(
                    modifier = Modifier.weight(1f),
                    text = title,
                    style = AppTheme.styles.text.sectionHeaderText then TextAlignmentStyle()
                )
            }
        }

        content()
    }
}

@Composable
fun DetailsSectionIcon(
    modifier: Modifier = Modifier,
    style: Style = Style,
    sectionIcon: SectionIcon?
) {
    when (sectionIcon) {
        is SectionIcon.Vector -> {
            Icon(
                modifier = modifier.styleable(style = style),
                imageVector = sectionIcon.imageVector,
                contentDescription = null,
                tint = AppTheme.colors.onSurface
            )
        }

        is Drawable -> {
            Image(
                modifier = modifier.styleable(style = style),
                painter = painterResource(sectionIcon.drawableResource),
                contentDescription = null
            )
        }

        else -> {}
    }
}

@Composable
fun SubSectionTitleStyle() = AppTheme.styles.text.subSectionText then TextAlignmentStyle()

@Composable
fun SectionTextStyle() =
    AppTheme.styles.text.bodyText then AppTheme.styles.text.sectionText then TextAlignmentStyle()

@Composable
fun TextAlignmentStyle() = if (LocalCompact.current) {
    Style { textAlign(TextAlign.Center) }
} else {
    Style { textAlign(TextAlign.Start) }
}

sealed interface SectionIcon {
    data class Vector(val imageVector: ImageVector) : SectionIcon
    data class Drawable(val drawableResource: DrawableResource) : SectionIcon
}