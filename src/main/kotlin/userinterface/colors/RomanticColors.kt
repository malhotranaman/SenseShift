package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Passionate, warm colors with soft pinks and deep roses
 * to evoke feelings of romance and affection
 */
class RomanticColors: AppColors {
    // Primary colors
    override val primary = Color(0xFFEC407A) // Pink
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFFCE4EC) // Light pink
    override val onPrimaryContainer = Color(0xFFAD1457) // Deep pink

    // Secondary colors
    override val secondary = Color(0xFFE91E63) // Deeper pink
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFF8BBD0) // Soft pink
    override val onSecondaryContainer = Color(0xFFAD1457) // Deep pink

    // Background colors
    override val background = Color(0xFFFFF5F8) // Very soft pink
    override val onBackground = Color(0xFF880E4F) // Deep rose text
    override val surface = Color.White
    override val onSurface = Color(0xFF880E4F)
    override val surfaceVariant = Color(0xFFFEF2F6) // Subtle pink tint

    // Text field specific colors
    override val textFieldContainer = Color(0xFFFEF2F6)
    override val textFieldFocusedContainer = Color(0xFFFCE4EC)
    override val textFieldIndicator = Color(0xFFF48FB1)
    override val textFieldFocusedIndicator = Color(0xFFEC407A)
    override val textFieldText = Color(0xFFD81B60)
    override val textFieldFocusedText = Color(0xFFC2185B)
    override val textFieldPlaceholder = Color(0xFFF8BBD0)
    override val textFieldFocusedPlaceholder = Color(0xFFF48FB1)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFFCE4EC)
    override val systemMessageText = Color(0xFFC2185B)

    // Border colors
    override val border = Color(0xFFF48FB1)

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
        tertiary = Color(0xFFF06292), // Another pink shade
        onTertiary = Color.White,
        tertiaryContainer = Color(0xFFFFCDD2), // Light pink-red
        onTertiaryContainer = Color(0xFFB71C1C),
        error = Color(0xFFE57373),
        onError = Color.White,
        errorContainer = Color(0xFFFFEBEE),
        onErrorContainer = Color(0xFFB71C1C),
        background = background,
        onBackground = onBackground,
        surface = surface,
        onSurface = onSurface,
        surfaceVariant = surfaceVariant,
        onSurfaceVariant = Color(0xFFC2185B),
        outline = Color(0xFFF48FB1),
        outlineVariant = Color(0xFFF8BBD0),
        scrim = Color(0x99FCE4EC),
        inverseSurface = Color(0xFF880E4F),
        inverseOnSurface = Color.White,
        inversePrimary = Color(0xFFF8BBD0),
        surfaceTint = primary,
        surfaceBright = Color.White,
        surfaceContainer = Color(0xFFFEF2F6),
        surfaceContainerHigh = Color(0xFFFCE4EC),
        surfaceContainerHighest = Color(0xFFF8BBD0),
        surfaceContainerLow = Color(0xFFFFF5F8),
        surfaceContainerLowest = Color.White,
        surfaceDim = Color(0xFFFFF5F8),
    )
}