# 🚀 Quick Deploy Guide

## Fastest Way to Deploy (Free)

### Option 1: Railway + Vercel (Recommended)

**Time: ~10 minutes**

#### Step 1: Push to GitHub
```bash
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/yourusername/adventure-game.git
git push -u origin main
```

#### Step 2: Deploy Backend on Railway

1. Go to [railway.app](https://railway.app) and sign in with GitHub
2. Click **"New Project"** → **"Deploy from GitHub repo"**
3. Select your `adventure-game` repository
4. Click **"Add variables"** and add:
   ```
   JWT_SECRET=your-random-secret-key-here
   ```
5. Click **"New"** → **"Database"** → **"Add MySQL"**
6. Railway will auto-configure the database connection
7. In Settings:
   - Root Directory: `backend`
   - Build Command: `mvn clean package -DskipTests`
   - Start Command: `java -jar target/adventure-game-1.0.0.jar`
8. Copy your Railway backend URL (e.g., `https://adventure-game-production.up.railway.app`)

#### Step 3: Deploy Frontend on Vercel

1. Go to [vercel.com](https://vercel.com) and sign in with GitHub
2. Click **"Add New"** → **"Project"**
3. Import your `adventure-game` repository
4. Configure:
   - Framework Preset: **Create React App**
   - Root Directory: `frontend`
   - Build Command: `npm run build`
   - Output Directory: `build`
5. Add Environment Variable:
   ```
   REACT_APP_API_URL=https://your-railway-backend-url.railway.app
   ```
6. Click **"Deploy"**
7. Your app will be live at `https://your-app.vercel.app`

#### Step 4: Update CORS

Go back to Railway and add environment variable:
```
CORS_ALLOWED_ORIGINS=https://your-app.vercel.app
```

**Done! Your app is live! 🎉**

---

### Option 2: Docker (Local or VPS)

**Time: ~5 minutes**

#### Prerequisites
- Docker and Docker Compose installed

#### Deploy

1. **Create `.env` file:**
```bash
cat > .env << EOF
DB_PASSWORD=your-secure-password
JWT_SECRET=$(openssl rand -base64 64)
FRONTEND_URL=http://localhost
BACKEND_URL=http://localhost:8080
EOF
```

2. **Build and run:**
```bash
docker-compose up -d
```

3. **Access your app:**
- Frontend: http://localhost
- Backend: http://localhost:8080

4. **View logs:**
```bash
docker-compose logs -f
```

5. **Stop:**
```bash
docker-compose down
```

---

### Option 3: Render (All-in-One)

**Time: ~15 minutes**

#### Backend

1. Go to [render.com](https://render.com) and sign in
2. Click **"New"** → **"Web Service"**
3. Connect your GitHub repository
4. Configure:
   - **Name:** adventure-game-api
   - **Root Directory:** backend
   - **Environment:** Java
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/adventure-game-1.0.0.jar`
5. Click **"New Database"** → **"PostgreSQL"** (free tier)
6. Add environment variables:
   ```
   JWT_SECRET=your-secret-key
   CORS_ALLOWED_ORIGINS=https://your-frontend.onrender.com
   ```
7. Deploy!

#### Frontend

1. Click **"New"** → **"Static Site"**
2. Connect repository
3. Configure:
   - **Root Directory:** frontend
   - **Build Command:** `npm install && npm run build`
   - **Publish Directory:** build
4. Add environment variable:
   ```
   REACT_APP_API_URL=https://adventure-game-api.onrender.com
   ```
5. Deploy!

---

## 🔧 Configuration Files Included

All necessary files are already created:

- ✅ `backend/Dockerfile` - Backend container
- ✅ `frontend/Dockerfile` - Frontend container
- ✅ `docker-compose.yml` - Full stack deployment
- ✅ `backend/Procfile` - Heroku/Railway config
- ✅ `frontend/nginx.conf` - Production web server
- ✅ `.env.example` - Environment variables template
- ✅ `application-prod.properties` - Production settings

---

## 🎯 What You Need

### For Railway + Vercel:
- GitHub account
- Railway account (free)
- Vercel account (free)

### For Docker:
- Docker installed
- Docker Compose installed

### For Render:
- Render account (free tier available)
- GitHub account

---

## 🔐 Generate Secure JWT Secret

```bash
# Option 1: OpenSSL
openssl rand -base64 64

# Option 2: Node.js
node -e "console.log(require('crypto').randomBytes(64).toString('base64'))"

# Option 3: Python
python -c "import secrets; print(secrets.token_urlsafe(64))"
```

---

## ✅ Post-Deployment Checklist

- [ ] Backend is accessible (test: `curl https://your-backend-url/api/auth/login`)
- [ ] Frontend loads correctly
- [ ] Can register a new user
- [ ] Can login successfully
- [ ] Game loads and works
- [ ] Database is connected (check logs)
- [ ] CORS is configured correctly
- [ ] HTTPS is enabled (automatic on Railway/Vercel/Render)

---

## 🐛 Common Issues

### CORS Error
**Solution:** Update `CORS_ALLOWED_ORIGINS` environment variable with your frontend URL

### Database Connection Failed
**Solution:** Check database credentials in environment variables

### Build Failed
**Solution:** 
- Ensure Java 17+ is specified
- Check Maven build logs
- Verify all dependencies are available

### Frontend Can't Connect to Backend
**Solution:** Update `REACT_APP_API_URL` to your backend URL

---

## 📊 Deployment Status

Check your deployment:

```bash
# Backend health
curl https://your-backend-url/actuator/health

# Frontend
curl https://your-frontend-url

# Database (if accessible)
mysql -h your-db-host -u username -p
```

---

## 💰 Cost Summary

| Platform | Cost | Database | Notes |
|----------|------|----------|-------|
| Railway + Vercel | **FREE** | MySQL included | Best for beginners |
| Render | **FREE** | PostgreSQL included | Sleeps after inactivity |
| Docker (VPS) | $5-10/mo | Included | Full control |
| Heroku | $7-19/mo | $5/mo extra | No free tier |

---

## 🎉 You're Ready!

Choose your deployment method and follow the steps above. Your adventure game will be live in minutes!

**Need help?** Check `DEPLOYMENT_GUIDE.md` for detailed instructions.
