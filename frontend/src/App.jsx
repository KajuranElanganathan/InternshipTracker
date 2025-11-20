import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom'
import { useState, useEffect } from 'react'
import Home from './pages/Home'
import Internships from './pages/Internships'
import Login from './pages/Login'
import Signup from './pages/Signup'
import Navbar from './components/Navbar'
import { AuthProvider } from './context/AuthContext'
import './App.css'

function App() {
  return (
    <AuthProvider>
      <Router>
        <div className="app">
          <Navbar />
          <Routes>
            <Route path="/home" element={<Home />} />
            <Route path="/internships" element={<Internships />} />
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<Signup />} />
            <Route path="/" element={<Navigate to="/home" replace />} />
          </Routes>
        </div>
      </Router>
    </AuthProvider>
  )
}

export default App

