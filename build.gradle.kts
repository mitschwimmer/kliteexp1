// We can choose between Gradle complaining about incompatible features or the IDE complaining about unstable API
@file:Suppress("UnstableApiUsage")

plugins {
    kotlin("jvm") version "1.9.21"
    `jvm-test-suite`
}

group = "de.mitschwimmer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.codeborne.klite:klite-server:1.6.2")
    implementation("com.github.codeborne.klite:klite-json:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test")
            }
        }
    }
}

kotlin {
    jvmToolchain(21)
}