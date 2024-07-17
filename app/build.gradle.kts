import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.gms.google.services)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.secrets.gradle.plugin)
}

android {
    namespace = "com.kryptopass.owmapi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.kryptopass.owmapi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        lateinit var properties: Properties

        var mapsKey: String = ""

        if (File("local.properties").exists()) {
            properties = Properties().apply { load(project.rootProject.file("local.properties").inputStream()) }
            mapsKey = properties.getProperty("GOOGLE_MAPS_ANDROID_KEY")
        } else {
            mapsKey = System.getenv("GOOGLE_MAPS_ANDROID_KEY")
        }

        buildConfigField("String", "GOOGLE_MAPS_ANDROID_KEY", "\"$mapsKey\"")
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
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
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
        resources.excludes += "/META-INF/gradle/incremental.annotation.processors"
    }
    testOptions.unitTests {
        isIncludeAndroidResources = true
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)

    implementation(libs.gson)
    implementation(libs.maps.ktx)
    implementation(libs.maps.utils.ktx)
    implementation(libs.play.services.maps)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    testImplementation(libs.junit)
}
