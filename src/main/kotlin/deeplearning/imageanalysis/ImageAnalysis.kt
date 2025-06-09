package org.example.userinterface.control.deeplearning.imageanalysis

import org.example.userinterface.control.deeplearning.emotionclassifier.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import kotlin.math.sqrt

/**
 * Named common colors for reference
 */
private val COMMON_COLORS =
    listOf(
        Color("Red", 255, 0, 0),
        Color("Green", 0, 255, 0),
        Color("Blue", 0, 0, 255),
        Color("Yellow", 255, 255, 0),
        Color("Cyan", 0, 255, 255),
        Color("Magenta", 255, 0, 255),
        Color("White", 255, 255, 255),
        Color("Black", 0, 0, 0),
        Color("Gray", 128, 128, 128),
        Color("Orange", 255, 165, 0),
        Color("Purple", 128, 0, 128),
        Color("Brown", 165, 42, 42),
        Color("Pink", 255, 192, 203),
        Color("Olive", 128, 128, 0),
        Color("Navy", 0, 0, 128),
        Color("Teal", 0, 128, 128),
        Color("Maroon", 128, 0, 0),
        Color("Lime", 0, 255, 0),
        Color("Turquoise", 64, 224, 208),
        Color("Lavender", 230, 230, 250),
        Color("Beige", 245, 245, 220),
    )

/**
 * Analyzes an image file and extracts the main colors using k-means clustering
 * @param selectedImage The image file to analyze
 * @param numColors The number of main colors to extract (default: 5)
 * @return List of Color objects representing the dominant colors in the image
 */
fun mainColorsInImage(
    selectedImage: File,
    numColors: Int = 5,
): List<Color> {
    // Load image using standard Java/Kotlin image handling
    val originalImage = ImageIO.read(selectedImage)

    // Resize for performance
    val resizedImage = resizeImage(originalImage, 200, 200)

    // Extract pixel data
    val pixels = mutableListOf<IntArray>()
    for (y in 0 until resizedImage.height) {
        for (x in 0 until resizedImage.width) {
            val rgb = resizedImage.getRGB(x, y)
            val color = java.awt.Color(rgb)
            pixels.add(intArrayOf(color.red, color.green, color.blue))
        }
    }

    // Find dominant colors using k-means clustering
    val colorClusters = kMeansClustering(pixels, numColors)

    // Convert to custom Color objects with names
    return colorClusters.map { rgbValues ->
        val (r, g, b) = rgbValues
        val colorName = findClosestColorName(r, g, b)
        Color(colorName, r, g, b)
    }
}

/**
 * Resize an image to the specified dimensions
 * @param image Original image
 * @param width Target width
 * @param height Target height
 * @return Resized BufferedImage
 */
private fun resizeImage(
    image: BufferedImage,
    width: Int,
    height: Int,
): BufferedImage {
    val resized = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g = resized.createGraphics()
    g.drawImage(image, 0, 0, width, height, null)
    g.dispose()
    return resized
}

/**
 * Perform k-means clustering to find dominant colors
 * @param pixels List of RGB values for each pixel
 * @param k Number of clusters (colors) to find
 * @return List of IntArray with RGB values for each dominant color
 */
