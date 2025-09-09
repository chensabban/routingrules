# Routing Rules Service

A backend service for managing routing rules and calculating member assignments based on contact information.

## Features

- Create and update routing rules configuration via REST API
- Calculate routing assignments based on contact information and rule ID
- MongoDB persistence with Docker Compose
- Swagger UI for API documentation
- Comprehensive validation using Bean Validation
- Lombok for reduced boilerplate code

## Quick Start

1. Start MongoDB with Docker Compose:
```bash
docker-compose up -d
```

2. Run the application:
```bash
./mvnw spring-boot:run
```

3. Access Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## API Endpoints

- `POST /api/routing-rules` - Create routing rules
- `PUT /api/routing-rules/{id}` - Update routing rules
- `GET /api/routing-rules/{id}` - Get routing rules by ID
- `GET /api/routing-rules` - Get all routing rules
- `DELETE /api/routing-rules/{id}` - Delete routing rules
- `POST /api/routing-rules/calculate` - Calculate member assignment

## Example Usage

### Create Routing Rules
```json
{
  "name": "Sales Team Rules",
  "rules": [
    {
      "conditions": [
        {
          "field": "contactCountry",
          "operator": "EQUALS",
          "value": "US"
        },
        {
          "field": "companyIndustry",
          "operator": "EQUALS",
          "value": "BANKING"
        }
      ],
      "memberId": "eldad"
    }
  ],
  "defaultMemberId": "stav"
}
```

### Calculate Routing
```json
{
  "ruleId": "rule-id-here",
  "contact": {
    "contactCountry": "US",
    "companySize": 100,
    "companyIndustry": "BANKING"
  }
}
```