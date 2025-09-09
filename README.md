# Routing Rules Service

API for managing routing rules and calculating member assignments based on contact information.

## Quick Start

1. **Start MongoDB:**
```bash
docker-compose up -d
```

2. **Run Application:**
```bash
./mvnw spring-boot:run
```

3. **API Documentation:**
[Swagger UI](http://localhost:8080/api/routing-rules/swagger-ui.html)

## Docker

```bash
# Build
./mvnw clean package
docker build -t routing-rules .

# Run
docker run -p 8080:8080 routing-rules
```

## API Endpoints

- `POST /api/routing-rules/1.0/rules` - Create routing rules
- `PUT /api/routing-rules/1.0/{id}` - Update routing rules
- `GET /api/routing-rules/1.0/{id}` - Get routing rules by ID
- `GET /api/routing-rules/1.0/` - Get all routing rules
- `POST /api/routing-rules/1.0/calculate` - Calculate member assignment