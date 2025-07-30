# URL Shortener

Simple Service with Kotlin and Spring Boot to shorten an URL by giving it a random generated Hash.

## Setup

1. A .env file is needed containing the following:
POSTGRES_USER=username
POSTGRES_PASSWORD=password
POSTGRES_DB=db

2. Start PostgreSQL DB with docker: `docker-compose up -d`

3. Start the Spring Boot application: `./gradlew bootRun`

## Examples
### Shorten URL:
**Request:**
```bash
curl -X POST http://localhost:8080/api/shorten \
     -H "Content-Type: application/json" \
     -d '{"url": "https://youtube.com"}'
```
**Response:**
hash: 1234abcd

### Get URL with Hash:
**Request:**
`curl http://localhost:8080/api/1234abcd`
Response:
https://youtube.com