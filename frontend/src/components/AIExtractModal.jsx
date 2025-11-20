import { useState } from 'react'
import './Modal.css'

const AIExtractModal = ({ onClose, onExtract }) => {
  const [url, setUrl] = useState('')
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(null)

  const handleSubmit = async (e) => {
    e.preventDefault()
    setError(null)

    if (!url.trim()) {
      setError('Please enter a valid URL')
      return
    }

    setLoading(true)

    try {
      await onExtract(url.trim())
      setUrl('')
    } catch (err) {
      setError('Failed to extract internship. Please check the URL and try again.')
      console.error('Error extracting internship:', err)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <div className="modal-header">
          <h2>AI Extract from URL</h2>
          <button className="modal-close" onClick={onClose}>×</button>
        </div>

        <form onSubmit={handleSubmit} className="modal-form">
          <div className="ai-extract-info">
            <p>✨ Paste a job posting URL and let AI extract the internship details automatically.</p>
          </div>

          {error && (
            <div className="form-error">
              {error}
            </div>
          )}

          <div className="form-group">
            <label htmlFor="url">Job Posting URL *</label>
            <input
              type="url"
              id="url"
              name="url"
              value={url}
              onChange={(e) => setUrl(e.target.value)}
              required
              placeholder="https://example.com/job-posting"
              disabled={loading}
            />
          </div>

          <div className="modal-actions">
            <button type="button" onClick={onClose} className="btn-cancel" disabled={loading}>
              Cancel
            </button>
            <button type="submit" className="btn-submit" disabled={loading}>
              {loading ? 'Extracting...' : 'Extract & Add'}
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default AIExtractModal

