plugins {
    alias(libs.plugins.runiqueAndroidLibrary)
}

android {
    namespace = "com.example.core.database"
}

dependencies {
    implementation(libs.org.mongodb.bson)
    implementation(projects.core.domain)
}