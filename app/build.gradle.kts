import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version libs.versions.ksp.get() apply false//agrega este plugin
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.example.mybabysiteapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mybabysiteapplication"
        minSdk = 26
        targetSdk = 34
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
        compose = true//habilita Jetpack Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Asegúrate de usar la versión correcta
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui:1.7.7")
    implementation(libs.material3)
    implementation(libs.ui.tooling.preview)
    implementation(libs.androidx.activity.compose.v172)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    //Dependencias de Room usando libs.version.tml
    implementation(libs.androidx.room.runtime.v261)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.tracing.perfetto.handshake)
    implementation(libs.volley)
    implementation(libs.firebase.crashlytics.buildtools)
    annotationProcessor(libs.androidx.room.compiler.v261)
    implementation(libs.androidx.room.ktx.v261)
    implementation (libs.androidx.room.runtime)//Runtime para Room
    implementation(libs.ktor.client.okhttp)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.androidx.room.compiler) // Para Kotlin
    implementation(libs.androidx.room.ktx) // Para Java
    ksp(libs.hilt.android.compiler)
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    //ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.kotlin.serialization)
    implementation(libs.ktor.client.darwin)
    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation(libs.kotlinx.coroutines.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
// 🔽 Aplicar el plugin de Hilt AL FINAL del archivo
apply(plugin = "dagger.hilt.android.plugin")