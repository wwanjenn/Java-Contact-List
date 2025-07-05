# 📇 Contact Book

A simple Java-based CLI Contact Book that allows users to manage contacts with features like search, update, and persistent storage via JSON.

---

## ✨ Features

* **CRUD Operations**: Create, Read (View), Update, and Delete contacts.
* **Flexible Data Fields**:
  * Name (updatable)
  * Multiple nicknames
  * Multiple phone numbers
  * Multiple email addresses
* **Unique Contact IDs**: Each contact is assigned a unique, auto-incrementing ID for consistent identification even if the name changes.
* **Partial Match Search**: Find contacts by partial name, nickname, phone number, or email match.
* **Console Interface**:
  * Text-based menu loop
  * Input validation
  * Confirmation prompts
* **Persistent Storage**:
  * Load contacts from a JSON file on startup
  * Save contacts to a JSON file with overwrite support
* **Extensible Architecture**:
  * Clean OOP design (`Contact`, `ContactBook`)
  * Uses `List<Contact>` for easy manipulation
  * Uses `Set<String>` for deduplicated nicknames, numbers, and emails

---

## 🔧 Technologies

* **Java SE**
* **Gson** (for JSON parsing)
* **Java Collections** (`ArrayList`, `HashSet`)

---

## 📂 Future Improvements

> Things to consider adding later:

* GUI with JavaFX or Swing
* Category tags (e.g., "Family", "Work")
* Import/export to CSV
* `TreeMap`/`HashMap` for faster lookup by name or ID
* Phone/email validation using regular expressions
