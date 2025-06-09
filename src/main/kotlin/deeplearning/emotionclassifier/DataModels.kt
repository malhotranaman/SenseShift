package org.example.userinterface.control.deeplearning.emotionclassifier

import kotlinx.serialization.Serializable
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject

/**
 * Enumeration of emotions/moods that our classifier can predict
 */
enum class Emotion(
    val description: String,
) {
    HAPPY(listOf(
        "dog", "puppy", "ice cream", "birthday cake", "balloon",
        "playground", "beach", "confetti", "gift", "candy",
        "sunshine", "rainbow", "carousel", "swing", "toy",
        "bicycle", "flower", "festival", "fireworks", "concert"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    SAD(listOf(
        "rain", "gray clouds", "wilted flower", "broken toy",
        "empty chair", "lone shoe", "abandoned house", "cemetery",
        "hospital bed", "fallen leaf", "shattered glass", "tissue box",
        "closed door", "empty bottle", "torn book", "crumpled paper",
        "black umbrella", "crutches", "lost mitten", "melting ice cream"
    ).foldRight("") {acc: String, cur: String -> acc + ", " +cur}),
    ANGRY(listOf(
        "broken glass", "traffic jam", "alarm clock", "burnt food",
        "wasp", "thorn", "shattered plate", "crashed car",
        "spilled coffee", "flat tire", "tangled wires", "locked door",
        "stain", "thunderstorm", "bill", "weeds", "leaking faucet",
        "error message", "smartphone with cracked screen", "stubbed toe"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    NEUTRAL(listOf(
        "chair", "wall", "book", "pencil", "clock", "table",
        "door", "window", "floor", "ceiling", "paper", "rock",
        "sidewalk", "fence", "shelf", "box", "bowl", "plate",
        "mug", "utensil", "button", "light switch", "lamp", "notebook"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    MYSTERIOUS(listOf(
        "fog", "shadow", "key", "cave", "mask", "abandoned building",
        "old map", "compass", "locked chest", "ancient book", "labyrinth",
        "old photograph", "attic", "moonlight", "telescope", "hidden door",
        "puzzle box", "whisper", "silhouette", "hourglass", "distant island"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    ROMANTIC(listOf(
        "rose", "candle", "wine glass", "sunset", "engagement ring",
        "heart locket", "love letter", "picnic basket", "chocolate box",
        "hammock for two", "gondola", "string lights", "dance floor",
        "gazebo", "fireplace", "champagne", "starry sky", "porch swing",
        "walkway with flower petals", "shared dessert", "lakeside bench"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    ENERGETIC(listOf(
        "lightning", "running shoes", "bicycle", "soccer ball",
        "fireworks", "drum", "wave", "roller coaster", "racing car",
        "basketball", "skateboard", "mountain bike", "surfboard",
        "coffee cup", "alarm clock", "jumping dog", "waterfall",
        "trampoline", "energy drink", "spinning top", "motorcycle"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
    CALM(listOf(
        "hammock", "rocking chair", "tea cup", "book", "lake",
        "sunset", "blanket", "slippers", "cat", "fireplace",
        "candle", "ocean view", "wind chimes", "garden bench",
        "sleeping pet", "gentle rain", "massage table", "meditation cushion",
        "scented bath", "warm socks", "starry night", "fish tank"
    ).foldRight("") {acc: String, cur: String -> acc + ", " + cur}),
}

/**
 * Color representation with RGB values
 */
@Serializable
data class Color(
    val name: String,
    val red: Int,
    val green: Int,
    val blue: Int,
) {
    // Normalize RGB values to range [0, 1]
    fun normalizedRGB(): Triple<Float, Float, Float> = Triple(red / 255f, green / 255f, blue / 255f)
}

/**
 * Input for the emotion classifier containing all relevant details
 */
@Serializable
data class ClassifierInput(
    val mainColor: Color,
    val secondaryColors: List<Color> = emptyList(),
    val objects: List<DetectedObject> = emptyList(),
    val additionalDetails: Map<String, String> = emptyMap(),
)

/**
 * Output from the emotion classifier
 */
@Serializable
data class ClassifierOutput(
    val predictedEmotion: Emotion,
    val confidenceScores: Map<Emotion, Float>,
)
