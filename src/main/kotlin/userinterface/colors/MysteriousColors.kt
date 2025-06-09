package org.example.userinterface.control.userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color
import userinterface.colors.AppColors

/**
 * Enigmatic, atmospheric colors with deep purples and midnight blues
 * to create a sense of mystery, wonder and depth
 */
class MysteriousColors : AppColors {
    // Primary colors
    override val primary = Color(0xFF4527A0) // Deep purple
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFD1C4E9) // Light purple
    override val onPrimaryContainer = Color(0xFF311B92) // Very dark purple

    // Secondary colors
    override val secondary = Color(0xFF303F9F) // Deep indigo
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFC5CAE9) // Light indigo
    override val onSecondaryContainer = Color(0xFF1A237E) // Very dark indigo

    // Background colors
    override val background = Color(0xFFF5F3FA) // Very light purple tint
    override val onBackground = Color(0xFF1A1042) // Deep purple-black
    override val surface = Color(0xFFFEFBFF) // White with subtle purple undertone
    override val onSurface = Color(0xFF1A1042)
    override val surfaceVariant = Color(0xFFEDE7F6) // Light purple tint

    // Text field specific colors
    override val textFieldContainer = Color(0xFFEDE7F6)
    override val textFieldFocusedContainer = Color(0xFFD1C4E9)
    override val textFieldIndicator = Color(0xFF9575CD)
    override val textFieldFocusedIndicator = Color(0xFF7E57C2)
    override val textFieldText = Color(0xFF5E35B1)
    override val textFieldFocusedText = Color(0xFF4527A0)
    override val textFieldPlaceholder = Color(0xFFB39DDB)
    override val textFieldFocusedPlaceholder = Color(0xFF9575CD)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFE8E3F4)
    override val systemMessageText = Color(0xFF4527A0)

    // Border colors
    override val border = Color(0xFF9575CD)

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
            tertiary = Color(0xFF512DA8), // Another purple shade
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFD1C4E9),
            onTertiaryContainer = Color(0xFF311B92),
            error = Color(0xFF7986CB), // Using indigo for errors - more mysterious
            onError = Color.White,
            errorContainer = Color(0xFFE8EAF6),
            onErrorContainer = Color(0xFF1A237E),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF4527A0),
            outline = Color(0xFF9575CD),
            outlineVariant = Color(0xFFD1C4E9),
            scrim = Color(0x99EDE7F6),
            inverseSurface = Color(0xFF1A1042),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFD1C4E9),
            surfaceTint = primary,
            surfaceBright = Color.White,
            surfaceContainer = Color(0xFFEDE7F6),
            surfaceContainerHigh = Color(0xFFE4DEF1),
            surfaceContainerHighest = Color(0xFFD9D0E8),
            surfaceContainerLow = Color(0xFFF5F3FA),
            surfaceContainerLowest = Color(0xFFFEFBFF),
            surfaceDim = Color(0xFFF5F3FA),
        )
}
