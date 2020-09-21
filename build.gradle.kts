import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "app.sonderful"
version = "0.1"

repositories {
    mavenCentral()
}

javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls", "javafx.graphics")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("no.tornado", "tornadofx", "1.7.20")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
