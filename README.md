# SkillBank 2.0 – Profesjonalny System Wymiany Usług

## 📝 Opis Projektu
SkillBank to zaawansowana platforma backendowa typu "Time Banking", zaprojektowana zgodnie ze standardami **Enterprise Java Development**. Projekt kładzie nacisk na bezpieczeństwo, czystość architektury i separację warstw.

### Kluczowe rozwiązania:
* **Separacja Warstw (DTO & Mappers):** API nigdy nie zwraca wewnętrznych encji bazy danych. Zastosowano wzorzec DTO (Data Transfer Object) oraz Mappery, aby oddzielić warstwę prezentacji od warstwy danych. Zwiększa to bezpieczeństwo (brak wycieku haseł) i elastyczność.
* **Globalna Obsługa Błędów (Centralized Error Handling):** Zamiast bloków `try-catch` w kontrolerach, zaimplementowano `GlobalExceptionHandler` (oparty o `@ControllerAdvice`). Aplikacja zwraca spójne komunikaty błędów JSON (z kodami 400/404/500) dzięki własnym wyjątkom (`BusinessException`, `ResourceNotFoundException`).
* **Logowanie (SLF4J):** Zastąpiono standardowe wyjście konsoli profesjonalnym logowaniem zdarzeń, co pozwala na monitorowanie działania aplikacji w środowisku produkcyjnym.
* **Bezpieczeństwo i Walidacja:**
    * Walidacja danych wejściowych na poziomie DTO (`@Valid`, `@NotBlank`).
    * Szyfrowanie haseł algorytmem BCrypt.
* **Czysty Kod (Lombok & DI):** Wykorzystanie `@RequiredArgsConstructor` do wstrzykiwania zależności przez konstruktor (Constructor Injection) oraz wzorca Builder do tworzenia obiektów.

---

## 🛠️ Stack Technologiczny
* **Core:** Java 17, Spring Boot 3
* **Data:** Spring Data JPA, Hibernate, MySQL
* **Documentation:** Swagger UI (OpenAPI)
* **Testing:** JUnit 5, Mockito
* **Utils:** Lombok, Maven, SLF4J (Logging)
---

## 🚀 Funkcjonalności i Bezpieczeństwo

### 1. Rejestracja i Autentykacja
* Rejestracja użytkownika z automatycznym tworzeniem portfela.
* Walidacja siły hasła i unikalności loginu.
* Blokada dostępu do API dla niezalogowanych gości.

### 2. System Transakcyjny
* Przelewanie godzin między użytkownikami.
* **Explicit Save:** Jawny zapis stanu portfeli w transakcji.
* Zabezpieczenie przed ujemnym saldem i przelewami "do siebie".

### 3. Ogłoszenia (Ad System)
* Dodawanie i przeglądanie ogłoszeń.
* Walidacja istnienia kategorii i użytkownika przed zapisem.
* Odpowiedzi API zawierają nazwy kategorii/autorów zamiast zagnieżdżonych obiektów JSON.

---

## 💾 Schemat Danych (Zabezpieczony)
Encje posiadają zabezpieczenia `@JsonIgnore` dla relacji dwukierunkowych oraz pól wrażliwych (hasło), co stanowi dodatkową warstwę ochrony obok DTO.

```mermaid
erDiagram
    USERS ||--|| WALLETS : ma
    USERS ||--o{ ADS : tworzy
    CATEGORIES ||--o{ ADS : kategoryzuje
    USERS ||--o{ TRANSACTIONS : wykonuje

    USERS {
        Long id
        String username
        String password(HASH)
    }