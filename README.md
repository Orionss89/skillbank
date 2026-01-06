# SkillBank 2.0 – Professional Service Exchange System

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.0-green)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-brown)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue)

## 📝 Project Description
SkillBank is an advanced backend "Time Banking" platform, designed in accordance with **Enterprise Java Development** standards. The project emphasizes security, clean architecture, and separation of concerns.

### Key Solutions:
* **Layer Separation (DTO & Mappers):** The API never returns internal database entities. The DTO (Data Transfer Object) pattern and Mappers were implemented to separate the presentation layer from the data layer. This increases security (prevention of password leakage) and flexibility.
* **Global Error Handling:** Instead of `try-catch` blocks in controllers, a `GlobalExceptionHandler` (based on `@ControllerAdvice`) was implemented. The application returns consistent JSON error messages (with 400/404/500 codes) thanks to custom exceptions (`BusinessException`, `ResourceNotFoundException`).
* **Logging (SLF4J):** Standard console output was replaced with professional event logging, allowing for the monitoring of application activity in a production environment.
* **Security and Validation:**
    * Input data validation at the DTO level (`@Valid`, `@NotBlank`).
    * Password hashing using the BCrypt algorithm.
* **Clean Code (Lombok & DI):** Utilization of `@RequiredArgsConstructor` for dependency injection via constructor (Constructor Injection) and the Builder pattern for object creation.

---

## 🛠️ Tech Stack
* **Core:** Java 17, Spring Boot 3
* **Data:** Spring Data JPA, Hibernate, MySQL
* **Documentation:** Swagger UI (OpenAPI)
* **Testing:** JUnit 5, Mockito
* **Utils:** Lombok, Maven, SLF4J (Logging)

---

## 🚀 Functionalities

### 1. Registration and Authentication
* User registration with automatic wallet creation.
* Validation of password strength and login uniqueness.
* API access restriction for unauthenticated guests.

### 2. Transaction System
* Transferring hours between users.
* **Explicit Save:** Explicit saving of wallet states within a transaction.
* Protection against negative balances and "self-transfers".

### 3. Ads (Ad System)
* Adding and browsing ads/listings.
* Validation of category and user existence before saving.
* API responses contain category/author names instead of nested JSON objects.

---

## 💾 Data Schema
Entities feature `@JsonIgnore` safeguards for bidirectional relationships and sensitive fields (passwords).

```mermaid
erDiagram
    USERS ||--|| WALLETS : has
    USERS ||--o{ ADS : creates
    CATEGORIES ||--o{ ADS : categorizes
    USERS ||--o{ TRANSACTIONS : executes

    USERS {
        Long id
        String username
        String password(HASH)
    }
