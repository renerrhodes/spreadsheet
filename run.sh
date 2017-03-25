#!/bin/sh

#arg1=$1
#arg2=$2

##directory where jar file is located    
dir=./target/

##jar file name
jar_name=spreadsheet-1.0.-SNAPSHOT.jar

## Perform some validation on input arguments, one example below
#if [ -z "$1" ] || [ -z "$2" ]; then
#        echo "Missing arguments, exiting.."
#        echo "Usage : $0 arg1 arg2"
#        exit 1
#fi

java -jar $dir/$jar_name com.rhodes.spreadsheet.Launcher