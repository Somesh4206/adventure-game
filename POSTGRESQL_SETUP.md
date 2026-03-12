# PostgreSQL Setup Guide

This project now uses PostgreSQL for both local development and production deployment on Render.

## Local Development Setup

### Option 1: Using Docker (Recommended)

1. Start PostgreSQL with Docker Compose:
```bash
docker-compose up postgres
```

2. The database will be available at:
   - Host: localhost
   - Port: 5432
   - Database: adventuredb
   - Username: postgres
   - Password: postgres

### Option 2: Install PostgreSQL Locally

#### Windows:
1. Download PostgreSQL from: https://www.postgresql.org/download/windows/
2. Run the installer (recommended version: PostgreSQL 15 or 16)
3. During installation:
   - Set a password for the `postgres` user (remember this!)
   - Default port: 5432
   - Install pgAdmin (GUI tool) - recommended

#### Create the Database:

Using pgAdmin (GUI):
1. Open pgAdmin
2. Connect to your PostgreSQL server
3. Right-click "Databases" → "Create" → "Database"
4. Name: `adventuredb`
5. Click "Save"

Using psql (Command Line):
```bash
psql -U postgres
CREATE DATABASE adventuredb;
\q
```

3. Update `backend/src/main/resources/application.properties` if needed:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/adventuredb
spring.datasource.username=postgres
spring.datasource.password=your_password
```

## Running the Application Locally

1. Start PostgreSQL (if not using Docker Compose for full stack)
2. Run the backend:
```bash
cd backend
mvn spring-boot:run
```

## Render Deployment

### 1. Create PostgreSQL Database on Render

1. Go to https://dashboard.render.com
2. Click "New +" → "PostgreSQL"
3. Configure:
   - Name: adventure-game-db
   - Database: adventuredb
   - User: (auto-generated)
   - Region: Choose closest to your users
   - Plan: Free or paid
4. Click "Create Database"
5. Copy the "Internal Database URL" (starts with `postgresql://`)

### 2. Deploy Backend on Render

1. Click "New +" → "Web Service"
2. Connect your GitHub repository
3. Configure:
   - Name: adventure-game-backend
   - Environment: Java
   - Build Command: `cd backend && mvn clean package -DskipTests`
   - Start Command: `java -jar backend/target/adventure-game-1.0.0.jar --spring.profiles.active=prod`
4. Add Environment Variables:
   - `DATABASE_URL`: Paste the Internal Database URL from step 1
   - `JWT_SECRET`: Generate a secure random string (min 256 bits)
   - `CORS_ALLOWED_ORIGINS`: Your frontend URL (e.g., https://your-app.onrender.com)
5. Click "Create Web Service"

### 3. Deploy Frontend on Render

1. Click "New +" → "Static Site"
2. Connect your GitHub repository
3. Configure:
   - Name: adventure-game-frontend
   - Build Command: `cd frontend && npm install && npm run build`
   - Publish Directory: `frontend/build`
4. Add Environment Variable:
   - `REACT_APP_API_URL`: Your backend URL (e.g., https://adventure-game-backend.onrender.com)
5. Click "Create Static Site"

## Environment Variables Summary

### Backend (Render Web Service)
- `DATABASE_URL`: PostgreSQL connection string (from Render PostgreSQL)
- `JWT_SECRET`: Secure random string for JWT signing
- `CORS_ALLOWED_ORIGINS`: Frontend URL

### Frontend (Render Static Site)
- `REACT_APP_API_URL`: Backend API URL

## Verifying the Setup

1. Check backend health: `https://your-backend.onrender.com/actuator/health`
2. Test authentication: Try registering/logging in through the frontend
3. Check Render logs if issues occur

## Troubleshooting

### Database Connection Issues
- Verify `DATABASE_URL` is set correctly in Render
- Ensure you're using the "Internal Database URL" not "External"
- Check PostgreSQL instance is running on Render

### CORS Errors
- Verify `CORS_ALLOWED_ORIGINS` includes your frontend URL
- Ensure no trailing slashes in URLs

### Build Failures
- Check Java version is 17 in Render settings
- Verify Maven build succeeds locally first
- Check Render build logs for specific errors

## Migration from MySQL

If you were previously using MySQL, the application will automatically create the PostgreSQL schema on first run. No manual migration is needed for a fresh deployment.

## Benefits of PostgreSQL

✅ Data persists between restarts
✅ Production-ready and Render-compatible
✅ Better performance for multiple users
✅ Advanced querying capabilities
✅ Proper user management and security
✅ Free tier available on Render
