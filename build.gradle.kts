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
    implementation("com.google.code.gson:gson:2.13.0")
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

publishing {
    repositories {
        maven {
            name = "paulem"
            url = uri("https://maven.paulem.ovh/releases")
            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])
        }
    }
}