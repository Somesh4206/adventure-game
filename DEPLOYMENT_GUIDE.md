# 🚀 Deployment Guide - Adventure Game

This guide covers multiple deployment options for your full-stack application.

---

## 📋 Pre-Deployment Checklist

### 1. Update Security Settings

**Backend: `application.properties`**
```properties
# Change JWT secret to a strong random value
app.jwt.secret=GENERATE_A_SECURE_256_BIT_SECRET_KEY_HERE

# Update CORS for production domain
app.cors.allowed-origins=https://yourdomain.com,https://www.yourdomain.com
```

**Generate secure JWT secret:**
```bash
# Option 1: Using OpenSSL
openssl rand -base64 64

# Option 2: Using Node.js
node -e "console.log(require('crypto').randomBytes(64).toString('base64'))"
```

### 2. Environment Variables

Create `.env` files for sensitive data:

**Backend `.env`:**
```env
DB_HOST=your-db-host
DB_PORT=3306
DB_NAME=adventuredb
DB_USERNAME=your-db-user
DB_PASSWORD=your-db-password
JWT_SECRET=your-jwt-secret
```

**Frontend `.env.production`:**
```env
REACT_APP_API_URL=https://your-backend-api.com
```

---

## 🌐 Deployment Options

### Option 1: Railway (Recommended - Free Tier Available)

**Advantages:**
- ✅ Free tier available
- ✅ Automatic deployments from GitHub
- ✅ Built-in MySQL database
- ✅ Easy setup
- ✅ HTTPS included

#### Step 1: Prepare Your Code

1. **Create `.railwayignore`:**
```
node_modules/
target/
.git/
*.log
.env
```

2. **Update `application.properties` for Railway:**
```properties
# Use environment variables
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
app.jwt.secret=${JWT_SECRET}
app.cors.allowed-origins=${FRONTEND_URL}
server.port=${PORT:8080}
```

#### Step 2: Deploy Backend

