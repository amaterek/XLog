plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvm()
    @Suppress("OPT_IN_USAGE")
    wasmJs { browser {} }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.jvmTarget.get()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "util.log"
            isStatic = true
        }
    }

    sourceSets {
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.mockk.common)
        }
        jvmTest.dependencies {
            implementation(libs.junit)
            implementation(libs.mockk)
        }
    }
}

android {
    namespace = "amaterek.util.log"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    dependencies {
        testImplementation(libs.junit)
        testImplementation(libs.mockk)
    }
}