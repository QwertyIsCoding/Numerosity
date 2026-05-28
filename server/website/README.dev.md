# Numerosity Developer Integration Guide

This guide is for developers who want to integrate the Numerosity API into an existing website, learning platform, or internal tool.

## What You Get

Numerosity exposes a small public API surface for:

- question retrieval
- user profile and progress storage
- a health endpoint for deployment checks

Public endpoints use the `/api/v1` prefix.

## Base URL

```text
http://localhost:8080/api/v1
```

For production, replace `localhost:8080` with your deployed backend host.

## Public Endpoints

### Health

```http
GET /health
```

Use this to check whether the backend is online.

### Questions

```http
GET /questions
GET /questions/category/{category}
GET /questions/difficulty/{difficulty}
GET /questions/random
```

These endpoints are intended for browser clients, server-side renderers, and mobile apps that need question content.

### Users

```http
POST /users
GET /users/{userId}
PUT /users/{userId}
```

These endpoints depend on Firebase/Firestore being configured in the backend.

## Browser Integration

If you are calling the API from a website, include the normal `fetch` headers:

```javascript
const response = await fetch("https://your-backend.example/api/v1/questions/random", {
  headers: {
    "Content-Type": "application/json"
  }
});
```

The backend is configured with permissive CORS by default so browser requests can work without extra proxying.

If you want to lock CORS down, set:

```properties
app.cors.allowed-origins=https://your-site.example
```

## Live Demo

The public website includes a browser demo at [`demo.html`](demo.html). It shows the same flow a real integration would use:

1. point the frontend at the JAR-backed API
2. call `GET /api/v1/health`
3. fetch questions from `GET /api/v1/questions/category/{category}`
4. render the response in the browser

This is the quickest way to verify that a separate website can talk to Numerosity.

## Recommended Integration Flow

1. Call `GET /api/v1/health` during deployment or startup checks.
2. Fetch questions with `GET /api/v1/questions/...`.
3. Store or update users with `POST` and `PUT` requests when Firestore is enabled.
4. Keep your frontend on the same origin or update the allowed CORS list before going live.

## Packaging

The backend builds as an executable Spring Boot JAR:

```bash
cd server
mvn clean package
java -jar target/numerosity-1.0.0.jar
```

## Notes

- The UI and API live in the same project, but the API can be consumed independently.
- If you are building a separate website, you do not need to embed Vaadin. You can call the REST API directly.
- If you want to build a custom client SDK, this guide is the best starting point.
