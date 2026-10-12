# 🏛️ THE ARCHIVE — Text Adventure Game

A full-stack text adventure game with a Java Spring Boot backend and React frontend. **No database required** - runs entirely in-memory.

---

Check the Frontend at : 
https://adventure-game-frontend-rujv.onrender.com

## 🎮 About the Game

**The Archive** is a narrative mystery spanning 3 chapters. You play as an Archivist who awakens in a void, confronted by your own reflection gone wrong — the Pale Archivist — who has stolen the Codex of Unmaking and threatens to rewrite existence.

- **3 full chapters** with branching narrative paths
- **30+ unique scenes** with divergent storylines
- **Item system** — find items that unlock hidden choices
- **Achievement system** — 6 unlockable achievements
- **Score tracking** (stored in session, not persisted)
- **Immersive UI** — animated particle backgrounds, typewriter narration, cinematic design

---

## 🏗️ Project Structure

```
adventure-game/
├── backend/          # Spring Boot Java API
│   ├── src/
│   │   └── main/java/com/adventure/
│   │       ├── controller/   # REST controllers
│   │       ├── model/        # Game models (no JPA)
│   │       ├── service/      # Business logic + game data
│   │       └── config/       # CORS config
│   └── pom.xml
│
└── frontend/         # React application
    ├── public/
    └── src/
        ├── components/   # GameScreen, PlayerHUD, AmbientBackground
        ├── context/      # AuthContext
        ├── hooks/        # useTypewriter
        ├── pages/        # AuthPage
        ├── styles/       # Global CSS
        └── utils/        # API client
```

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Node.js 18+
- Maven 3.8+

---

### Backend Setup

```bash
cd backend

# Run with in-memory storage (no database needed)
mvn spring-boot:run
```

Backend runs on **http://localhost:10000** (or PORT env var)

---

### Frontend Setup

```bash
cd frontend

# Install dependencies
npm install

# Start development server
npm start
```

Frontend runs on **http://localhost:3000**

---

## 🔌 API Endpoints

### Auth (Public)
| Method | Path | Body | Description |
|--------|------|------|-------------|
| POST | `/auth/name` | `{name}` | Enter with just a name |

### Game (Requires session - token in localStorage)
| Method | Path | Body | Description |
|--------|------|------|-------------|
| GET | `/scene?playerId=` | — | Get current scene |
| POST | `/choose` | `{playerId, choiceId}` | Make a choice |
| POST | `/reset?playerId=` | — | Restart game |
| GET | `/player?playerId=` | — | Get player info |

**Note:** No database persistence. Player data lives in server memory for the session only. Leaderboard removed.

---

## 🌍 Deployment

### Render (Free Tier - No Database Needed)
1. Push to GitHub
2. Create a **Web Service** on Render pointing to `backend/`
3. Set build command: `mvn clean package -DskipTests`
4. Set start command: `java -jar target/adventure-game-1.0.0.jar`
5. No database required!

### Frontend build for production
```bash
cd frontend
REACT_APP_API_URL=https://your-backend-url.com npm run build
```
Serve the `build/` folder from Spring Boot or a CDN.

---

## 🎯 Game Achievements

| Achievement | How to Unlock |
|------------|---------------|
| 🔐 First Seal | Answer the Chamber of Closed Eyes |
| 🔥 Second Seal | Answer the Burning Library |
| ⭐ True Ending | Reach the perfect ending |
| 💙 Compassionate | Witness the lost stories |
| 🔍 Investigator | Find the Pale Journal |
| 🏆 High Scorer | Reach 200+ points |

---

## 🛠️ Tech Stack

- **Backend**: Java 17, Spring Boot 3.2, Spring Web, Spring Validation
- **Storage**: In-memory (ConcurrentHashMap) - no database
- **Frontend**: React 18, React Router v6, Axios, CSS Animations
- **Fonts**: Cinzel (display), Crimson Pro (body), JetBrains Mono (UI)