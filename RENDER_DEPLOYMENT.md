# Render Deployment Quick Guide

This guide will help you deploy your Adventure Game to Render with PostgreSQL.

## Prerequisites

- GitHub account with your code pushed
- Render account (sign up at https://render.com)

## Deployment Steps

### Step 1: Create PostgreSQL Database

1. Log in to Render Dashboard: https://dashboard.render.com
2. Click "New +" → "PostgreSQL"
3. Fill in:
   - **Name**: `adventure-game-db`
   - **Database**: `adventuredb`
   - **Region**: Choose closest to your users
   - **PostgreSQL Version**: 15 (recommended)
   - **Plan**: Free (or paid for production)
4. Click "Create Database"
5. Wait for database to be created
6. **IMPORTANT**: Copy the "Internal Database URL" from the database page
   - It looks like: `postgresql://user:password@host/database`
   - You'll need this for the backend

### Step 2: Deploy Backend (Java Spring Boot)

1. Click "New +" → "Web Service"
2. Connect your GitHub repository
3. Configure the service:
   - **Name**: `adventure-game-backend`
   - **Region**: Same as your database
   - **Branch**: `main` (or your default branch)
   - **Root Directory**: Leave empty
   - **Environment**: `Java`
   - **Build Command**: 
     ```
     cd backend && mvn clean package -DskipTests
     ```
   - **Start Command**: 
     ```
     java -jar backend/target/adventure-game-1.0.0.jar --spring.profiles.active=prod
     ```

4. Add Environment Variables (click "Advanced" → "Add Environment Variable"):
   - `DATABASE_URL`: Paste the Internal Database URL from Step 1
   - `JWT_SECRET`: Generate a secure random string (at least 32 characters)
     - Example: `MySecureJWTSecret2024ForAdventureGameProduction`
   - `CORS_ALLOWED_ORIGINS`: `*` (for now, update with frontend URL later)
   - `PORT`: `8080`

5. Click "Create Web Service"
6. Wait for deployment to complete (5-10 minutes)
7. **Copy your backend URL** (e.g., `https://adventure-game-backend.onrender.com`)

### Step 3: Deploy Frontend (React)

1. Click "New +" → "Static Site"
2. Connect your GitHub repository (same repo)
3. Configure the site:
   - **Name**: `adventure-game-frontend`
   - **Branch**: `main`
   - **Root Directory**: Leave empty
   - **Build Command**: 
     ```
     cd frontend && npm install && npm run build
     ```
   - **Publish Directory**: 
     ```
     frontend/build
     ```

4. Add Environment Variable:
   - `REACT_APP_API_URL`: Your backend URL from Step 2
     - Example: `https://adventure-game-backend.onrender.com`

5. Click "Create Static Site"
6. Wait for deployment to complete (3-5 minutes)
7. **Copy your frontend URL** (e.g., `https://adventure-game-frontend.onrender.com`)

### Step 4: Update CORS Settings

1. Go back to your backend service in Render
2. Click "Environment" in the left sidebar
3. Update the `CORS_ALLOWED_ORIGINS` variable:
   - Change from `*` to your frontend URL
   - Example: `https://adventure-game-frontend.onrender.com`
4. Click "Save Changes"
5. Backend will automatically redeploy

### Step 5: Test Your Application

1. Open your frontend URL in a browser
2. Try to register a new account
3. Log in with your credentials
4. Play the game!

## Important Notes

### Free Tier Limitations
- Backend services spin down after 15 minutes of inactivity
- First request after spin-down takes 30-60 seconds
- Database has 90-day expiration on free tier
- Consider upgrading for production use

### Environment Variables Reference

**Backend:**
- `DATABASE_URL`: PostgreSQL connection string (from Render PostgreSQL)
- `JWT_SECRET`: Secret key for JWT token signing (keep secure!)
- `CORS_ALLOWED_ORIGINS`: Frontend URL (for security)
- `PORT`: 8080 (Render sets this automatically)

**Frontend:**
- `REACT_APP_API_URL`: Backend API URL

### Troubleshooting

**Backend won't start:**
- Check logs in Render dashboard
- Verify `DATABASE_URL` is set correctly
- Ensure Java 17 is being used
- Check build command completed successfully

**Frontend can't connect to backend:**
- Verify `REACT_APP_API_URL` is set correctly
- Check CORS settings in backend
- Ensure backend is running (check Render dashboard)
- Open browser console for error messages

**Database connection errors:**
- Use "Internal Database URL" not "External"
- Verify database is running in Render dashboard
- Check database and backend are in same region

**CORS errors:**
- Update `CORS_ALLOWED_ORIGINS` with exact frontend URL
- No trailing slashes in URLs
- Wait for backend to redeploy after changes

## Updating Your Application

### Update Backend:
1. Push changes to GitHub
2. Render automatically rebuilds and deploys
3. Check logs for any errors

### Update Frontend:
1. Push changes to GitHub
2. Render automatically rebuilds and deploys
3. Clear browser cache if changes don't appear

## Monitoring

- Check logs: Click on your service → "Logs" tab
- View metrics: Click on your service → "Metrics" tab
- Set up alerts: Click on your service → "Settings" → "Notifications"

## Next Steps

- Set up custom domain
- Enable HTTPS (automatic on Render)
- Configure environment-specific settings
- Set up monitoring and alerts
- Consider upgrading to paid tier for production

## Support

- Render Documentation: https://render.com/docs
- Render Community: https://community.render.com
- Check application logs for specific errors
