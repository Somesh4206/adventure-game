import { createContext, useContext, useState, useCallback } from 'react';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [player, setPlayer] = useState(() => {
    try {
      const saved = localStorage.getItem('player');
      return saved ? JSON.parse(saved) : null;
    } catch { return null; }
  });
  const [token, setToken] = useState(() => localStorage.getItem('token'));

  const login = useCallback((playerData, authToken) => {
    setPlayer(playerData);
    setToken(authToken);
    localStorage.setItem('player', JSON.stringify(playerData));
    localStorage.setItem('token', authToken);
  }, []);

  const logout = useCallback(() => {
    setPlayer(null);
    setToken(null);
    localStorage.removeItem('player');
    localStorage.removeItem('token');
  }, []);

  const updatePlayer = useCallback((data) => {
    setPlayer(data);
    localStorage.setItem('player', JSON.stringify(data));
  }, []);

  return (
    <AuthContext.Provider value={{ player, token, login, logout, updatePlayer, isAuthenticated: !!token }}>
      {children}
    </AuthContext.Provider>
  );
}

export const useAuth = () => {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth must be inside AuthProvider');
  return ctx;
};
