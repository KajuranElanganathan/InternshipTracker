import { Link, useLocation } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import './Navbar.css'

const Navbar = () => {
  const { isAuthenticated, user, login, logout } = useAuth()
  const location = useLocation()

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/home" className="navbar-logo">
          <span className="logo-icon">⚡</span>
          <span className="logo-text">InternshipTracker</span>
        </Link>
        
        <div className="navbar-links">
          <Link 
            to="/home" 
            className={location.pathname === '/home' ? 'active' : ''}
          >
            Home
          </Link>
          {isAuthenticated && (
            <Link 
              to="/internships" 
              className={location.pathname === '/internships' ? 'active' : ''}
            >
              Internships
            </Link>
          )}
        </div>

        <div className="navbar-actions">
          {isAuthenticated ? (
            <div className="user-menu">
              <span className="user-email">{user?.email || 'User'}</span>
              <button onClick={logout} className="btn-logout">
                Sign Out
              </button>
            </div>
          ) : (
            <div className="auth-buttons">
              <Link to="/login" className="btn-secondary">
                Sign In
              </Link>
              <Link to="/signup" className="btn-primary">
                Get Started
              </Link>
            </div>
          )}
        </div>
      </div>
    </nav>
  )
}

export default Navbar
