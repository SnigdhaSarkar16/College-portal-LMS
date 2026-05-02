# 🎓 LMS Backend System

## 📌 Overview
This is a Spring Boot based Learning Management System (LMS) with JWT authentication and Role-Based Access Control (RBAC).

---

## ⚙️ Tech Stack
- Java (Spring Boot)
- Spring Security (JWT)
- PostgreSQL
- JPA / Hibernate

---

## 🔐 Features
- Authentication (Student, Faculty, Admin)
- Role-Based Access Control
- Subject & Course Management
- Enrollment System
- Attendance Tracking
- Marks Management (auto grading)
- Fee Payment System
- Announcements

---

## 🚀 How to Run

1. Clone repo
2. Setup PostgreSQL
3. Update `application.properties`
4. Run:
   mvn spring-boot:run


---

## 🔗 API Examples

### Login
POST /api/auth/student/login

### Enroll
POST /api/student/enroll?courseId=1

### Attendance
POST /api/faculty/attendance

---

## 🧪 Postman Collection
[LMS Week 5 – RBAC Tests.postman_collection.json](../LMS%20Week%205%20%E2%80%93%20RBAC%20Tests.postman_collection.json)
[LMS Week 5 – Error Tests.postman_collection.json](../LMS%20Week%205%20%E2%80%93%20Error%20Tests.postman_collection.json)
[LMS Week 4 APIs.postman_collection.json](../LMS%20Week%204%20APIs.postman_collection.json)
[LMS Auth API.postman_collection.json](../LMS%20Auth%20API.postman_collection.json)
---

## 👨‍💻 Author
Snigdha Sarkar