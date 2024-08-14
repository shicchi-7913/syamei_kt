plugins {
    kotlin("jvm") version "2.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
}

group = "com.syamei"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
    debug.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    outputColorName.set("RED")
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}
