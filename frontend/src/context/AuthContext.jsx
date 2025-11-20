import { createContext, useContext, useState, useEffect } from 'react'
import axios from 'axios'

const AuthContext = createContext()

export const useAuth = () => {
  const context = useContext(AuthContext)
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider')
  }
  return context
}

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    checkAuth()
  }, [])

  const checkAuth = async () => {
    try {
      // Check if user is authenticated via OAuth2
      const response = await axios.get('/api/user', { withCredentials: true })
      setUser(response.data)
    } catch (error) {
      setUser(null)
    } finally {
      setLoading(false)
    }
  }

  const login = () => {
    // Redirect to OAuth2 login
    window.location.href = '/oauth2/authorization/github'
  }

  const logout = async () => {
    try {
      await axios.post('/api/logout', {}, { withCredentials: true })
    } catch (error) {
      console.error('Logout error:', error)
    }
    setUser(null)
    window.location.href = '/home'
  }

  const value = {
    user,
    loading,
    login,
    logout,
    checkAuth,
    isAuthenticated: !!user
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

