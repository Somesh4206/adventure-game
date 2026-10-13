# 🚀 Deploy Backend to Render - Blueprint Method

If you're not seeing Build Command and Start Command fields, Render might be using **Blueprint detection**. Here's the updated guide:

---

## 📋 Two Ways to Deploy on Render

### Option A: Using render.yaml (Recommended - Easier)

I've created a `render.yaml` file in your backend folder. This tells Render exactly what to do.

#### Steps:

1. **Go to Render Dashboard**: https://dashboard.render.com
2. Click **"New +"** → **"Blueprint"**
3. Connect your GitHub account if needed
4. Select repository: **`adventure-game`**
5. **Render will detect `backend/render.yaml`** automatically
6. Click **"Apply"**
7. Your service will be created with all settings pre-configured!
8. Wait 3-5 minutes for build to complete

✅ **That's it!** Your backend URL will be shown once deployment completes.

---

### Option B: Manual Configuration (If Blueprint doesn't work)

If you don't see the render.yaml option or want to configure manually:

#### Step 1: After Connecting Repository

After clicking **"Connect"** on your repository, you should see a configuration page.

#### Step 2: Look for "Build & Deploy" Settings

Scroll down on the configuration page. You should see either:

- **"Build Command"** field
- **"Docker"** option
- **"Blueprint"** option

#### Step 3a: If You See Build Command Field

Fill in:
- **Build Command**: `mvn clean package -DskipTests`
- **Start Command**: `java -jar target/adventure-game-1.0.0.jar`

#### Step 3b: If Render Auto-Detects Settings

Render might show:
- ✅ "Detected Java application"
- ✅ Build command pre-filled

**Verify these values:**
- Build: `mvn clean package` or `mvn clean package -DskipTests`
- Start: `java -jar target/adventure-game-1.0.0.jar`

If they're wrong, you can edit them.

#### Step 4: Set Root Directory

**THIS IS CRITICAL:**
- Look for **"Root Directory"** field
- Enter: `backend`
- This tells Render to look inside the backend folder

---

## 🎯 What to Look For

When you're on the "Create Web Service" page, here's what the fields might be called:

| Field Name | What to Enter |
|------------|---------------|
| **Name** | `adventure-game-backend` |
| **Region** | Choose closest to you |
| **Branch** | `master` |
| **Root Directory** | `backend` ⚠️ **CRITICAL** |
| **Runtime** / **Environment** | `Java` |
| **Build Command** | `mvn clean package -DskipTests` |
| **Start Command** | `java -jar target/adventure-game-1.0.0.jar` |

---

## 🔧 Troubleshooting: Can't Find Build Command

### Scenario 1: Render Shows "Blueprint Detected"

✅ **This is GOOD!** 
- Render found your `render.yaml` file
- Just click "Apply" and let it use the blueprint
- Everything is pre-configured

### Scenario 2: Render Auto-Filled Everything

✅ **This is GOOD TOO!**
- Render detected Maven/Java project
- Verify the commands look correct
- Make sure **Root Directory** is set to `backend`

### Scenario 3: Shows Docker Options Only

❌ **Wrong detection**
- Go back and make sure **Root Directory** is set to `backend` FIRST
- Then Render should detect it as a Java project
- Or use the Blueprint option instead

### Scenario 4: Build Command Field is Grayed Out

- This might mean Render is using auto-detection
- Look for an **"Override"** or **"Custom"** toggle
- OR look for **"Advanced"** settings section

---

## 📸 Visual Guide - What Screen Are You On?

### Screen 1: "Connect Repository"
- You see a list of your GitHub repos
- **Action**: Click "Connect" next to `adventure-game`

### Screen 2: "Configure Your Web Service"
This is where you set:
- Name
- Region  
- Branch
- **Root Directory** ← Set this to `backend`
- Environment variables

After setting Root Directory to `backend`, scroll down for more options.

### Screen 3: "Build Settings" (might be collapsed)
- Click **"Advanced"** or expand **"Build Settings"**
- Now you should see Build Command and Start Command

---

## ✅ Alternative: Use Dockerfile

If all else fails, Render can use Docker. Your project already has a Dockerfile!

1. On the configuration page, select **"Docker"** as the environment
2. Render will use `backend/Dockerfile`
3. Much simpler - Docker handles everything!

---

## 🆘 Still Stuck? Share This Info

If you're still not seeing the options, tell me:

1. **What options DO you see?** (Docker? Static Site? Background Worker?)
2. **What does Render say it detected?** (Node? Python? Nothing?)
3. **Did you set Root Directory to `backend`?**
4. **Screenshot of the configuration page** (optional)

I'll help you figure it out!

---

## 🎯 Expected Result

Once configured correctly, you should see:
```
✅ Build: mvn clean package -DskipTests
✅ Start: java -jar target/adventure-game-1.0.0.jar
✅ Root: backend
✅ Environment: Java
```

Then click **"Create Web Service"** and watch it deploy!

---

## 📝 Quick Reminder: Environment Variables

Don't forget to add these (usually at the bottom):
- `PORT` = `10000`
- `CORS_ALLOWED_ORIGINS` = `http://localhost:3000`

---

**Need more help?** Let me know exactly what you're seeing on the screen!
