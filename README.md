# SkillBank – Sąsiedzki Bank Umiejętności

## 📝 Opis Projektu
**SkillBank** to innowacyjna platforma webowa typu "Time Banking" (Bank Czasu), stworzona jako projekt końcowy kursu Java Developer. Aplikacja rozwiązuje problem braku środków finansowych na usługi profesjonalne, umożliwiając użytkownikom wymianę umiejętności w modelu barterowym (godzina za godzinę).

### Główny cel
Stworzenie bezpiecznego i skalowalnego środowiska, w którym społeczność może wymieniać się usługami (np. korepetycje za naprawę kranu), budując kapitał społeczny bez użycia pieniędzy.

---

## 🛠️ Technologie i Narzędzia
Projekt został zrealizowany zgodnie z najnowszymi standardami Java Developmentu:

* **Backend:** Java 17, Spring Boot 3 (Web, Data JPA, Security, Validation)
* **Baza Danych:** MySQL (Relacyjna)
* **ORM:** Hibernate
* **Bezpieczeństwo:** Spring Security + BCrypt (Szyfrowanie haseł)
* **Testy:** JUnit 5 + Mockito (Testy jednostkowe logiki biznesowej)
* **Dokumentacja API:** Swagger UI / OpenAPI
* **Narzędzia:** Maven, Lombok, Postman

---

## 🚀 Funkcjonalności (MoSCoW)

### MUST HAVE (Kluczowe funkcje zaimplementowane)
1.  **Rejestracja i Bezpieczeństwo:**
    * Tworzenie konta z walidacją danych.
    * Automatyczne tworzenie Portfela (Wallet) z bonusem startowym (5h).
    * Szyfrowanie haseł algorytmem BCrypt (Standard rynkowy).
2.  **Zarządzanie Ogłoszeniami (CRUD):**
    * Dodawanie ogłoszeń z przypisaniem do Kategorii.
    * Przeglądanie listy dostępnych usług.
3.  **System Transakcyjny (Core Logic):**
    * Przelewanie "godzin" między użytkownikami za wykonane usługi.
    * Pełna transakcyjność (`@Transactional`) – gwarancja spójności danych.
    * Zabezpieczenie przed ujemnym saldem.
    * Historia transakcji.

### DODATKI (Extra Points)
* **Integracja z zewnętrznym API:** Moduł motywacyjny pobierający losowe cytaty z zewnętrznego serwera.
* **Data Loader:** Automatyczne uzupełnianie bazy danymi startowymi (Kategorie, Role) przy uruchomieniu.
* **Automatyczna dokumentacja:** Wbudowany Swagger UI.

---

## 💾 Schemat Bazy Danych (ERD)

Aplikacja wykorzystuje znormalizowaną bazę danych składającą się z 6 tabel połączonych relacjami (One-to-One, One-to-Many, Many-to-Many).

```mermaid
erDiagram
    USERS ||--|| WALLETS : posiada
    USERS ||--o{ ADS : wystawia
    CATEGORIES ||--o{ ADS : zawiera
    USERS ||--o{ USER_ROLES : ma
    ROLES ||--o{ USER_ROLES : przypisana
    USERS ||--o{ TRANSACTIONS : wysyla
    USERS ||--o{ TRANSACTIONS : odbiera

    USERS {
        Long id
        String username
        String password
    }
    WALLETS {
        Long id
        int balance
    }
    TRANSACTIONS {
        Long id
        int amount
        DateTime timestamp
    }