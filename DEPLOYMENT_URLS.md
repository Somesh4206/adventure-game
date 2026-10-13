# 🌐 Deployment URLs Tracker

Use this file to track your deployment URLs.

---

## 📝 Fill This In During Deployment

### Backend (Render)
- **Service Name**: `adventure-game-backend`
- **Backend URL**: `https://________________________.onrender.com`
- **Status**: ⬜ Not Deployed / ⬜ Deployed
- **Deployed On**: _______________

### Frontend (Vercel)
- **Project Name**: `adventure-game`
- **Frontend URL**: `https://________________________.vercel.app`
- **Status**: ⬜ Not Deployed / ⬜ Deployed
- **Deployed On**: _______________

---

## 🔧 Configuration Checklist

### Backend Environment Variables (Render):
- [x] `PORT` = `10000`
- [ ] `CORS_ALLOWED_ORIGINS` = Updated with Vercel URL

### Frontend Environment Variables (Vercel):
- [ ] `REACT_APP_API_URL` = Backend URL from above

---

## ✅ Deployment Steps

### Step 1: Deploy Backend to Render
- [ ] Create Render account
- [ ] Create new Web Service
- [ ] Connect GitHub repo
- [ ] Set Root Directory to `backend`
- [ ] Configure build & start commands
- [ ] Add environment variables
- [ ] Deploy and wait for completion
- [ ] Copy backend URL above

### Step 2: Deploy Frontend to Vercel
- [ ] Create Vercel account
- [ ] Import GitHub repo
- [ ] Set Root Directory to `frontend`
- [ ] Add `REACT_APP_API_URL` environment variable
- [ ] Deploy and wait for completion
- [ ] Copy frontend URL above

### Step 3: Update CORS
- [ ] Update backend `CORS_ALLOWED_ORIGINS` with Vercel URL
- [ ] Redeploy backend
- [ ] Test frontend can connect to backend

### Step 4: Test Everything
- [ ] Visit frontend URL
- [ ] Login with a name
- [ ] Play through a scene
- [ ] Verify no console errors

---

## 🎮 Quick Links

- [Render Dashboard](https://dashboard.render.com)
- [Vercel Dashboard](https://vercel.com/dashboard)
- [GitHub Repository](https://github.com/Somesh4206/adventure-game)

---

## 🚀 Deployment Commands Reference

### Push to GitHub:
```bash
git add .
git commit -m "Your message"
git push origin master
```

### Test Backend Locally:
```bash
cd backend
mvn spring-boot:run
```

### Test Frontend Locally:
```bash
cd frontend
npm start
```

---

## 📞 Support Resources

- **Render Docs**: https://render.com/docs
- **Vercel Docs**: https://vercel.com/docs
- **Project README**: See `README.md`

---

**Last Updated**: _________________
