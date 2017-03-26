# spreadsheet
Parses a CSV input file of Reverse Polish Notation (Postfix) expressions and writes the evaluated results to an output CSV file.

REQUIREMENTS: 
    1) JAVA - The project requires a Java SE 1.7 (or higher) installation available on the System PATH
    2) MAVEN - in order to build the project an Apache Mavendinstallation (M2 or higher) is required
              
BUILDING THE PROJECT:
    Run 'mvn clean install -DskipTests' from the command line.
    
RUNNING THE PROGRAM:
    Simply run the run.sh or run.bat script from the command line. The input file located in the data/input directory will be processed. 
    The result will be written to data/output. The paths and names of these two files can be changes in the /config/config.properties
    file.
    
INPUT:
    The program takes a CSV file containing mathematical expressions in Reverse Polish notation/Postfix notation 
    (see https://en.wikipedia.org/wiki/Reverse_Polish_notation ) as input. Additionally, cells in the input 
    spreadsheet can contain (valid!) addresses of other cells in the spreadsheet, according to the well-know
    MS Excel convention, e.g. 'A1, B3, AA23', etc. WARNING:The program is not currently designed to handle or validate circular
    dependencies between cell address references!
    
OUTPUT:
    A well-formed CSV file, corresponding to the input file, containing the results of the RPN expressions or the string '#ERR',
    if the expression was invalid, undefined or not well-formed. Decimal arguments and all standard mathematical operations are
    supported (+, -, *, /, %). WARNING: The program is not currently designed to handle or validate prohibitively large numbers
    which exceed the limitations of the Java BigInteger and/or BigDecimal classes, or the resources of the computer running
    the program. 
    
LOGGING:
    Currently only console logging is enabled. The logging level can me altered in the config/log4j.xml file (set to INFO by default),
    in order to change the verbosity/granularity of the logging messages (ERROR for less, DEBUG for more).
