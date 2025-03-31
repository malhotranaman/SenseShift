package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Bright, cheerful colors with sunny yellows and warm oranges
 * to evoke feelings of joy and optimism
 */
class HappyColors: AppColors {
    // Primary colors
    override val primary = Color(0xFFFFC107) // Bright yellow
    override val onPrimary = Color(0xFF333333) // Dark text for contrast
    override val primaryContainer = Color(0xFFFFECB3) // Light yellow container
    override val onPrimaryContainer = Color(0xFF8C6D00) // Darker yellow for text

    // Secondary colors
    override val secondary = Color(0xFFFF9800) // Warm orange
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFFFE0B2) // Light orange container
    override val onSecondaryContainer = Color(0xFF995A00) // Darker orange for text

    // Background colors
    override val background = Color(0xFFFFFBE6) // Warm cream background
    override val onBackground = Color(0xFF5D4037) // Brown text
    override val surface = Color.White
    override val onSurface = Color(0xFF5D4037) // Brown text
    override val surfaceVariant = Color(0xFFFFF8E1) // Lighter cream variant

    // Text field specific colors
    override val textFieldContainer = Color(0xFFFFF8E1)
    override val textFieldFocusedContainer = Color(0xFFFFECB3)
    override val textFieldIndicator = Color(0xFFFFD54F)
    override val textFieldFocusedIndicator = Color(0xFFFFC107)
    override val textFieldText = Color(0xFF8C6D00)
    override val textFieldFocusedText = Color(0xFF5D4037)
    override val textFieldPlaceholder = Color(0xFFFFD54F)
    override val textFieldFocusedPlaceholder = Color(0xFFFFC107)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFFFECB3)
    override val systemMessageText = Color(0xFF8C6D00)

    // Border colors
    override val border = Color(0xFFFFD54F)

    // Create Material ColorScheme from our custom colors
    override fun toMaterialColorScheme() = ColorScheme(
        primary = primary,
        onPrimary = onPrimary,
        primaryContainer = primaryContainer,
        onPrimaryContainer = onPrimaryContainer,
        secondary = secondary,
        onSecondary = onSecondary,
        secondaryContainer = secondaryContainer,
        onSecondaryContainer = onSecondaryContainer,
        tertiary = Color(0xFF4CAF50), // Green accent
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFC8E6C9),
        onTertiaryContainer = Color(0xFF1B5E20),
        error = Color(0xFFE57373),
        onError = Color.White,
        errorContainer = Color(0xFFFFEBEE),
        onErrorContainer = Color(0xFFB71C1C),
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = Color(0xFF8C6D00),
        outline = Color(0xFFFFD54F),
        outlineVariant = Color(0xFFFFE0B2),
        scrim = Color(0x99FFF8E1),
        inverseSurface = Color(0xFF5D4037),
        inverseOnSurface = Color.White,
        inversePrimary = Color(0xFFFFECB3),
        surfaceTint = primary,
        surfaceBright = Color.White,
        surfaceContainer = Color(0xFFFFF8E1),
        surfaceContainerHigh = Color(0xFFFFF8D6),
        surfaceContainerHighest = Color(0xFFFFF5CC),
        surfaceContainerLow = Color(0xFFFFFBEB),
        surfaceContainerLowest = Color.White,
        surfaceDim = Color(0xFFFFFBE6),
    )
}