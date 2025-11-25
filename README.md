# SkillBank 2.0 – Profesjonalny System Wymiany Usług

## 📝 Opis Projektu
SkillBank to zaawansowana platforma backendowa typu "Time Banking", zaprojektowana zgodnie ze standardami **Enterprise Java Development**. Projekt kładzie nacisk na bezpieczeństwo, czystość architektury i separację warstw.

### Kluczowe Cechy Architektury
* **Wzorzec DTO (Data Transfer Object):** API nigdy nie zwraca encji bazodanowych. Dane są mapowane na bezpieczne obiekty transferowe.
* **Separacja Warstw:** Logika biznesowa jest całkowicie oddzielona od Kontrolerów REST.
* **Bezpieczeństwo:** Hasła są szyfrowane (BCrypt), a dostęp do kluczowych funkcji wymaga autoryzacji (Basic Auth).
* **Walidacja:** Dane wejściowe są weryfikowane na poziomie DTO (`@Valid`, `@NotBlank`), a błędy obsługiwane globalnie.

---

## 🛠️ Stack Technologiczny
* **Core:** Java 17, Spring Boot 3
* **Data:** Spring Data JPA, Hibernate, MySQL
* **Security:** Spring Security (Basic Auth + BCrypt)
* **API Docs:** Swagger UI (OpenAPI)
* **Testing:** JUnit 5, Mockito
* **Utils:** Lombok, Maven

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