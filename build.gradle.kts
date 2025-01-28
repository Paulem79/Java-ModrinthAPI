plugins {
    id("idea")
    id("java")
    `maven-publish`
}

group = "ovh.paulem"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("org.jetbrains:annotations:26.0.2")
    implementation("com.google.code.gson:gson:2.11.0")
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>().configureEach {
    JavaVersion.VERSION_17.toString().also {
        sourceCompatibility = it
        targetCompatibility = it
    }
    options.encoding = "UTF-8"
}