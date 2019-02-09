import org.gradle.api.tasks.testing.logging.TestExceptionFormat

buildscript {
  val kotlin_version by extra("1.3.21")
  val junit_version by extra("5.1.0")

  repositories {
    jcenter()
  }

  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
  }
}

val kotlin_version: String by extra
val junit_version: String by extra

plugins {
  application
  kotlin("jvm").version("1.3.21")
}

application {
  mainClassName = "common"
}

repositories {
  jcenter()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:$kotlin_version")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junit_version")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_version")
}

tasks {
  test {
    useJUnitPlatform()

    testLogging {
        events("PASSED", "SKIPPED", "FAILED")
        exceptionFormat = TestExceptionFormat.FULL
        showStandardStreams = true
    }
  }
}

// ------------ //
//    KTLint    //
// ------------ //

val ktlint by configurations.creating

dependencies {
  ktlint("com.github.shyiko:ktlint:0.30.0")
}

tasks {
  val ktlint by registering(JavaExec::class) {
    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.github.shyiko.ktlint.Main"
    args("src/**/*.kt")
    // to generate report in checkstyle format prepend following args:
    // "--reporter=plain", "--reporter=checkstyle,output=${buildDir}/ktlint.xml"
    // see https://github.com/shyiko/ktlint#usage for more
  }
  check {
    dependsOn(ktlint)
  }

  // register("ktlintFormat", JavaExec::class) {
  //   description = "Fix Kotlin code style deviations."
  //   classpath = ktlint
  //   main = "com.github.shyiko.ktlint.Main"
  //   args("-F", "src/**/*.kt")
  // }
}
