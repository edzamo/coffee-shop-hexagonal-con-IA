#!/bin/bash

echo "🔧 Creando estructura del proyecto en la raíz..."

mkdir -p .copilot
mkdir -p src/main/java/com/ezamora/mcp/domain/model
mkdir -p src/main/java/com/ezamora/mcp/domain/ports
mkdir -p src/main/java/com/ezamora/mcp/application/service
mkdir -p src/main/java/com/ezamora/mcp/infrastructure/controller
mkdir -p src/main/java/com/ezamora/mcp/infrastructure/repository
mkdir -p src/main/java/com/ezamora/mcp/infrastructure/config
mkdir -p src/main/java/com/ezamora/mcp/mcphexagonal
mkdir -p src/main/resources/db
mkdir -p src/test/java/com/ezamora/mcp/unit/application
mkdir -p src/test/java/com/ezamora/mcp/unit/domain
mkdir -p src/test/java/com/ezamora/mcp/integration/infrastructure

echo "📁 Estructura creada."

# Copiar archivos base
echo "⚙️ Generando archivos base..."
cat > .copilot/config.json <<'EOF'
{
  "instructions": {
    "language": "spanish and english",
    "style": "Sigue los principios de Clean Code y SOLID. Usa camelCase para métodos y variables. Escribe comentarios claros en español o inglés cuando sea posible. // Follow Clean Code and SOLID principles. Use camelCase for methods and variables. Use clear comments in Spanish or English when possible.",
    "framework": "Spring Boot con arquitectura hexagonal (puertos y adaptadores), Gradle, Java 17 o 21. // Spring Boot using hexagonal architecture (ports and adapters), Gradle, Java 17 or 21.",
    "avoid": "No mezcles lógica de negocio en los controladores. Evita programación reactiva a menos que sea requerida. // Do not mix business logic in controllers. Avoid reactive programming unless explicitly needed.",
    "focus": "Estructura modular: domain, application, infrastructure. Testing con JUnit 5 y Mockito. Usa DTOs para exponer entidades. Separa pruebas unitarias y de integración. // Modular layered structure: domain, application, infrastructure. Testing with JUnit 5 and Mockito. Use DTOs when exposing domain entities. Separate unit and integration tests."
  }
}EOF

cat > build.gradle <<'EOF'
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.2.5'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.ezamora.mcp'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '17'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    runtimeOnly 'com.mysql:mysql-connector-j'
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testImplementation 'org.mockito:mockito-core'
}EOF

cat > settings.gradle <<'EOF'
rootProject.name = 'mcp-hexagonal'EOF

cat > Dockerfile <<'EOF'
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]EOF

cat > .gitignore <<'EOF'
/build/
/out/
.gradle/
.idea/
/*.iml
*.log
*.class
*.jar
*.war
*.db
.env
.DS_StoreEOF

cat > README.md <<'EOF'
# 🧱 MCP Hexagonal Project

Proyecto base con arquitectura hexagonal usando Java 17+ y Spring Boot.

---

## ⚙️ Requisitos

- Java 17 o 21
- Docker
- Gradle

---

## 🚀 Ejecutar localmente

```bash
./gradlew bootRun
```

---

## 🐳 Construir y correr con Docker

```bash
./gradlew build
docker build -t mcp-hexagonal .
docker run -p 8080:8080 mcp-hexagonal
```

---

## ⚙️ Alternativa rápida (auto-generación)

```bash
chmod +x setup-hexagonal.sh
./setup-hexagonal.sh
```

> Esto generará toda la estructura del proyecto, archivos de configuración, Dockerfile y README.

---

## 🧪 Testing

```bash
./gradlew test
```

---

## 👤 Autor

Edwin Zamora  
✉️ ezamora@tudominio.com  
🔗 [LinkedIn](https://www.linkedin.com/in/ezamora)
EOF

echo "✅ Proyecto generado con éxito."
