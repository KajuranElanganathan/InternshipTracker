# Quick Start Guide

## Prerequisites

1. **Java 17+** installed
2. **Node.js 16+** and npm installed
3. **PostgreSQL** running and configured
4. **GitHub OAuth App** created (for authentication)

## Setup Steps

### 1. Backend Setup

1. Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5333/userinfo
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

2. Update GitHub OAuth credentials:
```properties
spring.security.oauth2.client.registration.github.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.github.client-secret=YOUR_CLIENT_SECRET
```

3. Start the backend:
```bash
./mvnw spring-boot:run
```

Backend will run on `http://localhost:8080`

### 2. Frontend Setup

1. Navigate to frontend directory:
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

Frontend will run on `http://localhost:5173`

### 3. Access the Application

1. Open your browser and go to `http://localhost:5173`
2. Click "Get Started" or "Sign In"
3. Authenticate with GitHub
4. Start tracking your internships!

## Features to Try

1. **Add Internship Manually**: Click "Add Internship" and fill in the details
2. **AI Extraction**: Click "AI Extract" and paste a job posting URL
3. **View Statistics**: See your total, applied, and rejected counts
4. **Edit/Delete**: Manage your internships with the action buttons

## Troubleshooting

### CORS Errors
- Ensure backend CORS is configured for `http://localhost:5173`
- Check that both servers are running

### Authentication Issues
- Verify GitHub OAuth credentials are correct
- Check that the OAuth callback URL matches your GitHub app settings

### Database Connection
- Ensure PostgreSQL is running
- Verify database credentials in `application.properties`
- Check that the database exists

## Next Steps

- Customize the UI theme in `frontend/src/index.css`
- Add more status types in `InternshipStatus.java`
- Extend the AI extraction capabilities
- Add more features as needed!

