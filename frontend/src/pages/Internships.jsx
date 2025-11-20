import { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import { useAuth } from '../context/AuthContext'
import { internshipAPI, aiAPI } from '../services/api'
import InternshipCard from '../components/InternshipCard'
import InternshipModal from '../components/InternshipModal'
import AIExtractModal from '../components/AIExtractModal'
import './Internships.css'

const Internships = () => {
  const { isAuthenticated } = useAuth()
  const navigate = useNavigate()
  const [internships, setInternships] = useState([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState(null)
  const [isModalOpen, setIsModalOpen] = useState(false)
  const [isAIModalOpen, setIsAIModalOpen] = useState(false)
  const [editingInternship, setEditingInternship] = useState(null)

  useEffect(() => {
    if (!isAuthenticated) {
      navigate('/login')
      return
    }
    fetchInternships()
  }, [isAuthenticated, navigate])

  const fetchInternships = async () => {
    try {
      setLoading(true)
      const response = await internshipAPI.getAll()
      setInternships(response.data)
      setError(null)
    } catch (err) {
      setError('Failed to load internships. Please try again.')
      console.error('Error fetching internships:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleCreate = async (data) => {
    try {
      await internshipAPI.create(data)
      await fetchInternships()
      setIsModalOpen(false)
      setEditingInternship(null)
    } catch (err) {
      console.error('Error creating internship:', err)
      throw err
    }
  }

  const handleUpdate = async (id, data) => {
    try {
      await internshipAPI.update(id, data)
      await fetchInternships()
      setIsModalOpen(false)
      setEditingInternship(null)
    } catch (err) {
      console.error('Error updating internship:', err)
      throw err
    }
  }

  const handleDelete = async (id) => {
    if (!window.confirm('Are you sure you want to delete this internship?')) {
      return
    }
    try {
      await internshipAPI.delete(id)
      await fetchInternships()
    } catch (err) {
      console.error('Error deleting internship:', err)
      alert('Failed to delete internship. Please try again.')
    }
  }

  const handleAIExtract = async (url) => {
    try {
      await aiAPI.extractFromUrl(url)
      await fetchInternships()
      setIsAIModalOpen(false)
    } catch (err) {
      console.error('Error extracting internship:', err)
      throw err
    }
  }

  const openCreateModal = () => {
    setEditingInternship(null)
    setIsModalOpen(true)
  }

  const openEditModal = (internship) => {
    setEditingInternship(internship)
    setIsModalOpen(true)
  }

  const stats = {
    total: internships.length,
    applied: internships.filter(i => i.status === 'APPLIED').length,
    rejected: internships.filter(i => i.status === 'REJECTED').length,
  }

  if (loading) {
    return (
      <div className="internships-page">
        <div className="loading-container">
          <div className="spinner"></div>
          <p>Loading your internships...</p>
        </div>
      </div>
    )
  }

  return (
    <div className="internships-page">
      <div className="internships-header">
        <div className="header-content">
          <h1>My Internships</h1>
          <p>Track and manage all your internship applications</p>
        </div>
        <div className="header-actions">
          <button onClick={() => setIsAIModalOpen(true)} className="btn-ai">
            <span>✨</span>
            AI Extract
          </button>
          <button onClick={openCreateModal} className="btn-primary">
            + Add Internship
          </button>
        </div>
      </div>

      {error && (
        <div className="error-banner">
          <p>{error}</p>
          <button onClick={fetchInternships}>Retry</button>
        </div>
      )}

      <div className="stats-section">
        <div className="stat-card">
          <div className="stat-value">{stats.total}</div>
          <div className="stat-label">Total</div>
        </div>
        <div className="stat-card stat-applied">
          <div className="stat-value">{stats.applied}</div>
          <div className="stat-label">Applied</div>
        </div>
        <div className="stat-card stat-rejected">
          <div className="stat-value">{stats.rejected}</div>
          <div className="stat-label">Rejected</div>
        </div>
      </div>

      <div className="internships-grid">
        {internships.length === 0 ? (
          <div className="empty-state">
            <div className="empty-icon">📋</div>
            <h2>No internships yet</h2>
            <p>Get started by adding your first internship application</p>
            <button onClick={openCreateModal} className="btn-primary">
              Add Your First Internship
            </button>
          </div>
        ) : (
          internships.map((internship) => (
            <InternshipCard
              key={internship.id}
              internship={internship}
              onEdit={() => openEditModal(internship)}
              onDelete={() => handleDelete(internship.id)}
            />
          ))
        )}
      </div>

      {isModalOpen && (
        <InternshipModal
          internship={editingInternship}
          onClose={() => {
            setIsModalOpen(false)
            setEditingInternship(null)
          }}
          onSave={editingInternship 
            ? (data) => handleUpdate(editingInternship.id, data)
            : handleCreate
          }
        />
      )}

      {isAIModalOpen && (
        <AIExtractModal
          onClose={() => setIsAIModalOpen(false)}
          onExtract={handleAIExtract}
        />
      )}
    </div>
  )
}

export default Internships

