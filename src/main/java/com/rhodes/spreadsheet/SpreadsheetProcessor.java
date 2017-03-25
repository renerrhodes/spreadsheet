package com.rhodes.spreadsheet;

import java.io.IOException;
import java.util.List;

import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.parsing.ParsingStrategyIF;

public class SpreadsheetProcessor {
    
    ParsingStrategyIF strategy; 
    
    private String inputFilePath;
    
    private String outputFilePath;
    
    public SpreadsheetProcessor(String inpath, String outpath, ParsingStrategyIF parsingStrategy){
        this.inputFilePath = inpath;
        this.outputFilePath = outpath;
        this.strategy = parsingStrategy;
    }
    
    public void processSpreadsheet() throws IOException {
        // Load records from input spreadsheet data
        List<String> inputRecords = DataManager.getInstance().loadSpreadsheet(inputFilePath);       
     
        // Nested list output data for CSV utility
        List<List<String>> outputRecords = strategy.parseData(inputRecords);

        // Write spreadsheet containing result set
        DataManager.getInstance().writeOutput(outputRecords, outputFilePath);
    }
   
}
