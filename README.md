# 🏛️ THE ARCHIVE — Text Adventure Game

A full-stack text adventure game with a Java Spring Boot backend, React frontend, and database persistence.

---
Check the Frontend at : 
https://adventure-game-frontend-rujv.onrender.com

## 🎮 About the Game

**The Archive** is a narrative mystery spanning 3 chapters. You play as an Archivist who awakens in a void, confronted by your own reflection gone wrong — the Pale Archivist — who has stolen the Codex of Unmaking and threatens to rewrite existence.

- **3 full chapters** with branching narrative paths
- **30+ unique scenes** with divergent storylines
- **Item system** — find items that unlock hidden choices
- **Achievement system** — 6 unlockable achievements
- **Score tracking** and leaderboard
- **Immersive UI** — animated particle backgrounds, typewriter narration, cinematic design

---

## 🏗️ Project Structure

```
adventure-game/
├── backend/          # Spring Boot Java API
│   ├── src/
│   │   └── main/java/com/adventure/
│   │       ├── controller/   # REST controllers
│   │       ├── model/        # JPA entities + game models
│   │       ├── repository/   # Spring Data repositories
│   │       ├── service/      # Business logic + game data
│   │       ├── security/     # JWT auth filter + utility
│   │       └── config/       # Spring Security + CORS config
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

# Run with H2 in-memory database (no setup needed)
mvn spring-boot:run
```

Backend runs on **http://localhost:8080**

> H2 Console available at: http://localhost:8080/h2-console  
> JDBC URL: `jdbc:h2:mem:adventuredb`

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
| POST | `/api/auth/register` | `{username, email, password}` | Register new player |
| POST | `/api/auth/login` | `{username, password}` | Login + get JWT |

### Game (Authenticated — Bearer token required)
| Method | Path | Body | Description |
|--------|------|------|-------------|
| GET | `/api/game/scene` | — | Get current scene |
| POST | `/api/game/choose` | `{choiceId}` | Make a choice |
| POST | `/api/game/reset` | — | Restart game |
| GET | `/api/game/player` | — | Get player info |
| GET | `/api/game/leaderboard` | — | Top 10 scores |

---

## 🐘 Switching to PostgreSQL (Production)

In `backend/src/main/resources/application.properties`:

```properties
# Comment out H2 lines, uncomment PostgreSQL lines
spring.datasource.url=jdbc:postgresql://localhost:5432/adventuredb
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.h2.console.enabled=false
```

Then create the database:
```sql
CREATE DATABASE adventuredb;
```

---

## 🌍 Deployment

### Option 1: Render (Free Tier)
1. Push to GitHub
2. Create a **Web Service** on Render pointing to `backend/`
3. Set build command: `mvn clean package -DskipTests`
4. Set start command: `java -jar target/adventure-game-1.0.0.jar`
5. Add a **PostgreSQL** database on Render and set env vars

### Option 2: Railway
1. Deploy backend as a Java service
2. Add PostgreSQL plugin
3. Deploy frontend as a static site (after `npm run build`)

### Frontend build for production
```bash
cd frontend
REACT_APP_API_URL=https://your-backend-url.com npm run build
```
Serve the `build/` folder from Spring Boot or a CDN.

---

## 🔐 Security Notes

- Change `app.jwt.secret` in production to a secure random 256-bit string
- Set proper CORS origins in `app.cors.allowed-origins`
- Use environment variables for all secrets

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

- **Backend**: Java 17, Spring Boot 3.2, Spring Security, JWT, Spring Data JPA
- **Database**: H2 (dev) / PostgreSQL (production)
- **Frontend**: React 18, React Router v6, Axios, CSS Animations
- **Fonts**: Cinzel (display), Crimson Pro (body), JetBrains Mono (UI)
