package com.rhodes.spreadsheet;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.rhodes.spreadsheet.parsing.ParsingStrategyIF;
import com.rhodes.spreadsheet.rpn.RPNParsingStrategy;

/**
* Launcher class for starting the spreadsheet program.
*
* @author  R. Rhodes
* @version 1.0
* @since   2017-03-25
*/
public class Launcher {

    private static final Logger LOGGER = Logger.getLogger(Launcher.class);

    /**
     * Main method for program launcher.
     *
     * @param argv
     *            The first argument is an (optional) input file path.
     *            If it is not set, the value will be taken from config.properties
     *            
     *            The second argument is an (optional) output file path.
     *            If it is not set, the value will be taken from config.properties
     */
    public static void main(final String[] argv) {
        
        long time1 = System.currentTimeMillis();
        
        Configuration properties = new Configuration();
        
        try {
            // Load application properties
            properties.getPropValues();
             
            // Set input file path with 1st command line argument or value from config.properties 
            String inputFilePath = argv.length > 0 ? argv[0] : Configuration.getProperty(Properties.INPUT_FILE);            
            
            LOGGER.info("Input file path is: <" + inputFilePath + ">");
            
            // Set output file path with 2nd command line argument or value from config.properties 
            String outputFilePath = argv.length > 1 ? argv[1] : Configuration.getProperty(Properties.OUTPUT_FILE);
            
            LOGGER.info("Output file path is: <" + outputFilePath + ">");
            
            // Instantiate parsing strategy for postfix parsing
            ParsingStrategyIF strategy = new RPNParsingStrategy();
            
            // Instantiate spreadsheet processor with input- and output paths and concrete strategy implementation
            SpreadsheetProcessor processor = new SpreadsheetProcessor(inputFilePath, outputFilePath, strategy);
            
            // Process data
            processor.processSpreadsheet();
            
            long time2 = System.currentTimeMillis();
            
            LOGGER.info("Finished processing spreadsheet in : "+(time2-time1)+" ms");
            
        } catch (IOException ioe) {
            LOGGER.error("I/O error loading input data or configuration properties:"
                    + ioe.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error processing input data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
