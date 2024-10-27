plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.rais.movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rais.movies"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        buildConfigField("String", "API_KEY", "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIwMzA2ZTViZDBmMTVkOGFjZTk1MDFkNTRjMmVmNzVlZSIsIm5iZiI6MTcyOTgyNDM5NS41MzkyOTgsInN1YiI6IjU4Yzc3NjExYzNhMzY4M2RjZDAwNmE3YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.ERncp00VNnpPxKvE_svD3mNu23JGJEBr-rkwOf0A_CI")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // coroutines
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // view pagger
    implementation(libs.view.pager)

    // dagger - hilt
    implementation(libs.dagger.hilt)
    kapt(libs.hilt.compiler)

    // glide
    implementation(libs.glide)
    kapt(libs.glide.compiler)

    // room
    implementation("androidx.room:room-ktx:2.5.1")
    implementation("androidx.room:room-runtime:2.5.1")
    kapt("androidx.room:room-compiler:2.5.1")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")

    //mockito
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)

    // For testing with JUnit
    testImplementation("junit:junit:4.13.2")

    // For mocking
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("'org.mockito:mockito-inline:4.0.0")

    // For coroutines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")

    // For LiveData testing
    testImplementation("androidx.arch.core:core-testing:2.1.0")

    // Hilt testing
    testImplementation("com.google.dagger:hilt-android-testing:2.40.5")
    kaptTest("com.google.dagger:hilt-android-compiler:2.40.5")

    //special testing
    testImplementation(libs.androidx.core.testing) // InstantTaskExecutorRule
    testImplementation(libs.kotlinx.coroutines.test) //TestCoroutineDispatcher

    //special instrumentation testing
    androidTestImplementation(libs.androidx.core.testing) // InstantTaskExecutorRule
    androidTestImplementation(libs.kotlinx.coroutines.test) //TestDispatcher


}