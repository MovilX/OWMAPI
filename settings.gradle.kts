pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "OWM API"
include(":app")
include(":owm")
include(":data")
include(":domain")
include(":common")
