# Ecommerce_project

A complete e-commerce shoes web application built using **Java**, **JSP**, **JDBC**, and **MySQL**. This project provides essential online shopping features including user authentication, product catalog, cart management, and order processing.

## 🔑 Key Features

- 🔐 User Registration & Login
- 🛍️ Browse Products by Category
- 🛒 Add to Cart
- ✅ Place Orders
- 📋 View Order History
- 🚪 Session-based Login/Logout
- 🛠️ Admin Product Management (optional extension)


## 🧱 Tech Stack

- **Frontend:** HTML, CSS, JSP
- **Backend:** Java (Servlets), JDBC
- **Database:** MySQL
- **Server:** Apache Tomcat
- **IDE:** Eclipse


## ⚙️ How to Run the Project

1. **Clone this Repository**
   ```bash
   git clone https://github.com/dipakdongre/Ecommerce_project.git
   
2. **Open in Eclipse:**
File → Import → Existing Projects into Workspace → Select Folder

3. **Create MySQL Database:**
Import your ecommerce.sql dump if provided
OR create tables manually if not included

4. **Configure Database Connection:**
Edit DbCon.java file:
String url = "jdbc:mysql://localhost:3306/your_database";
String user = "your_username";
String password = "your_password";

5. **Run the Project:**
Deploy the project on Apache Tomcat
Access it via http://localhost:8080/Ecommerce_project

## 📁 Project Structure

```
Ecommerce_project/
├── src/
│   ├── connection/      # Database connection class
│   ├── dao/             # Data Access Object (JDBC logic)
│   ├── model/           # JavaBeans / POJOs
│   └── servlet/         # Controller logic
├── webapp/
│   ├── includes/        # Header/footer JSPs
│   ├── pages/           # User-facing pages
│   └── *.jsp            # Main JSP files
├── .gitignore
└── README.md
```
## 🧑‍💻 Author
Dipak Dongre
