@echo off
echo Starting Adventure Game Backend with PostgreSQL...
echo.
echo Database: PostgreSQL (localhost:5432/adventuredb)
echo Backend will run on: http://localhost:8081
echo.

cd backend
call mvn spring-boot:run

pause
