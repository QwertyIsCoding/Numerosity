# Numerosity API Documentation

## Overview

The Numerosity REST API provides endpoints for:
- Question retrieval
- User profile and progress storage
- Health checks for integrations

**Base URL**: `http://localhost:8080/api/v1`

## Authentication

The public question and health endpoints are open.
The user endpoints depend on Firebase/Firestore being configured.

### Headers

```http
Authorization: Bearer <firebase-id-token>
Content-Type: application/json
```

## Endpoints

### Health API

#### Health Check

```http
GET /api/v1/health
```

### Questions API

#### Get All Questions

```http
GET /api/v1/questions
```

**Response**
```json
{
  "message": "Use /category/{category} or /difficulty/{difficulty} to filter",
  "endpoints": [
    "/api/v1/questions/category/{category}",
    "/api/v1/questions/difficulty/{difficulty}",
    "/api/v1/questions/random"
  ]
}
```

#### Get Questions by Category

```http
GET /api/v1/questions/category/{category}
```

**Example**
```bash
curl -X GET http://localhost:8080/api/v1/questions/category/Algebra
```

#### Get Questions by Difficulty

```http
GET /api/v1/questions/difficulty/{difficulty}
```

**Example**
```bash
curl -X GET http://localhost:8080/api/v1/questions/difficulty/hard
```

#### Get Random Question

```http
GET /api/v1/questions/random
```

**Query Parameters**
- `category` - optional filter by category
- `difficulty` - optional filter by difficulty

**Example**
```bash
curl -X GET "http://localhost:8080/api/v1/questions/random?category=Geometry&difficulty=medium"
```

### Users API

#### Create User

```http
POST /api/v1/users
```

**Request Body**
```json
{
  "userId": "abc123",
  "username": "user123"
}
```

#### Get User by ID

```http
GET /api/v1/users/{userId}
```

#### Update User

```http
PUT /api/v1/users/{userId}
```

## CORS

The API sends permissive CORS headers by default so browser-based websites can call it directly.
To lock it down, set:

```properties
app.cors.allowed-origins=https://your-website.example
```

## Error Responses

### 400 Bad Request

```json
{
  "error": "Invalid request",
  "message": "Email is required",
  "timestamp": "2026-05-28T14:45:00-04:00"
}
```

### 401 Unauthorized

```json
{
  "error": "Unauthorized",
  "message": "Invalid or expired token",
  "timestamp": "2026-05-28T14:45:00-04:00"
}
```

### 404 Not Found

```json
{
  "error": "Not Found",
  "message": "Question not found with id: xyz",
  "timestamp": "2026-05-28T14:45:00-04:00"
}
```

### 500 Internal Server Error

```json
{
  "error": "Internal Server Error",
  "message": "An unexpected error occurred",
  "timestamp": "2026-05-28T14:45:00-04:00"
}
```

## Code Examples

### JavaScript (Fetch)

```javascript
async function getQuestions(category) {
  const response = await fetch(`/api/v1/questions/category/${category}`, {
    headers: {
      'Authorization': `Bearer ${firebaseToken}`,
      'Content-Type': 'application/json'
    }
  });
  return await response.json();
}

async function createUser(userId, username) {
  const response = await fetch('/api/v1/users', {
    method: 'POST',
    headers: {
      'Authorization': `Bearer ${firebaseToken}`,
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({ userId, username })
  });
  return await response.json();
}
```

### Java (RestTemplate)

```java
@RestController
public class QuestionClient {

    private final RestTemplate restTemplate;

    public List<Question> getQuestionsByCategory(String category) {
        String url = "http://localhost:8080/api/v1/questions/category/" + category;
        ResponseEntity<QuestionResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            new HttpEntity<>(createHeaders()),
            QuestionResponse.class
        );
        return response.getBody().getQuestions();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(firebaseToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
```

### Python (Requests)

```python
import requests

def get_questions(category, token):
    headers = {
        'Authorization': f'Bearer {token}',
        'Content-Type': 'application/json'
    }
    response = requests.get(
        f'http://localhost:8080/api/v1/questions/category/{category}',
        headers=headers
    )
    return response.json()
```

## Packaging

The server builds as a Spring Boot executable JAR.

```bash
cd server
mvn clean package
java -jar target/numerosity-1.0.0.jar
```

## Integration Demo

If another developer wants to test the JAR quickly, the public website now includes a browser demo at [`server/website/demo.html`](../website/demo.html).

That demo shows the real integration loop:

1. start the JAR locally
2. set the frontend base URL to `http://localhost:8080/api/v1`
3. call `GET /health`
4. call `GET /questions/category/{category}`
5. render the JSON response in the browser
