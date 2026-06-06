# Construction Workforce Management Backend

## Overview

This project is based on an existing Spring Boot HRMS codebase and has been extended to support workforce attendance and overtime management for construction workers.

The application uses:

* Java 21
* Spring Boot 3.5.x
* Spring Data JPA
* PostgreSQL
* Lombok
* Hibernate

---

## Current Implementation Status

### Completed

* Spring Boot migration to 3.5.x
* Java 21 compatibility update
* PostgreSQL integration
* Workforce domain setup

Entities:

* Worker
* Site
* AttendanceLog
* OvertimeEntry

Repositories:

* WorkerRepository
* SiteRepository
* AttendanceLogRepository
* OvertimeEntryRepository

APIs:

### Worker APIs

* POST /api/workers
* GET /api/workers/{id}

### Site APIs

* POST /api/sites
* GET /api/sites/{id}

### Attendance APIs

* POST /api/attendance/clock-in
* POST /api/attendance/clock-out

Implemented Business Rules:

* Worker must exist
* Site must exist
* Worker must be active
* Site must be active
* Duplicate clock-in prevention
* Total hours calculation
* Overtime hours calculation
* Attendance flagging for long shifts
* OvertimeEntry creation on clock-out

---

## Pending Features

* Redis Active Worker Cache
* Overtime Summary API
* Overtime Settlement API
* Monthly overtime cap validation
* Advanced overtime rate calculation
* Attendance History API with Pagination
* Structured Exception Handling
* LF-201 Ticket
* LF-202 Ticket
* LF-203 Ticket
* LF-204 Ticket
* LF-205 Ticket

---

## Project Structure

```text
src/main/java

└── workforce
    ├── controller
    │
    ├── entity
    │   ├── Worker
    │   ├── Site
    │   ├── AttendanceLog
    │   └── OvertimeEntry
    │
    ├── enums
    │   ├── Designation
    │   └── SettlementStatus
    │
    ├── io
    │   └── request
    │       ├── WorkerRequest
    │       ├── SiteRequest
    │       ├── ClockInRequest
    │       └── ClockOutRequest
    │
    ├── repository
    │
    └── service
```

---

## Database

Database Name:

```text
amigoscode
```

Configured through PostgreSQL.

---

## Run Instructions

1. Create PostgreSQL database

```sql
CREATE DATABASE amigoscode;
```

2. Update database credentials in application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/amigoscode
spring.datasource.username=postgres
spring.datasource.password=your_password
```

3. Run the Spring Boot application

```bash
mvn spring-boot:run
```

4. Access APIs using Postman

---

## Notes

This repository contains a partially completed implementation of the workforce attendance and overtime assignment.

The core attendance workflow (Worker → Site → Clock-In → Clock-Out) is functional and successfully persists data in PostgreSQL.

Additional features and optimization tickets are planned for future implementation.
