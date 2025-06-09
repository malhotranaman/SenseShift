package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Soft, muted colors with gentle sage greens and pale blues
 * to promote tranquility and peace
 */
class CalmColors : AppColors {
    // Primary colors
    override val primary = Color(0xFF90A4AE) // Soft blue-grey
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFECEFF1) // Very light blue-grey
    override val onPrimaryContainer = Color(0xFF546E7A) // Darker blue-grey

    // Secondary colors
    override val secondary = Color(0xFF7CB342) // Soft sage green
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFE8F5E9) // Very light green
    override val onSecondaryContainer = Color(0xFF33691E) // Darker green

    // Background colors
    override val background = Color(0xFFF5F7F8) // Soft off-white
    override val onBackground = Color(0xFF455A64) // Dark blue-grey text
    override val surface = Color.White
    override val onSurface = Color(0xFF455A64) // Dark blue-grey text
    override val surfaceVariant = Color(0xFFEEF2F6) // Very light blue tint

    // Text field specific colors
    override val textFieldContainer = Color(0xFFF0F4F8)
    override val textFieldFocusedContainer = Color(0xFFECEFF1)
    override val textFieldIndicator = Color(0xFFB0BEC5)
    override val textFieldFocusedIndicator = Color(0xFF90A4AE)
    override val textFieldText = Color(0xFF546E7A)
    override val textFieldFocusedText = Color(0xFF455A64)
    override val textFieldPlaceholder = Color(0xFFB0BEC5)
    override val textFieldFocusedPlaceholder = Color(0xFF90A4AE)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFECEFF1)
    override val systemMessageText = Color(0xFF546E7A)

    // Border colors
    override val border = Color(0xFFCFD8DC)

    // Create Material ColorScheme from our custom colors
    override fun toMaterialColorScheme() =
        ColorScheme(
            primary = primary,
            onPrimary = onPrimary,
            primaryContainer = primaryContainer,
            onPrimaryContainer = onPrimaryContainer,
            secondary = secondary,
            onSecondary = onSecondary,
            secondaryContainer = secondaryContainer,
            onSecondaryContainer = onSecondaryContainer,
            tertiary = Color(0xFF78909C), // Another blue-grey shade for tertiary
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFECEFF1),
            onTertiaryContainer = Color(0xFF455A64),
            error = Color(0xFFEF9A9A), // Muted red for errors
            onError = Color.White,
            errorContainer = Color(0xFFFFEBEE),
            onErrorContainer = Color(0xFFB71C1C),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF607D8B),
            outline = Color(0xFFCFD8DC),
            outlineVariant = Color(0xFFECEFF1),
            scrim = Color(0x99ECEFF1),
            inverseSurface = Color(0xFF455A64),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFECEFF1),
            surfaceTint = primary,
            surfaceBright = Color.White,
            surfaceContainer = Color(0xFFF0F4F8),
            surfaceContainerHigh = Color(0xFFEAEFF3),
            surfaceContainerHighest = Color(0xFFE1E6EA),
            surfaceContainerLow = Color(0xFFF5F7F8),
            surfaceContainerLowest = Color.White,
            surfaceDim = Color(0xFFF5F7F8),
        )
}
