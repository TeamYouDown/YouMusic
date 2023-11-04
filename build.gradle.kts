plugins {
    id("com.google.dagger.hilt.android").version("2.44").apply(false)
    id("com.google.devtools.ksp").version("1.8.0-1.0.9").apply(false)
}

buildscript {
    val isFullBuild by extra {
        gradle.startParameter.taskNames.none { task -> task.contains("foss", ignoreCase = true) }
    }

    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
        maven { setUrl("https://developer.huawei.com/repo/") }
    }
    dependencies {
//        classpath("com.huawei.agconnect:agcp:1.9.0.300")
        classpath(libs.gradle)
        classpath(kotlin("gradle-plugin", libs.versions.kotlin.get()))
        if (isFullBuild) {
            classpath(libs.google.services)
            classpath(libs.firebase.crashlytics.plugin)
            classpath(libs.firebase.perf.plugin)
            classpath("com.huawei.agconnect:agcp:1.9.0.300")
        }
    }
}

tasks.register<Delete>("Clean") {
    delete(rootProject.buildDir)
}

subprojects {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions {
            if (project.findProperty("enableComposeCompilerReports") == "true") {
                arrayOf("reports", "metrics").forEach {
                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-P", "plugin:androidx.compose.compiler.plugins.kotlin:${it}Destination=${project.buildDir.absolutePath}/compose_metrics"
                    )
                }
            }
        }
    }
}
