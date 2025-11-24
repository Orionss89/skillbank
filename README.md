# SkillBank – Sąsiedzki Bank Umiejętności

## 📝 Opis Projektu
**SkillBank** to innowacyjna platforma webowa typu "Time Banking", która umożliwia użytkownikom wymianę usług bez użycia tradycyjnych pieniędzy.

### Jaki problem rozwiązujemy?
Wiele osób posiada cenne umiejętności (naprawy, edukacja, pomoc domowa), ale barierą w ich wykorzystaniu jest brak pieniędzy na opłacenie usług profesjonalnych. Nasza aplikacja łączy ludzi, pozwalając im płacić "czasem" – jedną godzinę pracy za jedną godzinę innej usługi.

### Główne założenia
* Wymiana usług w modelu barterowym (godzina za godzinę).
* Budowanie zaufanej społeczności sąsiedzkiej.
* Możliwość zdobycia pomocy bez angażowania środków finansowych.

---

## 🛠️ Wykorzystane Technologie
Projekt został stworzony w oparciu o nowoczesne standardy Java Developmentu:

* **Język:** Java 17
* **Framework:** Spring Boot 3.x (Web, Data JPA, Validation)
* **Baza Danych:** MySQL
* **Narzędzia:** Maven, Lombok, Docker (opcjonalnie)
* **Dokumentacja API:** Swagger / OpenAPI

---

## 📋 Planowane Funkcjonalności (Metoda MoSCoW)

### MUST HAVE (Kluczowe dla działania)
* **Rejestracja i Logowanie:** Bezpieczny dostęp do konta.
* **Portfel Godzin:** System naliczania i przechowywania salda czasu.
* **Ogłoszenia (CRUD):** Dodawanie, edycja, usuwanie i przeglądanie ofert.
* **Transakcje:** Przekazywanie waluty czasu między użytkownikami.
* **Kategorie:** Grupowanie ogłoszeń (np. Edukacja, Dom).

### SHOULD HAVE (Ważne rozszerzenia)
* Historia transakcji użytkownika.
* Wyszukiwanie i filtrowanie ogłoszeń.
* Walidacja danych (np. blokada transakcji przy braku środków).

### COULD HAVE (Dodatki)
* System ocen i komentarzy po wykonanej usłudze.
* Panel Administratora.

---

## 💾 Baza Danych

Aplikacja korzysta z relacyjnej bazy danych MySQL. Struktura zawiera **5 głównych tabel** połączonych relacjami (One-to-Many oraz Many-to-Many).

### Schemat Logiczny
```mermaid
[USER] --<posiada>-- [ROLE]
[USER] --<wystawia>-- [AD]
[CATEGORY] --<zawiera>-- [AD]
[USER] --<wykonuje>-- [TRANSACTION]
.