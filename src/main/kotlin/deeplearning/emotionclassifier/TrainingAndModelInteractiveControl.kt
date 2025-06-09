package org.example.userinterface.control.deeplearning.emotionclassifier

import kotlinx.coroutines.runBlocking
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import java.io.File

fun main() =
    runBlocking {
        println("Emotion Classifier - Deep Learning with Kotlin (ONNX Runtime)")
        println("----------------------------------------------------------")

        // Initialize training data generator
        val dataGenerator = TrainingDataGenerator()
        val objectCategories = dataGenerator.getObjectCategories()
        val objectAttributes = dataGenerator.getObjectAttributes()

        // Initialize feature extractor
        val featureExtractor =
            FeatureExtractor(
                objectCategories = objectCategories,
                objectAttributes = objectAttributes,
            )

        // Initialize classifier model
        val classifier =
            EmotionClassifier(
                objectCategories = objectCategories,
                objectAttributes = objectAttributes,
                featureExtractor = featureExtractor,
            )

        try {
            // Check if model exists, otherwise explain training process
            val modelFile = File("/Users/namanmalhotra/IdeaProjects/MoodRelate/src/main/python/emotion_classifier_model.onnx")
            if (modelFile.exists()) {
                println("Loading existing ONNX model...")
                classifier.loadModel()
            } else {
                println("No ONNX model found. The ONNX model needs to be created externally.")
                println("Steps to create an ONNX model:")
                println("1. Generate training data using this application")
                println("2. Export features and labels to files")
                println("3. Train the model in Python/TensorFlow/PyTorch")
                println("4. Export the trained model to ONNX format")
                println("5. Place the ONNX model file in the application directory")
                println("\nWould you like to generate and export training data? (y/n)")

                val response = readLine()?.trim()?.lowercase()
                if (response == "y" || response == "yes") {
                    println("Generating training data...")
                    val (trainingInputs, trainingLabels) = dataGenerator.generateTrainingData(1000)

                    // Export training data
                    exportTrainingData(trainingInputs, trainingLabels, featureExtractor)

                    println("Training data exported. Use this data to train your model in your preferred framework.")
                    println("After training, export to ONNX format and place the model file in the application directory.")
                    return@runBlocking
                } else {
                    println("Exiting application. Please provide an ONNX model to continue.")
                    return@runBlocking
                }
            }

            // Demonstrate prediction with some examples
            println("\nTesting the model with some examples:")

            // Test with each emotion
            Emotion.entries.forEach { emotion ->
                val testInput = dataGenerator.generateInputForEmotion(emotion)
                val prediction = classifier.predict(testInput)

                println("\nInput with expected emotion: $emotion")
                println("Main color: ${testInput.mainColor.name}")
                println("Objects: ${testInput.objects.joinToString(", ") { it.label ?: "Unknown" }}")
                println("Predicted emotion: ${prediction.predictedEmotion}")
                println("Confidence: ${prediction.confidenceScores[prediction.predictedEmotion]?.times(100)}%")
            }

            // Interactive mode
            println("\n\nInteractive Mode")
            println("----------------")
            println("Enter 'q' to quit")

            while (true) {
                println("\nEnter main color (name,R,G,B), e.g., 'red,255,0,0':")
                val colorInput = readLine() ?: ""
                if (colorInput.equals("q", ignoreCase = true)) break

                val colorParts = colorInput.split(",")
                val mainColor =
                    if (colorParts.size == 4) {
                        try {
                            Color(
                                name = colorParts[0].trim(),
                                red = colorParts[1].trim().toInt(),
                                green = colorParts[2].trim().toInt(),
                                blue = colorParts[3].trim().toInt(),
                            )
                        } catch (e: Exception) {
                            println("Invalid color format. Using default.")
                            Color("white", 255, 255, 255)
                        }
                    } else {
                        println("Invalid color format. Using default.")
                        Color("white", 255, 255, 255)
                    }

                println("Enter objects (comma separated), e.g., 'sun,beach,ocean':")
                val objectsInput = readLine() ?: ""
                if (objectsInput.equals("q", ignoreCase = true)) break

                val objects =
                    objectsInput
                        .split(",")
                        .filter { it.isNotBlank() }
                        .map {
                            val label = it.trim()
                            // Create a DetectedObject with a reasonable confidence
                            DetectedObject(
                                label = label,
                                xMin = Math.random().toFloat() * 0.8f,
                                yMin = Math.random().toFloat() * 0.8f,
                                xMax = Math.random().toFloat() * 0.3f + 0.1f,
                                yMax = Math.random().toFloat() * 0.3f + 0.1f,
                                probability = Math.random().toFloat() * 0.3f + 0.7f,
                            )
                        }

                // Create input for prediction
                val input =
                    ClassifierInput(
                        mainColor = mainColor,
                        objects = objects,
                    )

                // Make prediction
                val prediction = classifier.predict(input)

                println("\nPredicted Emotion: ${prediction.predictedEmotion}")
                println("Confidence Scores:")
                prediction.confidenceScores
                    .toList()
                    .sortedByDescending { it.second }
                    .take(3)
                    .forEach { (emotion, score) ->
                        println("  ${emotion.name}: ${score * 100}%")
                    }
            }
        } finally {
            // Clean up resources
            classifier.close()
        }
    }

