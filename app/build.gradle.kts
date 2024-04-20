import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.gmsGoogleServices)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.secretsGradlePlugin)
}

var properties = Properties()
var mapsKey: String = ""

if (File("local.properties").exists()) {
    properties = Properties().apply { load(project.rootProject.file("local.properties").inputStream()) }
    mapsKey = properties.getProperty("GOOGLE_MAPS_ANDROID_KEY")
} else {
    mapsKey = System.getenv("GOOGLE_MAPS_ANDROID_KEY")
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
    }
    buildFeatures {
        buildConfig = true
    }
    buildTypes {
        getByName("debug") {
            buildConfigField("String", "GOOGLE_MAPS_ANDROID_KEY", "\"$mapsKey\"")
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            buildConfigField("String", "GOOGLE_MAPS_ANDROID_KEY", "\"$mapsKey\"")
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
