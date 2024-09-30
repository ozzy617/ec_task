import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    val kotlinVersion = "1.9.22"
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
}

group = "testtask"

noArg {
    annotation("jakarta.persistance.Entity")
}

allOpen {
    annotation("jakarta.persistence.Entity")
}
java.sourceCompatibility = JavaVersion.VERSION_17


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.23")
    implementation("org.springframework.boot:spring-boot-configuration-processor:3.3.4")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")


    //other
    implementation("io.jsonwebtoken:jjwt-api:0.12.3")
    implementation("io.jsonwebtoken:jjwt-impl:0.12.3")
    implementation("io.jsonwebtoken:jjwt-jackson:0.12.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("com.h2database:h2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

extra["springCloudVersion"] = "2023.0.2"

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}