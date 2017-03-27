@REM Sets optional input and output file paths with first and second positional
@REM arguments, if one or both are present, otherwise the settings in the
@REM config/config.properties file are used.

@echo off
set infile=%1

set outfile=%2

set argC=0
for %%x in (%*) do Set /A argC+=1

if %argC% NEQ 0 (
    @echo %argC% positional argument^(s^) found.
) else (
    @echo No input arguments found. The input and output file paths will be set from config/config.properties.
) 

if [%1] NEQ [] (
    @echo Input file from command line %infile%
)

if [%2] NEQ [] (
    @echo Output file from command line %outfile%
)

java -cp .\\target\\spreadsheet-1.0-SNAPSHOT.jar com.rhodes.spreadsheet.Launcher %infile% %outfile%
