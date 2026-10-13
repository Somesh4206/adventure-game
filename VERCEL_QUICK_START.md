# ⚡ Vercel Quick Start - Deploy in 5 Minutes

## 🎯 What You Need
- GitHub account
- Vercel account (free - sign up at [vercel.com](https://vercel.com))
- Your backend already deployed (currently at Render)

---

## 🚀 Deploy Steps

### 1️⃣ Push Your Code to GitHub (if not already done)

```bash
git add .
git commit -m "Add Vercel configuration"
git push origin main
```

---

### 2️⃣ Import Project to Vercel

1. Go to **[vercel.com/new](https://vercel.com/new)**
2. Click **Continue with GitHub**
3. Select your `adventure-game` repository
4. Configure:
   - **Root Directory**: `frontend` ← **IMPORTANT!**
   - **Framework Preset**: Create React App (auto-detected)
   - Click **Edit** next to "Build and Output Settings" to verify:
     - Build Command: `npm run build`
     - Output Directory: `build`
     - Install Command: `npm install`

---

### 3️⃣ Add Environment Variable

**Before clicking Deploy:**

1. Expand **Environment Variables** section
2. Add:
   - **Key**: `REACT_APP_API_URL`
   - **Value**: `https://your-backend-url.onrender.com` ← Use your actual backend URL
   - **Environment**: Check all (Production, Preview, Development)
3. Click **Add**

---

### 4️⃣ Deploy!

1. Click **Deploy**
2. Wait 1-2 minutes for build to complete
3. Click **Visit** to see your live site!

---

## 🔄 Update Backend CORS

After getting your Vercel URL, you need to allow it in your backend:

### Your Vercel URL will be:
`https://your-project-name.vercel.app`

### Update Backend on Render:

1. The CORS config has already been updated to allow `*.vercel.app` domains
2. **Push the backend changes** to trigger a redeploy:
   ```bash
   git add backend/src/main/java/com/adventure/config/CorsConfig.java
   git commit -m "Update CORS for Vercel deployment"
   git push origin main
   ```
3. Your Render service will automatically redeploy

---

## ✅ Test Your Deployment

1. Visit your Vercel URL: `https://your-project.vercel.app`
2. Try to log in with a name
3. Play through a scene
4. If you see CORS errors, verify:
   - Backend is redeployed with new CORS settings
   - Environment variable is set correctly in Vercel

---

## 🎮 Your Live URLs

- **Frontend (Vercel)**: `https://your-project.vercel.app`
- **Backend (Render)**: Your existing Render URL

---

## 🔧 Common Issues

### "API calls failing"
- Check: Environment Variables in Vercel dashboard
- Fix: Add `REACT_APP_API_URL` with correct backend URL

### "CORS error in console"
- Check: Backend CORS configuration
- Fix: Redeploy backend after updating CorsConfig.java

### "Blank page after deployment"
- Check: Build logs in Vercel dashboard
- Fix: Ensure `frontend` is set as Root Directory

---

## 📱 Automatic Deployments

Every time you push to GitHub:
- Vercel automatically rebuilds and deploys your frontend
- You get preview URLs for branches and PRs
- Main branch deploys to production URL

---

## 💰 Cost

**$0** - Both Vercel and Render free tiers are sufficient for this project!

---

## 🎉 You're Done!

Share your game at: `https://your-project.vercel.app`
