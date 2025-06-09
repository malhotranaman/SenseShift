package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Vibrant, high-contrast colors with bold reds and bright accents
 * to convey energy, excitement and dynamism
 */
class EnergeticColors : AppColors {
    // Primary colors
    override val primary = Color(0xFFF44336) // Vibrant red
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFFFCDD2) // Light red container
    override val onPrimaryContainer = Color(0xFFB71C1C) // Dark red

    // Secondary colors
    override val secondary = Color(0xFFFF9800) // Energetic orange
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFFFE0B2) // Light orange
    override val onSecondaryContainer = Color(0xFFE65100) // Dark orange

    // Background colors
    override val background = Color(0xFFFFF3E0) // Warm off-white
    override val onBackground = Color(0xFF212121) // Near-black text
    override val surface = Color.White
    override val onSurface = Color(0xFF212121)
    override val surfaceVariant = Color(0xFFFFF8E1) // Very light warm tone

    // Text field specific colors
    override val textFieldContainer = Color(0xFFFFF8E1)
    override val textFieldFocusedContainer = Color(0xFFFFEBEE)
    override val textFieldIndicator = Color(0xFFFFAB91)
    override val textFieldFocusedIndicator = Color(0xFFF44336)
    override val textFieldText = Color(0xFFD32F2F)
    override val textFieldFocusedText = Color(0xFFB71C1C)
    override val textFieldPlaceholder = Color(0xFFFFAB91)
    override val textFieldFocusedPlaceholder = Color(0xFFEF9A9A)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFFFEBEE)
    override val systemMessageText = Color(0xFFD32F2F)

    // Border colors
    override val border = Color(0xFFFF8A80)

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
            tertiary = Color(0xFFFFEB3B), // Bright yellow for tertiary
            onTertiary = Color(0xFF212121),
            tertiaryContainer = Color(0xFFFFF9C4),
            onTertiaryContainer = Color(0xFF827717),
            error = Color(0xFFFF5252),
            onError = Color.White,
            errorContainer = Color(0xFFFFEBEE),
            onErrorContainer = Color(0xFFB71C1C),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF616161),
            outline = Color(0xFFFFAB91),
            outlineVariant = Color(0xFFFFCCBC),
            scrim = Color(0x99FFF3E0),
            inverseSurface = Color(0xFF212121),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFFF8A80),
            surfaceTint = primary,
            surfaceBright = Color.White,
            surfaceContainer = Color(0xFFFFF8E1),
            surfaceContainerHigh = Color(0xFFFFF3E0),
            surfaceContainerHighest = Color(0xFFFFE0B2),
            surfaceContainerLow = Color(0xFFFFF8E1),
            surfaceContainerLowest = Color.White,
            surfaceDim = Color(0xFFFFF3E0),
        )
}
