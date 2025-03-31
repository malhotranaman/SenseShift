plugins {
    kotlin("jvm") version "2.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.20")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-api:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-tensorflow:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-onnx:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-visualization-jvm:0.5.2")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")


    // build.gradle.kts
    implementation ("org.tensorflow:libtensorflow:1.15.0")
    implementation ("org.tensorflow:libtensorflow_jni_gpu:1.15.0")
    implementation("org.tensorflow:tensorflow-core-platform:1.0.0")
}

//dependencies {
//    testImplementation(kotlin("test"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.20")
//    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-api:0.6.0-alpha-1")
//    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-onnx:0.6.0-alpha-1")
//    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-visualization-jvm:0.6.0-alpha-1")
//    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
//    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
//    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
//
//    // Use TensorFlow 2.x dependencies
//    implementation("org.tensorflow:tensorflow-core-platform:2.17.0")  // Update to TensorFlow 2.x
//    implementation("org.tensorflow:tensorflow-core-api:2.17.0")      // Update to TensorFlow 2.x
//}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

