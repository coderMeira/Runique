plugins {
    `kotlin-dsl`
}

group = "com.example.buildlogic"

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "runique.android.application"
            implementationClass = "com.example.convention.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "runique.android.application.compose"
            implementationClass = "com.example.convention.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runique.android.library"
            implementationClass = "com.example.convention.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runique.android.library.compose"
            implementationClass = "com.example.convention.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "runique.android.feature.ui"
            implementationClass = "com.example.convention.AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "runique.android.room"
            implementationClass = "com.example.convention.AndroidRoomConventionPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    implementation(libs.room.gradlePlugin)
}