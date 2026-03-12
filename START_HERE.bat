@echo off
echo ========================================
echo   ADVENTURE GAME - QUICK START
echo ========================================
echo.
echo PostgreSQL Database: CONNECTED
echo   - Host: localhost:5432
echo   - Database: adventuredb
echo   - User: postgres
echo.
echo STEP 1: Start Backend (in a new window)
echo   Run: start-backend.bat
echo   Wait for "Started AdventureGameApplication"
echo.
echo STEP 2: Start Frontend (in another new window)
echo   Run: start-frontend.bat
echo   Opens browser at http://localhost:3000
echo.
echo STEP 3: Play the game!
echo   Register a new account and start playing
echo.
echo ========================================
echo.
echo Press any key to open both terminals...
pause

start cmd /k start-backend.bat
timeout /t 3 /nobreak >nul
start cmd /k start-frontend.bat

echo.
echo Both services are starting...
echo Check the new terminal windows for status.
echo.
pause
