import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { authAPI } from '../utils/api';
import AmbientBackground from '../components/AmbientBackground';
import './AuthPage.css';

export default function AuthPage() {
  const [mode, setMode] = useState('login');
  const [form, setForm] = useState({ username: '', email: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async () => {
    setError('');
    if (!form.username || !form.password) { setError('Please fill all fields'); return; }
    if (mode === 'register' && !form.email) { setError('Email is required'); return; }
    setLoading(true);
    try {
      const res = mode === 'login'
        ? await authAPI.login({ username: form.username, password: form.password })
        : await authAPI.register(form);
      login(res.data.player, res.data.token);
      navigate('/game');
    } catch (err) {
      setError(err.response?.data?.error || 'Authentication failed');
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
          <div className="auth-tabs">
            <button className={`auth-tab ${mode === 'login' ? 'active' : ''}`} onClick={() => { setMode('login'); setError(''); }}>
              Enter
            </button>
            <button className={`auth-tab ${mode === 'register' ? 'active' : ''}`} onClick={() => { setMode('register'); setError(''); }}>
              Become Archivist
            </button>
          </div>

          <div className="auth-form">
            <div className="auth-field">
              <label className="auth-label">Archivist Name</label>
              <input
                className="auth-input"
                type="text"
                placeholder="Your name in the Archive..."
                value={form.username}
                onChange={e => setForm(f => ({ ...f, username: e.target.value }))}
                onKeyDown={handleKey}
                autoComplete="username"
              />
            </div>

            {mode === 'register' && (
              <div className="auth-field">
                <label className="auth-label">Correspondence</label>
                <input
                  className="auth-input"
                  type="email"
                  placeholder="Your message address..."
                  value={form.email}
                  onChange={e => setForm(f => ({ ...f, email: e.target.value }))}
                  onKeyDown={handleKey}
                  autoComplete="email"
                />
              </div>
            )}

            <div className="auth-field">
              <label className="auth-label">Cipher</label>
              <input
                className="auth-input"
                type="password"
                placeholder="Your secret word..."
                value={form.password}
                onChange={e => setForm(f => ({ ...f, password: e.target.value }))}
                onKeyDown={handleKey}
                autoComplete={mode === 'login' ? 'current-password' : 'new-password'}
              />
            </div>

            {error && <div className="auth-error">{error}</div>}

            <button
              className={`auth-submit ${loading ? 'loading' : ''}`}
              onClick={handleSubmit}
              disabled={loading}
            >
              {loading ? '...' : mode === 'login' ? 'Open the Archive' : 'Claim Your Title'}
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
