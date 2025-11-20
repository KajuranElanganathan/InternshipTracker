# Internship Tracker - Frontend

A modern, professional React frontend for the Internship Tracker application.

## Features

- 🎨 **Modern Dark Theme UI** - Inspired by cutting-edge design trends
- 🔐 **OAuth2 Authentication** - Secure GitHub OAuth integration
- 📊 **Internship Management** - Full CRUD operations for tracking internships
- ✨ **AI-Powered Extraction** - Automatically extract internship details from URLs
- 📱 **Responsive Design** - Works seamlessly on all devices
- ⚡ **Fast & Optimized** - Built with Vite for lightning-fast performance

## Tech Stack

- **React 18** - Modern UI library
- **React Router** - Client-side routing
- **Vite** - Next-generation build tool
- **Axios** - HTTP client for API calls
- **CSS3** - Custom styling with modern CSS features

## Getting Started

### Prerequisites

- Node.js 16+ and npm/yarn
- Backend server running on `http://localhost:8080`

### Installation

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

The frontend will be available at `http://localhost:5173`

### Building for Production

```bash
npm run build
```

The production build will be in the `dist` directory.

## Project Structure

```
frontend/
├── src/
│   ├── components/      # Reusable UI components
│   ├── pages/          # Page components
│   ├── context/        # React context providers
│   ├── services/      # API service layer
│   ├── App.jsx         # Main app component
│   ├── main.jsx        # Entry point
│   └── index.css       # Global styles
├── public/             # Static assets
├── index.html          # HTML template
├── vite.config.js      # Vite configuration
└── package.json        # Dependencies
```

## Routes

- `/home` - Landing page with hero section
- `/internships` - Main dashboard for managing internships
- `/login` - Login page with OAuth2
- `/signup` - Signup page with OAuth2

## API Integration

The frontend communicates with the backend API at `http://localhost:8080/api`. All API calls are configured in `src/services/api.js`.

### Endpoints Used

- `GET /api/user` - Get current authenticated user
- `GET /api/internships` - Get all internships
- `POST /api/internships` - Create new internship
- `PUT /api/internships/{id}` - Update internship
- `DELETE /api/internships/{id}` - Delete internship
- `POST /api/ai/extract` - Extract internship from URL

## Authentication

The app uses OAuth2 with GitHub. When users click "Sign in with GitHub", they are redirected to the backend OAuth2 endpoint which handles the authentication flow.

## Styling

The app uses a custom dark theme with:
- CSS Variables for theming
- Modern animations and transitions
- Responsive design patterns
- Glassmorphism effects

## Development

The development server includes:
- Hot Module Replacement (HMR)
- Proxy configuration for API calls
- Source maps for debugging

## License

Part of the Internship Tracker application.

