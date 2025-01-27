buildscript {
    configurations.classpath {
        resolutionStrategy {
            val ktlintVersion = "0.50.0"
            force(
                // "com.pinterest.ktlint:ktlint-rule-engine:$ktlintVersion",
                // "com.pinterest.ktlint:ktlint-rule-engine-core:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-cli-reporter-core:$ktlintVersion",
                "com.pinterest.ktlint:ktlint-ruleset-standard:$ktlintVersion",
            )
        }
    }
}
plugins {
    kotlin("multiplatform") version "1.9.25"
    kotlin("plugin.serialization") version "2.1.10"
    id("org.jmailen.kotlinter") version "3.16.0"
}

group = "net.turtton"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://maven.turtton.net")
}

kotlin {
    jvm {
        compilations {
            all {
                kotlinOptions.jvmTarget = "1.8"
            }
        }
    }
    js {
        nodejs {
            testTask {
                useMocha()
            }
        }
        binaries.library()
        generateTypeScriptDefinitions()
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains:markdown:0.7.3")
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:1.0.0-pre.856"))
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-emotion")
                implementation("org.jetbrains.kotlin-wrappers:kotlin-react-router-dom")

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.1")

                implementation("io.github.xxfast:kstore:0.9.1")?.version?.also {
                    implementation("io.github.xxfast:kstore-file:$it")
                }

                // I'm waiting for supporting to ESM package support!!!!!!!!!!
                // implementation(npm("refractor", "4.8.1"))
                // implementation(npm("hast-util-to-html", "8.0.4"))
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("io.github.mysticfall:kotlin-react-test:18.2.0-pre.522+build.19")
            }
        }
        // For converting JavaFlex to Kotlin
        val jvmMain by getting
    }
}
