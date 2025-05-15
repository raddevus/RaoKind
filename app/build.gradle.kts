plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.newlibre.raokind"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.newlibre.raokind"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.ktor.client.core.v239)

    // Ktor Engine (Choose one)
    implementation(libs.ktor.client.cio.v239) // Lightweight & async
    // OR
    implementation(libs.ktor.client.okhttp) // Uses OkHttp under the hood

    // JSON Serialization
    implementation(libs.ktor.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Kotlinx Serialization (Required for JSON parsing)
    implementation(libs.kotlinx.serialization.json.v160)

    // Logging (Optional, but useful for debugging)
    implementation(libs.ktor.client.logging)
    implementation(libs.kotlinx.serialization.json.v162)

    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation.v230)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.kotlinx.serialization.json.v162)
    implementation("com.google.code.gson:gson:2.10.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}