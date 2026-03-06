import { useState, useEffect, useRef } from 'react';

export function useTypewriter(text, speed = 22, onComplete) {
  const [displayed, setDisplayed] = useState('');
  const [isDone, setIsDone] = useState(false);
  const indexRef = useRef(0);
  const timerRef = useRef(null);

  useEffect(() => {
    setDisplayed('');
    setIsDone(false);
    indexRef.current = 0;

    const tick = () => {
      if (indexRef.current < text.length) {
        setDisplayed(text.slice(0, indexRef.current + 1));
        indexRef.current++;
        timerRef.current = setTimeout(tick, speed);
      } else {
        setIsDone(true);
        onComplete?.();
      }
    };

    timerRef.current = setTimeout(tick, 100);
    return () => clearTimeout(timerRef.current);
  }, [text]);

  const skip = () => {
    clearTimeout(timerRef.current);
    setDisplayed(text);
    setIsDone(true);
    indexRef.current = text.length;
    onComplete?.();
  };

  return { displayed, isDone, skip };
}
