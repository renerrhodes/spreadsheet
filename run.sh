#!/bin/sh

#
# Sets optional input and output file paths with first and second positional
# arguments, if one or both are present, otherwise the settings in the 
# config/config.properties file are used. 
# 

infile=$1
outfile=$2

if [ $# -gt 0 ]; then
    echo "$# positional argument(s) found."
else
    echo "No input arguments found. The input and output file paths will be set from config/config.properties."
fi

if [ "$1" != "" ]; then 
   echo "Input file path from command line: ${infile}"   
fi

if [ "$2" != "" ]; then 
    echo "Output file path from command line: ${outfile}"
fi


java -cp target/spreadsheet-1.0-SNAPSHOT.jar com.rhodes.spreadsheet.Launcher $1 $2 

