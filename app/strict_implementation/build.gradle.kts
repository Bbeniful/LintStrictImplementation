plugins {
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    kapt(libs.auto.service)
    implementation(libs.auto.service)
    implementation(libs.kotlin.stdlib)
}