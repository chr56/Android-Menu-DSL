buildscript {
    val kotlinVersion by extra { "1.8.10" }
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://jitpack.io")
    }
}

