echo off
rem set port=%1
set port=8282
echo "terminating..."
FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr :%port%') DO TaskKill.exe /F /PID %%P

