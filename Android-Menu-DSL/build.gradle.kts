plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.create("sourceJar", Jar::class.java) {
    from(android.sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
}

val libVersion = "0.1.0-beta01"

publishing {
    publications {
        this.create("release", MavenPublication::class.java) {

            artifact(tasks.getByName("sourceJar"))
            afterEvaluate {
                artifact(tasks.getByName("bundleReleaseAar"))
            }

            groupId = "com.github.chr56"
            artifactId = "android-menu-dsl"
            version = libVersion
        }
    }
}
