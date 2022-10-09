import java.util.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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

val libVersion = "0.1.0"

val secretPropsFile = rootProject.file("secrets.properties")
var secrets = Properties()
if (secretPropsFile.exists()) {
    secretPropsFile.inputStream().use {
        secrets.load(it)
    }
}

publishing {
    publications {
        create("release", MavenPublication::class.java) {

            groupId = "io.github.chr56"
            artifactId = "android-menu-dsl"
            version = libVersion
            afterEvaluate {
                from(components["release"])
            }

            pom {
                name.set("Android Menu DSL")
                description.set("A kotlin Android library to define menu items using kotlin DSL.")
                url.set("https://github.com/chr56/Android-Menu-DSL")

                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("chr_56")
                        name.set("chr_56")
                        timezone.set("UTC+8")
                    }
                }
                scm {
                    connection.set("https://github.com/chr56/Android-Menu-DSL.git")
                    developerConnection.set("https://github.com/chr56/Android-Menu-DSL.git")
                    url.set("https://github.com/chr56/Android-Menu-DSL")
                }
            }
        }
    }
    repositories {
        maven("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2") {
            name = "MavenCentral"
            if (secretPropsFile.exists()) {
                credentials {
                    username = secrets["sonatype_username"] as String
                    password = secrets["sonatype_password"] as String
                }
            }
        }
    }
}
if (secretPropsFile.exists()) {
    signing {
        sign(publishing.publications)
        val key = File(secrets["signing_file"] as String).readText()
        useInMemoryPgpKeys(
            secrets["signing_key"] as String,
            key,
            secrets["signing_password"] as String
        )
    }
}
