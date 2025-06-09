package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Somber, desaturated colors with deep blues and muted grays
 * to express sadness, melancholy, and introspection
 */
class SadColors : AppColors {
    // Primary colors
    override val primary = Color(0xFF455A64) // Deep blue-gray
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFCFD8DC) // Light gray-blue
    override val onPrimaryContainer = Color(0xFF263238) // Very dark blue-gray

    // Secondary colors
    override val secondary = Color(0xFF546E7A) // Slightly lighter blue-gray
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFECEFF1) // Very light blue-gray
    override val onSecondaryContainer = Color(0xFF263238) // Very dark blue-gray

    // Background colors
    override val background = Color(0xFFF0F3F4) // Desaturated off-white
    override val onBackground = Color(0xFF263238) // Very dark text
    override val surface = Color(0xFFF9FAFB) // Slightly lighter than background
    override val onSurface = Color(0xFF263238)
    override val surfaceVariant = Color(0xFFE8EBED) // Another light gray variant

    // Text field specific colors
    override val textFieldContainer = Color(0xFFE8EBED)
    override val textFieldFocusedContainer = Color(0xFFDCE1E5)
    override val textFieldIndicator = Color(0xFF90A4AE)
    override val textFieldFocusedIndicator = Color(0xFF607D8B)
    override val textFieldText = Color(0xFF455A64)
    override val textFieldFocusedText = Color(0xFF37474F)
    override val textFieldPlaceholder = Color(0xFFB0BEC5)
    override val textFieldFocusedPlaceholder = Color(0xFF90A4AE)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFE0E6E9)
    override val systemMessageText = Color(0xFF37474F)

    // Border colors
    override val border = Color(0xFFB0BEC5)

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
            tertiary = Color(0xFF78909C), // Mid blue-gray for tertiary
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFECEFF1),
            onTertiaryContainer = Color(0xFF263238),
            error = Color(0xFF9E9E9E), // Gray for errors - less intensity
            onError = Color.White,
            errorContainer = Color(0xFFEEEEEE),
            onErrorContainer = Color(0xFF424242),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF455A64),
            outline = Color(0xFFB0BEC5),
            outlineVariant = Color(0xFFCFD8DC),
            scrim = Color(0x99E0E0E0),
            inverseSurface = Color(0xFF263238),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFCFD8DC),
            surfaceTint = primary,
            surfaceBright = Color(0xFFFCFDFD),
            surfaceContainer = Color(0xFFE8EBED),
            surfaceContainerHigh = Color(0xFFDFE3E6),
            surfaceContainerHighest = Color(0xFFD6DCE0),
            surfaceContainerLow = Color(0xFFF0F3F4),
            surfaceContainerLowest = Color(0xFFF9FAFB),
            surfaceDim = Color(0xFFF0F3F4),
        )
}
