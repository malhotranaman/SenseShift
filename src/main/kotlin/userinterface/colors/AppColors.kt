package userinterface.colors

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

interface AppColors {
    fun toMaterialColorScheme(): ColorScheme

    // Primary colors
    val primary: Color
    val onPrimary: Color
    val primaryContainer: Color
    val onPrimaryContainer: Color

    // Secondary colors
    val secondary: Color
    val onSecondary: Color
    val secondaryContainer: Color
    val onSecondaryContainer: Color

    // Background colors
    val background : Color
    val onBackground: Color
    val surface : Color
    val onSurface: Color
    val surfaceVariant : Color

    // Text field specific colors
    val textFieldContainer: Color
    val textFieldFocusedContainer : Color
    val textFieldIndicator : Color
    val textFieldFocusedIndicator: Color
    val textFieldText : Color
    val textFieldFocusedText : Color
    val textFieldPlaceholder: Color
    val textFieldFocusedPlaceholder : Color

    // Message colors
    val userMessageBackground : Color
    val userMessageText: Color
    val systemMessageBackground : Color
    val systemMessageText: Color

    // Border colors
    val border : Color
}