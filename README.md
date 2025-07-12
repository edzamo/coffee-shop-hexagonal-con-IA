# 🧠 Microservicio con Arquitectura Hexagonal usando IA (Copilot + Gemini + Spring Boot)

## 🚀 Introducción

Este artículo explora cómo construir un microservicio back-end utilizando únicamente herramientas de IA gratuitas, a través de instrucciones personalizadas.  
El objetivo es demostrar lo cómodo y poderoso que puede ser el uso de IA para generar proyectos estructurados profesionalmente, sin necesidad de escribir cada línea de código manualmente.

---

## 👁️‍🗨️ ¿Qué quiero probar?

¿Hasta dónde podemos llegar construyendo un microservicio profesional con IA como copiloto?  
En este caso, usamos **Gemini** y **Copilot** para el desarrollo e implementación del software.

Este experimento demuestra que la IA, bien guiada, puede acompañar el desarrollo técnico sin reemplazar al programador. Funciona como un “asistente técnico” que responde a nuestras instrucciones personalizadas, ayudando a aplicar buenas prácticas y mantener la calidad del diseño.

---

## 🧱 ¿Qué contiene este POC?

- 🧩 Arquitectura hexagonal: entradas/salidas desacopladas, dominio limpio.
- ☕ Spring Boot 3 con dependencias profesionales.
- 🐳 Contenedores con Docker Compose y PostgreSQL.
- 🔁 Mapeo DTO ↔ Entidades con MapStruct.
- 🧪 Pruebas unitarias con JUnit y Mockito.
- 📘 Documentación con Swagger/OpenAPI.
- 🤖 Generado usando IA: GitHub Copilot y Gemini.

🔗 **Ver el repositorio:** [github.com/edzamo/coffee-shop-hexagonal-con-IA](https://github.com/edzamo/coffee-shop-hexagonal-con-IA)

---

## 🧠 Cómo generar código estructurado con IA

Usar IA para codificar no se trata simplemente de pedirle código.  
Es enseñarle el **contexto de tu proyecto**, guiarla con **prompts claros**, y revisar cada respuesta con sentido crítico.

Durante este desarrollo, descubrí que la IA puede:
- Entender patrones complejos como la arquitectura hexagonal.
- Generar clases coherentes y bien nombradas.
- Sugerir pruebas unitarias efectivas.
- Validar diseños y estructuras del proyecto.

💡 Tip: si defines tus propios prompts y estructuras de carpetas (como en `.heHexaBarista`), puedes convertir a la IA en una **copiloto técnica real**.

---

## ✨ El profundo beneficio de usar la IA al desarrollar software

Herramientas como Copilot y Gemini no solo aceleran el trabajo, sino que también ayudan a mantener la coherencia y calidad en el código.

Se convierten en asistentes técnicos constantes que:
- 🧠 Disminuyen el esfuerzo repetitivo.
- ⚙️ Respetan tu estilo y convenciones.
- 🚀 Te permiten concentrarte en la lógica de negocio y decisiones de arquitectura.

La clave está en **usar instrucciones personalizadas y brindar contexto técnico**. Así, la IA deja de ser genérica y se convierte en una herramienta altamente productiva.

🛑 **La IA no reemplaza al desarrollador, lo potencia.**

---

## 🛠️ Cómo practicar con IA al codificar

Recomendaciones personales para aprovechar IA en proyectos reales:

- 💡 Empieza con proyectos simples y aplica patrones reales (como MVC o arquitectura hexagonal).
- 🧭 Dale contexto: nombres de clases, estructura de carpetas, convenciones.
- ✍️ Usa prompts claros y paso a paso, como si explicaras a un junior.
- 🧪 Revisa todo lo que genere la IA. Aprende de sus errores y aciertos.
- 📁 Define una estructura clara (adaptadores, puertos, dominio) para que la IA la entienda fácilmente.

---

## 🧾 Conclusión

Este proyecto no solo demuestra una arquitectura funcional, sino también **cómo integrar IA en un flujo de trabajo profesional**.  
Cuando se le guía bien, la IA permite desarrollar más rápido sin sacrificar diseño ni calidad.

Si quieres probarlo tú mismo:
1. Explora la carpeta `.heHexaBarista`.
2. Instala GitHub Copilot o Gemini.
3. Crea con IA como tu aliada técnica. 🚀


---
# 🧱 Coffee Shop Hexagonal Project

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
🔗 [LinkedIn](https://www.linkedin.com/in/ezamora)


SpringBoot Hexagonal Architecture
```
src/
├── main/
│   ├── java/
│   │   └── com/yourcompany/yourapp/
│   │       ├── application/                                 // Lógica de aplicación (casos de uso)
│   │       │   └── service/
│   │       │       └── CreateOrderService.java              // Servicio que orquesta la creación de órdenes
│   │
│   │       ├── domain/                                      // Lógica del dominio (modelo puro, sin frameworks)
│   │       │   ├── model/
│   │       │   │   ├── Order.java                           // Entidad del dominio
│   │       │   │   └── Product.java                         // Value Object
│   │       │   └── repository/
│   │       │       └── OrderRepository.java                 // Puerto de salida (interfaz para persistencia)
│   │
│   │       ├── infrastructure/                              // Capa de infraestructura (adaptadores, configuración)
│   │       │   ├── adapter/
│   │       │   │   ├── in/
│   │       │   │   │   └── rest/
│   │       │   │   │       └── OrderController.java         // Adaptador de entrada: controlador REST
│   │       │   │   └── out/
│   │       │   │       ├── persistence/
│   │       │   │       │   ├── JpaOrderRepository.java      // Implementación del repositorio usando JPA
│   │       │   │       │   └── OrderEntity.java             // Entidad JPA (mapeo a DB)
│   │       │   │       └── client/
│   │       │   │           └── ProductApiClient.java        // Cliente HTTP para obtener productos desde otro servicio
│   │       │   └── config/
│   │       │       ├── SwaggerConfig.java                   // Configuración de Swagger/OpenAPI
│   │       │       └── WebSecurityConfig.java               // Configuración de seguridad con Spring Security
│   │
│   │       └── YourAppApplication.java                      // Clase principal con @SpringBootApplication
│
│   └── resources/
│       ├── application.yml                                  // Archivo de configuración principal
│       ├── application-dev.yml                              // Configuración para entorno de desarrollo
│       ├── static/                                           // Archivos estáticos (solo si es web MVC)
│       ├── templates/                                        // Plantillas Thymeleaf u otras (si aplica)
│       └── banner.txt                                        // Banner de inicio de Spring Boot (opcional)
│
├── test/
│   ├── java/
│   │   └── com/yourcompany/yourapp/
│   │       ├── application/
│   │       │   └── service/
│   │       │       └── CreateOrderServiceTest.java          // Test del servicio de aplicación
│   │       ├── domain/
│   │       │   └── model/
│   │       │       └── OrderTest.java                       // Test de lógica de dominio
│   │       └── infrastructure/
│   │           └── adapter/
│   │               ├── in/
│   │               │   └── rest/
│   │               │       └── OrderControllerTest.java     // Test del controlador REST
│   │               └── out/
│   │                   └── persistence/
│   │                       └── JpaOrderRepositoryTest.java  // Test de la implementación JPA
│   └── resources/
│       └── test-application.yml                             // Configuración para entorno de test
│
├── build.gradle                                              // Build script del módulo raíz
├── settings.gradle                                           // Incluye los módulos del proyecto
├── gradle.properties                                         // Propiedades del proyecto (versiones, etc.)
├── gradlew                                                   // Wrapper script Unix
├── gradlew.bat                                               // Wrapper script Windows
├── gradle/
│   └── wrapper/                                              // Archivos del wrapper de Gradle
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties

```
