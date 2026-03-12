# Setup Checklist

## ✅ Completed

- [x] PostgreSQL 18 installed at `C:\Program Files\PostgreSQL\18\bin`
- [x] Database `adventuredb` created
- [x] Backend configured to use PostgreSQL
- [x] Frontend configured to connect to backend
- [x] Startup scripts created

## ⚠️ Required Software

Before starting the application, ensure you have:

### 1. Java 17 or higher
Check: `java -version`

If not installed:
- Download from: https://adoptium.net/
- Install Java 17 (LTS)

### 2. Maven 3.8+
Check: `mvn --version`

If not installed:
- Download from: https://maven.apache.org/download.cgi
- Extract to `C:\Program Files\Maven`
- Add to PATH: `C:\Program Files\Maven\bin`

### 3. Node.js 18+
Check: `node --version`

If not installed:
- Download from: https://nodejs.org/
- Install LTS version

### 4. PostgreSQL Service Running
Check in Services (Win + R → services.msc):
- Service: "postgresql-x64-18"
- Status: Running

## 🚀 Starting the Application

Once all software is installed:

1. **Install frontend dependencies** (first time only):
   ```bash
   cd frontend
   npm install
   ```

2. **Start backend**:
   ```bash
   cd backend
   mvn spring-boot:run
   ```
   Wait for: "Started AdventureGameApplication"

3. **Start frontend** (in new terminal):
   ```bash
   cd frontend
   npm start
   ```
   Browser opens at http://localhost:3000

## 🔍 Quick Tests

### Test PostgreSQL Connection:
```bash
"C:\Program Files\PostgreSQL\18\bin\psql.exe" -U postgres -d adventuredb
```
Password: `postgres`

### Test Backend:
Open: http://localhost:8081/api/game/leaderboard

### Test Frontend:
Open: http://localhost:3000

## 📝 Configuration Summary

| Component | Value |
|-----------|-------|
| PostgreSQL Host | localhost:5432 |
| Database Name | adventuredb |
| DB Username | postgres |
| DB Password | postgres |
| Backend Port | 8081 |
| Frontend Port | 3000 |

## 🎮 Ready to Play!

Once everything is running:
1. Go to http://localhost:3000
2. Click "Register" to create an account
3. Login with your credentials
4. Start playing The Archive!

Your game progress will be saved in PostgreSQL and persist between sessions.

## 📚 Additional Resources

- **Render Deployment**: See `RENDER_DEPLOYMENT.md`
- **PostgreSQL Setup**: See `POSTGRESQL_SETUP.md`
- **Connection Details**: See `CONNECTION_SETUP.md`
