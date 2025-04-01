package org.example.userinterface.control.deeplearning.emotionclassifier

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject

/**
 * Enumeration of emotions/moods that our classifier can predict
 */
enum class Emotion(val description: String) {
    HAPPY("Joy, happiness, and contentment"),
    SAD("Sadness, grief, and melancholy"),
    ANGRY("Anger, frustration, and rage"),
    NEUTRAL("Normal, consistent, and boring"),
    MYSTERIOUS("Peculiar, wierd, and Unknown"),
    ROMANTIC("Love, affection, and romance"),
    ENERGETIC("Excitement, enthusiasm, and eagerness"),
    CALM("Peaceful, serene, and restful")
}

/**
 * Color representation with RGB values
 */
@Serializable
data class Color(
    val name: String,
    val red: Int,
    val green: Int,
    val blue: Int
) {
    // Normalize RGB values to range [0, 1]
    fun normalizedRGB(): Triple<Float, Float, Float> =
        Triple(red / 255f, green / 255f, blue / 255f)
}

/**
 * Input for the emotion classifier containing all relevant details
 */
@Serializable
data class ClassifierInput(
    val mainColor: Color,
    val secondaryColors: List<Color> = emptyList(),
    val objects: List<DetectedObject> = emptyList(),
    val additionalDetails: Map<String, String> = emptyMap()
)

/**
 * Output from the emotion classifier
 */
@Serializable
data class ClassifierOutput(
    val predictedEmotion: Emotion,
    val confidenceScores: Map<Emotion, Float>
)