1. Go to [railway.app](https://railway.app)
2. Sign up with GitHub
3. Click "New Project" → "Deploy from GitHub repo"
4. Select your repository
5. Add MySQL database:
   - Click "New" → "Database" → "Add MySQL"
   - Railway auto-creates `DATABASE_URL`
6. Configure environment variables:
   ```
   JWT_SECRET=your-secret-key
   FRONTEND_URL=https://your-frontend.vercel.app
   ```
7. Set root directory: `backend`
8. Build command: `mvn clean package -DskipTests`
9. Start command: `java -jar target/adventure-game-1.0.0.jar`

#### Step 3: Deploy Frontend

1. Go to [vercel.com](https://vercel.com)
2. Import your GitHub repository
3. Configure:
   - Framework: Create React App
   - Root Directory: `frontend`
   - Build Command: `npm run build`
   - Output Directory: `build`
4. Add environment variable:
   ```
   REACT_APP_API_URL=https://your-backend.railway.app
   ```
5. Deploy!

---

### Option 2: Render (Free Tier)

**Advantages:**
- ✅ Free tier with PostgreSQL
- ✅ Auto-deploy from GitHub
- ✅ Easy database management

#### Backend Deployment

1. Go to [render.com](https://render.com)
2. Create "New Web Service"
3. Connect GitHub repository
4. Configure:
   - **Name:** adventure-game-api
   - **Root Directory:** backend
   - **Environment:** Java
   - **Build Command:** `mvn clean package -DskipTests`
   - **Start Command:** `java -jar target/adventure-game-1.0.0.jar`
5. Add PostgreSQL database (free tier)
6. Environment variables:
   ```
   DATABASE_URL=<auto-filled>
   JWT_SECRET=your-secret
   FRONTEND_URL=https://your-frontend.onrender.com
   ```

#### Frontend Deployment

1. Create "New Static Site"
2. Configure:
   - **Root Directory:** frontend
   - **Build Command:** `npm install && npm run build`
   - **Publish Directory:** build
3. Environment variable:
   ```
   REACT_APP_API_URL=https://adventure-game-api.onrender.com
   ```

---

### Option 3: Heroku

**Note:** Heroku no longer has a free tier, but offers affordable plans.

#### Backend

1. Install Heroku CLI
2. Create `Procfile` in backend folder:
```
web: java -jar target/adventure-game-1.0.0.jar
```

3. Deploy:
```bash
cd backend
heroku create adventure-game-api
heroku addons:create jawsdb:kitefin  # MySQL addon
heroku config:set JWT_SECRET=your-secret
heroku config:set FRONTEND_URL=https://your-frontend.herokuapp.com
git push heroku main
```

#### Frontend

```bash
cd frontend
heroku create adventure-game-frontend
heroku buildpacks:set mars/create-react-app
heroku config:set REACT_APP_API_URL=https://adventure-game-api.herokuapp.com
git push heroku main
```

---

### Option 4: AWS (Production-Grade)

**Components:**
- EC2 for backend
- S3 + CloudFront for frontend
- RDS for MySQL database

#### Backend (EC2)

1. **Launch EC2 Instance:**
   - AMI: Amazon Linux 2
   - Instance Type: t2.micro (free tier)
   - Security Group: Allow ports 22, 8080

2. **Install Java & Maven:**
```bash
sudo yum update -y
sudo yum install java-17-amazon-corretto -y
sudo yum install maven -y
```

3. **Deploy Application:**
```bash
# Clone repository
git clone your-repo-url
cd adventure-game/backend

# Build
mvn clean package -DskipTests

# Run with systemd
sudo nano /etc/systemd/system/adventure-game.service
```

**Service file:**
```ini
[Unit]
Description=Adventure Game API
After=network.target

[Service]
Type=simple
User=ec2-user
WorkingDirectory=/home/ec2-user/adventure-game/backend
ExecStart=/usr/bin/java -jar target/adventure-game-1.0.0.jar
Restart=always

[Install]
WantedBy=multi-user.target
```

```bash
sudo systemctl enable adventure-game
sudo systemctl start adventure-game
```

4. **Setup RDS MySQL:**
   - Create RDS MySQL instance
   - Update security groups
   - Update application.properties with RDS endpoint

#### Frontend (S3 + CloudFront)

1. **Build Frontend:**
```bash
cd frontend
REACT_APP_API_URL=https://your-ec2-ip:8080 npm run build
```

2. **Upload to S3:**
```bash
aws s3 sync build/ s3://your-bucket-name --acl public-read
```

3. **Setup CloudFront:**
   - Create distribution pointing to S3 bucket
   - Enable HTTPS
   - Set default root object: index.html

---

### Option 5: Docker + DigitalOcean

#### Create Dockerfiles

**Backend Dockerfile:**
```dockerfile
FROM maven:3.8-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/adventure-game-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**Frontend Dockerfile:**
```dockerfile
FROM node:18-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=$REACT_APP_API_URL
RUN npm run build

FROM nginx:alpine
COPY --from=build /app/build /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

**docker-compose.yml:**
```yaml
version: '3.8'

services:
  mysql:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: ${DB_PASSWORD}
      MYSQL_DATABASE: adventuredb
    volumes:
      - mysql_data:/var/lib/mysql
    ports:
      - "3306:3306"

  backend:
    build: ./backend
    environment:
      DB_HOST: mysql
      DB_PORT: 3306
      DB_NAME: adventuredb
      DB_USERNAME: root
      DB_PASSWORD: ${DB_PASSWORD}
      JWT_SECRET: ${JWT_SECRET}
    ports:
      - "8080:8080"
    depends_on:
      - mysql

  frontend:
    build:
      context: ./frontend
      args:
        REACT_APP_API_URL: http://localhost:8080
    ports:
      - "80:80"
    depends_on:
      - backend

volumes:
  mysql_data:
```

**Deploy to DigitalOcean:**
```bash
# Install Docker on droplet
curl -fsSL https://get.docker.com -o get-docker.sh
sh get-docker.sh

# Deploy
git clone your-repo
cd adventure-game
docker-compose up -d
```

---

### Option 6: Netlify (Frontend) + Railway (Backend)

**Frontend on Netlify:**
1. Connect GitHub to Netlify
2. Configure:
   - Build command: `npm run build`
   - Publish directory: `build`
   - Base directory: `frontend`
3. Environment variable:
   ```
   REACT_APP_API_URL=https://your-backend.railway.app
   ```

**Backend on Railway:** (See Option 1)

---

## 🔒 Production Security Checklist

- [ ] Change default passwords
- [ ] Use environment variables for secrets
- [ ] Enable HTTPS/SSL
- [ ] Update CORS origins
- [ ] Set strong JWT secret
- [ ] Enable database backups
- [ ] Set up monitoring/logging
- [ ] Configure rate limiting
- [ ] Update security headers
- [ ] Use production database (not root user)

---

## 📊 Post-Deployment

### Monitor Your Application

**Backend Health Check:**
```bash
curl https://your-backend-url/actuator/health
```

**Database Connection:**
```bash
mysql -h your-db-host -u username -p adventuredb
```

### Setup CI/CD

**GitHub Actions Example:**
```yaml
name: Deploy

on:
  push:
    branches: [ main ]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Deploy to Railway
        run: railway up
```

---

## 💰 Cost Comparison

| Platform | Backend | Database | Frontend | Total/Month |
|----------|---------|----------|----------|-------------|
| Railway | Free | Free | - | $0 |
| Vercel + Railway | Free | Free | Free | $0 |
| Render | Free | Free | Free | $0 |
| Heroku | $7 | $5 | $7 | $19 |
| AWS | $8 | $15 | $1 | $24 |
| DigitalOcean | $6 | - | - | $6 |

---

## 🎯 Recommended Setup for Beginners

**Best Free Option:**
1. **Backend:** Railway (with MySQL)
2. **Frontend:** Vercel or Netlify
3. **Total Cost:** $0/month

**Best Production Option:**
1. **Backend:** AWS EC2 + RDS
2. **Frontend:** CloudFront + S3
3. **Total Cost:** ~$25/month

---

## 📝 Quick Deploy Commands

### Railway + Vercel (Fastest)

```bash
# 1. Push to GitHub
git add .
git commit -m "Ready for deployment"
git push origin main

# 2. Deploy backend on Railway
# - Go to railway.app
# - Connect GitHub repo
# - Add MySQL database
# - Set environment variables

# 3. Deploy frontend on Vercel
# - Go to vercel.com
# - Import GitHub repo
# - Set REACT_APP_API_URL
# - Deploy!
```

---

## 🆘 Troubleshooting

### CORS Errors
Update `application.properties`:
```properties
app.cors.allowed-origins=https://your-frontend-domain.com
```

### Database Connection Failed
- Check database credentials
- Verify database host/port
- Ensure database exists
- Check firewall rules

### Build Failures
- Verify Java version (17+)
- Check Node version (18+)
- Clear caches: `mvn clean` / `npm cache clean --force`

---

## 📚 Additional Resources

- [Railway Documentation](https://docs.railway.app)
- [Vercel Documentation](https://vercel.com/docs)
- [Render Documentation](https://render.com/docs)
- [AWS Documentation](https://docs.aws.amazon.com)

---

**Need help?** Check the logs on your deployment platform for detailed error messages.
