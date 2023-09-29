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
            val artifactoryContextUrl: String = getKeyFromLocalProperty("artifactory_contextUrl") as String//"https://vitasokolova.jfrog.io/artifactory/gradle-dev/"
            val artifactoryUser: String = getKeyFromLocalProperty("artifactory_user") as String
            val artifactoryPassword: String = getKeyFromLocalProperty("artifactory_password") as String
            setUrl(artifactoryContextUrl)
            credentials {
                username = artifactoryUser
                password = artifactoryPassword
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

 