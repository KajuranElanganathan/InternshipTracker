import './InternshipCard.css'

const InternshipCard = ({ internship, onEdit, onDelete }) => {
  const formatDate = (dateString) => {
    if (!dateString) return 'N/A'
    const date = new Date(dateString)
    return date.toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    })
  }

  const getStatusColor = (status) => {
    switch (status) {
      case 'APPLIED':
        return 'status-applied'
      case 'REJECTED':
        return 'status-rejected'
      default:
        return 'status-default'
    }
  }

  return (
    <div className="internship-card">
      <div className="card-header">
        <div className="card-title-section">
          <h3 className="card-title">{internship.title || 'Untitled'}</h3>
          <p className="card-company">{internship.company || 'Unknown Company'}</p>
        </div>
        <span className={`status-badge ${getStatusColor(internship.status)}`}>
          {internship.status || 'PENDING'}
        </span>
      </div>

      <div className="card-body">
        <div className="card-meta">
          <span className="meta-item">
            <span className="meta-label">Added:</span>
            <span className="meta-value">{formatDate(internship.createdAt)}</span>
          </span>
        </div>
      </div>

      <div className="card-actions">
        <button onClick={onEdit} className="btn-edit">
          Edit
        </button>
        <button onClick={onDelete} className="btn-delete">
          Delete
        </button>
      </div>
    </div>
  )
}

export default InternshipCard

