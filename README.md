# Bank Management System (Java Desktop App)

A GUI-based desktop application built using **Java (AWT/Swing)** and **JDBC** to manage essential banking operations such as creating accounts, handling transactions, and storing data securely.

---

## ✅ Features

### 👤 Account Management
- Create new bank accounts
- View and update customer details
- Delete accounts if needed

### 💰 Transactions
- Deposit money
- Withdraw funds
- Check account balance

### 🔐 User Authentication
- Basic login functionality for users/admin

### 🗃 Database Integration
- MySQL database connectivity using JDBC
- CRUD operations for account and transaction management

---

## 🛠 Tech Stack

| Component     | Technology |
|---------------|------------|
| Language      | Java       |
| GUI Framework | AWT/Swing  |
| Architecture  | MVC        |
| Database      | MySQL      |
| Connectivity  | JDBC       |

---

## 📂 Project Structure

├── AWT/ # GUI forms and screens
├── BankManagementSystem/ # Core logic, controllers, and database classes
├── JDBC Demo/ # JDBC example/testing code
├── .gitignore
├── BankManagementSystem.iml


## ⚙️ Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/Priyanshutrip/BankManagement.git
cd BankManagement
2. Open the Project in an IDE
Use IntelliJ IDEA, Eclipse, or NetBeans.

3. Add MySQL JDBC Connector
Include the JDBC driver JAR in your project libraries.

4. Configure the Database
Create a database (e.g., bankdb)

Create tables for accounts, users, and transactions

Update database credentials in the connection class

5. Run the Application
Start the main class (like Main.java or the first GUI screen).

📧 Author
Priyanshu Tripathi
GitHub: Priyanshutrip
