plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.yaperecipes"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.yaperecipes"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.example.yaperecipes.HiltTestRunner"
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
        viewBinding = true
        dataBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.junit.ktx)

    //Testing
    testImplementation(libs.junit)
    testImplementation (libs.androidx.core.testing)
    testImplementation (libs.mockk)
    testImplementation (libs.mockito.core)
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation (libs.androidx.core.testing)
    androidTestImplementation (libs.hilt.android.testing)
    androidTestImplementation (libs.mockito.android)
    debugImplementation(libs.androidx.ui.tooling)
    kaptAndroidTest (libs.hilt.android.compiler)
    debugImplementation (libs.androidx.fragment.testing)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.converter.gson)

    // Material Design
    implementation (libs.material.v1110)

    // Glide
    implementation (libs.glide)

    // Dagger Hilt
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    //Maps
    implementation (libs.play.services.maps)

    //Lottie (animations)
    implementation (libs.lottie)

    //Compose
    implementation ("androidx.compose.ui:ui:1.6.7")
    implementation ("androidx.compose.material:material:1.6.7")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.6.7")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation ("androidx.activity:activity-compose:1.9.0")
    implementation ("androidx.compose.material3:material3:1.2.1")



}