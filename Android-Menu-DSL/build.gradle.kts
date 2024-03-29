import java.util.*

plugins {
    alias(libs.plugins.androidGradlePluginLibrary)
    alias(libs.plugins.kotlin.android)
    id("maven-publish")
    id("signing")
}

android {
    compileSdk = 34
    namespace = "com.github.chr56.android.menu_dsl"

    defaultConfig {
        minSdk = 21

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(libs.androidx.annotation)
    compileOnly(libs.androidx.appcompat.resources)
}

val libVersion = "0.1.3"

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
