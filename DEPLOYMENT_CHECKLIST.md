# ✅ Deployment Checklist

Use this checklist to track your deployment progress.

---

## 🔵 Phase 1: Backend Deployment (Render)

### Account Setup
- [ ] Created Render account at https://render.com
- [ ] Signed in with GitHub
- [ ] Authorized Render to access GitHub

### Service Creation
- [ ] Clicked "New +" → "Web Service"
- [ ] Connected `adventure-game` repository
- [ ] Named service: `adventure-game-backend`
- [ ] Selected region: ________________
- [ ] Set branch to: `master`
- [ ] ⚠️ **CRITICAL**: Set Root Directory to: `backend`
- [ ] Selected Language: Java

### Build Configuration
- [ ] Build Command: `mvn clean package -DskipTests`
- [ ] Start Command: `java -jar target/adventure-game-1.0.0.jar`
- [ ] Selected Instance Type: Free

### Environment Variables
- [ ] Added `PORT` = `10000`
- [ ] Added `CORS_ALLOWED_ORIGINS` = `http://localhost:3000`

### Deployment
- [ ] Clicked "Create Web Service"
- [ ] Watched build logs (3-5 minutes)
- [ ] Saw "BUILD SUCCESS"
- [ ] Saw "Your service is live"
- [ ] Service status shows green "Live" badge

### Testing
- [ ] Copied backend URL: `https://________________________________.onrender.com`
- [ ] Tested endpoint with curl or browser
- [ ] Received valid response (no 404)

**✅ Backend Deployment Complete!**

---

## 🟢 Phase 2: Frontend Deployment (Vercel)

### Account Setup
- [ ] Created Vercel account at https://vercel.com
- [ ] Signed in with GitHub
- [ ] Authorized Vercel to access GitHub

### Project Import
- [ ] Clicked "Add New" → "Project"
- [ ] Selected `adventure-game` repository
- [ ] Clicked "Import"
- [ ] Named project: `adventure-game`
- [ ] ⚠️ **CRITICAL**: Set Root Directory to: `frontend`
- [ ] Framework: Create React App (auto-detected)

### Build Configuration
- [ ] Build Command: `npm run build` (auto-detected)
- [ ] Output Directory: `build` (auto-detected)
- [ ] Install Command: `npm install` (auto-detected)

### Environment Variables
- [ ] Added `REACT_APP_API_URL` = Your backend URL from above
- [ ] Selected: Production, Preview, Development

### Deployment
- [ ] Clicked "Deploy"
- [ ] Watched build (2-3 minutes)
- [ ] Saw "Build Completed"
- [ ] Saw "Deployment Ready"

### Testing
- [ ] Copied frontend URL: `https://________________________________.vercel.app`
- [ ] Visited frontend URL in browser
- [ ] Page loads without errors
- [ ] Can see login/auth page

**✅ Frontend Deployment Complete!**

---

## 🟡 Phase 3: Connect Backend + Frontend

### Update Backend CORS
- [ ] Went to Render Dashboard
- [ ] Selected backend service
- [ ] Clicked "Environment" tab
- [ ] Edited `CORS_ALLOWED_ORIGINS`
- [ ] Updated value to: `http://localhost:3000,https://your-vercel-app.vercel.app`
- [ ] Clicked "Save Changes"
- [ ] Waited for automatic redeploy (1-2 minutes)

### Full Integration Test
- [ ] Visited frontend URL
- [ ] Entered a name in auth page
- [ ] Successfully logged in (no CORS errors)
- [ ] Game scene loaded
- [ ] Made a choice
- [ ] Game progressed to next scene
- [ ] Checked browser console: No errors

**✅ Full Deployment Complete!**

---

## 📝 Your Live URLs

**Backend (Render):**
```
https://________________________________.onrender.com
```

**Frontend (Vercel):**
```
https://________________________________.vercel.app
```

---

## 🎮 Share Your Game!

Your game is now live at:
```
https://________________________________.vercel.app
```

Share this URL with anyone to play your adventure game!

---

## 🔧 Post-Deployment

### Monitoring
- [ ] Bookmarked Render Dashboard
- [ ] Bookmarked Vercel Dashboard
- [ ] Know how to check logs (Render → Service → Logs tab)
- [ ] Know how to check deployments (Vercel → Deployments tab)

### Future Updates
- [ ] Understand: Push to GitHub = Automatic deployment
- [ ] Tested: Make a small change, push, verify auto-deploy
- [ ] Know how to rollback if needed (Vercel → Deployments → Promote)

---

## ⏰ Important Notes

### Free Tier Limitations
- **Render**: Spins down after 15 min inactivity (first request takes ~30s)
- **Vercel**: 100GB bandwidth/month (plenty for hobby projects)

### Automatic Deployments
- Every `git push` triggers automatic rebuild & deploy
- Render: Backend redeploys (~3-5 min)
- Vercel: Frontend redeploys (~2-3 min)

---

## 🆘 Common Issues & Solutions

### "CORS error" in browser console
**Solution:** Update backend `CORS_ALLOWED_ORIGINS` with Vercel URL

### Backend is slow to respond
**Solution:** Normal on free tier - service is waking up from sleep

### Changes not showing up
**Solution:** 
1. Hard refresh browser (Ctrl+F5)
2. Check deployment succeeded in Vercel/Render dashboard
3. Clear browser cache

### Build failed
**Solution:**
1. Check logs in dashboard
2. Verify Root Directory is correct
3. Ensure all files committed to GitHub

---

## ✅ Success Criteria

You know deployment is successful when:
1. ✅ Backend URL returns valid responses
2. ✅ Frontend loads in browser
3. ✅ Can log in with a name
4. ✅ Game scenes load and display
5. ✅ Can make choices and progress
6. ✅ No errors in browser console

---

**Deployment Status:**  
- Backend: ⬜ In Progress / ⬜ Complete  
- Frontend: ⬜ In Progress / ⬜ Complete  
- Integration: ⬜ In Progress / ⬜ Complete  

**Date Completed:** _______________

🎉 **Congratulations on deploying your game!** 🎉
