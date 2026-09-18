@echo off
echo Starting Adventure Game Backend...
echo.
echo Backend will run on: http://localhost:8081
echo No database required - uses in-memory storage
echo.
cd backend
call mvn spring-boot:run

pause