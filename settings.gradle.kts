pluginManagement {
    repositories {
        google()
//        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
////        mavenLocal()
        mavenCentral()
        maven {
            name = "KMP-shared"
            url = uri("https://maven.pkg.github.com/VitaSokolova/KMP-shared")
            credentials {
                username = getKeyFromLocalProperty("GITHUB_PACKAGES_USERNAME") as String
                password = getKeyFromLocalProperty("GITHUB_PACKAGES_PASSWORD") as String
            }
        }
    }
}

rootProject.name = "KMP-android"
include(":app")

fun getKeyFromLocalProperty(key: String, file: String = "local.properties"): Any? {
    val properties = java.util.Properties()
    val localProperties = File(file)
    if (localProperties.isFile) {
        java.io.InputStreamReader(java.io.FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("local.properties file is not found")

    return properties.getProperty(key)
}

 