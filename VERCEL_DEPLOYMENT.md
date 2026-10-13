# 🚀 Deploying to Vercel

This guide covers deploying **The Archive** adventure game to Vercel.

## 📋 Architecture Overview

Since Vercel is optimized for frontend applications and serverless functions (not Java Spring Boot), we'll use a **hybrid deployment**:

- **Frontend (React)**: Deploy to Vercel ✅
- **Backend (Spring Boot)**: Keep on Render/Railway/Heroku 🔧

---

## 🎯 Option 1: Frontend on Vercel + Backend on Render (Recommended)

### Step 1: Deploy Backend to Render

Your backend is already deployed at:
```
https://adventure-game-frontend-rujv.onrender.com
```

If you need to redeploy or update:

1. Go to [Render Dashboard](https://dashboard.render.com/)
2. Select your backend service
3. Note your backend URL (e.g., `https://your-backend.onrender.com`)

---

### Step 2: Deploy Frontend to Vercel

#### A. Install Vercel CLI (Optional but Recommended)

```bash
npm install -g vercel
```

#### B. Prepare Frontend for Vercel

The `vercel.json` configuration file has been created in your project root.

#### C. Deploy via Vercel CLI

```bash
# From project root
cd frontend

# Login to Vercel
vercel login

# Deploy (first time)
vercel

# Follow prompts:
# - Setup and deploy? Yes
# - Scope: Your account
# - Link to existing project? No
# - Project name: adventure-game (or your choice)
# - Directory: ./ (current directory is already frontend)
# - Override settings? No

# Deploy to production
vercel --prod
```

#### D. Set Environment Variable in Vercel

After deployment, configure your backend URL:

**Via CLI:**
```bash
vercel env add REACT_APP_API_URL production
# Then paste your backend URL: https://your-backend.onrender.com
```

**Via Dashboard:**
1. Go to [Vercel Dashboard](https://vercel.com/dashboard)
2. Select your project
3. Go to **Settings** → **Environment Variables**
4. Add:
   - **Name**: `REACT_APP_API_URL`
   - **Value**: `https://your-backend.onrender.com` (your actual backend URL)
   - **Environment**: Production (and Preview if needed)
5. Click **Save**
6. Go to **Deployments** tab → Click ⋯ on latest → **Redeploy**

---

### Step 3: Deploy via GitHub (Alternative Method)

1. **Push to GitHub** (if not already done):
   ```bash
   git add .
   git commit -m "Add Vercel configuration"
   git push origin main
   ```

2. **Import to Vercel**:
   - Go to [Vercel Dashboard](https://vercel.com/dashboard)
   - Click **Add New** → **Project**
   - Import your GitHub repository
   - **Root Directory**: Set to `frontend`
   - **Framework Preset**: Create React App (auto-detected)
   - **Build Command**: `npm run build`
   - **Output Directory**: `build`
   - **Install Command**: `npm install`

3. **Add Environment Variable**:
   - Before deploying, click **Environment Variables**
   - Add `REACT_APP_API_URL` with your backend URL
   - Click **Deploy**

4. **Update Backend CORS**:
   Your backend needs to allow requests from Vercel. Update `backend/src/main/java/com/adventure/config/CorsConfig.java`:
   
   ```java
   @Configuration
   public class CorsConfig {
       @Bean
       public WebMvcConfigurer corsConfigurer() {
           return new WebMvcConfigurer() {
               @Override
               public void addCorsMappings(CorsRegistry registry) {
                   registry.addMapping("/**")
                       .allowedOrigins(
                           "http://localhost:3000",
                           "https://your-vercel-app.vercel.app",  // Add your Vercel URL
                           "https://adventure-game-frontend-rujv.onrender.com"
                       )
                       .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                       .allowedHeaders("*")
                       .allowCredentials(true);
               }
           };
       }
   }
   ```

---

## 🎯 Option 2: Full Project on Vercel (Using Vercel Functions)

⚠️ **Note**: This requires converting your Java backend to Node.js serverless functions. This is a **major rewrite** and not recommended unless you want to maintain only one platform.

If interested, you would need to:
1. Rewrite Spring Boot controllers as Vercel serverless functions (Node.js/Python)
2. Move business logic to `/api` directory
3. Use Vercel KV or external database for state management

**This is complex and outside the scope of this guide.**

---

## 🔧 Troubleshooting

### CORS Errors
If you see CORS errors in browser console:
1. Verify backend CORS configuration includes your Vercel domain
2. Redeploy backend after updating CORS settings
3. Check browser network tab to confirm backend URL is correct

### Environment Variables Not Loading
1. Ensure `REACT_APP_API_URL` is set in Vercel dashboard
2. Redeploy after adding environment variables
3. Check build logs for environment variable confirmation

### API Calls Failing
1. Verify backend is running: Visit `https://your-backend.onrender.com/actuator/health` (if actuator enabled) or test any endpoint
2. Check Network tab in browser DevTools
3. Verify `REACT_APP_API_URL` matches your backend URL exactly (no trailing slash)

### Build Failures
1. Check build logs in Vercel dashboard
2. Ensure `package.json` and `package-lock.json` are committed
3. Verify Node.js version compatibility (Vercel uses Node 18 by default)

---

## 📊 Post-Deployment Checklist

- [ ] Frontend deploys successfully on Vercel
- [ ] Backend is accessible from Vercel domain
- [ ] CORS is configured for Vercel domain
- [ ] Environment variables are set correctly
- [ ] Game authentication works
- [ ] Game scenes load properly
- [ ] Choices persist through gameplay
- [ ] No console errors in browser

---

## 🔗 Useful Links

- [Vercel Dashboard](https://vercel.com/dashboard)
- [Vercel Documentation](https://vercel.com/docs)
- [Render Dashboard](https://dashboard.render.com/)

---

## 💡 Cost Estimate

- **Vercel**: Free tier includes:
  - Unlimited deployments
  - 100GB bandwidth/month
  - Automatic HTTPS
  - Preview deployments for git branches

- **Render (Backend)**: Free tier includes:
  - 750 hours/month
  - Spins down after inactivity
  - 512MB RAM

**Total: $0/month** for hobby/portfolio projects!

---

## 🎮 Your Live URLs After Deployment

- **Frontend**: `https://your-project.vercel.app`
- **Backend**: `https://your-backend.onrender.com` (existing)

---

Need help? Check the troubleshooting section or reach out!
