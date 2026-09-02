import org.jetbrains.kotlin.gradle.dsl.abi.ExperimentalAbiValidation

plugins {
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.android.library)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.tapmoc)
    alias(libs.plugins.spotless)
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.injekt)
    implementation(libs.kotlinx.serialization.json)
}

kotlin {
    @OptIn(ExperimentalAbiValidation::class)
    abiValidation {}
}

@Suppress("DEPRECATION")
android {
    namespace = "eu.kanade.tachiyomi.extensions"
    compileSdk = 37

    defaultConfig {
        minSdk = 26
    }
}

spotless {
    val ktlintVersion = libs.ktlint.cli.get().version
    kotlin {
        target("src/**/*.kt")
        ktlint(ktlintVersion)
    }

    kotlinGradle {
        target("*.gradle.kts")
        ktlint(ktlintVersion)
    }
}

tapmoc {
    java(17)
}

mavenPublishing {
    coordinates("com.github.keiyoushi", "extensions-lib", "1.6")

    pom {
        name.set("extensions-lib")
        description.set("Stubs used for extensions in Tachiyomi and Mihon 0.x.")
        url.set("https://github.com/keiyoushi/extensions-lib")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0")
                distribution.set("repo")
            }
        }

        organization {
            name.set("Keiyoushi")
            url.set("https://github.com/keiyoushi")
        }

        scm {
            connection.set("scm:git:git://github.com/keiyoushi/extensions-lib.git")
            url.set("https://github.com/keiyoushi/extensions-lib")
        }
    }
}
