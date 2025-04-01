plugins {
    kotlin("jvm") version "2.1.0"
    id("org.jetbrains.compose") version "1.7.0"

    id("org.jetbrains.kotlin.plugin.compose") version "2.1.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.1.20")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-api:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-tensorflow:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-onnx:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-visualization:0.5.2")
    implementation("org.jetbrains.kotlinx:kotlin-deeplearning-dataset:0.5.2")
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
    implementation("org.apache.logging.log4j:log4j-core:2.17.1")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")


    // build.gradle.kts
//    implementation("org.tensorflow:tensorflow-core-platform:0.3.3")
//    implementation("org.tensorflow:tensorflow-core-native:1.0.0")
//    implementation("org.tensorflow:tensorflow-framework:1.0.0")
//    implementation("org.tensorflow:ndarray:1.0.0")
//    implementation("org.tensorflow:tensorflow-lite-task-vision:0.4.0")
//    // Import the GPU delegate plugin Library for GPU inference
//    implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.0")
//    implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")

    implementation(compose.desktop.currentOs)
    implementation(compose.material3)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

