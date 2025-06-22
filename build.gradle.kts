plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.saigopa.travel"
version = "1.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-mail")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")

	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.jsonwebtoken:jjwt:0.12.5")
	implementation ("com.okta.spring:okta-spring-boot-starter:3.0.6")
	implementation ("dev.akkinoc.spring.boot:logback-access-spring-boot-starter:4.1.1")
	implementation ("org.json:json:20230227")

	implementation ("com.google.firebase:firebase-admin:9.3.0")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
