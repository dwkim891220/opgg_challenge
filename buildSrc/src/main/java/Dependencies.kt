object Apps {
    const val applicationId = "com.example.opgg"
    const val compileSdk = 31
    const val targetSdk = 31
    const val minSdk = 23
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Versions {
    const val gradle = "7.2.1"
    const val kotlin = "1.7.10"

    const val hilt = "2.43"

    const val appcompat = "1.4.2"
    const val androidxCore = "1.8.0"
    const val androidxActivity = "1.5.0"
    const val androidxFragment = "1.5.0"
    const val constraintLayout = "2.0.4"
    const val recyclerView = "1.2.1"
    const val swipeRefreshLayout = "1.1.0"
    const val lifeCycle = "2.5.0"
    const val material = "1.4.0"

    const val rxJava = "3.0.13"
    const val rxAndroid = "3.0.0"

    const val glide = "4.12.0"

    const val retrofit2 = "2.9.0"
    const val okhttp = "4.9.1"
    const val gson = "2.8.7"
}

object Libs {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val material = "com.google.android.material:material:${Versions.material}"

    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.androidxCore}"
    const val activity = "androidx.activity:activity-ktx:${Versions.androidxActivity}"
    const val fragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val lifeCycleLiveData = "androidx.lifecycle:lifecycle-livedata:${Versions.lifeCycle}"

    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"

    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2}"
    const val retrofit2Gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2}"
    const val retrofit2RxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit2}"
    const val retrofit2Logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
}