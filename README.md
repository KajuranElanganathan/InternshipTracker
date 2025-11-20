# Internship Tracker

A full-stack application for tracking internship applications with AI-powered extraction capabilities.

## Features

- 🎯 **Track Internships** - Manage all your internship applications in one place
- ✨ **AI Extraction** - Automatically extract internship details from job posting URLs
- 🔐 **OAuth2 Authentication** - Secure login with GitHub
- 📊 **Status Tracking** - Monitor application status (Applied, Rejected)
- 🎨 **Modern UI** - Professional, dark-themed frontend
- 🚀 **Full-Stack** - Spring Boot backend with React frontend

## Tech Stack

### Backend
- **Spring Boot 3.5.7** - Java framework
- **Spring Security** - Authentication & authorization
- **Spring Data JPA** - Database access
- **PostgreSQL** - Database
- **OAuth2** - GitHub authentication
- **Google Gemini AI** - AI-powered extraction

### Frontend
- **React 18** - UI library
- **React Router** - Routing
- **Vite** - Build tool
- **Axios** - HTTP client

## Getting Started

### Prerequisites

- Java 17+
- Node.js 16+
- PostgreSQL
- Maven

### Backend Setup

1. Configure PostgreSQL database in `src/main/resources/application.properties`
2. Update OAuth2 credentials (GitHub Client ID and Secret)
3. Run the application:
```bash
./mvnw spring-boot:run
```

The backend will run on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will run on `http://localhost:5173`

## Project Structure

```
.
├── src/                    # Backend source code
│   └── main/
│       ├── java/com/example/
│       └── resources/
├── frontend/               # Frontend React application
│   ├── src/
│   │   ├── components/
│   │   ├── pages/
│   │   ├── context/
│   │   └── services/
│   └── package.json
└── pom.xml                 # Maven configuration
```

## API Endpoints

### Authentication
- `GET /oauth2/authorization/github` - Initiate GitHub OAuth login
- `GET /api/user` - Get current authenticated user

### Internships
- `GET /api/internships` - Get all internships (authenticated)
- `POST /api/internships` - Create new internship (authenticated)
- `PUT /api/internships/{id}` - Update internship (authenticated)
- `DELETE /api/internships/{id}` - Delete internship (authenticated)

### AI Extraction
- `POST /api/ai/extract` - Extract internship from URL (authenticated)

## Features in Detail

### AI-Powered Extraction
Paste a job posting URL and let AI automatically extract:
- Company name
- Position title
- Other relevant details

### Status Management
Track your applications with statuses:
- **APPLIED** - Application submitted
- **REJECTED** - Application rejected

### User Dashboard
- View all your internships
- See statistics (Total, Applied, Rejected)
- Quick actions (Add, Edit, Delete)

## Configuration

### Backend Configuration

Update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5333/userinfo
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.security.oauth2.client.registration.github.client-id=your_client_id
spring.security.oauth2.client.registration.github.client-secret=your_client_secret
```

### Frontend Configuration

The frontend is configured to proxy API requests to `http://localhost:8080`. Update `vite.config.js` if your backend runs on a different port.

## Development

### Running in Development Mode

**Backend:**
```bash
./mvnw spring-boot:run
```

**Frontend:**
```bash
cd frontend
npm run dev
```

### Building for Production

**Backend:**
```bash
./mvnw clean package
java -jar target/Internship-Tracker-0.0.1-SNAPSHOT.jar
```

**Frontend:**
```bash
cd frontend
npm run build
```

## Security

- OAuth2 authentication via GitHub
- CORS configured for frontend
- CSRF disabled for API testing (configure properly for production)
- User-specific data isolation

## License

This project is part of an internship tracking application.

