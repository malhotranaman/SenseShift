package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Intense, fiery colors with deep reds and dark accents
 * to express anger, intensity and passion
 */
class AngryColors : AppColors {
    // Primary colors
    override val primary = Color(0xFFB71C1C) // Deep red
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFFFCDD2) // Light red
    override val onPrimaryContainer = Color(0xFF7F0000) // Very dark red

    // Secondary colors
    override val secondary = Color(0xFF7F0000) // Darker red
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFEF9A9A) // Softer red
    override val onSecondaryContainer = Color(0xFF5D0000) // Very dark red

    // Background colors
    override val background = Color(0xFFFFF5F5) // Off-white with red tint
    override val onBackground = Color(0xFF330000) // Nearly black with red undertone
    override val surface = Color.White
    override val onSurface = Color(0xFF330000)
    override val surfaceVariant = Color(0xFFFFF0F0) // Very light warm tone

    // Text field specific colors
    override val textFieldContainer = Color(0xFFFFF0F0)
    override val textFieldFocusedContainer = Color(0xFFFFEBEE)
    override val textFieldIndicator = Color(0xFFEF5350)
    override val textFieldFocusedIndicator = Color(0xFFE53935)
    override val textFieldText = Color(0xFFD32F2F)
    override val textFieldFocusedText = Color(0xFFC62828)
    override val textFieldPlaceholder = Color(0xFFEF9A9A)
    override val textFieldFocusedPlaceholder = Color(0xFFE57373)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFFFE6E6)
    override val systemMessageText = Color(0xFFC62828)

    // Border colors
    override val border = Color(0xFFEF5350)

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
            tertiary = Color(0xFF6D4C41), // Brown accent for depth
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFD7CCC8),
            onTertiaryContainer = Color(0xFF3E2723),
            error = Color(0xFFFF1744),
            onError = Color.White,
            errorContainer = Color(0xFFFFCDD2),
            onErrorContainer = Color(0xFFC62828),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF8C2A2A),
            outline = Color(0xFFEF5350),
            outlineVariant = Color(0xFFFFCDD2),
            scrim = Color(0x99FFF5F5),
            inverseSurface = Color(0xFF5D0000),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFFF8A80),
            surfaceTint = primary,
            surfaceBright = Color.White,
            surfaceContainer = Color(0xFFFFF0F0),
            surfaceContainerHigh = Color(0xFFFFE0E0),
            surfaceContainerHighest = Color(0xFFFFD6D6),
            surfaceContainerLow = Color(0xFFFFF5F5),
            surfaceContainerLowest = Color.White,
            surfaceDim = Color(0xFFFFF5F5),
        )
}
