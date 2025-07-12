# MCP Hexagonal - Spring Boot Modular Architecture

Este proyecto implementa una arquitectura **hexagonal (ports & adapters)** con Spring Boot, siguiendo buenas prácticas de diseño limpio, modularidad, testing automatizado y preparación para integraciones CI/CD. Compatible con Visual Studio Code + GitHub Copilot para desarrollo asistido.

---

## 🚀 Generación del Proyecto (Spring Initializr)

Utiliza estas configuraciones para generar tu proyecto base desde Visual Studio Code o desde [https://start.spring.io](https://start.spring.io):

- **Language**: Java
- **Build Tool**: Gradle
- **Group Id**: `com.ezamora.mcp`
- **Artifact Id**: `mcp-hexagonal`
- **Packaging**: Jar
- **Java Version**: 17 o 21
- **Dependencies**:
  - Spring Web
  - Spring Boot DevTools
  - Spring Data JPA
  - Validation
  - Lombok
  - MySQL Driver

---

## 🧱 Estructura de Proyecto (Arquitectura Hexagonal)

```
src/
├── main/
│   ├── java/
│   │   └── com/yourcompany/yourapp/
│   │       ├── application/                         # Casos de uso
│   │       │   └── service/
│   │       │       └── CreateOrderService.java
│   │
│   │       ├── domain/                              # Modelo puro (sin frameworks)
│   │       │   ├── model/
│   │       │   │   ├── Order.java                   # Entidad del dominio
│   │       │   │   └── OrderLocation.java           # Enum del dominio
│   │       │   └── repository/
│   │       │       └── OrderRepository.java         # Puerto de salida (interfaz)
│   │
│   │       ├── infrastructure/                      # Adaptadores y config
│   │       │   ├── adapter/
│   │       │   │   ├── in/
│   │       │   │   │   └── rest/
│   │       │   │   │       └── OrderController.java # Adaptador de entrada
│   │       │   │   └── out/
│   │       │   │       ├── persistence/
│   │       │   │       │   ├── entity/
│   │       │   │       │   │   ├── OrderEntity.java       # Entidad JPA
│   │       │   │       │   │   └── OrderLocationEntity.java # Enum para persistencia (si es necesario)
│   │       │   │       │   ├── mapper/
│   │       │   │       │   │   └── OrderEntityMapper.java  # MapStruct: OrderEntity <-> Order
│   │       │   │       │   └── JpaOrderRepository.java     # Implementación JPA del puerto
│   │       │   │       └── client/
│   │       │   │           └── ProductApiClient.java       # Cliente HTTP externo
│   │       │   └── config/
│   │       │       ├── SwaggerConfig.java
│   │       │       └── WebSecurityConfig.java
│   │
│   │       └── YourAppApplication.java              # Clase principal @SpringBootApplication
│
│   └── resources/
│       ├── application.yml                          # Config principal
│       ├── application-dev.yml                      # Config dev
│       ├── application-prod.yml                     # Config prod
│       ├── static/
│       ├── templates/
│       └── banner.txt
│
├── test/
│   ├── java/
│   │   └── com/yourcompany/yourapp/
│   │       ├── application/
│   │       ├── domain/
│   │       └── infrastructure/
│   └── resources/
│       └── test-application.yml
│
├── build.gradle.kts                                 # o build.gradle
├── settings.gradle.kts                              # o settings.gradle
├── gradle.properties
├── Dockerfile                                       # Docker para empaquetado del microservicio
├── docker-compose.yml                               # Para levantar base de datos y dependencias
├── .dockerignore
├── .gitignore
└── gradle/
    └── wrapper/
        ├── gradle-wrapper.jar
        └── gradle-wrapper.properties
```

---

## ✅ Convenciones y buenas prácticas

| Capa         | Contenido                                                       |
|--------------|-----------------------------------------------------------------|
| `domain`     | Entidades puras y puertos (`interfaces`)                       |
| `application`| Lógica de negocio desacoplada (`@Service`)                     |
| `infrastructure`| Adaptadores externos: controladores REST, repositorios JPA  |
| `resources`  | Configuración del entorno y base de datos                      |
| `test`       | Pruebas unitarias y de integración separadas                   |

---

## 🧪 Testing Unitario y de Integración

Organiza los tests de la siguiente forma:

```
src/test/java/com/ezamora/mcp/
├── unit/
│   ├── application/
│   └── domain/
└── integration/
    └── infrastructure/
```

### Recomendaciones:

- Usa **JUnit 5 + Mockito** para pruebas unitarias.
- Usa **Testcontainers** o H2 para integración (opcional).
- Sigue la nomenclatura `ClaseTest.java` para cada clase.
- Aplica los principios FIRST:
  - **Fast** (rápidas)
  - **Independent** (independientes entre sí)
  - **Repeatable** (repetibles)
  - **Self-validating** (deben fallar solas si algo anda mal)
  - **Timely** (escritas junto al desarrollo)

---

### 📌 Ejemplo de Test Unitario

```java
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        User input = new User("1", "Edwin");
        when(userRepository.save(any())).thenReturn(input);

        User result = userService.execute(input);

        assertEquals("Edwin", result.getName());
    }
}
```

---

## 🛠 Configuración de MySQL (application.yml)

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mcp_hexagonal
    username: root
    password: tu_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MySQL8Dialect
```

---

## 💻 Configuración de Visual Studio Code

Recomendaciones para trabajar de forma óptima:

### Extensiones necesarias

- ✅ Java Extension Pack
- ✅ Spring Boot Extension Pack
- ✅ Lombok Annotations Support
- ✅ Gradle for Java
- ✅ Test Explorer for Java
- ✅ GitLens
- ✅ REST Client (para probar endpoints)
- ✅ GitHub Copilot (opcional)

### Scripts útiles para GitHub Copilot CLI

Puedes automatizar la estructura con este script:

```bash
.githubcopilotr/setup-hexagonal.sh
```

Contiene generación automática de carpetas y un README estandarizado.

---

## 📚 Recomendaciones adicionales

- Aplica principios SOLID + Clean Code
- No mezcles lógica de negocio en los controladores
- Usa DTOs para exponer entidades si el dominio es sensible
- Configura Swagger/OpenAPI para documentar tu API
- Usa perfiles (`dev`, `test`, `prod`) en `application.yml`
- Escribe pruebas junto al desarrollo y no después

---

## 👤 Autor

Edwin Zamora  
✉️ ezamora@tudominio.com  
🔗 LinkedIn: [https://www.linkedin.com/in/ezamora](https://www.linkedin.com/in/ezamora)


---

## 🧩 Ejemplo completo: Controlador REST + Puerto + Adaptador JPA

### 📦 Dominio

```java
// domain/model/User.java
package com.ezamora.mcp.domain.model;

public class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters y setters
}
```

```java
// domain/ports/UserRepositoryPort.java
package com.ezamora.mcp.domain.ports;

