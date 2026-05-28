
![numerosity_org_cover](https://github.com/user-attachments/assets/a40c4845-7b31-4a02-ab4f-8676afe3ab30)

# Numerosity

## Development Status

The codebase is currently in Version 1.0.

This release is stable, but the project is now archived and will no longer receive future updates.

---

## Branches

Please use the `Prod` branch for the latest development state.

The `main` branch is intended only for stable pushes and snapshots.

---

## Codebase Notes

This project was developed under a tight timeframe. Some systems and structures were prioritized for functionality over long-term cleanup and refactoring.

---

## Launch / Project Purpose

Numerosity was built as a demonstration of:

- Java REST technologies
- Vaadin application architecture
- Firebase integration
- educational software design patterns
- full-stack application organization

The platform can still serve as a backend foundation for educational software projects.

However:

- the project is archived
- no future security updates will be provided
- maintainers are responsible for privacy and security compliance if reused

---

## Contributions

Contributions were previously welcome, but the project is now closed to further development.

---

# Numerosity Website

Numerosity is a math practice platform built around:

- Vaadin + Spring Boot
- Firebase-backed account/data support
- a lightweight public-facing website

This folder contains the static website for the project.

---

## AI Usage Disclaimer

- Demo questions were generated with LLMs
- Development was accelerated using tools such as:
  - OpenAI Codex
  - GitHub Copilot
  - other open-source tooling

Initial development and architecture were primarily implemented manually.

---

# Project At A Glance

Numerosity is organized as a full-stack Java application with:

- web UI
- REST endpoints
- backend services
- Firebase integration

## High-Level Flow

```text
Visitor / Student
│
├── Website Landing Page
│   ├── Project Overview
│   └── README Viewer
│
└── Vaadin Application
    ├── MainView
    ├── DashboardView
    └── REST API
        ├── Question Controller
        │   └── QuestionSeeder
        │
        └── User Controller
            └── UserService
                └── UserRepository
                    └── Firestore / Firebase
````

## General Application Flow

1. The static site explains the project
2. The Vaadin UI handles interaction
3. REST endpoints expose question/user operations
4. Firebase stores user/application data when configured

---

# Current Application Structure

The live application code is located under:

```text
server/src/main/java/org/vaadin/numerosity/
```

The project is split into several logical layers.

---

## Entry Point

* `Application.java`

  * Spring Boot entry point
  * includes `@PWA`
  * uses `@Theme(variant = "Lumo.dark")`

---

## Main UI

* `MainView.java`

  * primary landing view
  * top navigation
  * login dialog
  * mode routing

---

## Dashboard

* `ui/views/DashboardView.java`

  * statistics dashboard
  * performance summaries
  * repository-backed metrics

---

## REST API

* `rest/QuestionRestController.java`
* `rest/UserRestController.java`
* `rest/UserDTO.java`

---

## Services And Data Access

* `service/UserService.java`
* `repository/UserRepository.java`
* `repository/FsUserRepository.java`
* `repository/exception/DbException.java`

---

## Supporting Subsystems

* `Subsystems/LoginHandler.java`
* `Subsystems/QuestionSeeder.java`
* `Subsystems/QuestionContentLoader.java`
* `Subsystems/DatabaseHandler.java`
* `Subsystems/FirebaseHandler.java`
* `Subsystems/UserManager.java`

---

## Math And Practice Features

### Practice Modes

* `Featureset/AppFunctions/bank.java`
* `Featureset/AppFunctions/rush.java`
* `Featureset/AppFunctions/zen.java`

### Shared Components

* `Featureset/Supporter/OptionButton.java`

---

## Math Engine Tests

Located under:

```text
server/src/test/java/org/vaadin/numerosity/Featureset/MathEngine/
```

### Included Test Suites

* `AlgebraOneTest.java`

  * linear functions
  * systems
  * quadratics
  * slopes

* `AlgebraTwoTest.java`

  * logarithms
  * sequences
  * polynomial evaluation

* `CalculusTest.java`

  * derivatives
  * integrals
  * Riemann sums
  * limits

* `GeometryTest.java`

  * area/perimeter
  * polygons
  * triangles

* `PrecalculusTest.java`

  * trig functions
  * vectors
  * combinatorics

---

# Architecture

## Layered View

```text
Presentation Layer
│
├── MainView
├── DashboardView
├── Practice Modes
└── Static Website
     │
     ▼
Application Layer
│
├── QuestionRestController
├── UserRestController
└── UserService
     │
     ▼
Core Services
│
├── LoginHandler
├── QuestionSeeder
├── UserManager
└── DatabaseHandler
     │
     ▼
Persistence Layer
│
├── UserRepository
├── FsUserRepository
├── Firestore / Firebase
└── Question JSON Files
```

---

## Runtime Flow

1. User opens the website or Vaadin application
2. Frontend routes user to the correct view
3. UI requests data from services
4. Services delegate to repositories/subsystems
5. Firebase is used when credentials exist
6. Demo mode can be used for limited functionality

---

# Backend Features

## Application Entry

The backend launches through:

```text
Application.java
```

---

## Configuration

Firebase configuration is read from:

```text
server/src/main/resources/application.properties
```

### Important Properties

```properties
firebase.project.id=
firebase.credentials.path=
vaadin.launch-browser=true
server.port=${PORT:8080}
```

---

## Firebase Behavior

The backend supports flexible startup behavior:

* initializes Firebase when credentials exist
* supports fallback/demo operation
* startup handled through:

  * `ApplicationConfig`
  * `FirebaseInitializer`

---

## REST Endpoints

### Questions

```http
GET /api/questions
GET /api/questions/category/{category}
GET /api/questions/difficulty/{difficulty}
GET /api/questions/random
```

### Users

```http
GET /api/users
POST /api/users
```

---

## Dashboard Metrics

The dashboard tracks:

* total correct answers
* total incorrect answers
* accuracy rate
* streak indicators
* pace metrics
* average answer time
* total answered questions

---

# Practice Modes

The application currently exposes three main modes.

## Navigation Flow

```text
MainView
│
├── Bank Mode
│   └── Configurable question sets
│
├── Rush Mode
│   └── Timed answering
│
├── Zen Mode
│   └── Low-pressure practice
│
└── Dashboard
    └── Performance tracking
```

---

## Bank Mode

* configurable sessions
* topic targeting
* structured studying

---

## Rush Mode

* speed-focused answering
* live score tracking
* recall training

---

## Zen Mode

* relaxed environment
* lower pressure
* reinforcement-oriented

---

# Data Model And Storage

## User Data

The repository abstraction stores:

* user ID
* username
* correctness counts
* incorrect counts
* performance metrics

---

## Question Data

Question content is loaded from JSON resources.

### Example Files

```text
server/src/main/resources/data/questions-comprehensive.json
server/Database/Bank/questions.json
server/Database/Bank/questions-comprehensive.json
```

---

## Storage Flow

```text
Question JSON
    │
    ▼
QuestionSeeder

User Action
    │
    ▼
LoginHandler
    │
    ▼
Firebase / Firestore

API Request
    │
    ▼
QuestionRestController
    │
    ▼
QuestionSeeder

Dashboard Request
    │
    ▼
UserService
    │
    ▼
UserRepository
    │
    ▼
FsUserRepository
    │
    ▼
Firebase / Firestore
```

---

# Running Locally

## Static Website

Serve:

```text
server/website/
```

Then open:

```text
index.html
```

The README viewer works best when markdown is served from the same directory.

---

## Full Application

Run the app from:

```text
server/
```

using Maven or your IDE.

### Common Local URLs

```text
http://localhost:8080/
http://localhost:8080/dashboard
http://localhost:8080/api/questions
http://localhost:8080/api/users
```

---

## Firebase Setup

Configure Firebase credentials and project ID in:

```text
application.properties
```

If Firebase is missing:

* app may still start
* some persistence features will be unavailable
* demo/fallback behavior may be used

---

# Repository Layout

```text
docs/
  API.md
  ARCHITECTURE.md
  SETUP.md

src/
  main/
    java/org/vaadin/numerosity/
      Application.java
      MainView.java
      config/
      entity/
      Featureset/
      repository/
      rest/
      service/
      Subsystems/
      ui/

    resources/
      application.properties
      data/

  test/

website/
  index.html
  styles.css
  script.js
  physics.js
  README.md

target/
```

---

# Maintainer Notes

* Keep this README aligned with the live `server/` codebase
* Prefer documenting implemented behavior over planned behavior
* Update architecture sections when routes/classes change
* Document new website pages/features when added

---

# Quick Summary

Numerosity includes:

* a public-facing website
* a Vaadin-based application
* REST APIs
* Firebase-backed persistence

---

# Demo UI
<img width="1897" height="922" alt="image" src="https://github.com/user-attachments/assets/f5681be8-a407-43c8-baab-a92888978719" />

---

<img width="1892" height="914" alt="image" src="https://github.com/user-attachments/assets/d0eaa50e-66d4-4866-b840-2e016409e137" />


---

<img width="373" height="696" alt="image" src="https://github.com/user-attachments/assets/214dfd97-b543-4439-b0cc-ef0674f851aa" />

---

<img width="554" height="464" alt="image" src="https://github.com/user-attachments/assets/babfb094-cfc8-4e75-9f32-455379639890" />

---

<img width="521" height="723" alt="image" src="https://github.com/user-attachments/assets/155f0912-e774-4567-8490-b87946e13f52" />

---

* dashboards
* multiple practice modes
* configurable math practice systems

```
```
