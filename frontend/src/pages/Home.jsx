import { Link } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import './Home.css'

const Home = () => {
  const { isAuthenticated } = useAuth()

  return (
    <div className="home">
      <div className="hero-section">
        <div className="hero-content">
          <div className="hero-text">
            <h1 className="hero-title">
              <span className="title-line">From scattered applications</span>
              <span className="title-line">to organized success.</span>
            </h1>
            <p className="hero-subtitle">
              The AI-powered tracker that transforms your internship journey into actionable insights.
            </p>
            <p className="hero-description">
              INTERNSHIP TRACKER ORGANIZES YOUR APPLICATIONS, TRACKS YOUR PROGRESS, AND GIVES YOU INSTANT INSIGHTS—SO YOU MOVE FASTER TOWARD YOUR GOALS.
            </p>
            <div className="hero-actions">
              {isAuthenticated ? (
                <Link to="/internships" className="btn-hero-primary">
                  View Internships
                </Link>
              ) : (
                <>
                  <Link to="/signup" className="btn-hero-primary">
                    Get Started
                  </Link>
                  <Link to="/login" className="btn-hero-secondary">
                    Sign In
                  </Link>
                </>
              )}
            </div>
          </div>
          <div className="hero-visual">
            <div className="glow-orb orb-1"></div>
            <div className="glow-orb orb-2"></div>
            <div className="hero-image-container">
              <div className="abstract-shape shape-1"></div>
              <div className="abstract-shape shape-2"></div>
              <div className="abstract-shape shape-3"></div>
            </div>
          </div>
        </div>
      </div>

      <div className="features-section">
        <div className="features-container">
          <div className="feature-card">
            <div className="feature-icon">⚡</div>
            <h3>AI-Powered Extraction</h3>
            <p>Automatically extract internship details from job postings with a single URL. No more manual data entry.</p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">📊</div>
            <h3>Track Everything</h3>
            <p>Monitor your application status, deadlines, and follow-ups all in one centralized dashboard.</p>
          </div>
          <div className="feature-card">
            <div className="feature-icon">🎯</div>
            <h3>Stay Organized</h3>
            <p>Never lose track of an application. Keep your internship search organized and efficient.</p>
          </div>
        </div>
      </div>

      <div className="cta-section">
        <div className="cta-content">
          <h2>Ready to streamline your internship search?</h2>
          <p>Join thousands of students tracking their applications with ease.</p>
          {!isAuthenticated && (
            <Link to="/signup" className="btn-cta">
              Get Early Access
            </Link>
          )}
        </div>
      </div>
    </div>
  )
}

export default Home
