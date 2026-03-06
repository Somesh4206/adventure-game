# MySQL Database Setup - COMPLETE ✅

## Database Configuration

Your application is now connected to MySQL database with the following configuration:

### Connection Details
- **Database Name:** `adventuredb`
- **Host:** localhost
- **Port:** 3306
- **Username:** root
- **Password:** root123
- **Character Set:** utf8mb4
- **Collation:** utf8mb4_unicode_ci

### Tables Created
The following tables have been automatically created by Hibernate:

1. **players** - Stores user accounts and game progress
   - id (Primary Key, Auto Increment)
   - username (Unique)
   - email (Unique)
   - password (Encrypted with BCrypt)
   - current_scene_id
   - current_chapter
   - total_score
   - health_points
   - max_health
   - choices_made
   - deaths
   - game_flags (JSON)
   - role (USER/ADMIN)
   - created_at
   - last_played

2. **player_achievements** - Tracks unlocked achievements
3. **player_inventory** - Stores collected items
4. **player_completed_scenes** - Tracks visited scenes

## Application Status

✅ Backend running on: http://localhost:8081
✅ Frontend running on: http://localhost:3000
✅ MySQL database connected and ready
✅ Tables created automatically
✅ User registration and login working

## Testing the Setup

### 1. Register a New User
- Go to http://localhost:3000
- Click "Become Archivist"
- Enter username, email, and password
- Click "Claim Your Title"

### 2. Verify in Database
```bash
mysql -u root -proot123 -D adventuredb -e "SELECT id, username, email, created_at FROM players;"
```

### 3. Login
- Enter your username and password
- Click "Open the Archive"
- Start playing!

## Data Persistence

✅ **All user data is now persistent!**
- Users remain registered even after server restart
- Game progress is saved automatically
- Achievements and inventory are stored
- Leaderboard scores are permanent

## Viewing Data

### Using MySQL Command Line
```bash
# Connect to database
mysql -u root -proot123 -D adventuredb

# View all users
SELECT id, username, email, total_score FROM players;

# View user with achievements
SELECT p.username, p.total_score, p.current_chapter 
FROM players p 
ORDER BY p.total_score DESC;

# Exit
exit;
```

### Using MySQL Workbench (GUI)
1. Open MySQL Workbench
2. Connect to localhost:3306
3. Select `adventuredb` database
4. Browse tables and data

## Configuration Files

### Backend Configuration
File: `backend/src/main/resources/application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/adventuredb
spring.datasource.username=root
spring.datasource.password=root123
spring.jpa.hibernate.ddl-auto=update
```

### Maven Dependencies
File: `backend/pom.xml`
- MySQL Connector/J 8.1.0 (added)
- Spring Data JPA
- Hibernate

## Security Notes

⚠️ **For Production:**
1. Change the database password from `root123`
2. Create a dedicated MySQL user (not root)
3. Update JWT secret in application.properties
4. Enable SSL for MySQL connection
5. Use environment variables for sensitive data

## Troubleshooting

### Connection Issues
```bash
# Check MySQL service
Get-Service MySQL91

# Test connection
mysql -u root -proot123 -e "SELECT 1;"
```

### View Logs
Check backend console for SQL queries (show-sql is enabled)

### Reset Database
```sql
DROP DATABASE adventuredb;
CREATE DATABASE adventuredb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
Then restart the backend to recreate tables.

## Next Steps

Your application is fully functional with MySQL! You can now:
- Register multiple users
- Track game progress
- View leaderboards
- Store achievements
- All data persists between restarts

Enjoy your adventure game! 🎮
