
# sample-java21-rest (Maven)

Java 21 REST sample using Spring Boot 3.4.x, records for models, validation, and a simple controller.

## Prerequisites
- Java 21 (JDK)
- Maven 3.9+

## Run
```bash
mvn spring-boot:run
```
App will start on http://localhost:8080

## Endpoints
- `GET /api/users` — list users
- `GET /api/users/{id}` — get user by id
- `POST /api/users` — create user
- `PUT /api/users/{id}` — update user
- `DELETE /api/users/{id}` — delete user

## cURL examples
```bash
curl -X POST http://localhost:8080/api/users       -H "Content-Type: application/json"       -d '{ "name": "Jhansi Vummiti", "email": "jhansi@example.com", "roles": ["developer","reviewer"] }'

curl http://localhost:8080/api/users

curl http://localhost:8080/api/users/{id}

curl -X PUT http://localhost:8080/api/users/{id}       -H "Content-Type: application/json"       -d '{ "name": "Jhansi Vummiti", "email": null, "roles": ["developer"] }'

curl -X DELETE http://localhost:8080/api/users/{id}
```
# SampleAgenticCheckout
