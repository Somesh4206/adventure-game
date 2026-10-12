import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { authAPI } from '../utils/api';
import AmbientBackground from '../components/AmbientBackground';
import './AuthPage.css';

export default function AuthPage() {
  const [username, setUsername] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async () => {
    setError('');
    if (!username.trim()) { setError('Please enter your name'); return; }
    setLoading(true);
    try {
      const res = await authAPI.setName({ name: username.trim() });
      login(res.data.player, res.data.token);
      navigate('/game');
    } catch (err) {
      setError(err.response?.data?.error || 'Failed to enter the Archive');
    }
    setLoading(false);
  };

  const handleKey = (e) => { if (e.key === 'Enter') handleSubmit(); };

  return (
    <div className="auth-screen">
      <AmbientBackground ambiance="void" />

      <div className="auth-container">
        <div className="auth-sigil animate-float">⊕</div>

        <div className="auth-brand">
          <h1>THE ARCHIVE</h1>
          <p className="auth-tagline">A tale of memory, void, and the written word</p>
        </div>

        <div className="auth-card">
          <div className="auth-form">
            <div className="auth-field">
              <label className="auth-label">Archivist Name</label>
              <input
                className="auth-input"
                type="text"
                placeholder="Your name in the Archive..."
                value={username}
                onChange={e => setUsername(e.target.value)}
                onKeyDown={handleKey}
                autoComplete="username"
                autoFocus
              />
            </div>

            {error && <div className="auth-error">{error}</div>}

            <button
              className={`auth-submit ${loading ? 'loading' : ''}`}
              onClick={handleSubmit}
              disabled={loading}
            >
              {loading ? '...' : 'Enter the Archive'}
            </button>
          </div>
        </div>

        <p className="auth-lore">
          *The Archive holds all stories. Those who enter are forever changed.*
        </p>
      </div>
    </div>
  );
}