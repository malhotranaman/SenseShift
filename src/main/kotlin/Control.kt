package org.example.userinterface.control
// We are so used to doing social media interactions over the internet, sending pictures and recieving them
// Sometimes the main message we want to spread is not only what we are wearing or doing but the feeling of being in that enviornment with the person you are looking at.
// Your social media feed is always the same regardless of who you are looking at.

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import userinterface.application.ChatApp

// The main application
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SenseShift",
        focusable = true,

    ) {
        ChatApp()
    }
}