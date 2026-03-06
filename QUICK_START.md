# 🎮 Adventure Game - Quick Start Guide

## ✅ Current Status

Your application is **FULLY CONFIGURED** and **RUNNING**!

### Services Running
- ✅ Backend API: http://localhost:8081
- ✅ Frontend: http://localhost:3000
- ✅ MySQL Database: adventuredb (localhost:3306)

### Database
- **Type:** MySQL 9.1
- **Database:** adventuredb
- **Username:** root
- **Password:** root123
- **Status:** Connected and tables created

## 🚀 How to Use

### 1. Open the Game
Navigate to: **http://localhost:3000**

### 2. Register a New Account
- Click "Become Archivist" tab
- Enter:
  - Archivist Name (username)
  - Correspondence (email)
  - Cipher (password)
- Click "Claim Your Title"

### 3. Login
- Click "Enter" tab
- Enter your username and password
- Click "Open the Archive"

### 4. Play the Game!
- Read the story
- Make choices
- Collect items
- Unlock achievements
- Track your score

## 📊 View Your Data in MySQL

```bash
# Connect to MySQL
mysql -u root -proot123 -D adventuredb

# View all registered users
SELECT id, username, email, total_score, created_at FROM players;

# View user details
SELECT * FROM players WHERE username = 'your_username';

# Exit
exit;
```

## 🔄 Restart Services

### Stop Services
```bash
# Stop backend (Ctrl+C in backend terminal)
# Stop frontend (Ctrl+C in frontend terminal)
```

### Start Services
```bash
# Terminal 1 - Backend
cd backend
mvn spring-boot:run

# Terminal 2 - Frontend
cd frontend
npm start
```

## 📁 Project Structure

```
adventure-game/
├── backend/                 # Spring Boot API
│   ├── src/main/java/
│   │   └── com/adventure/
│   │       ├── controller/  # REST endpoints
│   │       ├── service/     # Business logic
│   │       ├── model/       # Database entities
│   │       ├── repository/  # Data access
│   │       └── security/    # JWT authentication
│   └── src/main/resources/
│       └── application.properties  # MySQL config
│
├── frontend/                # React application
│   ├── src/
│   │   ├── components/      # UI components
│   │   ├── pages/           # Auth & Game pages
│   │   ├── context/         # Auth context
│   │   └── utils/           # API client
│   └── package.json
│
└── Database: adventuredb (MySQL)
```

## 🎯 Features

### Authentication
- ✅ User registration with email validation
- ✅ Secure login with JWT tokens
- ✅ Password encryption (BCrypt)
- ✅ Session management

### Game Features
- ✅ 3 chapters with branching storylines
- ✅ 30+ unique scenes
- ✅ Item collection system
- ✅ Achievement tracking
- ✅ Score system
- ✅ Leaderboard
- ✅ Save/load game progress

### Data Persistence
- ✅ All user data saved in MySQL
- ✅ Game progress persists between sessions
- ✅ Achievements and inventory stored
- ✅ Leaderboard rankings maintained

## 🛠️ Configuration Files

### Backend Database Config
`backend/src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/adventuredb
spring.datasource.username=root
spring.datasource.password=root123
```

### Frontend API Config
`frontend/src/utils/api.js`
```javascript
const API_BASE = 'http://localhost:8081';
```

## 📝 API Endpoints

### Authentication
- POST `/api/auth/register` - Register new user
- POST `/api/auth/login` - Login user

### Game (Requires JWT Token)
- GET `/api/game/scene` - Get current scene
- POST `/api/game/choose` - Make a choice
- POST `/api/game/reset` - Restart game
- GET `/api/game/player` - Get player info
- GET `/api/game/leaderboard` - Get top scores

## 🎨 Game Story

**THE ARCHIVE** - A tale of memory, void, and the written word

You play as an Archivist who awakens in a void, confronted by the Pale Archivist who has stolen the Codex of Unmaking. Navigate through 3 chapters of mystery and make choices that determine the fate of existence itself.

## 🏆 Achievements

- 🔐 First Seal - Answer the Chamber of Closed Eyes
- 🔥 Second Seal - Answer the Burning Library
- ⭐ True Ending - Reach the perfect ending
- 💙 Compassionate - Witness the lost stories
- 🔍 Investigator - Find the Pale Journal
- 🏆 High Scorer - Reach 200+ points

## 💡 Tips

1. **Save Progress:** Your game saves automatically after each choice
2. **Multiple Playthroughs:** Try different choices to unlock all achievements
3. **Collect Items:** Some choices require specific items
4. **Read Carefully:** Clues are hidden in the narrative
5. **Check Leaderboard:** Compete with other players for high scores

## 🐛 Troubleshooting

### Authentication Failed
- Check if backend is running on port 8081
- Verify MySQL is running: `Get-Service MySQL91`
- Check browser console for errors

### Database Connection Error
- Ensure MySQL service is running
- Verify credentials in application.properties
- Check if adventuredb exists: `SHOW DATABASES;`

### Frontend Not Loading
- Check if running on port 3000
- Clear browser cache
- Restart frontend: `npm start`

## 📚 Documentation

- `README.md` - Project overview
- `MYSQL_SETUP_COMPLETE.md` - Database setup details
- `POSTGRESQL_SETUP.md` - PostgreSQL alternative

## 🎉 You're All Set!

Your adventure game is ready to play! Open http://localhost:3000 and start your journey through The Archive!

---

**Need Help?** Check the logs in your terminal windows for any errors.
