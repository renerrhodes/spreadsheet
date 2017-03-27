package com.rhodes.spreadsheet;

import java.io.IOException;
import java.util.List;

import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.parsing.ParsingStrategyIF;

/**
* The Spreadsheet Processor class encapsulates the primary tasks involved in processing 
* the spreadsheet: 1) read input 2) parse input 3) write output. This class is decoupled
* from the underlying concrete parser implementation, since it uses the <code>ParserIF</code>
* interface.
* 
* @author  R. Rhodes
* @version 1.0
* @since   2017-03-24
*/
public class SpreadsheetProcessor {
    
    // Parser interface
    ParsingStrategyIF strategy; 
    
    // File path for the input data
    private String inputFilePath;
    // File path for the output data
    private String outputFilePath;
    
    // Parameterized constructor
    public SpreadsheetProcessor(String inpath, String outpath, ParsingStrategyIF parsingStrategy){
        this.inputFilePath = inpath;
        this.outputFilePath = outpath;
        this.strategy = parsingStrategy;
    }
    
    // Spreadsheet processing method
    /**
     * General processing method to encapsulate the input, processing and output tasks.
     * 
     * @throws IOException
     */
    public void processSpreadsheet() throws IOException {
        // Load records from input spreadsheet data
        List<String> inputRecords = DataManager.getInstance().loadSpreadsheet(inputFilePath);       
     
        // Nested list output data for CSV utility
        List<List<String>> outputRecords = strategy.parseData(inputRecords);

        // Write spreadsheet containing result set
        DataManager.getInstance().writeOutput(outputRecords, outputFilePath);
    }
   
}
