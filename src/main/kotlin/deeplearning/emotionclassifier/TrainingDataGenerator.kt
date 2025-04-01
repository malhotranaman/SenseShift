package org.example.userinterface.control.deeplearning.emotionclassifier

import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import kotlin.random.Random

/**
 * Utility class to generate sample training data for the emotion classifier
 */
class TrainingDataGenerator {

    // Common object categories
    private val objectCategories = listOf(
        "nature", "animal", "vehicle", "food", "furniture",
        "building", "clothing", "technology", "art", "people"
    )

    // Common attributes
    private val objectAttributes = listOf(
        "small", "large", "bright", "dark", "soft", "hard",
        "round", "square", "old", "new", "shiny", "dull",
        "warm", "cold", "smooth", "rough", "heavy", "light"
    )

    // Emotion-related colors
    private val emotionColors = mapOf(
        Emotion.HAPPY to listOf(
            Color("yellow", 255, 255, 0),
            Color("orange", 255, 165, 0),
            Color("bright green", 0, 255, 0)
        ),
        Emotion.SAD to listOf(
            Color("blue", 0, 0, 255),
            Color("gray", 128, 128, 128),
            Color("dark blue", 0, 0, 139)
        ),
        Emotion.ANGRY to listOf(
            Color("red", 255, 0, 0),
            Color("dark red", 139, 0, 0),
            Color("black", 0, 0, 0)
        ),
        Emotion.NEUTRAL to listOf(
            Color("beige", 245, 245, 220),
            Color("gray", 128, 128, 128),
            Color("tan", 210, 180, 140)
        ),
        Emotion.MYSTERIOUS to listOf(
            Color("purple", 128, 0, 128),
            Color("deep blue", 0, 0, 139),
            Color("midnight blue", 25, 25, 112)
        ),
        Emotion.ROMANTIC to listOf(
            Color("pink", 255, 192, 203),
            Color("light red", 255, 102, 102),
            Color("lavender", 230, 230, 250)
        ),
        Emotion.ENERGETIC to listOf(
            Color("bright pink", 255, 105, 180),
            Color("neon green", 57, 255, 20),
            Color("electric blue", 125, 249, 255)
        ),
        Emotion.CALM to listOf(
            Color("light blue", 173, 216, 230),
            Color("sage green", 176, 208, 176),
            Color("lavender", 230, 230, 250)
        )
    )

    // Emotion-related objects
    private val emotionObjects = mapOf(
        Emotion.HAPPY to listOf(
            "sun", "flower", "puppy", "balloon", "party"
        ),
        Emotion.SAD to listOf(
            "rain", "cloud", "wilted flower", "tissue", "tear"
        ),
        Emotion.ANGRY to listOf(
            "fire", "storm", "broken glass", "lightning", "fist"
        ),
        Emotion.NEUTRAL to listOf(
            "wall", "chair", "paper", "clock", "desk"
        ),
        Emotion.MYSTERIOUS to listOf(
            "fog", "shadow", "moon", "mask", "cave"
        ),
        Emotion.ROMANTIC to listOf(
            "heart", "rose", "candle", "wine glass", "sunset"
        ),
        Emotion.ENERGETIC to listOf(
            "fireworks", "sports", "lightning", "wave", "runner"
        ),
        Emotion.CALM to listOf(
            "lake", "tree", "mountain", "book", "hammock"
        )
    )

    /**
     * Generate sample training data
     * @param numSamples Number of samples to generate
     * @return Pair of inputs and labels
     */
    fun generateTrainingData(numSamples: Int): Pair<List<ClassifierInput>, List<Emotion>> {
        val inputs = mutableListOf<ClassifierInput>()
        val labels = mutableListOf<Emotion>()

        repeat(numSamples) {
            // Choose a random emotion
            val emotion = Emotion.entries[Random.nextInt(Emotion.entries.size)]

            // Generate input based on emotion
            val input = generateInputForEmotion(emotion)

            inputs.add(input)
            labels.add(emotion)
        }

        return Pair(inputs, labels)
    }

    /**
     * Generate input for a specific emotion
     * @param emotion The target emotion
     * @return Classifier input
     */
    fun generateInputForEmotion(emotion: Emotion): ClassifierInput {
        // Select main color related to emotion
        val mainColor = emotionColors[emotion]?.random() ?:
        Color("gray", 128, 128, 128)

        // Select 1-2 secondary colors
        val numSecondaryColors = Random.nextInt(1, 3)
        val secondaryColors = emotionColors[emotion]?.shuffled()?.take(numSecondaryColors) ?: emptyList()

        // Select 1-3 objects associated with emotion
        val numObjects = Random.nextInt(1, 4)
        val objectLabels = emotionObjects[emotion]?.shuffled()?.take(numObjects) ?: emptyList()

        // Create DetectedObject instances for the selected object labels
        val objects = objectLabels.map { createDetectedObject(it) }

        // Create additional details
        val additionalDetails = mapOf(
            "intensity" to (1..10).random().toString(),
            "complexity" to (1..10).random().toString()
        )

        return ClassifierInput(
            mainColor = mainColor,
            secondaryColors = secondaryColors,
            objects = objects,
            additionalDetails = additionalDetails
        )
    }

    /**
     * Create a DetectedObject with the given label
     * @param label The label for the detected object
     * @return A DetectedObject instance
     */
    private fun createDetectedObject(label: String): DetectedObject {
        // Generate random bounding box coordinates (0.0 to 1.0)
        val x = Random.nextFloat() * 0.8f
        val y = Random.nextFloat() * 0.8f
        val width = Random.nextFloat() * 0.3f + 0.1f
        val height = Random.nextFloat() * 0.3f + 0.1f

        // Generate random probability (0.7 to 1.0 for more realistic confidence)
        val probability = Random.nextFloat() * 0.3f + 0.7f

        return DetectedObject(
            label = label,
            xMin = x,
            yMin = y,
            xMax = width,
            yMax = height,
            probability = probability
        )
    }

    /**
     * Get the list of object categories
     */
    fun getObjectCategories(): List<String> = objectCategories

    /**
     * Get the list of object attributes
     */
    fun getObjectAttributes(): List<String> = objectAttributes
}