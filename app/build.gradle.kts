@file:Suppress("UnstableApiUsage")

val isFullBuild: Boolean by rootProject.extra

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
//    id("com.huawei.agconnect")
}

if (isFullBuild && System.getenv("PULL_REQUEST") == null) {
    apply(plugin = "com.google.gms.google-services")
    apply(plugin = "com.google.firebase.crashlytics")
    apply(plugin = "com.google.firebase.firebase-perf")
    apply(plugin = "com.huawei.agconnect")
}

android {
    namespace = "com.zionhuang.music"
    compileSdk = 33
    buildToolsVersion = "30.0.3"
    defaultConfig {
        applicationId = "com.ozsoft.youmusic"
        minSdk = 24
        targetSdk = 33
        versionCode = 14
        versionName = "1.0.7"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }
    flavorDimensions += "version"
    productFlavors {
        create("full") {
            dimension = "version"
        }
        create("foss") {
            dimension = "version"
        }
    }
    signingConfigs {
        getByName("debug") {
            if (System.getenv("MUSIC_DEBUG_SIGNING_STORE_PASSWORD") != null) {
                storeFile = file(System.getenv("MUSIC_DEBUG_KEYSTORE_FILE"))
                storePassword = System.getenv("MUSIC_DEBUG_SIGNING_STORE_PASSWORD")
                keyAlias = "debug"
                keyPassword = System.getenv("MUSIC_DEBUG_SIGNING_KEY_PASSWORD")
            }
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
        jvmTarget = "11"
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
    lint {
        disable += "MissingTranslation"
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    implementation(libs.guava)
    implementation(libs.coroutines.guava)
    implementation(libs.concurrent.futures)

    implementation(libs.activity)
    implementation(libs.navigation)
    implementation(libs.hilt.navigation)
    implementation(libs.datastore)

    implementation(libs.compose.runtime)
    implementation(libs.compose.foundation)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.util)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.animation)
    implementation(libs.compose.animation.graphics)
    implementation(libs.compose.reorderable)

    implementation(libs.viewmodel)
    implementation(libs.viewmodel.compose)

    implementation(libs.material3)
    implementation(libs.palette)
    implementation(projects.materialColorUtilities)

    implementation(libs.accompanist.swiperefresh)

    implementation(libs.coil)

    implementation(libs.shimmer)

    implementation(libs.media3)
    implementation(libs.media3.session)
    implementation(libs.media3.okhttp)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    ksp(libs.room.compiler)
    implementation(libs.room.ktx)

    implementation(libs.apache.lang3)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(projects.innertube)
    implementation(projects.kugou)

    coreLibraryDesugaring(libs.desugaring)

    "fullImplementation"(platform(libs.firebase.bom))
    "fullImplementation"(libs.firebase.analytics)
    "fullImplementation"(libs.firebase.crashlytics)
    "fullImplementation"(libs.firebase.config)
    "fullImplementation"(libs.firebase.perf)
    "fullImplementation"(libs.mlkit.language.id)
    "fullImplementation"(libs.mlkit.translate)
    "fullImplementation"(libs.opencc4j)

    implementation(libs.timber)
//    implementation("com.unity3d.ads:unity-ads:4.7.0")
//    implementation("com.google.android.gms:play-services-ads:21.3.0")
    implementation("com.onesignal:OneSignal:[4.0.0, 4.99.99]")
    implementation("com.huawei.agconnect:agconnect-remoteconfig:1.9.0.300")
    implementation("com.huawei.hms:hianalytics:4.0.1.300")
}
