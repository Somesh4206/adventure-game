# 📤 Upload to GitHub - Simple Guide

## 🎯 Choose Your Method

### Method 1: Automatic Script (Easiest) ⭐

**Just double-click:** `upload-to-github.bat`

The script will:
1. ✅ Initialize Git
2. ✅ Add all files
3. ✅ Create commit
4. ✅ Ask for your GitHub URL
5. ✅ Push to GitHub

**That's it!** 🎉

---

### Method 2: Manual Steps (5 minutes)

#### Step 1: Create GitHub Repository

1. Go to [github.com](https://github.com)
2. Click **"+"** (top right) → **"New repository"**
3. Name: `adventure-game`
4. Click **"Create repository"**
5. **Copy the repository URL** (looks like: `https://github.com/yourusername/adventure-game.git`)

#### Step 2: Open Terminal

**Windows:**
- Press `Win + R`
- Type `cmd` and press Enter
- Navigate to your project:
  ```cmd
  cd C:\Text_Game\adventure-game
  ```

**Or use Git Bash:**
- Right-click in your project folder
- Select "Git Bash Here"

#### Step 3: Run These Commands

```bash
# Initialize Git
git init

# Add all files
git add .

# Create first commit
git commit -m "Initial commit"

# Add your GitHub repository (replace with YOUR URL)
git remote add origin https://github.com/YOUR_USERNAME/adventure-game.git

# Push to GitHub
git branch -M main
git push -u origin main
```

#### Step 4: Enter Credentials

When prompted:
- **Username:** Your GitHub username
- **Password:** Your Personal Access Token (see below)

**Done!** 🎉

---

## 🔑 Getting a Personal Access Token

GitHub requires a token instead of your password:

1. Go to GitHub → Click your profile picture → **Settings**
2. Scroll down → Click **Developer settings** (left sidebar)
3. Click **Personal access tokens** → **Tokens (classic)**
4. Click **"Generate new token (classic)"**
5. Name: `adventure-game-upload`
6. Check: ☑️ **repo** (full control of private repositories)
7. Click **"Generate token"**
8. **Copy the token** (you won't see it again!)
9. Use this token as your password when pushing to GitHub

---

## 🎬 Quick Video Guide

### Using the Script:
1. Double-click `upload-to-github.bat`
2. When asked, paste your GitHub repository URL
3. Enter your GitHub username
4. Enter your Personal Access Token
5. Done!

### Manual Upload:
```bash
cd C:\Text_Game\adventure-game
git init
git add .
git commit -m "Initial commit"
git remote add origin YOUR_GITHUB_URL
git push -u origin main
```

---

## ✅ Verify Upload

After uploading, visit:
```
https://github.com/YOUR_USERNAME/adventure-game
```

You should see all your files! 🎉

---

## 🚨 Troubleshooting

### "Git is not recognized"
**Fix:** Install Git from [git-scm.com](https://git-scm.com)

### "Authentication failed"
**Fix:** Use Personal Access Token (not your password)

### "Repository not found"
**Fix:** Make sure you created the repository on GitHub first

### "Permission denied"
**Fix:** Check your GitHub username and token are correct

---

## 📋 What Happens Next?

After uploading to GitHub:

1. ✅ Your code is safely backed up
2. ✅ You can deploy to Railway/Vercel (free!)
3. ✅ Others can see your project
4. ✅ You can collaborate with teammates

**Next:** Open `DEPLOY_QUICK_START.md` to deploy your app! 🚀

---

## 💡 Quick Tips

- ✅ Files in `.gitignore` won't be uploaded (like `node_modules/`)
- ✅ Your `.env` file is protected (not uploaded)
- ✅ You can update your code anytime with `git push`
- ✅ Keep your Personal Access Token safe!

---

## 🎯 Summary

**Easiest way:**
1. Create repository on GitHub
2. Double-click `upload-to-github.bat`
3. Enter your repository URL
4. Done!

**Total time:** 2-3 minutes ⏱️

**Ready? Let's upload your project! 🚀**
