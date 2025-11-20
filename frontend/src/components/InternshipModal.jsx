import { useState, useEffect } from 'react'
import './Modal.css'

const InternshipModal = ({ internship, onClose, onSave }) => {
  const [formData, setFormData] = useState({
    company: '',
    title: '',
    status: 'APPLIED'
  })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  useEffect(() => {
    if (internship) {
      setFormData({
        company: internship.company || '',
        title: internship.title || '',
        status: internship.status || 'APPLIED'
      })
    }
  }, [internship])

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)
    setLoading(true)

    try {
      await onSave(formData)
    } catch (err) {
      setError('Failed to save internship. Please try again.')
      console.error('Error saving internship:', err)
    } finally {
      setLoading(false)
    }
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setFormData(prev => ({
      ...prev,
      [name]: value
    }))
  }

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <h2>{internship ? 'Edit Internship' : 'Add New Internship'}</h2>
          <button className="modal-close" onClick={onClose}>×</button>
        </div>

        <form onSubmit={handleSubmit} className="modal-form">
          {error && (
            <div className="form-error">
              {error}
            </div>
          )}

          <div className="form-group">
            <label htmlFor="company">Company *</label>
            <input
              type="text"
              id="company"
              name="company"
              value={formData.company}
              onChange={handleChange}
              required
              placeholder="e.g., Google, Microsoft"
            />
          </div>

          <div className="form-group">
            <label htmlFor="title">Position Title *</label>
            <input
              type="text"
              id="title"
              name="title"
              value={formData.title}
              onChange={handleChange}
              required
              placeholder="e.g., Software Engineering Intern"
            />
          </div>

          <div className="form-group">
            <label htmlFor="status">Status *</label>
            <select
              id="status"
              name="status"
              value={formData.status}
              onChange={handleChange}
              required
            >
              <option value="APPLIED">Applied</option>
              <option value="REJECTED">Rejected</option>
            </select>
          </div>

          <div className="modal-actions">
            <button type="button" onClick={onClose} className="btn-cancel">
              Cancel
            </button>
            <button type="submit" className="btn-submit" disabled={loading}>
              {loading ? 'Saving...' : (internship ? 'Update' : 'Create')}
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default InternshipModal

