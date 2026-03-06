import { useState, useEffect, useRef } from 'react';
import { useAuth } from '../context/AuthContext';
import { gameAPI } from '../utils/api';
import { useTypewriter } from '../hooks/useTypewriter';
import AmbientBackground from './AmbientBackground';
import PlayerHUD from './PlayerHUD';
import './GameScreen.css';

function parseNarrative(text) {
  // Bold: **text**
  // Italic with gold: *text*
  const parts = [];
  let remaining = text;
  const regex = /(\*\*([^*]+)\*\*|\*([^*]+)\*)/g;
  let last = 0;
  let match;
  while ((match = regex.exec(text)) !== null) {
    if (match.index > last) parts.push({ type: 'text', content: text.slice(last, match.index) });
    if (match[2]) parts.push({ type: 'bold', content: match[2] });
    else if (match[3]) parts.push({ type: 'italic', content: match[3] });
    last = match.index + match[0].length;
  }
  if (last < text.length) parts.push({ type: 'text', content: text.slice(last) });
  return parts;
}

function NarrativeText({ text }) {
  const parts = parseNarrative(text);
  return (
    <>
      {parts.map((p, i) => {
        if (p.type === 'bold') return <strong key={i}>{p.content}</strong>;
        if (p.type === 'italic') return <em key={i}>{p.content}</em>;
        return <span key={i}>{p.content}</span>;
      })}
    </>
  );
}

function TypedNarrative({ text, onDone }) {
  const { displayed, isDone, skip } = useTypewriter(text, 18, onDone);
  return (
    <div className="narrative-text" onClick={!isDone ? skip : undefined} style={{ cursor: isDone ? 'default' : 'pointer' }}>
      <NarrativeText text={displayed} />
      {!isDone && <span className="cursor-blink">▊</span>}
      {!isDone && <div className="skip-hint">Click to skip</div>}
    </div>
  );
}

const CHAPTER_SUBTITLES = {
  1: 'Chapter I — The Awakening',
  2: 'Chapter II — The Burning Library',
  3: 'Chapter III — The Heart',
};

