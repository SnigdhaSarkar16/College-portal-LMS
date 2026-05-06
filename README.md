
# 🎓 Learning Management System (LMS)

## 📌 Project Overview

The Learning Management System (LMS) is a full-stack web application developed using Spring Boot, React.js, and PostgreSQL. The system provides secure JWT-based authentication and Role-Based Access Control (RBAC) for Students, Faculty, and Admin users.

The project allows students to enroll in courses, view attendance and marks, faculty to manage attendance and marks, and admins to manage academic resources such as subjects, courses, and announcements.

---

# 🚀 Features

## 🔐 Authentication & Security
- JWT Authentication
- Role-Based Access Control (RBAC)
- Secure APIs using Spring Security
- Protected routes for Student, Faculty, and Admin

---

## 👨‍🎓 Student Features
- Student Registration & Login
- View Profile
- Update Profile
- Enroll in Courses
- View Attendance
- View Marks

---

## 👨‍🏫 Faculty Features
- Faculty Registration & Login
- Mark Attendance
- Add / Update Student Marks

---

## 👨‍💼 Admin Features
- Admin Registration & Login
- Manage Subjects
- Manage Courses
- Create Announcements

---

# 🛠️ Tech Stack

## Backend
- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Hibernate / JPA
- PostgreSQL

## Frontend
- React.js
- JavaScript
- HTML/CSS

## Tools & Platforms
- IntelliJ IDEA
- Postman
- GitHub
- Render

---

# 🗄️ Database Modules

- Student Management
- Faculty Management
- Subject Management
- Course Management
- Enrollment Management
- Attendance Management
- Marks Management
- Fee Management
- Announcement Management

---

# 🔐 Role-Based Access Control (RBAC)

| Role | Permissions |
|------|-------------|
| Student | View enrollments, attendance, marks |
| Faculty | Mark attendance, add/update marks |
| Admin | Manage subjects, courses, announcements |

---

# ⚙️ Backend Setup Instructions

## 1️⃣ Clone Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_LINK
````

---

## 2️⃣ Open Project

Open the backend project in IntelliJ IDEA.

---

## 3️⃣ Configure PostgreSQL

Create a PostgreSQL database named:

```text
lms
```

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lms
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

---

## 4️⃣ Run Backend

```bash
mvn spring-boot:run
```

Backend runs on:

```text
http://localhost:8080
```

---

# 💻 Frontend Setup Instructions

## 1️⃣ Navigate to Frontend

```bash
cd lms-frontend
```

---

## 2️⃣ Install Dependencies

```bash
npm install
```

---

## 3️⃣ Start Frontend

```bash
npm start
```

Frontend runs on:

```text
http://localhost:3000
```

---

# 📮 Main API Endpoints

## 🔐 Authentication APIs

### Student

* `POST /api/auth/student/register`
* `POST /api/auth/student/login`

### Faculty

* `POST /api/auth/faculty/register`
* `POST /api/auth/faculty/login`

### Admin

* `POST /api/auth/admin/register`
* `POST /api/auth/admin/login`

---

## 👨‍🎓 Student APIs

* `GET /api/student/profile`
* `PUT /api/student/profile`
* `POST /api/student/enroll`
* `GET /api/student/enrollments`
* `GET /api/student/attendance`
* `GET /api/student/marks`

---

## 👨‍🏫 Faculty APIs

* `POST /api/faculty/attendance`
* `POST /api/faculty/marks`

---

## 👨‍💼 Admin APIs

* Subject Management APIs
* Course Management APIs
* Announcement APIs

---

# 🧪 Testing

## Postman Testing

All APIs were tested using Postman.

### Tested Scenarios

* Valid Authentication
* Invalid Token
* Unauthorized Access
* Duplicate Registration
* Role-Based Access Validation
* Attendance & Marks APIs

---

# 🌐 Deployment

Backend deployed using Render.

Deployment includes:

* Environment Variables
* PostgreSQL Integration
* Secure JWT Configuration

---

# 🔑 Environment Variables

```properties
JWT_SECRET=YOUR_SECRET_KEY
DB_PASSWORD=YOUR_DATABASE_PASSWORD
```

---

# 📷 Project Modules Demonstrated

* Authentication System
* JWT Security
* RBAC
* Student Dashboard
* Faculty Dashboard
* Attendance Module
* Marks Module
* Enrollment Module
* Frontend Integration

---

# 📚 Future Enhancements

* Swagger/OpenAPI Integration
* Email Notifications
* Fee Payment Gateway
* File Uploads
* Analytics Dashboard
* Responsive UI Improvements

---

# 👨‍💻 Author

Name:Snigdha Sarkar 

---

# 📌 Conclusion

This project demonstrates a complete full-stack Learning Management System with secure authentication, RBAC, database integration, RESTful APIs, and frontend-backend communication using modern web development technologies.

```
```
