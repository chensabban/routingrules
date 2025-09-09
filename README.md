# Routing Rules Service

API for managing routing rules and calculating member assignments based on contact information.

## Quick Start (No Java Required)

**Prerequisites:** Docker and Docker Compose

1. **Start Everything:**
```bash
docker-compose up -d
```

2. **Access Application:**
- **API:** http://localhost:8080/api/routing-rules/1.0/
- **Swagger UI:** http://localhost:8080/api/routing-rules/swagger-ui.html

3. **Stop:**
```bash
docker-compose down
```

## Development Setup

```bash
# Build and push to Docker Hub
chmod +x build-and-push.sh
./build-and-push.sh
```

## API Endpoints

- `POST /api/routing-rules/1.0/rules` - Create routing rules
- `PUT /api/routing-rules/1.0/{id}` - Update routing rules
- `GET /api/routing-rules/1.0/{id}` - Get routing rules by ID
- `GET /api/routing-rules/1.0/` - Get all routing rules
- `POST /api/routing-rules/1.0/calculate` - Calculate member assignment