export default function GameScreen() {
  const { player, updatePlayer, logout } = useAuth();
  const [scene, setScene] = useState(null);
  const [loading, setLoading] = useState(true);
  const [choosing, setChoosing] = useState(false);
  const [narrativeDone, setNarrativeDone] = useState(false);
  const [scoreFlash, setScoreFlash] = useState(null);
  const [notification, setNotification] = useState(null);
  const [localPlayer, setLocalPlayer] = useState(player);
  const [showReset, setShowReset] = useState(false);
  const [showLeaderboard, setShowLeaderboard] = useState(false);
  const [leaderboard, setLeaderboard] = useState([]);
  const narrativeRef = useRef(null);

  useEffect(() => {
    loadScene();
  }, []);

  const loadScene = async () => {
    setLoading(true);
    setNarrativeDone(false);
    try {
      const [sceneRes, playerRes] = await Promise.all([
        gameAPI.getScene(),
        gameAPI.getPlayer(),
      ]);
      setScene(sceneRes.data);
      setLocalPlayer(playerRes.data);
      updatePlayer(playerRes.data);
    } catch (err) {
      console.error(err);
    }
    setLoading(false);
  };

  const makeChoice = async (choiceId) => {
    if (choosing || !narrativeDone) return;
    setChoosing(true);
    setNarrativeDone(false);
    try {
      const res = await gameAPI.makeChoice(choiceId);
      const { scene: newScene, player: updatedPlayer, scoreEarned } = res.data;

      setScene(null);
      setTimeout(() => {
        setScene(newScene);
        setLocalPlayer(updatedPlayer);
        updatePlayer(updatedPlayer);

        if (scoreEarned > 0) {
          setScoreFlash(`+${scoreEarned}`);
          setTimeout(() => setScoreFlash(null), 2000);
        }

        // Check for new achievements
        const prevAch = player?.achievements || [];
        const newAch = updatedPlayer.achievements || [];
        const gained = newAch.filter(a => !prevAch.includes(a));
        if (gained.length) {
          setNotification(`Achievement unlocked: ${gained[0]}`);
          setTimeout(() => setNotification(null), 3500);
        }
      }, 300);
    } catch (err) {
      console.error(err);
      setNotification(err.response?.data?.error || 'Something went wrong');
      setTimeout(() => setNotification(null), 3000);
    }
    setChoosing(false);
  };

  const resetGame = async () => {
    setShowReset(false);
    setLoading(true);
    try {
      const res = await gameAPI.resetGame();
      setLocalPlayer(res.data);
      updatePlayer(res.data);
      await loadScene();
    } catch (err) { console.error(err); }
    setLoading(false);
  };

  const openLeaderboard = async () => {
    try {
      const res = await gameAPI.getLeaderboard();
      setLeaderboard(res.data);
      setShowLeaderboard(true);
    } catch (err) { console.error(err); }
  };

  if (loading) {
    return (
      <div className="loading-screen">
        <AmbientBackground ambiance="void" />
        <div className="loading-inner">
          <div className="loading-sigil">⊕</div>
          <p className="loading-text">Consulting the Archive...</p>
        </div>
      </div>
    );
  }

  const isEnding = scene?.endScene;
  const ambiance = scene?.ambiance || 'void';

  return (
    <div className="game-screen">
      <AmbientBackground ambiance={ambiance} />
      <PlayerHUD player={localPlayer} />

      {/* Top bar */}
      <div className="game-topbar">
        <div className="game-chapter-label">
          {CHAPTER_SUBTITLES[scene?.chapter] || 'The Archive'}
        </div>
        <div className="game-topbar-actions">
          <button className="topbar-btn" onClick={openLeaderboard}>⟡ Scores</button>
          <button className="topbar-btn" onClick={() => setShowReset(true)}>↺ Restart</button>
          <button className="topbar-btn danger" onClick={logout}>Exit</button>
        </div>
      </div>

      {/* Score flash */}
      {scoreFlash && <div className="score-flash">{scoreFlash}</div>}

      {/* Notification */}
      {notification && (
        <div className="notification">{notification}</div>
      )}

      {/* Main content */}
      <main className="game-main" ref={narrativeRef}>
        <div className="game-content">
          {scene && (
            <>
              <div className="scene-header" key={`header-${scene.id}`}>
                <div className="scene-chapter-marker">
                  {Array.from({ length: Math.min(scene.chapter, 3) }, (_, i) => (
                    <span key={i} className={`chapter-dot ${i < scene.chapter ? 'active' : ''}`} />
                  ))}
                </div>
                <h2 className="scene-title animate-fade-in">{scene.title}</h2>
                <div className="scene-divider" />
              </div>

              <div className="scene-narrative" key={`narrative-${scene.id}`}>
                <TypedNarrative
                  text={scene.narrative}
                  onDone={() => setNarrativeDone(true)}
                />
              </div>

              {narrativeDone && !isEnding && scene.choices?.length > 0 && (
                <div className="choices-section" key={`choices-${scene.id}`}>
                  <div className="choices-prompt">What do you do?</div>
                  <div className="choices-list">
                    {scene.choices.map((choice, i) => (
                      <button
                        key={choice.id}
                        className={`choice-btn ${choosing ? 'disabled' : ''}`}
                        onClick={() => makeChoice(choice.id)}
                        disabled={choosing}
                        style={{ animationDelay: `${i * 0.1}s` }}
                      >
                        <span className="choice-glyph">
                          {String.fromCharCode(9312 + i)}
                        </span>
                        <span className="choice-text">{choice.text}</span>
                        {choice.requiresItem && (
                          <span className="choice-requires">Requires item</span>
                        )}
                      </button>
                    ))}
                  </div>
                </div>
              )}

              {narrativeDone && isEnding && (
                <div className="ending-section" key="ending">
                  <div className="ending-ornament">
                    {scene.endType === 'victory' ? '✦ ✦ ✦' : '✦'}
                  </div>
                  <div className={`ending-label ${scene.endType}`}>
                    {scene.endType === 'victory' ? 'The Archive Endures' : 'End of the Road'}
                  </div>
                  <div className="ending-stats">
                    <div className="ending-stat">
                      <span>Final Score</span>
                      <strong>{localPlayer?.totalScore}</strong>
                    </div>
                    <div className="ending-stat">
                      <span>Scenes Visited</span>
                      <strong>{localPlayer?.completedScenes?.length}</strong>
                    </div>
                    <div className="ending-stat">
                      <span>Achievements</span>
                      <strong>{localPlayer?.achievements?.length}</strong>
                    </div>
                  </div>
                  <button className="restart-btn" onClick={() => setShowReset(true)}>
                    Begin Again
                  </button>
                </div>
              )}
            </>
          )}
        </div>
      </main>

      {/* Reset confirm modal */}
      {showReset && (
        <div className="modal-overlay" onClick={() => setShowReset(false)}>
          <div className="modal" onClick={e => e.stopPropagation()}>
            <div className="modal-title">Restart the Archive?</div>
            <p className="modal-text">Your progress will be lost. Your death will be recorded.</p>
            <div className="modal-actions">
              <button className="modal-btn confirm" onClick={resetGame}>Yes, restart</button>
              <button className="modal-btn cancel" onClick={() => setShowReset(false)}>Cancel</button>
            </div>
          </div>
        </div>
      )}

      {/* Leaderboard modal */}
      {showLeaderboard && (
        <div className="modal-overlay" onClick={() => setShowLeaderboard(false)}>
          <div className="modal leaderboard-modal" onClick={e => e.stopPropagation()}>
            <div className="modal-title">The Archive's Champions</div>
            {leaderboard.length === 0 ? (
              <p className="modal-text">No scores yet. Be the first.</p>
            ) : (
              <table className="lb-table">
                <thead>
                  <tr><th>#</th><th>Archivist</th><th>Score</th><th>Chapter</th></tr>
                </thead>
                <tbody>
                  {leaderboard.map((entry, i) => (
                    <tr key={i} className={entry.username === player?.username ? 'lb-me' : ''}>
                      <td>{i + 1}</td>
                      <td>{entry.username}</td>
                      <td>{entry.score}</td>
                      <td>{entry.chapter}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            )}
            <button className="modal-btn cancel" onClick={() => setShowLeaderboard(false)}>Close</button>
          </div>
        </div>
      )}
    </div>
  );
}
