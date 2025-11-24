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

