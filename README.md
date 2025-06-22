# Travel Guide Backend

A Spring Boot-based backend service for a Travel Guide application that provides APIs for user management, places information, and travel-related functionalities.

## 🚀 Technologies Used

- **Java 17**
- **Spring Boot 3.2.4**
- **Spring Security** with JWT Authentication
- **MongoDB** (NoSQL Database)
- **Firebase Admin SDK** (v9.3.0)
- **Okta OAuth2** for authentication
- **Spring Mail** for email notifications
- **JWT** for token-based authentication
- **Maven** for dependency management
- **Docker** for containerization

## 📋 Prerequisites

- Java 17 or higher
- MongoDB instance
- Firebase Admin SDK credentials
- Okta OAuth2 configuration
- SMTP server credentials (for email functionality)

## 🔧 Configuration

Create an `application.properties` file with the following configurations:

```properties
# MongoDB Configuration
spring.data.mongodb.uri=${mongodbUrl}
spring.data.mongodb.authentication-database=admin
spring.data.mongodb.database=Travelapp

# Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=${mail_id}
spring.mail.password=${mail_password}
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.transport.protocol=smtp
spring.mail.properties.mail.smtp.starttls.enable=true

# OAuth2 Configuration
spring.security.oauth2.client.provider.okta.issuer-uri=your-okta-issuer-uri
spring.security.oauth2.client.registration.okta.client-id=${o_auth_key}
spring.security.oauth2.client.registration.okta.client-secret=${o_auth_sec}

# File Upload
spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=200MB
spring.servlet.multipart.max-request-size=200MB
```

## 🏗️ Project Structure

```
src/main/java/com/saigopa/travel/Travel/
├── Config/               # Configuration classes
├── Controllers/          # REST Controllers
├── Models/               # Data models and DTOs
│   ├── User/            # User-related models
│   └── Feed/            # Feed and places related models
├── Repositories/         # MongoDB repositories
├── Services/             # Business logic
└── Utils/               # Helper classes and utilities
```

## 🔐 Authentication

The application uses JWT (JSON Web Tokens) for authentication. Include the token in the Authorization header for protected endpoints:

```
Authorization: Bearer <your_jwt_token>
```

## 📚 API Endpoints

### User Management

- `POST /signUp` - Register a new user
- `POST /login` - User login
- `GET /verifyEmail` - Verify user's email
- `GET /getUserData` - Get current user's data

### Places Management

- `GET /getPlaces` - Get list of places
- `POST /addPlace` - Add a new place (Admin only)
- `GET /getPlace/{id}` - Get place details by ID
- `POST /createOrder` - Create a new travel order

## 🐳 Docker Support

The application includes a `Dockerfile` and `docker-compose.yml` for containerization.

### Build and Run with Docker

1. Build the Docker image:
   ```bash
   docker build -t travel-guide-backend .
   ```

2. Run with Docker Compose:
   ```bash
   docker-compose up -d
   ```

## 🔄 Environment Variables

Set the following environment variables before running the application:

- `mongodbUrl`: MongoDB connection string
- `mail_id`: Email address for sending emails
- `mail_password`: Email password
- `o_auth_key`: Okta OAuth2 client ID
- `o_auth_sec`: Okta OAuth2 client secret
- `rp_key`: (Optional) Third-party API key
- `rp_pass`: (Optional) Third-party API password

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Spring Boot Team
- MongoDB
- Okta
- Firebase
- All open-source contributors
