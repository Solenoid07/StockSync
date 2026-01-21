# 📦 StockSync - Backend Inventory Management System

**StockSync** is a robust, production-ready REST API designed for efficient inventory and order management. Built with **Spring Boot** and secured with **JWT Authentication**, it is fully containerized using **Docker** and deployed on the cloud with a serverless **TiDB (MySQL)** database.

> 🚀 **Live Demo (Swagger UI):** [https://stocksync-2z64.onrender.com/swagger-ui/index.html](https://stocksync-2z64.onrender.com/swagger-ui/index.html)  
> *(Note: The server is hosted on Render's Free Tier. If it's sleeping, please allow 50-60 seconds for the cold start.)*

## 🛠️ Tech Stack
* **Backend Framework:** Java 21, Spring Boot 3.3
* **Database:** TiDB (Serverless Cloud MySQL)
* **Containerization:** Docker (Multi-stage builds)
* **Security:** Spring Security, JWT (Stateless Authentication)
* **Deployment:** Render (Cloud PaaS)
* **Documentation:** Swagger / OpenAPI 3.0

## ✨ Key Features
* **🔐 Secure Authentication:** Role-based access control (ADMIN vs CUSTOMER) using JWT tokens.
* **📦 Product Management:** CRUD operations for inventory tracking.
* **🛒 Order Processing:** Real-time order creation and status updates.
* **☁️ Cloud Native:** Stateless architecture designed for horizontal scaling.
* **🐳 Dockerized:** Fully containerized environment ensuring consistency.

