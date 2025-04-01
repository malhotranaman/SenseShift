package org.example.userinterface.control.deeplearning.emotionclassifier

import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject

/**
 * Converts human-readable input into numerical features for the ONNX model
 */
class FeatureExtractor(
    private val objectCategories: List<String>,
    private val objectAttributes: List<String>,
    private val vocabularySize: Int = 1000,
    private val maxObjects: Int = 5,
    private val maxColors: Int = 3
) {
    // Vocabulary indices for words in object names, attributes, etc.
    private val wordToIndex = mutableMapOf<String, Int>()
    private var nextIndex = 1  // 0 is reserved for padding

    /**
     * Extract features from classifier input
     * @param input The raw classifier input
     * @return FloatArray of numerical features for ONNX model input
     */
    fun extract(input: ClassifierInput): FloatArray {
        val features = mutableListOf<Float>()

        // Extract color features
        features.addAll(extractColorFeatures(input.mainColor))

        // Extract secondary colors (up to maxColors)
        val secondaryColorsFeatures = input.secondaryColors
            .take(maxColors - 1)
            .flatMap { extractColorFeatures(it) }

        // Pad if we have fewer than max secondary colors
        val colorPadding = (maxColors - 1 - input.secondaryColors.size).coerceAtLeast(0)
        features.addAll(secondaryColorsFeatures)
        features.addAll(List(colorPadding * 3) { 0f })

        // Extract object features (up to maxObjects)
        val objectFeatures = input.objects
            .take(maxObjects)
            .flatMap { extractObjectFeatures(it) }

        // Pad if we have fewer than max objects
        val objectsPadding = (maxObjects - input.objects.size).coerceAtLeast(0)
        features.addAll(objectFeatures)
        features.addAll(List(objectsPadding * (objectCategories.size + objectAttributes.size)) { 0f })

        return features.toFloatArray()
    }

    /**
     * Extract color features
     * @param color The color to extract features from
     * @return List of normalized RGB values
     */
    private fun extractColorFeatures(color: Color): List<Float> {
        val (r, g, b) = color.normalizedRGB()
        return listOf(r, g, b)
    }

    /**
     * Extract object features
     * @param obj The object to extract features from
     * @return List of one-hot encoded category and attribute features
     */
    private fun extractObjectFeatures(obj: DetectedObject): List<Float> {
        // One-hot encode category
        val categoryFeatures = objectCategories.map { category ->
            if (obj.label?.equals(category, ignoreCase = true) == true) 1f else 0f
        }

        // One-hot encode attributes
        val attributeFeatures = objectAttributes.map { attr ->
            if (obj.label?.lowercase()?.contains(attr.lowercase()) == true) 1f else 0f
        }

        return categoryFeatures + attributeFeatures
    }

    /**
     * Get word index for vocabulary
     * @param word The word to get index for
     * @return The index in the vocabulary
     */
    private fun getWordIndex(word: String): Int {
        return wordToIndex.getOrPut(word.lowercase()) {
            if (nextIndex < vocabularySize) nextIndex++ else 0
        }
    }

    /**
     * Get the input shape for the ONNX model
     * @return Long array representing input shape
     */
    fun getInputShape(): LongArray {
        // Calculate total feature size
        val colorFeaturesSize = 3 * maxColors  // RGB for each color
        val objectFeaturesSize = maxObjects * (objectCategories.size + objectAttributes.size)
        val totalFeatures = colorFeaturesSize + objectFeaturesSize

        // Batch size of 1, with totalFeatures dimensions
        return longArrayOf(1, totalFeatures.toLong())
    }
}