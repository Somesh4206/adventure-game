# 🚀 Deploy Backend to Render - Quick Guide

Your backend is **in-memory** (no database needed), making deployment super simple!

---

## 📋 Prerequisites

- GitHub account with code pushed ✅ (Already done!)
- Render account - Sign up FREE at [render.com](https://render.com)

---

## 🎯 Step-by-Step Deployment

### Step 1: Create Render Account

1. Go to **[render.com](https://render.com)**
2. Click **"Get Started"**
3. Sign up with GitHub (recommended) or email
4. Verify your email

---

### Step 2: Create New Web Service

1. Go to **[Render Dashboard](https://dashboard.render.com)**
2. Click **"New +"** button (top right)
3. Select **"Web Service"**

---

### Step 3: Connect Your Repository

1. Click **"Connect account"** to link GitHub (if not already)
2. Find and select your repository: **`Somesh4206/adventure-game`**
3. Click **"Connect"**

---

### Step 4: Configure Your Service

Fill in these settings:

#### Basic Settings:
- **Name**: `adventure-game-backend` (or any name you prefer)
- **Region**: Choose closest to you (e.g., Oregon, Singapore, Frankfurt)
- **Branch**: `master` (your default branch)
- **Root Directory**: `backend` ← **IMPORTANT!**
- **Runtime**: Select **"Java"**

#### Build Settings:
- **Build Command**: 
  ```bash
  mvn clean package -DskipTests
  ```
- **Start Command**: 
  ```bash
  java -jar target/adventure-game-1.0.0.jar
  ```

#### Instance Type:
- **Plan**: Select **"Free"** (or upgrade if needed)
  - Free tier: 750 hours/month
  - Spins down after 15 min inactivity
  - First request after spin-down takes ~30 seconds

---

### Step 5: Add Environment Variables

Scroll down to **"Environment Variables"** section and add:

| Key | Value | Notes |
|-----|-------|-------|
| `PORT` | `10000` | Render will override this automatically |
| `CORS_ALLOWED_ORIGINS` | `http://localhost:3000,https://your-project.vercel.app` | Update with your Vercel URL after frontend deployment |

**Note**: You can add your Vercel URL later. For now, just use `http://localhost:3000`

---

### Step 6: Deploy!

1. Scroll to bottom
2. Click **"Create Web Service"**
3. Wait 3-5 minutes for build to complete
4. Watch the logs in real-time

#### Expected Build Output:
```
[INFO] Building jar: target/adventure-game-1.0.0.jar
[INFO] BUILD SUCCESS
Starting service...
Started AdventureGameApplication in X seconds
```

---

### Step 7: Get Your Backend URL

Once deployed (status shows "Live"):

1. Your backend URL will be at the top of the page:
   ```
   https://adventure-game-backend-xxxx.onrender.com
   ```
2. **Copy this URL** - you'll need it for Vercel deployment!
3. Test it by visiting: `https://your-backend-url.onrender.com/actuator/health` or any endpoint

---

## ✅ Verify Your Backend is Working

Test your backend with these endpoints:

### 1. Health Check (if actuator enabled):
```
GET https://your-backend-url.onrender.com/actuator/health
```

### 2. Auth Endpoint:
```bash
curl -X POST https://your-backend-url.onrender.com/auth/name \
  -H "Content-Type: application/json" \
  -d '{"name":"TestPlayer"}'
```

Should return a token response.

---

## 🔄 Update CORS After Frontend Deployment

After deploying frontend to Vercel:

1. Go to Render Dashboard → Your backend service
2. Click **"Environment"** in left sidebar
3. Find `CORS_ALLOWED_ORIGINS` variable
4. Click **"Edit"**
5. Update value to:
   ```
   http://localhost:3000,https://your-project.vercel.app
   ```
6. Click **"Save Changes"**
7. Service will automatically redeploy

---

## 🎮 Your Backend URL

**Copy this for Vercel deployment:**
```
https://adventure-game-backend-xxxx.onrender.com
```

You'll use this as the value for `REACT_APP_API_URL` when deploying to Vercel!

---

## 🔧 Common Issues

### Build Fails - "Maven not found"
- **Fix**: Render auto-detects Java. Ensure `pom.xml` is in `backend/` directory
- Check Root Directory is set to `backend`

### Build Fails - "Could not find artifact"
- **Fix**: Add `-DskipTests` to build command
- Check your `pom.xml` dependencies

### Service Shows "Deploy failed"
- **Check Logs**: Click "Logs" tab to see detailed errors
- Verify Java version matches (Java 17)
- Ensure `target/adventure-game-1.0.0.jar` exists after build

### Backend Returns 503/504
- **Free tier**: Service spins down after inactivity
- First request takes 30-60 seconds to spin up
- This is normal on free tier!

### CORS Errors from Frontend
- Update `CORS_ALLOWED_ORIGINS` environment variable
- Include your Vercel frontend URL
- Redeploy after changes

---

## 💰 Free Tier Limits

- ✅ **750 hours/month** (enough for 1 service running 24/7)
- ✅ **Automatic HTTPS**
- ⚠️ **Spins down after 15 min** of inactivity
- ⚠️ **Cold start**: ~30 seconds first request

**Upgrade to Starter ($7/month)** for:
- No spin-down
- Faster cold starts
- More resources

---

## 🔄 Automatic Deployments

Every time you push to GitHub `master` branch:
1. Render automatically detects changes
2. Rebuilds your backend
3. Deploys new version
4. Zero-downtime deployment

---

## 📊 Monitoring Your Backend

### View Logs:
1. Go to Render Dashboard
2. Click your service
3. Click **"Logs"** tab
4. See real-time application logs

### View Metrics:
1. Click **"Metrics"** tab
2. See CPU, Memory, Request count

### Check Status:
- **Green "Live"** = Service running
- **Gray "Sleeping"** = Service spun down (free tier)
- **Red "Failed"** = Check logs

---

## 🎉 Next Step

Now that your backend is deployed, you can:
1. **Copy your backend URL**
2. **Deploy frontend to Vercel** (see `VERCEL_QUICK_START.md`)
3. Use your backend URL as `REACT_APP_API_URL` in Vercel

---

## 📝 Backend URL Format

Your URL will look like:
```
https://adventure-game-backend-xxxx.onrender.com
```

Where `xxxx` is a random string assigned by Render.

**Save this URL** - you need it for the next step!

---

## 🆘 Need Help?

- Check logs in Render Dashboard
- Verify build command completed successfully
- Ensure Root Directory is set to `backend`
- Test endpoints with curl or Postman

---

**Ready for Vercel?** Once you have your backend URL, head to `VERCEL_QUICK_START.md`!
