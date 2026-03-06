import axios from 'axios';

const API_BASE = process.env.REACT_APP_API_URL || 'http://localhost:8081';

const api = axios.create({
  baseURL: API_BASE,
  headers: { 'Content-Type': 'application/json' }
});

api.interceptors.request.use(config => {
  const token = localStorage.getItem('token');
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});

api.interceptors.response.use(
  res => res,
  err => {
    if (err.response?.status === 401) {
      localStorage.removeItem('token');
      localStorage.removeItem('player');
      window.location.href = '/login';
    }
    return Promise.reject(err);
  }
);

export const authAPI = {
  register: (data) => api.post('/api/auth/register', data),
  login: (data) => api.post('/api/auth/login', data),
};

export const gameAPI = {
  getScene: () => api.get('/api/game/scene'),
  makeChoice: (choiceId) => api.post('/api/game/choose', { choiceId }),
  resetGame: () => api.post('/api/game/reset'),
  getPlayer: () => api.get('/api/game/player'),
  getLeaderboard: () => api.get('/api/game/leaderboard'),
};

export default api;
