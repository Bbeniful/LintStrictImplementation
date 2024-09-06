plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation("com.android.tools.lint:lint-api:31.6.0")    // Adjust version as needed
    implementation("com.android.tools.lint:lint-checks:31.6.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}