# Database Connection Setup - COMPLETE ✅

## Your PostgreSQL Configuration

- **Installation Path**: `C:\Program Files\PostgreSQL\18\bin`
- **Host**: localhost
- **Port**: 5432
- **Database**: adventuredb ✅ (Created)
- **Username**: postgres
- **Password**: postgres

## Backend Configuration ✅

Your backend is now configured to connect to PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/adventuredb
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Location: `backend/src/main/resources/application.properties`

## Frontend Configuration ✅

Your frontend is configured to connect to the backend:

```javascript
API_BASE = http://localhost:8081
```

Location: `frontend/src/utils/api.js`

## How to Start Your Application

### Option 1: Quick Start (Recommended)
Double-click: `START_HERE.bat`

This will:
1. Open a terminal for the backend
2. Open a terminal for the frontend
3. Start both services automatically

### Option 2: Manual Start

**Terminal 1 - Backend:**
```bash
cd backend
mvn spring-boot:run
```
Wait for: "Started AdventureGameApplication in X seconds"

**Terminal 2 - Frontend:**
```bash
cd frontend
npm start
```
Browser opens automatically at http://localhost:3000

## Testing the Connection

1. **Backend Health Check**:
   - Open: http://localhost:8081/api/game/leaderboard
   - Should see: `[]` or list of players

2. **Frontend**:
   - Open: http://localhost:3000
   - Register a new account
   - Login and start playing

3. **Database Verification**:
   ```bash
   "C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -d adventuredb -c "SELECT * FROM player;"
   ```
   Password: postgres

## Troubleshooting

### Backend won't start
- Check PostgreSQL is running:
  - Open Services (Win + R → services.msc)
  - Look for "postgresql-x64-18"
  - Status should be "Running"

### Connection refused
- Verify PostgreSQL port:
  ```bash
  netstat -ano | findstr :5432
  ```
  Should show LISTENING

### Authentication failed
- Password is: `postgres`
- Username is: `postgres`
- Database is: `adventuredb`

### Frontend can't reach backend
- Ensure backend is running on port 8081
- Check backend logs for errors
- Verify no firewall blocking localhost

## What Happens When You Start

1. **Backend starts** → Connects to PostgreSQL → Creates tables automatically
2. **Frontend starts** → Connects to backend API
3. **You register** → Data saved to PostgreSQL
4. **You play** → Game state persists in database

## Data Persistence

✅ Your game data is now saved permanently in PostgreSQL!
- Player accounts persist
- Game progress saves
- Scores and achievements stored
- Data survives application restarts

## Next Steps

1. Start the application using `START_HERE.bat`
2. Register a new account
3. Play the game
4. Your progress is automatically saved!

For deployment to Render, see: `RENDER_DEPLOYMENT.md`
