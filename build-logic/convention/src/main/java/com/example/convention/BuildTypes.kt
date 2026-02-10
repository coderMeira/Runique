package com.example.convention

import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension
) {
    commonExtension.apply {
        buildFeatures.buildConfig = true
        val apiKey = gradleLocalProperties(
            rootDir,
            providers = providers
        ).getProperty("API_KEY") ?: "default_api_key"

        buildTypes.apply {
            getByName("release") {
                configureReleaseBuildType(commonExtension, apiKey)
            }
            getByName("debug") {
                configureDebugBuildType(apiKey)
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apiKey: String) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension,
    apiKey: String
) {
    buildConfigField("String", "API_KEY", "\"$apiKey\"")
    buildConfigField("String", "BASE_URL", "\"https://runique.pl-coding.com:8080\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}
