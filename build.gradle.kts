plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.daggerHiltAndroid) apply false
    alias(libs.plugins.devtoolsKsp) apply false
    alias(libs.plugins.firebaseCrashlytics) apply false
    alias(libs.plugins.gmsGoogleServices) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.secretsGradlePlugin) apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
