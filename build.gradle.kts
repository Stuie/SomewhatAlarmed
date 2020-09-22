import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
    id("org.openjfx.javafxplugin") version "0.0.8"
    application
}

group = "app.sonderful"
version = "0.1"

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
}

javafx {
    version = "14"
    modules = listOf("javafx.controls", "javafx.graphics")
}

application {
    mainClassName = "app.sonderful.somewhatalarmed.presentation.load.Launcher"
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
    implementation("no.tornado", "tornadofx", "2.0.0-SNAPSHOT")
    testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}
