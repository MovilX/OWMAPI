pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "FirstMap"
include(":app")
include(":owm")
include(":data")
include(":domain")
include(":common")
