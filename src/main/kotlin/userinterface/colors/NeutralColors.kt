package userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

class NeutralColors : AppColors {
    // Primary colors
    override val primary = Color(0xFF8DBEEF) // Pastel blue
    override val onPrimary = Color.White
    override val primaryContainer = Color(0xFFE6F5FF)
    override val onPrimaryContainer = Color(0xFF6B9AC4)

    // Secondary colors
    override val secondary = Color(0xFF9DCFCA)
    override val onSecondary = Color.White
    override val secondaryContainer = Color(0xFFE0F5F2)
    override val onSecondaryContainer = Color(0xFF6BA8A3)

    // Background colors
    override val background = Color(0xFFF0F8FF) // Light pastel blue background
    override val onBackground = Color(0xFF6B8299)
    override val surface = Color.White
    override val onSurface = Color(0xFF6B8299)
    override val surfaceVariant = Color(0xFFF2F9FF)

    // Text field specific colors
    override val textFieldContainer = Color(0xFFEFF8FF)
    override val textFieldFocusedContainer = Color(0xFFE6F5FF)
    override val textFieldIndicator = Color(0xFFAFD6F5)
    override val textFieldFocusedIndicator = Color(0xFF8DBEEF)
    override val textFieldText = Color(0xFF7CACD6)
    override val textFieldFocusedText = Color(0xFF6B9AC4)
    override val textFieldPlaceholder = Color(0xFFBFE1FA)
    override val textFieldFocusedPlaceholder = Color(0xFFAFD6F5)

    // Message colors
    override val userMessageBackground = primary
    override val userMessageText = onPrimary
    override val systemMessageBackground = Color(0xFFE8F4FF)
    override val systemMessageText = Color(0xFF6B9AC4)

    // Border colors
    override val border = Color(0xFFAFD6F5)

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
            tertiary = Color(0xFFB8A7E8),
            onTertiary = Color.White,
            tertiaryContainer = Color(0xFFF0E8FF),
            onTertiaryContainer = Color(0xFF9381C9),
            error = Color(0xFFF5A8A8),
            onError = Color.White,
            errorContainer = Color(0xFFFDECEF),
            onErrorContainer = Color(0xFFD48585),
            background = background,
            onBackground = onBackground,
            surface = surface,
            onSurface = onSurface,
            surfaceVariant = surfaceVariant,
            onSurfaceVariant = Color(0xFF93B4D1),
            outline = Color(0xFFAFD6F5),
            outlineVariant = Color(0xFFCFE7FA),
            scrim = Color(0x99D8EEFF),
            inverseSurface = Color(0xFF6B9AC4),
            inverseOnSurface = Color.White,
            inversePrimary = Color(0xFFD4E9FA),
            surfaceTint = primary,
            surfaceBright = Color.White,
            surfaceContainer = Color(0xFFF5FBFF),
            surfaceContainerHigh = Color(0xFFEAF6FF),
            surfaceContainerHighest = Color(0xFFDFEFFA),
            surfaceContainerLow = Color(0xFFFAFDFF),
            surfaceContainerLowest = Color.White,
            surfaceDim = Color(0xFFF0F8FF),
        )
}
