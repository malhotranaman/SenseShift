package org.example.userinterface.control
// We are so used to doing social media interactions over the internet, sending pictures and recieving them
// Sometimes the main message we want to spread is not only what we are wearing or doing but the feeling of being in that enviornment with the person you are looking at.
// Your social media feed is always the same regardless of who you are looking at.
// What

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.launch
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.imageio.ImageIO

// Messages can be either text or images
sealed class Message {
    data class TextMessage(val text: String, val isFromUser: Boolean = true) : Message()
    data class ImageMessage(val image: ImageBitmap, val isFromUser: Boolean = true) : Message()
}

// Define the app color scheme
object AppColors {
    // Primary colors
    val primary = Color(0xFF8DBEEF) // Pastel blue
    val onPrimary = Color.White
    val primaryContainer = Color(0xFFE6F5FF)
    val onPrimaryContainer = Color(0xFF6B9AC4)

    // Secondary colors
    val secondary = Color(0xFF9DCFCA)
    val onSecondary = Color.White
    val secondaryContainer = Color(0xFFE0F5F2)
    val onSecondaryContainer = Color(0xFF6BA8A3)

    // Background colors
    val background = Color(0xFFF0F8FF) // Light pastel blue background
    val onBackground = Color(0xFF6B8299)
    val surface = Color.White
    val onSurface = Color(0xFF6B8299)
    val surfaceVariant = Color(0xFFF2F9FF)

    // Text field specific colors
    val textFieldContainer = Color(0xFFEFF8FF)
    val textFieldFocusedContainer = Color(0xFFE6F5FF)
    val textFieldIndicator = Color(0xFFAFD6F5)
    val textFieldFocusedIndicator = Color(0xFF8DBEEF)
    val textFieldText = Color(0xFF7CACD6)
    val textFieldFocusedText = Color(0xFF6B9AC4)
    val textFieldPlaceholder = Color(0xFFBFE1FA)
    val textFieldFocusedPlaceholder = Color(0xFFAFD6F5)

    // Message colors
    val userMessageBackground = primary
    val userMessageText = onPrimary
    val systemMessageBackground = Color(0xFFE8F4FF)
    val systemMessageText = Color(0xFF6B9AC4)

    // Border colors
    val border = Color(0xFFAFD6F5)

    // Create Material ColorScheme from our custom colors
    fun toMaterialColorScheme() = ColorScheme(
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

// The main application
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Chat App"
    ) {
        ChatApp()
    }
}

// The main chat interface
@Composable
@Preview
fun ChatApp() {
    val messages = remember { mutableStateListOf<Message>() }
    val inputText = remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Initial welcome message
    LaunchedEffect(Unit) {
        messages.add(Message.TextMessage("Welcome to the chat! Please upload an image.", false))
    }

    MaterialTheme(
        colorScheme = AppColors.toMaterialColorScheme()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppColors.background)
                .padding(16.dp)
        ) {
            // App bar
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Chat",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.onBackground
                )
            }

            // Chat messages
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = AppColors.surfaceVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                state = scrollState
            ) {
                items(messages) { message ->
                    MessageItem(message)
                }
            }

            // If image is selected, show preview
            selectedImage?.let { image ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            bitmap = image,
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .border(1.dp, AppColors.border, RoundedCornerShape(8.dp))
                        )
                        Text(
                            "Image selected - click OK to send",
                            fontSize = 12.sp,
                            color = AppColors.onBackground,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }

            // Input area - always visible
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = inputText.value,
                    onValueChange = { inputText.value = it },
                    placeholder = { Text("Type a message...") },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.textFieldFocusedContainer,
                        unfocusedContainerColor = AppColors.textFieldContainer,
                        disabledContainerColor = AppColors.textFieldContainer,
                        focusedIndicatorColor = AppColors.textFieldFocusedIndicator,
                        unfocusedIndicatorColor = AppColors.textFieldIndicator,
                        focusedTextColor = AppColors.textFieldFocusedText,
                        unfocusedTextColor = AppColors.textFieldText,
                        focusedPlaceholderColor = AppColors.textFieldFocusedPlaceholder,
                        unfocusedPlaceholderColor = AppColors.textFieldPlaceholder
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Upload button - always visible
                Button(
                    onClick = {
                        val fileDialog = FileDialog(Frame(), "Select an image", FileDialog.LOAD)
                        fileDialog.isVisible = true

                        val directory = fileDialog.directory
                        val file = fileDialog.file

                        if (directory != null && file != null) {
                            val selectedFile = File(directory, file)
                            try {
                                val bufferedImage = ImageIO.read(selectedFile)
                                selectedImage = bufferedImage.toComposeImageBitmap()
                            } catch (e: Exception) {
                                println("Error loading image: ${e.message}")
                            }
                        }
                    },
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.primary,
                        contentColor = AppColors.onPrimary
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Upload Image"
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                // Send/OK button - always visible
                Button(
                    onClick = {
                        if (selectedImage != null) {
                            // Add image message to chat
                            messages.add(Message.ImageMessage(selectedImage!!))

                            // Reset the selected image
                            selectedImage = null

                            // Add confirmation message
                            messages.add(Message.TextMessage("Image sent successfully!", false))
                        } else if (inputText.value.isNotEmpty()) {
                            // Add text message
                            messages.add(Message.TextMessage(inputText.value))
                            inputText.value = ""
                        }

                        // Scroll to bottom
                        coroutineScope.launch {
                            scrollState.animateScrollToItem(messages.size - 1)
                        }
                    },
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.primary,
                        contentColor = AppColors.onPrimary
                    )
                ) {
                    Text("OK")
                }
            }
        }
    }
}

// Component for individual message items
@Composable
fun MessageItem(message: Message) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = if (message is Message.TextMessage && !message.isFromUser)
            Alignment.CenterStart else Alignment.CenterEnd
    ) {
        when (message) {
            is Message.TextMessage -> {
                val backgroundColor = if (message.isFromUser)
                    AppColors.userMessageBackground else AppColors.systemMessageBackground
                val textColor = if (message.isFromUser)
                    AppColors.userMessageText else AppColors.systemMessageText

                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(
                                topStart = 16.dp,
                                topEnd = 16.dp,
                                bottomStart = if (message.isFromUser) 16.dp else 4.dp,
                                bottomEnd = if (message.isFromUser) 4.dp else 16.dp
                            )
                        )
                        .background(backgroundColor)
                        .padding(12.dp)
                ) {
                    Text(
                        text = message.text,
                        color = textColor,
                        fontSize = 16.sp
                    )
                }
            }
            is Message.ImageMessage -> {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .border(1.dp, AppColors.border, RoundedCornerShape(12.dp))
                ) {
                    Image(
                        bitmap = message.image,
                        contentDescription = "Message Image",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    }
}