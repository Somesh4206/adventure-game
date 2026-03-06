@echo off
REM Adventure Game - GitHub Upload Script for Windows
REM This script helps you upload your project to GitHub

echo.
echo 🎮 Adventure Game - GitHub Upload Helper
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Git is not installed!
    echo Please install Git from: https://git-scm.com
    pause
    exit /b 1
)

echo ✅ Git is installed
echo.

REM Check if already initialized
if exist .git (
    echo ⚠️  Git repository already initialized
    echo.
) else (
    echo 📦 Initializing Git repository...
    git init
    echo ✅ Repository initialized
    echo.
)

REM Add all files
echo 📝 Adding files to Git...
git add .
echo ✅ Files added
echo.

REM Create commit
echo 💾 Creating commit...
git commit -m "Initial commit: Adventure Game with Spring Boot and React"
echo ✅ Commit created
echo.

REM Check if remote exists
git remote | findstr origin >nul 2>&1
if not errorlevel 1 (
    echo ⚠️  Remote 'origin' already exists
    echo Current remote URL:
    git remote get-url origin
    echo.
    set /p change_remote="Do you want to change it? (y/n): "
    if /i "%change_remote%"=="y" (
        set /p repo_url="Enter your GitHub repository URL: "
        git remote set-url origin "%repo_url%"
        echo ✅ Remote URL updated
    )
) else (
    echo 🔗 Setting up GitHub remote...
    set /p repo_url="Enter your GitHub repository URL (e.g., https://github.com/username/adventure-game.git): "
    git remote add origin "%repo_url%"
    echo ✅ Remote added
)

echo.
echo 🚀 Pushing to GitHub...
git branch -M main
git push -u origin main

if errorlevel 1 (
    echo.
    echo ❌ Push failed. Common issues:
    echo 1. Check your GitHub credentials
    echo 2. Ensure the repository exists on GitHub
    echo 3. Use a Personal Access Token instead of password
    echo.
    echo 📚 See GITHUB_UPLOAD_GUIDE.md for detailed help
) else (
    echo.
    echo 🎉 SUCCESS! Your project is now on GitHub!
    echo.
    echo 📍 Next steps:
    echo 1. Visit your repository on GitHub
    echo 2. Follow DEPLOY_QUICK_START.md to deploy your app
    echo 3. Share your repository URL with others!
)

echo.
pause
