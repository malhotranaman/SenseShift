package userinterface.application

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
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import userinterface.colors.AppColors
import userinterface.colors.NeutralColors
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.imageio.ImageIO

// The main chat interface
@Composable
@Preview
fun ChatApp() {
    val messages = remember { mutableStateListOf<Message>() }
    val inputText = remember { mutableStateOf("") }
    var selectedImage by remember { mutableStateOf<ImageBitmap?>(null) }
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var AppColors = NeutralColors();

    // Add a refresh state to trigger UI updates
    var refreshTrigger by remember { mutableStateOf(0) }

    // Function to refresh the UI that can be called from anywhere in the composable
    fun refreshUI() {
        refreshTrigger += 1  // Incrementing this value will cause recomposition
    }


    // Initial welcome message
    LaunchedEffect(Unit) {
        messages.add(Message.TextMessage("Send a text or image message.", false))
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
                    text = "SenseShift",
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
                    MessageItem(message, AppColors)
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

                            // TODO working with the selected Image


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
fun MessageItem(message: Message, AppColors: AppColors) {
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


// Messages can be either text or images
sealed class Message {
    data class TextMessage(val text: String, val isFromUser: Boolean = true) : Message()
    data class ImageMessage(val image: ImageBitmap, val isFromUser: Boolean = true) : Message()
}