/**
 * Export training data to files for external model training
 */
fun exportTrainingData(
    inputs: List<ClassifierInput>,
    labels: List<Emotion>,
    featureExtractor: FeatureExtractor,
) {
    val featuresFile = File("/Users/namanmalhotra/IdeaProjects/MoodRelate/training_features.csv")
    val labelsFile = File("/Users/namanmalhotra/IdeaProjects/MoodRelate/training_labels.csv")

    // Export features
    featuresFile.bufferedWriter().use { writer ->
        inputs.forEach { input ->
            val features = featureExtractor.extract(input)
            writer.write(features.joinToString(","))
            writer.newLine()
        }
    }

    // Export labels
    labelsFile.bufferedWriter().use { writer ->
        labels.forEach { emotion ->
            writer.write(emotion.ordinal.toString())
            writer.newLine()
        }
    }

    println("Exported ${inputs.size} training samples to:")
    println("- ${featuresFile.absolutePath}")
    println("- ${labelsFile.absolutePath}")
}

fun predictEmotions(
    colors: List<Color>,
    objectsDetected: List<DetectedObject>,
): ClassifierOutput {
    // Initialize training data generator
    val dataGenerator = TrainingDataGenerator()
    val objectCategories = dataGenerator.getObjectCategories()
    val objectAttributes = dataGenerator.getObjectAttributes()

    // Initialize feature extractor
    val featureExtractor =
        FeatureExtractor(
            objectCategories = objectCategories,
            objectAttributes = objectAttributes,
        )

    // Initialize classifier model
    val classifier =
        EmotionClassifier(
            objectCategories = objectCategories,
            objectAttributes = objectAttributes,
            featureExtractor = featureExtractor,
        )

    try {
        val modelFile = File("/Users/namanmalhotra/IdeaProjects/MoodRelate/src/main/python/emotion_classifier_model.onnx")
        if (modelFile.exists()) {
            println("Loading existing ONNX model...")
            classifier.loadModel()
        } else {
            throw Exception("ONNX Model is not found!")
        }

        val colorParts =
            colors
                .fold(
                    "",
                ) { acc: String, color: Color -> acc + "${color.name},${color.red},${color.green},${color.blue}" }
                .split(",")
        val mainColor =
            if (colorParts.size == 4) {
                try {
                    Color(
                        name = colorParts[0].trim(),
                        red = colorParts[1].trim().toInt(),
                        green = colorParts[2].trim().toInt(),
                        blue = colorParts[3].trim().toInt(),
                    )
                } catch (e: Exception) {
                    println("Invalid color format. Using default.")
                    Color("white", 255, 255, 255)
                }
            } else {
                println("Invalid color format. Using default.")
                Color("white", 255, 255, 255)
            }

        val objects =
            objectsDetected
                .filter { it.label?.isNotBlank() ?: false }

        // Create input for prediction
        val input =
            ClassifierInput(
                mainColor = mainColor,
                objects = objects,
            )

        return classifier.predict(input)

//        println("\nPredicted Emotion: ${prediction.predictedEmotion}")
//        println("Confidence Scores:")
//        prediction.confidenceScores
//            .toList()
//            .sortedByDescending { it.second }
//            .take(3)
//            .forEach { (emotion, score) ->
//                println("  ${emotion.name}: ${score * 100}%")
//            }
    } finally {
        // Clean up resources
        classifier.close()
    }
}
