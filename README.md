# EMS CRUD (Tomcat 9 - javax)

A simple Employee Management System using JSP, Servlet (javax), JDBC and MySQL following MVC.

## Requirements
- JDK 8+
- Maven 3+
- Apache Tomcat 9.x
- MySQL 5.7/8.0

## Database
```sql
CREATE DATABASE ems_db;
USE ems_db;
CREATE TABLE employees (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) NOT NULL UNIQUE,
  department VARCHAR(50),
  salary DOUBLE
);
```

## Run
1. `mvn clean install`
2. Deploy `target/ems-crud.war` to Tomcat 9
3. Open: http://localhost:8080/ems-crud/

## Notes
- Configure DB credentials in `EmployeeDAO.java` (jdbcURL, username, password).
- JSTL is included (javax.servlet:jstl:1.2)
- URL mappings:
  - `/employee/list`
  - `/employee/new`
  - `/employee/insert`
  - `/employee/edit?id=...`
  - `/employee/update`
  - `/employee/delete?id=...`
# ems-crud