import com.ezamora.mcp.domain.model.User;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(String id);
}
```

---

### ⚙️ Aplicación

```java
// application/service/UserService.java
package com.ezamora.mcp.application.service;

import com.ezamora.mcp.domain.model.User;
import com.ezamora.mcp.domain.ports.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepositoryPort userRepository;

    public UserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getById(String id) {
        return userRepository.findById(id);
    }
}
```

---

### 🌐 Infraestructura

```java
// infrastructure/controller/UserController.java
package com.ezamora.mcp.infrastructure.controller;

import com.ezamora.mcp.application.service.UserService;
import com.ezamora.mcp.domain.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        Optional<User> result = userService.getById(id);
        return result.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
}
```

```java
// infrastructure/repository/UserEntity.java
package com.ezamora.mcp.infrastructure.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserEntity {
    @Id
    private String id;
    private String name;

    // Getters and setters
}
```

```java
// infrastructure/repository/UserJpaRepository.java
package com.ezamora.mcp.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, String> {}
```

```java
// infrastructure/repository/UserRepositoryAdapter.java
package com.ezamora.mcp.infrastructure.repository;

import com.ezamora.mcp.domain.model.User;
import com.ezamora.mcp.domain.ports.UserRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        UserEntity saved = jpaRepository.save(entity);
        return new User(saved.getId(), saved.getName());
    }

    @Override
    public Optional<User> findById(String id) {
        return jpaRepository.findById(id)
            .map(entity -> new User(entity.getId(), entity.getName()));
    }
}
```

---

Este ejemplo respeta la separación de capas:  
- `UserController` entra por el adaptador (infraestructura)  
- Pasa por `UserService` en la capa de aplicación  
- Llama a la interfaz `UserRepositoryPort`  
- Y la implementación está en `UserRepositoryAdapter`, que conecta con la base de datos vía JPA

