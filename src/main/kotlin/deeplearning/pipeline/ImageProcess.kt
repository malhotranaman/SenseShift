package org.example.userinterface.control.deeplearning.pipeline

import org.example.userinterface.control.deeplearning.imageanalysis.detectAndHighlightObjects
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import java.io.File

class ImageProcess(
    private val selectedImage: File,

) {
    val highlightsAndObjects: Pair<File, List<DetectedObject>> = detectAndHighlightObjects(selectedImage)

    fun emotionClassify() {

    }
}