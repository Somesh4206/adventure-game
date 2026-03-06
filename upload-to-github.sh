#!/bin/bash

# Adventure Game - GitHub Upload Script
# This script helps you upload your project to GitHub

echo "🎮 Adventure Game - GitHub Upload Helper"
echo "========================================"
echo ""

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo "❌ Git is not installed!"
    echo "Please install Git from: https://git-scm.com"
    exit 1
fi

echo "✅ Git is installed"
echo ""

# Check if already initialized
if [ -d .git ]; then
    echo "⚠️  Git repository already initialized"
    echo ""
else
    echo "📦 Initializing Git repository..."
    git init
    echo "✅ Repository initialized"
    echo ""
fi

# Add all files
echo "📝 Adding files to Git..."
git add .
echo "✅ Files added"
echo ""

# Create commit
echo "💾 Creating commit..."
git commit -m "Initial commit: Adventure Game with Spring Boot and React"
echo "✅ Commit created"
echo ""

# Check if remote exists
if git remote | grep -q origin; then
    echo "⚠️  Remote 'origin' already exists"
    echo "Current remote URL:"
    git remote get-url origin
    echo ""
    read -p "Do you want to change it? (y/n): " change_remote
    if [ "$change_remote" = "y" ]; then
        read -p "Enter your GitHub repository URL: " repo_url
        git remote set-url origin "$repo_url"
        echo "✅ Remote URL updated"
    fi
else
    echo "🔗 Setting up GitHub remote..."
    read -p "Enter your GitHub repository URL (e.g., https://github.com/username/adventure-game.git): " repo_url
    git remote add origin "$repo_url"
    echo "✅ Remote added"
fi

echo ""
echo "🚀 Pushing to GitHub..."
git branch -M main
git push -u origin main

if [ $? -eq 0 ]; then
    echo ""
    echo "🎉 SUCCESS! Your project is now on GitHub!"
    echo ""
    echo "📍 Next steps:"
    echo "1. Visit your repository on GitHub"
    echo "2. Follow DEPLOY_QUICK_START.md to deploy your app"
    echo "3. Share your repository URL with others!"
else
    echo ""
    echo "❌ Push failed. Common issues:"
    echo "1. Check your GitHub credentials"
    echo "2. Ensure the repository exists on GitHub"
    echo "3. Use a Personal Access Token instead of password"
    echo ""
    echo "📚 See GITHUB_UPLOAD_GUIDE.md for detailed help"
fi
