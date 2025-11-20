import { BrowserRouter as Router, Routes, Route, Navigate, useSearchParams } from 'react-router-dom'
import { useState, useEffect } from 'react'
import Home from './pages/Home'
import Internships from './pages/Internships'
import Login from './pages/Login'
import Signup from './pages/Signup'
import Navbar from './components/Navbar'
import { AuthProvider, useAuth } from './context/AuthContext'
import './App.css'

function AppRoutes() {
  const [searchParams] = useSearchParams()
  const { checkAuth } = useAuth()

  useEffect(() => {
    // Check if we're returning from OAuth callback
    const loginStatus = searchParams.get('login')
    if (loginStatus === 'success') {
      checkAuth()
    }
  }, [searchParams, checkAuth])

  return (
    <Routes>
      <Route path="/home" element={<Home />} />
      <Route path="/internships" element={<Internships />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<Signup />} />
      <Route path="/" element={<Navigate to="/home" replace />} />
    </Routes>
  )
}

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="app">
          <Navbar />
          <AppRoutes />
        </div>
      </Router>
    </AuthProvider>
  )
}

export default App

