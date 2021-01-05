plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "com.upco.playground.mortycomposekmm.android"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerVersion = "1.4.21"
        kotlinCompilerExtensionVersion = "1.0.0-alpha09"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
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
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf("-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")

    implementation("androidx.compose.ui:ui:1.0.0-alpha09")
    implementation("androidx.compose.ui:ui-graphics:1.0.0-alpha09")
    implementation("androidx.compose.ui:ui-tooling:1.0.0-alpha09")
    implementation("androidx.compose.foundation:foundation-layout:1.0.0-alpha09")
    implementation("androidx.compose.material:material:1.0.0-alpha09")
    implementation("androidx.compose.runtime:runtime-livedata:1.0.0-alpha09")
    implementation("androidx.compose.material:material-icons-extended:1.0.0-alpha09")
    implementation("androidx.navigation:navigation-compose:1.0.0-alpha04")
    implementation("androidx.paging:paging-compose:1.0.0-alpha04")
    implementation("dev.chrisbanes.accompanist:accompanist-coil:0.4.1")

    implementation("org.koin:koin-core:2.2.2")
    implementation("org.koin:koin-android:2.2.2")
    implementation("org.koin:koin-androidx-viewmodel:2.2.2")
    implementation("org.koin:koin-androidx-compose:2.2.2")

    testImplementation("junit:junit:4.13.1")
    testImplementation("androidx.test:core:1.3.0")
    testImplementation("org.robolectric:robolectric:4.4")
    androidTestImplementation("androidx.test:runner:1.3.0")

    implementation(project(":shared"))
}