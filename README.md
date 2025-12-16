# Inventory Management API

A Spring Boot REST API for managing product inventory with strict business rules and automatic status handling.

This project was built to practice real-world backend development using clean architecture and domain logic.

---

## 🚀 Features

- Create, retrieve, update, and delete products
- Automatic product status management
- Strict business rule enforcement
- RESTful API design
- MySQL persistence using Spring Data JPA

---

## 📦 Product Status Lifecycle

Products have the following statuses:

- `ACTIVE`
- `OUT_OF_STOCK`
- `DISCONTINUED`

### Business Rules

- New products are always created as `ACTIVE`
- Quantity can never be negative
- If quantity becomes `0`, status changes to `OUT_OF_STOCK`
- If quantity is greater than `0`, status is `ACTIVE`
- `DISCONTINUED` products cannot be updated
- Only `ACTIVE` or `OUT_OF_STOCK` products can be discontinued

---

## 🛠 Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

---

## 📌 API Endpoints

### Create Product
POST /api/products
### Get All Products
GET /api/products
### Get Product by ID
GET /api/products/{id}
### Update Product Quantity
PATCH /api/products/{id}
### Discontinue Product 
PATCH /api/products/{id}/discontinue
### Delete Product

---

⚙️ Configuration

Update database credentials in:
src/main/resources/application.properties

Example:

spring.datasource.url=jdbc:mysql://localhost:3306/inventory_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD



