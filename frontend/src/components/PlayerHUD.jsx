import { useState } from 'react';
import './PlayerHUD.css';

const ITEM_LABELS = {
  satchel: '🎒 Satchel',
  compass: '🧭 Compass',
  lucidity_vial: '🧪 Lucidity',
  torn_page: '📄 Torn Page',
  pale_feather: '🪶 Pale Feather',
  pale_journal: '📓 Pale Journal',
  seal_fragment_1: '🔮 Seal I',
  seal_fragment_2: '🔮 Seal II',
  seal_fragment_3: '🔮 Seal III',
  witness_mark: '👁 Witness Mark',
  codex_location_known: '📍 Location Known',
};

const ACHIEVEMENT_LABELS = {
  first_seal: { icon: '🔐', label: 'First Seal', desc: 'Answered the Chamber of Closed Eyes' },
  second_seal: { icon: '🔥', label: 'Second Seal', desc: 'Answered the Burning Library' },
  true_ending: { icon: '⭐', label: 'True Ending', desc: 'Completed the perfect binding' },
  compassionate: { icon: '💙', label: 'Compassionate', desc: 'Witnessed the lost stories' },
  investigator: { icon: '🔍', label: 'Investigator', desc: 'Found the Pale Journal' },
  high_scorer: { icon: '🏆', label: 'High Scorer', desc: 'Reached 200+ points' },
};

export default function PlayerHUD({ player }) {
  const [open, setOpen] = useState(false);
  if (!player) return null;

  const hpPct = Math.max(0, Math.min(100, (player.healthPoints / player.maxHealth) * 100));
  const hpColor = hpPct > 60 ? '#4aaa88' : hpPct > 30 ? '#c8a84b' : '#ff4a6a';

  return (
    <>
      <button className="hud-toggle" onClick={() => setOpen(o => !o)} title="Character Sheet">
        <span className="hud-toggle-icon">📜</span>
        <span className="hud-score">{player.totalScore}</span>
      </button>

      <div className={`hud-panel ${open ? 'open' : ''}`}>
        <div className="hud-header">
          <span className="hud-name">{player.username}</span>
          <span className="hud-chapter">Chapter {player.currentChapter}</span>
          <button className="hud-close" onClick={() => setOpen(false)}>✕</button>
        </div>

        <div className="hud-hp">
          <span className="hud-label">Vitality</span>
          <div className="hud-bar-track">
            <div className="hud-bar-fill" style={{ width: `${hpPct}%`, background: hpColor }} />
          </div>
          <span className="hud-hp-value">{player.healthPoints}/{player.maxHealth}</span>
        </div>

        <div className="hud-stats">
          <div className="hud-stat"><span>Score</span><strong>{player.totalScore}</strong></div>
          <div className="hud-stat"><span>Choices</span><strong>{player.choicesMade}</strong></div>
          <div className="hud-stat"><span>Scenes</span><strong>{player.completedScenes?.length || 0}</strong></div>
          <div className="hud-stat"><span>Deaths</span><strong>{player.deaths}</strong></div>
        </div>

        {player.inventory?.length > 0 && (
          <div className="hud-section">
            <div className="hud-section-title">Inventory</div>
            <div className="hud-items">
              {player.inventory.map(item => (
                <span key={item} className="hud-item">{ITEM_LABELS[item] || item}</span>
              ))}
            </div>
          </div>
        )}

        {player.achievements?.length > 0 && (
          <div className="hud-section">
            <div className="hud-section-title">Achievements</div>
            <div className="hud-achievements">
              {player.achievements.map(a => {
                const info = ACHIEVEMENT_LABELS[a];
                return (
                  <div key={a} className="hud-achievement" title={info?.desc}>
                    <span>{info?.icon || '🎖'}</span>
                    <span>{info?.label || a}</span>
                  </div>
                );
              })}
            </div>
          </div>
        )}
      </div>
    </>
  );
}
