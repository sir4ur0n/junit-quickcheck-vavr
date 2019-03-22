import com.jfrog.bintray.gradle.BintrayExtension

group = "com.github.sir4ur0n"
version = "1.0"
val githubUrl = "https://github.com/Sir4ur0n/junit-quickcheck-vavr"

plugins {
    id("io.franzbecker.gradle-lombok") version ("1.14")
    java
    `maven-publish`
    `java-library`
    id("com.jfrog.bintray") version "1.8.4"
}

bintray {
    // Nullable because if the bintrayUpload task is not invoked, we don't care about the credentials
    val bintrayRepo: String? by project
    val bintrayUser: String? by project
    val bintrayKey: String? by project
    user = bintrayUser
    key = bintrayKey
    setPublications("mavenJava")
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = bintrayRepo
        name = project.group.toString() + ":" + project.name
        userOrg = bintrayUser
        websiteUrl = githubUrl
        githubRepo = "Sir4ur0n/junit-quickcheck-vavr"
        vcsUrl = githubUrl
        description = "JUnit QuickCheck generators for Vavr types"
        setLabels("vavr", "junit-quickcheck", "test", "property-based test")
        setLicenses("MIT")
        desc = description
        version(delegateClosureOf<BintrayExtension.VersionConfig> {
            name = project.version.toString()
        })
    })
}

lombok {
    version = "1.18.4"
    sha256 = ""
}

repositories {
    jcenter()
}

task<Jar>("sourcesJar") {
    from(sourceSets.main.get().allJava)
    classifier = "sources"
}

task<Jar>("javadocJar") {
    from(tasks.javadoc)
    classifier = "javadoc"
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
            artifact(tasks["javadocJar"])
            pom {
                name.set("JUnit QuickCheck Vavr")
                description.set("JUnit QuickCheck generators for Vavr")
                url.set(githubUrl)
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
                developers {
                    developer {
                        id.set("sir4ur0n")
                        name.set("Julien Debon")
                    }
                }
                scm {
                    url.set(githubUrl)
                }
            }
        }
    }
    repositories {
        jcenter()
    }
}

dependencies {
    // Functional control and immutable data types for Java
    compile("io.vavr", "vavr", "0.9.2")
    // Property Based Test framework
    val junitQuickcheck = "0.9-SNAPSHOT"
    compile("com.pholser", "junit-quickcheck-core", junitQuickcheck)
    compile("com.pholser", "junit-quickcheck-generators", junitQuickcheck)
    // Avoid maintaining the explicit resource file with all Quickcheck generators
    compile("com.google.auto.service", "auto-service", "1.0-rc4")
    annotationProcessor("com.google.auto.service", "auto-service", "1.0-rc4")
    // JUnit 5
    val junitJupiterVersion = "5.3.2"
    testCompile("org.junit.jupiter", "junit-jupiter-api", junitJupiterVersion)
    testCompile("org.junit.jupiter", "junit-jupiter-engine", junitJupiterVersion)
    testCompile("org.junit.jupiter", "junit-jupiter-params", junitJupiterVersion)
    val junitPlatformVersion = "1.3.2"
    testCompile("org.junit.platform", "junit-platform-launcher", junitPlatformVersion)
    // Fluent test assertion framework
    testCompile("org.assertj", "assertj-core", "3.11.1")
}

configure<JavaPluginConvention> {
    // Minimum required for Vavr is Java 8
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
