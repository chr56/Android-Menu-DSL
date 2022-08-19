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

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

tasks.create("sourceJar", Jar::class.java) {
    from(android.sourceSets.getByName("main").java.srcDirs)
    archiveClassifier.set("sources")
}

dependencies {
    implementation("androidx.annotation:annotation:1.3.0")
    compileOnly("androidx.appcompat:appcompat-resources:1.3.1")
}

val libVersion = "0.1.0-beta02"

publishing {
    publications {
        create("release", MavenPublication::class.java) {

            groupId = "com.github.chr56"
            artifactId = "android-menu-dsl"
            version = libVersion
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
