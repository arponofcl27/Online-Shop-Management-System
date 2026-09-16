# 🛍️ Online Shop Management System

A Java-based **Online Shop Management System** built for the **CSE282 — Introduction to Programming Language II (Java)** Complex Engineering Project (Summer 2026). The system enables users to register products and customers, manage orders, process payments, and generate reports — demonstrating all four Object-Oriented Programming principles, structured exception handling, file-based data persistence, and a Java Swing graphical user interface.

---

## 📌 Project Overview

This project simulates a simplified e-commerce backend and desktop interface where:
- Customers can be registered and manage their orders
- Products can be added, updated, and tracked for stock
- Orders link customers, products, and payments together
- Payments can be made via multiple methods, each processed differently
- All data is persisted to local files, with robust validation and custom exception handling throughout

The system is designed around a clean class hierarchy so that every core OOP principle — **Encapsulation, Abstraction, Inheritance, and Polymorphism** — is directly visible in the code structure, not just used incidentally.

---

## 📊 UML Class Diagram

![UML Class Diagram](Online_Shop_UML_Diagram.png)

The diagram above shows the full class structure, including inheritance (`User` → `Customer`), interface realization (`PaymentMethod` → `CashPayment`, `MobilePayment`), and the composition relationship between `Customer` and `Product`.

---

## 👥 Team Members

| # | Name | Student ID | GitHub |
|---|------|------------|--------|
| 1 | Muhammad Sydul Islam *(Team Lead)* | 2024100000409 | [@arponofcl27](https://github.com/arponofcl27) |
| 2 | Ainun Nahar Kona | 2024100000433 | [@ainunkona22](https://github.com/ainunkona22) |
| 3 | Saidur Rahman Joy | 2024100000246 | [@saidur246](https://github.com/saidur246) |
| 4 | Sadia Islam Sinthya | 2024100000056 | [@sinthywow](https://github.com/sinthywow) |

---

## 🧩 Core Classes & Responsibilities

| Class(es) | Owner | OOP Principle Demonstrated | Description |
|---|---|---|---|
| `Product` | Saidur Rahman Joy | Encapsulation | Manages product details, pricing, and stock, with private fields and controlled access via getters/setters |
| `User` (abstract), `Customer` | Sadia Islam Sinthya | Inheritance, Abstraction | Defines shared user behavior in an abstract base class, extended by `Customer` |
| `Order` | Muhammad Sydul Islam | Aggregation / composition of core entities | Links a `Customer`, one or more `Product`s, and a `PaymentMethod` into a single transaction |
| `PaymentMethod` (interface), `CashPayment`, `MobilePayment` | Ainun Nahar Kona| Polymorphism | A shared interface implemented differently by each payment type, allowing `Order` to process payments without knowing the exact implementation |

---

## ⚙️ Features

- ✅ Full CRUD (Create, Read, Update, Delete) operations for products, customers, and orders
- ✅ File-based data persistence (no external database required)
- ✅ Custom exception classes for input validation and business rule enforcement (e.g. `OutOfStockException`, `DuplicateUserException`, `EmptyCartException`, `PaymentFailedException`)
- ✅ Structured error handling using try-catch throughout the application
- ✅ Java Swing GUI with multiple panels for product management, customer registration, and order checkout

---

## 🛠️ Tech Stack

- **Language:** Java
- **GUI Framework:** Java Swing
- **IDE:** IntelliJ IDEA
- **Data Storage:** File-based (local text files)
- **Version Control:** Git & GitHub

---

## 📂 Project Structure

```
online-shop-management-system/
│
├── src/
│   ├── model/
│   │   ├── Product.java
│   │   ├── User.java
│   │   ├── Customer.java
│   │   ├── Order.java
│   │   ├── PaymentMethod.java
│   │   ├── CashPayment.java
│   │   └── MobilePayment.java
│   │
│   ├── exceptions/
│   │   ├── OutOfStockException.java
│   │   ├── DuplicateUserException.java
│   │   ├── EmptyCartException.java
│   │   ├── InvalidOrderException.java
│   │   ├── PaymentFailedException.java
│   │   └── InsufficientBalanceException.java
│   │
│   ├── gui/
│   │   ├── ProductPanel.java
│   │   ├── LoginPanel.java
│   │   ├── OrderPanel.java
│   │   └── MainFrame.java
│   │
│   └── util/
│       └── FileManager.java
│
├── data/
│   ├── products.txt
│   ├── customers.txt
│   └── orders.txt
│
└── README.md
```

---

## ▶️ How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/arponofcl27/online-shop-management-system.git
   ```
2. Open the project folder in **IntelliJ IDEA**.
3. Locate the `MainFrame.java` file inside the `gui` package.
4. Run `MainFrame.java` to launch the application.

---

## 📖 Course Information

- **Course:** CSE282 — Introduction to Programming Language II (Java)
- **Semester:** Summer 2026
- **Project Type:** Complex Engineering Project
- **Total Marks:** 40

---

## 📄 License

This project is developed for academic purposes as part of the CSE282 course requirements.
