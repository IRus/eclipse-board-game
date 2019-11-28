import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    application
    kotlin("jvm").version("1.3.61")
}

repositories {
    jcenter()
}

application {
    mainClassName = "by.ibragimov.eclipse.game.App"
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val slf4jVersion: String by project
val junitVersion: String by project

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
