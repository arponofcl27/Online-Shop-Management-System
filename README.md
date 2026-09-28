# 🛍️ Online Shop Management System

A Java-based **Online Shop Management System** developed for **CSE282 — Introduction to Programming Language II (Java)**, Summer 2026.

The project demonstrates the four core OOP principles — **Encapsulation, Abstraction, Inheritance, and Polymorphism** — along with exception handling, file-based data persistence, and a Java Swing GUI.

## ✨ Features

- Product management and stock tracking
- Customer registration and management
- Order management
- Cash and mobile payment methods
- Custom exception handling
- Local file-based data storage
- Java Swing graphical user interface

## 🧩 Main Classes

- `Product` — Product information and stock management
- `User` / `Customer` — User and customer management
- `Order` — Order processing
- `PaymentMethod` — Payment interface
- `CashPayment` — Cash payment processing
- `MobilePayment` — Mobile payment processing
- `OutOfStockException` — Stock validation
- `MainGUI` — Main graphical interface

## 📊 UML Class Diagram

![UML Class Diagram](java%20project%20diagram.jpeg)

## 📂 Project Structure

```text
Online-Shop-Management-System/
│
├── src/
│   ├── CashPayment.java
│   ├── Customer.java
│   ├── MainGUI.java
│   ├── MobilePayment.java
│   ├── Order.java
│   ├── OutOfStockException.java
│   ├── PaymentMethod.java
│   ├── Product.java
│   └── User.java
│
├── .gitignore
├── LICENSE
├── README.md
└── OnlineShopManagementSystem.iml
```

# ▶️ How to Run

### Requirements

- **JDK** installed on your computer
- **IntelliJ IDEA**
- Git (only required if cloning the repository)

### 1. Download the Project

Clone the repository:

```bash
git clone https://github.com/arponofcl27/Online-Shop-Management-System.git
```

Or download the repository as a ZIP from GitHub and extract it.

### 2. Open in IntelliJ IDEA

Open IntelliJ IDEA → **Open** → select the `Online-Shop-Management-System` folder.

Make sure a JDK is selected under:

**File → Project Structure → Project → Project SDK**

### 3. Run the Program

Open:

```text
src/MainGUI.java
```

Right-click `MainGUI.java` and select:

**Run 'MainGUI.main()'**

The Java Swing application will start.

> If IntelliJ does not recognize `src` as the source folder, right-click `src` → **Mark Directory as → Sources Root**.

## 👥 Team Members

| # | Name | Student ID | GitHub |
|---|---|---|---|
| 1 | Muhammad Sydul Islam *(Team Lead)* | 2024100000409 | [@arponofcl27](https://github.com/arponofcl27) |
| 2 | Ainun Nahar Kona | 2024100000433 | [@ainunkona22](https://github.com/ainunkona22) |
| 3 | Saidur Rahman Joy | 2024100000246 | [@saidur246](https://github.com/saidur246) |
| 4 | Sadia Islam Sinthya | 2024100000056 | [@sinthywow](https://github.com/sinthywow) |

## 🎓 Course

**CSE282 — Introduction to Programming Language II (Java)**  
**Semester:** Summer 2026  
**Project Type:** Complex Engineering Project

## 📄 License

Developed for academic purposes as part of the CSE282 course requirements.
