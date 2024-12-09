plugins {
    id("java")
    id("application")
}

group = "net.avuna.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.togetherjava", "AdventOfCode") {
        version {
            branch = "master"
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass = "net.avuna.aoc.Main"
}
