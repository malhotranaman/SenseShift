package org.example.userinterface.control.deeplearning.imageanalysis

import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import org.jetbrains.kotlinx.dl.onnx.inference.ONNXModelHub
import org.jetbrains.kotlinx.dl.onnx.inference.ONNXModels
import java.awt.*
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.JFrame
import kotlin.math.abs

fun detectAndHighlightObjects(fileToHighlight: File): Pair<File, List<DetectedObject>> {
    try {
        // Change extension to match format
        val outfile = File("/Users/namanmalhotra/IdeaProjects/MoodRelate/src/main/kotlin/resources/objecthighlighted/".plus(fileToHighlight.absolutePath.split("/").last()))

        val recognizer = ImageRecognition(fileToHighlight)
        val imageAndObjects = recognizer.visualise()

        val success = ImageIO.write(imageAndObjects.first, "jpg", outfile)

        if (success) {
            println("Image saved successfully to ${outfile.absolutePath}")
        } else {
            println("Failed to save image - no appropriate writer found")
        }

        return (outfile to imageAndObjects.second)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    throw Exception("Unreachable-Code")
}

class ImageRecognition(
    private val targetFile: File,
) {

    private fun detectObjects(): List<DetectedObject> {
        val modelHub = ONNXModelHub(cacheDirectory = File("cache/pretrainedModels"))

        val model = modelHub.loadPretrainedModel(ONNXModels.ObjectDetection.SSD)

        model.use { detectionModel ->
            println(detectionModel)

            val imageFile = targetFile
            val detectedObjects = detectionModel.detectObjects(imageFile = imageFile, topK = 20)

            detectedObjects.forEach {
                println("Found ${it.label} with probability ${it.probability}")
            }

            return detectedObjects
        }
    }

    class JPanel(
        image: File,
        private val detectedObjects: List<DetectedObject>
    ) : javax.swing.JPanel() {
        private var bufferedImage = ImageIO.read(image)

        override fun paint(graphics: Graphics) {
            super.paint(graphics)
            graphics.drawImage(bufferedImage, 0, 0, null)

            // Cast to Graphics2D once at the beginning
            val g2d = graphics as Graphics2D

            detectedObjects.forEach {
                val top = it.yMin * bufferedImage.height
                val left = it.xMin * bufferedImage.width
                val bottom = it.yMax * bufferedImage.height
                val right = it.xMax * bufferedImage.width

                if (abs(top - bottom) > 300 || abs(right - left) > 300) return@forEach

                // Draw the label
                g2d.color = Color.ORANGE
                g2d.font = Font("Courier New", Font.BOLD, 17)
                g2d.drawString(" ${it.label} : ${it.probability}", left.toInt(), top.toInt() - 8)

                // Set up the rectangle drawing
                val stroke1: Stroke = BasicStroke(6f)
                g2d.color = Color.RED
                g2d.stroke = stroke1

                // Draw the rectangle at the correct position with positive width and height
                val x = left.toInt()
                val y = top.toInt()
                val width = (right - left).toInt()
                val height = (bottom - top).toInt()

                g2d.drawRect(x, y, width, height)
            }
        }

        override fun getPreferredSize(): Dimension {
            return Dimension(bufferedImage.width, bufferedImage.height)
        }

        override fun getMinimumSize(): Dimension {
            return Dimension(bufferedImage.width, bufferedImage.height)
        }
    }

    fun visualise(): Pair<BufferedImage, List<DetectedObject>> {
        val frame = JFrame("Detected Objects")
        val detectedObjects: List<DetectedObject> = detectObjects()
        @Suppress("UNCHECKED_CAST")
        frame.contentPane.add(JPanel(targetFile, detectedObjects))
        frame.pack()
        frame.setLocationRelativeTo(null)
        return (createImage(frame) to detectedObjects)
    }

    private fun createImage(frame: JFrame): BufferedImage {
        frame.pack()

        val contentPane = frame.contentPane
        val w = contentPane.width
        val h = contentPane.height

        if (w <= 0 || h <= 0) {
            throw IllegalStateException("Invalid frame dimensions: ${w}x${h}")
        }

        val bi = BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
        val g = bi.createGraphics()

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)

        contentPane.paint(g)

        g.dispose()

        return bi
    }
}
