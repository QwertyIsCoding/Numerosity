# Numerosity Quickstart

This quickstart is for developers who already have the Numerosity JAR and want to make a first successful API call as fast as possible.

## 1. Run the JAR

From the `server/` directory:

```bash
java -jar target/numerosity-1.0.0.jar
```

By default, the API will be available at:

```text
http://localhost:8080/api/v1
```

## 2. Check That It Is Alive

Open this in a browser or use `curl`:

```bash
curl http://localhost:8080/api/v1/health
```

Expected response:

```json
{
  "status": "ok",
  "service": "numerosity",
  "timestamp": "..."
}
```

## 3. Fetch Questions

Try a category query:

```bash
curl http://localhost:8080/api/v1/questions/category/Algebra
```

Or ask for a random question:

```bash
curl "http://localhost:8080/api/v1/questions/random?category=Geometry"
```

## 4. Call It From a Website

Use `fetch` from your frontend:

```javascript
const baseUrl = "http://localhost:8080/api/v1";

async function loadRandomQuestion() {
  const response = await fetch(`${baseUrl}/questions/random?category=Algebra`);
  if (!response.ok) throw new Error("Request failed");
  return response.json();
}
```

## 5. Optional: Create a User

If Firebase and Firestore are configured in the backend, you can create or update users:

```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d "{\"userId\":\"abc123\",\"username\":\"demo-user\"}"
```

## Common Problems

- If the port is busy, set `server.port` in `server/src/main/resources/application.properties`.
- If user endpoints fail, check your Firebase credentials.
- If browser requests fail, confirm `app.cors.allowed-origins` includes your site.

## Next Step

Read [`README.dev.md`](README.dev.md) for the full integration guide and [`demo.html`](demo.html) for a browser demo.
