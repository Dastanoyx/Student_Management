# 🎓 Student Course Enrollment System

A **Spring Boot 3** + **Hibernate/JPA** project built to model and manage a student–course enrollment system.  
It demonstrates clean entity relationships, proper database mapping with composite keys, and PostgreSQL integration.

---

## 🧩 Overview

This system allows you to manage:

- **Students** — personal info, books, and ID cards  
- **Courses** — course name & department  
- **Enrollments** — connecting students and courses with enrollment dates  
- **Books** — representing resources associated with students  
- **Student ID Cards** — one-to-one relation for each student

---

## ⚙️ Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend** | Spring Boot 3.1.5 |
| **ORM / JPA** | Hibernate (Spring Data JPA) |
| **Database** | PostgreSQL |
| **Build Tool** | Maven |
| **Language** | Java 17 |
| **Utilities** | Lombok, Spring DevTools, Actuator |

---

## 🧠 Domain Model

### Entity Relationships

| Entity | Relationship | Description |
|---------|---------------|--------------|
| **Student** | `1-1` → StudentIdCard | Each student has one ID card |
| **Student** | `1-N` → Book | A student can have multiple books |
| **Student** | `M-N` → Course (via Enrolment) | Students can enroll in multiple courses |
| **Course** | `1-N` → Enrolment | Each course can have multiple enrollments |
| **Enrolment** | `@EmbeddedId` | Uses composite key (student_id + course_id) |

---

## 🗄️ Database ER Diagram

```
  STUDENT ||--|| STUDENT_ID_CARD : has
  STUDENT ||--o{ BOOK : owns
  STUDENT ||--o{ ENROLMENT : enrolls
  COURSE  ||--o{ ENROLMENT : includes

  STUDENT {
    BIGINT student_id PK
    TEXT firstname
    TEXT lastname
    TEXT email UNIQUE
    INT  age
  }

  STUDENT_ID_CARD {
    BIGINT student_id_card PK
    TEXT  cardNumber UNIQUE
    BIGINT student_id FK
  }

  BOOK {
    BIGINT book_id PK
    TEXT  bookname
    TIMESTAMP bookDate
    BIGINT student_id FK
  }

  COURSE {
    BIGINT course_id PK
    TEXT  course_name
    TEXT  department
  }

  ENROLMENT {
    BIGINT student_id PK, FK
    BIGINT course_id  PK, FK
    TIMESTAMP enrolment_date
  }
```
---

## 🔧 Configuration

### 🧾 application.properties
```properties
spring.application.name=Student Course Enrollment Sytems

spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
spring.datasource.username= *****
spring.datasource.password= *****

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
```
