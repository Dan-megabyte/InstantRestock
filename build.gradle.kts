plugins {
    java
    kotlin("jvm") version "1.7.22"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    maven {
        url = uri("https://repo.dmulloy2.net/nexus/repository/public/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.19.2-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:4.8.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.bstats:bstats-bukkit:3.0.0")
}

group = "me.spartacus04.instantrestock"
version = "2.3.2"
description = "instantrestock"
java.sourceCompatibility = JavaVersion.VERSION_1_8

tasks.jar { enabled = false }

artifacts.archives(tasks.shadowJar)

tasks.shadowJar {
    archiveFileName.set(rootProject.name + ".jar")
    val dependencyPackage = "${rootProject.group}.dependencies.${rootProject.name.toLowerCase()}"
    relocate("kotlin", "${dependencyPackage}.kotlin")
    relocate("com/google/gson", "${dependencyPackage}.gson")
    relocate("org/intellij/lang", "${dependencyPackage}.lang")
    relocate("org/jetbrains/annotations", "${dependencyPackage}.annotations")
    relocate("org/bstats", "${dependencyPackage}.bstats")
    exclude("ScopeJVMKt.class")
    exclude("DebugProbesKt.bin")
    exclude("META-INF/**")
}

java {
    val javaVersion = JavaVersion.toVersion(17)
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
    if(JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))
    }
}