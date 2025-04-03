
# Gemini Chat API

A Spring Boot application that integrates with Google's Gemini AI to provide an optimized chat experience.

## Features

- REST API endpoints for AI interactions
- Prompt optimization for better AI responses
- CORS enabled for cross-origin requests
- Error handling and custom error responses

## Tech Stack

- Java 17
- Spring Boot 3.4.0
- Spring WebFlux for reactive programming
- Maven for dependency management
- Google Gemini AI API

## API Endpoints

### Health Check
```http
GET /
Response: "Gemini Chat API is running!"
```

### Chat Endpoint
```http
POST /api/qna/ask
Content-Type: application/json

Request Body:
{
    "question": "Your question here"
}
```

## Configuration

The application requires the following environment variables:
- `GEMINI_API_KEY`: Your Google Gemini API key

## Running Locally

1. Click the "Run" button in your Replit workspace
2. The application will start on port 8080
3. You can test the API using the endpoints mentioned above

## Deployment

This application is configured for deployment on Replit using Cloud Run. The deployment process is automated through the Replit interface.

To deploy:
1. Navigate to the "Deployment" tab in your Replit workspace
2. Click "Deploy"
3. Your application will be live and accessible via the provided URL

## Development

The project uses Maven for dependency management. Common commands:

```bash
./mvnw clean install    # Install dependencies
./mvnw test            # Run tests
./mvnw spring-boot:run # Run the application
```
