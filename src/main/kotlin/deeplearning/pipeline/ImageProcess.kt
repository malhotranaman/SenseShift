package org.example.userinterface.control.deeplearning.pipeline

import org.example.userinterface.control.deeplearning.emotionclassifier.Color
import org.example.userinterface.control.deeplearning.emotionclassifier.Emotion
import org.example.userinterface.control.deeplearning.emotionclassifier.predictEmotions
import org.example.userinterface.control.deeplearning.imageanalysis.detectAndHighlightObjects
import org.example.userinterface.control.deeplearning.imageanalysis.mainColorsInImage
import org.example.userinterface.control.userinterface.colors.*
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import userinterface.colors.AppColors
import userinterface.colors.NeutralColors
import java.io.File

class ImageProcess(
    private val selectedImage: File,

) {
    fun getPrediction(): Triple<AppColors, String, File> {
        val highlightsAndObjects: Pair<File, List<DetectedObject>> = detectAndHighlightObjects(selectedImage)
        val dominantColors: List<Color> = mainColorsInImage(selectedImage, 3)
        val generatedColorSchemeAndMessage: Pair<AppColors, String> = emotionClassify(dominantColors, highlightsAndObjects.second);

        return Triple(generatedColorSchemeAndMessage.first, generatedColorSchemeAndMessage.second, highlightsAndObjects.first)
    }

    private fun emotionClassify(colors: List<Color>, objects: List<DetectedObject>): Pair<AppColors, String> = predictEmotions(colors, objects).let {
        // This will return the found AppColor, along with the confidence and objects generated, etc.

        val predictedColorScheme: AppColors = when (it.predictedEmotion) {
            Emotion.CALM -> CalmColors()
            Emotion.ANGRY -> AngryColors()
            Emotion.ENERGETIC -> EnergeticColors()
            Emotion.HAPPY -> HappyColors()
            Emotion.MYSTERIOUS -> MysteriousColors()
            Emotion.NEUTRAL -> NeutralColors()
            Emotion.ROMANTIC -> RomanticColors()
            Emotion.SAD -> SadColors()
        }

        val predictionMessage: String = buildString {
            appendLine("Predicted Emotion: ${it.predictedEmotion}")
            appendLine("Confidence Score for Possibilities:")
            it.confidenceScores.toList().sortedByDescending { it.second }.take(3).forEach { (emotion, score) ->
                appendLine("  ${emotion.name}: ${score * 100}%")
            }
            appendLine("Information used in Prediction:")
            objects.forEach { detectedObject ->
                appendLine("  Detected Object: ${detectedObject.label} with ${detectedObject.probability * 100}% confidence")
            }
            appendLine(" Detected Colors: ${colors.fold("") { acc: String, color: Color -> acc + "${color.name},${color.red},${color.green},${color.blue}" }}")
        }

        return predictedColorScheme to predictionMessage
    }
}