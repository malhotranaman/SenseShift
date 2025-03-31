import org.jetbrains.kotlinx.dl.api.inference.loaders.TFModelHub
import org.jetbrains.kotlinx.dl.api.inference.loaders.TFModels
import org.jetbrains.kotlinx.dl.api.inference.objectdetection.DetectedObject
import org.jetbrains.kotlinx.dl.impl.inference.imagerecognition.ImageRecognitionModel
import org.jetbrains.kotlinx.dl.impl.preprocessing.image.ImageConverter
import org.jetbrains.kotlinx.dl.onnx.inference.ONNXModelHub
import org.jetbrains.kotlinx.dl.onnx.inference.ONNXModels
import org.jetbrains.kotlinx.dl.onnx.inference.executionproviders.ExecutionProvider
import org.jetbrains.kotlinx.dl.onnx.inference.inferAndCloseUsing
import org.tensorflow.op.core.Placeholder
import java.io.File

fun main() {
    val modelHub = ONNXModelHub(cacheDirectory = File("cache/pretrainedModels"))
    val model = ONNXModels.PoseDetection.MoveNetMultiPoseLighting.pretrainedModel(modelHub)

//    val model = ONNXModels.ObjectDetection.SSDMobileNetV1.pretrainedModel(modelHub = modelHub)
    val model2: ImageRecognitionModel = ONNXModels.CV.ResNet152v2.pretrainedModel(modelHub)
    val model3 = ONNXModels.CV.ResNet50.pretrainedModel(modelHub)

    val image = ImageConverter.toBufferedImage(File("/Users/namanmalhotra/IdeaProjects/MoodRelate/src/main/kotlin/resource/image3.jpg"))

//    model2.use {
//        val image = ImageConverter.toBufferedImage(File("/Users/namanmalhotra/IdeaProjects/MoodRelate/src/main/kotlin/resource/image2.jpg"))
//
//        val recognizedObject = it.predictObject(imageFile = image)
//        println(recognizedObject)
//
//        val top10 = it.predictTopKObjects(imageFile = image, topK = 10)
//        println(top10.toString())
//    }

    val detectedPoses = model.inferAndCloseUsing(ExecutionProvider.CPU()) {
        model.detectPoses(image = image)
    }

    println(detectedPoses)

}