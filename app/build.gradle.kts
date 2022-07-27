plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    compileSdk = Apps.compileSdk

    defaultConfig {
        applicationId = Apps.applicationId
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        dataBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libs.kotlin)
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerView)
    implementation(Libs.swipeRefreshLayout)

    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    implementation(Libs.core)
    implementation(Libs.activity)
    implementation(Libs.fragment)
    implementation(Libs.lifeCycleLiveData)

    implementation(Libs.material)

    implementation(Libs.rxJava)
    implementation(Libs.rxAndroid)

    implementation(Libs.glide)
    kapt(Libs.glideCompiler)

    implementation(Libs.retrofit2)
    implementation(Libs.retrofit2Gson)
    implementation(Libs.retrofit2RxJava)
    implementation(Libs.retrofit2Logging)
    implementation(Libs.gson)
}