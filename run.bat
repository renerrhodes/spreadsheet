echo Launching spreadsheet program.

echo %DATE%
echo %TIME%
set datetimef=%date:~-4%_%date:~3,2%_%date:~0,2%__%time:~0,2%_%time:~3,2%_%time:~6,2%
echo %datetimef%

echo off

set arg1=%1
set arg2=%2

java -cp .\\target\\spreadsheet-1.0-SNAPSHOT.jar com.rhodes.spreadsheet.Launcher %arg1% %arg2%
