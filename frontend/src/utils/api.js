import axios from 'axios';

const API_BASE = process.env.REACT_APP_API_URL || 'http://localhost:10000';

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
  setName: (data) => api.post('/auth/name', data),
};

export const gameAPI = {
  getScene: (playerId) => api.get('/scene', { params: { playerId } }),
  makeChoice: (playerId, choiceId) => api.post('/choose', { playerId, choiceId }),
  resetGame: (playerId) => api.post('/reset', null, { params: { playerId } }),
  getPlayer: (playerId) => api.get('/player', { params: { playerId } }),
};

export default api;