import { useEffect, useRef } from 'react';

const AMBIANCE_CONFIGS = {
  void: {
    particles: { color: '#4a9eff', count: 40, size: [1, 2], speed: 0.2 },
    gradient: 'radial-gradient(ellipse at 50% 50%, #050510 0%, #030309 100%)',
    fog: 'rgba(10, 10, 40, 0.4)',
  },
  dark_corridor: {
    particles: { color: '#8888aa', count: 25, size: [1, 1.5], speed: 0.15 },
    gradient: 'radial-gradient(ellipse at 30% 70%, #0a0514 0%, #030309 70%)',
    fog: 'rgba(20, 5, 30, 0.5)',
  },
  ancient_temple: {
    particles: { color: '#c8a84b', count: 30, size: [1, 2], speed: 0.25 },
    gradient: 'radial-gradient(ellipse at 60% 40%, #100a00 0%, #080500 60%, #030309 100%)',
    fog: 'rgba(30, 20, 0, 0.4)',
  },
  burning_library: {
    particles: { color: '#ff6a2a', count: 50, size: [1, 3], speed: 0.4 },
    gradient: 'radial-gradient(ellipse at 50% 80%, #1a0500 0%, #100300 50%, #030309 100%)',
    fog: 'rgba(40, 10, 0, 0.5)',
  },
  heart_archive: {
    particles: { color: '#aa4aff', count: 60, size: [1, 2.5], speed: 0.3 },
    gradient: 'radial-gradient(ellipse at 50% 50%, #0a0020 0%, #050010 50%, #030309 100%)',
    fog: 'rgba(20, 0, 40, 0.4)',
  },
};

export default function AmbientBackground({ ambiance = 'void' }) {
  const canvasRef = useRef(null);
  const config = AMBIANCE_CONFIGS[ambiance] || AMBIANCE_CONFIGS.void;

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext('2d');

    let animId;
    const W = () => (canvas.width = window.innerWidth);
    const H = () => (canvas.height = window.innerHeight);
    W(); H();

    const particles = Array.from({ length: config.particles.count }, () => ({
      x: Math.random() * canvas.width,
      y: Math.random() * canvas.height,
      r: config.particles.size[0] + Math.random() * (config.particles.size[1] - config.particles.size[0]),
      vx: (Math.random() - 0.5) * config.particles.speed,
      vy: (Math.random() - 0.5) * config.particles.speed - config.particles.speed * 0.5,
      alpha: Math.random() * 0.6 + 0.2,
      life: Math.random(),
      lifeDelta: 0.003 + Math.random() * 0.005,
    }));

    const draw = () => {
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      particles.forEach(p => {
        p.life += p.lifeDelta;
        if (p.life > 1) { p.life = 0; p.x = Math.random() * canvas.width; p.y = canvas.height + 10; }

        const fade = p.life < 0.1 ? p.life * 10 : p.life > 0.9 ? (1 - p.life) * 10 : 1;
        p.x += p.vx;
        p.y += p.vy;

        ctx.beginPath();
        ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
        ctx.fillStyle = config.particles.color + Math.round(fade * p.alpha * 255).toString(16).padStart(2, '0');
        ctx.fill();
      });

      animId = requestAnimationFrame(draw);
    };

    draw();

    const resize = () => { W(); H(); };
    window.addEventListener('resize', resize);
    return () => { cancelAnimationFrame(animId); window.removeEventListener('resize', resize); };
  }, [ambiance]);

  return (
    <>
      <div style={{
        position: 'fixed', inset: 0, zIndex: 0,
        background: config.gradient,
        transition: 'background 1.5s ease',
      }} />
      <canvas
        ref={canvasRef}
        style={{ position: 'fixed', inset: 0, zIndex: 1, pointerEvents: 'none', opacity: 0.7 }}
      />
      <div style={{
        position: 'fixed', inset: 0, zIndex: 2, pointerEvents: 'none',
        background: `radial-gradient(ellipse at 50% 0%, transparent 40%, ${config.fog} 100%)`,
        transition: 'background 1.5s ease',
      }} />
    </>
  );
}
