import de.undercouch.gradle.tasks.download.Download

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.5.6"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
	id("de.undercouch.download") version "5.1.2"
}

group = "exercicio"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java.sourceCompatibility = JavaVersion.VERSION_17

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// observabilidade
	implementation("io.micrometer:micrometer-registry-otlp")
	implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")
	implementation("net.logstash.logback:logstash-logback-encoder:7.4")
}

// 🔽 Baixa automaticamente o agente OpenTelemetry
tasks.register<Download>("downloadOtelAgent") {
	val otelVersion = "2.8.0"
	src("https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v$otelVersion/opentelemetry-javaagent.jar")
	dest(layout.buildDirectory.file("otel/opentelemetry-javaagent.jar"))
	overwrite(false)
}

// 🚀 Configura o bootRun com o agente OpenTelemetry
tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
	dependsOn("downloadOtelAgent")

	val otelAgentPath = layout.buildDirectory.file("otel/opentelemetry-javaagent.jar").get().asFile.absolutePath

	jvmArgs = listOf(
		"-javaagent:$otelAgentPath",
		"-Dotel.service.name=application-observabilidade",
		"-Dotel.metrics.exporter=otlp",
		"-Dotel.logs.exporter=otlp",
		"-Dotel.traces.exporter=otlp",
		"-Dotel.exporter.otlp.endpoint=http://localhost:4318",
		"-Dotel.resource.attributes=service.environment=dev"
	)

}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
