# 📤 How to Upload Your Project to GitHub

## Step-by-Step Guide

### Prerequisites
- Git installed on your computer
- GitHub account (create one at [github.com](https://github.com) if you don't have one)

---

## Method 1: Using GitHub Desktop (Easiest)

### Step 1: Install GitHub Desktop
1. Download from [desktop.github.com](https://desktop.github.com)
2. Install and sign in with your GitHub account

### Step 2: Create Repository
1. Open GitHub Desktop
2. Click **"File"** → **"Add Local Repository"**
3. Click **"Choose..."** and select your project folder: `C:\Text_Game\adventure-game`
4. Click **"Create Repository"**
5. Click **"Publish repository"**
6. Configure:
   - Name: `adventure-game`
   - Description: `A text-based adventure game with Spring Boot and React`
   - ☐ Keep this code private (uncheck for public)
7. Click **"Publish repository"**

### Step 3: Done!
Your project is now on GitHub! 🎉

---

## Method 2: Using Command Line (Git Bash)

### Step 1: Initialize Git Repository

Open Git Bash in your project folder and run:

```bash
# Navigate to your project
cd /c/Text_Game/adventure-game

# Initialize git repository
git init

# Add all files
git add .

# Create first commit
git commit -m "Initial commit: Adventure Game with Spring Boot and React"
```

### Step 2: Create GitHub Repository

1. Go to [github.com](https://github.com)
2. Click the **"+"** icon (top right) → **"New repository"**
3. Fill in:
   - **Repository name:** `adventure-game`
   - **Description:** `A text-based adventure game with Spring Boot and React`
   - **Visibility:** Public or Private
   - ☐ **DO NOT** initialize with README (we already have one)
4. Click **"Create repository"**

### Step 3: Connect and Push

GitHub will show you commands. Copy your repository URL and run:

```bash
# Add remote repository (replace with YOUR GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/adventure-game.git

# Rename branch to main (if needed)
git branch -M main

# Push to GitHub
git push -u origin main
```

### Step 4: Enter Credentials

When prompted:
- **Username:** Your GitHub username
- **Password:** Use a Personal Access Token (not your password)

**To create a Personal Access Token:**
1. Go to GitHub → Settings → Developer settings → Personal access tokens → Tokens (classic)
2. Click "Generate new token (classic)"
3. Select scopes: `repo` (full control)
4. Click "Generate token"
5. Copy the token and use it as your password

### Step 5: Done!
Your project is now on GitHub! 🎉

---

## Method 3: Using Visual Studio Code

### Step 1: Open Project in VS Code
```bash
cd C:\Text_Game\adventure-game
code .
```

### Step 2: Initialize Git
1. Click the **Source Control** icon (left sidebar)
2. Click **"Initialize Repository"**
3. Enter commit message: `Initial commit`
4. Click the **✓** checkmark to commit

### Step 3: Publish to GitHub
1. Click **"Publish to GitHub"** button
2. Choose **Public** or **Private**
3. Select files to include (select all)
4. Click **"OK"**

### Step 4: Done!
VS Code will create the repository and push your code! 🎉

---

## 🔍 Verify Your Upload

After uploading, visit your repository:
```
https://github.com/YOUR_USERNAME/adventure-game
```

You should see:
- ✅ All your project files
- ✅ README.md displayed
- ✅ Folder structure (backend, frontend)
- ✅ Documentation files

---

## 📝 What Gets Uploaded

Your `.gitignore` file ensures these are **NOT** uploaded:
- ❌ `node_modules/` (too large)
- ❌ `target/` (build files)
- ❌ `.env` (sensitive data)
- ❌ IDE files (.idea, .vscode)
- ❌ Log files

These **ARE** uploaded:
- ✅ Source code (backend/src, frontend/src)
- ✅ Configuration files (pom.xml, package.json)
- ✅ Documentation (README.md, guides)
- ✅ Deployment files (Dockerfile, docker-compose.yml)

---

## 🔄 Making Updates Later

After making changes to your code:

### Using GitHub Desktop:
1. Open GitHub Desktop
2. Review changes
3. Enter commit message
4. Click **"Commit to main"**
5. Click **"Push origin"**

### Using Command Line:
```bash
# Add changed files
git add .

# Commit with message
git commit -m "Description of changes"

# Push to GitHub
git push
```

### Using VS Code:
1. Click Source Control icon
2. Review changes
3. Enter commit message
4. Click ✓ to commit
5. Click **"Sync Changes"**

---

## 🚨 Common Issues & Solutions

### Issue 1: "Git is not recognized"
**Solution:** Install Git from [git-scm.com](https://git-scm.com)

### Issue 2: "Permission denied"
**Solution:** Use Personal Access Token instead of password

### Issue 3: "Repository already exists"
**Solution:** 
```bash
git remote remove origin
git remote add origin https://github.com/YOUR_USERNAME/adventure-game.git
git push -u origin main
```

### Issue 4: "Large files"
**Solution:** Files are already excluded in `.gitignore`. If you get this error:
```bash
# Remove large files from git
git rm --cached -r frontend/node_modules
git rm --cached -r backend/target
git commit -m "Remove large files"
git push
```

### Issue 5: "Authentication failed"
**Solution:** Create and use a Personal Access Token:
1. GitHub → Settings → Developer settings → Personal access tokens
2. Generate new token with `repo` scope
3. Use token as password

---

## 🎯 Quick Commands Reference

```bash
# Check status
git status

# Add all files
git add .

# Commit changes
git commit -m "Your message"

# Push to GitHub
git push

# Pull latest changes
git pull

# View remote URL
git remote -v

# Change remote URL
git remote set-url origin https://github.com/YOUR_USERNAME/adventure-game.git
```

---

## 📋 Pre-Upload Checklist

Before uploading, ensure:
- [ ] `.gitignore` file exists (already created)
- [ ] No sensitive data in code (passwords, API keys)
- [ ] `.env` files are in `.gitignore`
- [ ] `node_modules/` is in `.gitignore`
- [ ] `target/` is in `.gitignore`
- [ ] README.md is complete
- [ ] All documentation files are included

---

## 🎉 After Upload

Once your code is on GitHub, you can:
1. ✅ Deploy to Railway/Vercel (see `DEPLOY_QUICK_START.md`)
2. ✅ Share your repository URL
3. ✅ Collaborate with others
4. ✅ Enable GitHub Actions for CI/CD
5. ✅ Add a custom domain

---

## 🔗 Next Steps

After uploading to GitHub:
1. **Deploy your app:** Follow `DEPLOY_QUICK_START.md`
2. **Add a description:** Edit repository settings on GitHub
3. **Add topics:** Tag your repo (java, spring-boot, react, game)
4. **Create releases:** Tag versions of your app
5. **Enable GitHub Pages:** For documentation

---

## 💡 Pro Tips

- Commit often with clear messages
- Use branches for new features
- Write good commit messages
- Keep sensitive data out of git
- Review changes before committing
- Pull before pushing to avoid conflicts

---

## 🆘 Need Help?

If you encounter issues:
1. Check the error message carefully
2. Verify Git is installed: `git --version`
3. Ensure you're in the correct directory
4. Check your GitHub credentials
5. Try GitHub Desktop if command line fails

---

## ✅ Success!

Once uploaded, your repository URL will be:
```
https://github.com/YOUR_USERNAME/adventure-game
```

Share this URL to show your project to others! 🎮

**Ready to deploy? Open `DEPLOY_QUICK_START.md` next!**
