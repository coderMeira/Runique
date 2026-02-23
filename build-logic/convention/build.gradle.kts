plugins {
    `kotlin-dsl`
}

group = "com.runique.buildlogic"

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "runique.android.application"
            implementationClass = "com.runique.convention.AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "runique.android.application.compose"
            implementationClass = "com.runique.convention.AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runique.android.library"
            implementationClass = "com.runique.convention.AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runique.android.library.compose"
            implementationClass = "com.runique.convention.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "runique.android.feature.ui"
            implementationClass = "com.runique.convention.AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "runique.android.room"
            implementationClass = "com.runique.convention.AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "runique.jvm.library"
            implementationClass = "com.runique.convention.JvmLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "runique.jvm.ktor"
            implementationClass = "com.runique.convention.JvmKtorConventionPlugin"
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