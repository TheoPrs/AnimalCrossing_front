plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.animalcrossing"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.animalcrossing"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Jetpack Compose dependencies
    implementation(libs.androidx.material) // Material 2 (optionnel si tu utilises Material 3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3.material32)
    implementation(libs.ui)
    implementation(libs.coil.compose)

    implementation(platform("io.github.jan-tennert.supabase:bom:3.0.1"))
    implementation("io.github.jan-tennert.supabase:postgrest-kt")
    implementation("io.github.jan-tennert.supabase:auth-kt")
    implementation("io.github.jan-tennert.supabase:realtime-kt")
    implementation("io.ktor:ktor-client-android:3.0.0")


    // Navigation for Jetpack Compose
    implementation(libs.androidx.navigation.compose)

    // AndroidX Core and Lifecycle
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Activity for Compose
    implementation(libs.androidx.activity.compose)

    // Test dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit and Coil
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.coil.compose.v200)

    // Ktor for HTTP Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio) // CIO engine pour les requêtes réseau
    implementation(libs.ktor.client.content.negotiation) // Plugin pour la négociation de contenu
    implementation(libs.ktor.serialization.kotlinx.json) // Serialization avec Kotlinx
    implementation(libs.ktor.client.logging) // Logging HTTP (optionnel)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation("io.coil-kt:coil-compose:2.0.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.5.1")

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

configurations.all {
    resolutionStrategy {
        force ("org.jetbrains.kotlinx:kotlinx-serialization-core:1.5.1")
        force ("org.jetbrains.kotlinx:kotlinx-serialization-core-jvm:1.5.1")
    }
}
