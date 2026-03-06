# PostgreSQL Setup Guide

## Step 1: Install PostgreSQL

### Windows:
1. Download PostgreSQL from: https://www.postgresql.org/download/windows/
2. Run the installer (recommended version: PostgreSQL 15 or 16)
3. During installation:
   - Set a password for the `postgres` user (remember this!)
   - Default port: 5432
   - Install pgAdmin (GUI tool) - recommended

### Alternative - Using Docker:
```bash
docker run --name adventure-postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:15
```

## Step 2: Create the Database

### Option A: Using pgAdmin (GUI)
1. Open pgAdmin
2. Connect to your PostgreSQL server
3. Right-click "Databases" → "Create" → "Database"
4. Name: `adventuredb`
5. Click "Save"

### Option B: Using psql (Command Line)
```bash
# Connect to PostgreSQL
psql -U postgres

# Create database
CREATE DATABASE adventuredb;

# Verify
\l

# Exit
\q
```

### Option C: Using SQL Client
```sql
CREATE DATABASE adventuredb;
```

## Step 3: Update Application Configuration

Edit `backend/src/main/resources/application.properties`:

```properties
# Comment out H2 configuration
# spring.datasource.url=jdbc:h2:mem:adventuredb...
# spring.datasource.driver-class-name=org.h2.Driver
# spring.h2.console.enabled=true

# Uncomment and update PostgreSQL configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/adventuredb
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD_HERE
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Step 4: Restart the Backend

Stop the current backend process and restart:

```bash
cd backend
mvn spring-boot:run
```

## Step 5: Verify Connection

Check the backend logs for:
```
HikariPool-1 - Start completed.
Initialized JPA EntityManagerFactory for persistence unit 'default'
```

## Troubleshooting

### Connection Refused
- Ensure PostgreSQL service is running
- Check if port 5432 is open: `netstat -ano | findstr :5432`
- Verify firewall settings

### Authentication Failed
- Double-check username and password
- Ensure the postgres user has proper permissions

### Database Does Not Exist
- Create the database using one of the methods above
- Verify with: `psql -U postgres -l`

## Current Status

Your application is currently using **H2 in-memory database** (data is lost on restart).

To switch to PostgreSQL for persistent storage:
1. Install PostgreSQL
2. Create the `adventuredb` database
3. Update `application.properties` with your credentials
4. Restart the backend

## Benefits of PostgreSQL

✅ Data persists between restarts
✅ Production-ready
✅ Better performance for multiple users
✅ Advanced querying capabilities
✅ Proper user management and security