private fun kMeansClustering(
    pixels: List<IntArray>,
    k: Int,
): List<IntArray> {
    if (pixels.isEmpty()) return emptyList()

    // Initialize centroids randomly
    val centroids = mutableListOf<IntArray>()
    val random = kotlin.random.Random(System.currentTimeMillis())
    val indices = (0 until pixels.size).shuffled(random).take(k)

    for (i in indices) {
        centroids.add(pixels[i].clone())
    }

    // Run k-means for 10 iterations
    for (iteration in 0 until 10) {
        // Assign pixels to clusters
        val clusters = IntArray(pixels.size) { -1 }

        for (i in pixels.indices) {
            val pixel = pixels[i]
            var minDistance = Double.MAX_VALUE
            var closestCluster = 0

            for (j in centroids.indices) {
                val distance = colorDistance(pixel, centroids[j])
                if (distance < minDistance) {
                    minDistance = distance
                    closestCluster = j
                }
            }

            clusters[i] = closestCluster
        }

        // Update centroids
        val newCentroids = Array(k) { IntArray(3) }
        val counts = IntArray(k)

        for (i in pixels.indices) {
            val cluster = clusters[i]
            val pixel = pixels[i]

            newCentroids[cluster][0] += pixel[0]
            newCentroids[cluster][1] += pixel[1]
            newCentroids[cluster][2] += pixel[2]
            counts[cluster]++
        }

        for (j in 0 until k) {
            if (counts[j] > 0) {
                centroids[j][0] = newCentroids[j][0] / counts[j]
                centroids[j][1] = newCentroids[j][1] / counts[j]
                centroids[j][2] = newCentroids[j][2] / counts[j]
            }
        }
    }

    // Calculate proportions for sorting
    val finalClusters = IntArray(pixels.size)
    for (i in pixels.indices) {
        var minDistance = Double.MAX_VALUE
        var closestCluster = 0

        for (j in centroids.indices) {
            val distance = colorDistance(pixels[i], centroids[j])
            if (distance < minDistance) {
                minDistance = distance
                closestCluster = j
            }
        }

        finalClusters[i] = closestCluster
    }

    val counts = IntArray(k)
    for (cluster in finalClusters) {
        counts[cluster]++
    }

    // Create results and sort by frequency
    val colorArrays = mutableListOf<Pair<IntArray, Int>>()
    for (i in 0 until k) {
        if (counts[i] > 0) {
            val rgb = IntArray(3)
            rgb[0] = centroids[i][0].coerceIn(0, 255)
            rgb[1] = centroids[i][1].coerceIn(0, 255)
            rgb[2] = centroids[i][2].coerceIn(0, 255)
            colorArrays.add(Pair(rgb, counts[i]))
        }
    }

    // Sort by count (descending) and return just the RGB values
    return colorArrays.sortedByDescending { it.second }.map { it.first }
}

/**
 * Find the closest named color to an RGB value
 * @param r Red component (0-255)
 * @param g Green component (0-255)
 * @param b Blue component (0-255)
 * @return Name of the closest color
 */
private fun findClosestColorName(
    r: Int,
    g: Int,
    b: Int,
): String {
    var closestColor = COMMON_COLORS.first()
    var closestDistance = Double.MAX_VALUE

    for (color in COMMON_COLORS) {
        val distance =
            colorDistanceSquared(
                r,
                g,
                b,
                color.red,
                color.green,
                color.blue,
            )

        if (distance < closestDistance) {
            closestDistance = distance
            closestColor = color
        }
    }

    // If it's a shade, add descriptor
    val brightness = (r + g + b) / 3
    val prefix =
        when {
            brightness < 64 -> "Dark "
            brightness > 220 -> "Light "
            else -> ""
        }

    return if (prefix.isNotEmpty() && closestColor.name != "Black" && closestColor.name != "White") {
        "$prefix${closestColor.name}"
    } else {
        closestColor.name
    }
}

/**
 * Calculate Euclidean distance between two colors in RGB space
 */
private fun colorDistance(
    c1: IntArray,
    c2: IntArray,
): Double = sqrt(colorDistanceSquared(c1[0], c1[1], c1[2], c2[0], c2[1], c2[2]))

/**
 * Calculate squared Euclidean distance between two colors in RGB space
 */
private fun colorDistanceSquared(
    r1: Int,
    g1: Int,
    b1: Int,
    r2: Int,
    g2: Int,
    b2: Int,
): Double {
    val dr = r1 - r2
    val dg = g1 - g2
    val db = b1 - b2
    return (dr * dr + dg * dg + db * db).toDouble()
}
