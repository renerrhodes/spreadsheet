package com.rhodes.spreadsheet.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.rhodes.spreadsheet.rpn.RPNExpressions;
import com.rhodes.spreadsheet.rpn.RPNParsingStrategy;

public class DataManager {
	
	// singleton (yet not final, to allow mocking for JUnit tests
	private static final Logger LOGGER = Logger.getLogger(DataManager.class);
	
	private static DataManager instance = new DataManager();
	
	private static List<String> records = new ArrayList<String>();
	
	private static final String NEW_LINE_SEPARATOR = "\n";
    
    
    // private constructor for singleton
	private DataManager(){
		
	}
	
	public static DataManager getInstance(){
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}
	
	
	
	/**
	 * Takes an Excel-style spreadsheet cell address, e.g. 'A1, B3, etc.' and
	 * returns the value of the cell contents in the input spreadsheet or
	 * throws an exception, if the address is invalid.
	 * 
	 * @param input - the cell address
	 * @return      - the cell data
	 * @throws InvalidInputException
	 */
	// TODO: works with both capital and lowercase?
	public String getSpreadsheetCellData(String input) throws InvalidInputException {
	    String record;
	    
		String[] cellAddress = RPNExpressions.splitCellAddressIntoAlphabeticAndNumeric(input);
		
		// TODO: throw exception in utiliy method
		if (cellAddress == null || cellAddress[0] == null || cellAddress[1] == null){
			throw new InvalidInputException("Invalid Excel-style cell address!");
		}		
		
		int recordNumber = Integer.valueOf(cellAddress[1]) -1;
	    
	    String columnName = cellAddress[0];
	    
	    int columnNumber;
	    
	    try {
	        
	        record = DataManager.getInstance().getRecord(recordNumber);	        
	     // Subtract 1 since records start with 0, not 1
	        
	        columnNumber = RPNParsingStrategy.getExcelColumnNumber(columnName) -1;
	        
	    } catch (IndexOutOfBoundsException ioobe){
	        LOGGER.error("Error referencing cell address <" + input + ">");
	        throw new InvalidInputException("Invalid Cell Reference in input data <" + input + ">");
	    }
	    
	    
	    String value = getRecordValueFromCSString(record, columnNumber);
	    
	    return value;
	}

    private String getRecordValueFromCSString(String record, int index) throws InvalidInputException {
        int pos = 0, end;
        int count = 0;
        
        while ((end = record.indexOf(',', pos)) >= 0) {
            // Return value from entry matching index
            if(index==count) return record.substring(pos, end);
            count++;
            pos = end + 1;
        }
        // 
        String lastEntry = record.substring(pos).trim();
        if (index!=count) throw new InvalidInputException("No entry in record matching index <" + index + ">");
        return lastEntry;
    }

    private String getRecord(int recordNumber) throws IndexOutOfBoundsException {
        return records.get(recordNumber);
    }

    public List<String> loadSpreadsheet(String inputFilePath) throws IOException {
        
        List<String> inputRecords = new ArrayList<String>();
        
        BufferedReader bufferedReader = null;
        
        try {
                
        bufferedReader = new BufferedReader(new FileReader(inputFilePath));
        
        String record = null;
        
        while ((record = bufferedReader.readLine()) != null) {
            inputRecords.add(record);
         }  
        DataManager.records = (inputRecords);
        
        validateRecords(records);
        
        return inputRecords;
        
        } finally {
            try { 
                bufferedReader.close();
            } catch (Exception e){
                LOGGER.error("Error closing BufferedReader/FileReader <" + e.getMessage() +">");
            }
        }
    }

    private void validateRecords(List<String> records) {
        // TODO: cyclical cell reference validation
        /*Map<String> map = new HashMap<String>();
        
        Iterator iterator = records.iterator();
        StringTokenizer tokenizer;
        int column, row = 0;
        while(iterator.hasNext()){
            tokenizer = new StringTokenizer(",", (String)iterator.next());
            row++;
            while(tokenizer.hasMoreElements()){
                String token = (String)tokenizer.nextToken();
                if(token.matches(RPNExpressions.CELL_ADDRESS)){
                    String addressee = token.mateches(RPNExpressions.CELL_ADDRESS.s)
                    
                }
                column++;
            }
        }*/
        
        
    }

    public void writeOutput(List<List<String>> outputRecords, String filepath) {
        FileWriter fileWriter = null;
        
        try {
            
            //initialize FileWriter object in overwrite mode
            fileWriter = new FileWriter(filepath, false);
            
            
            Iterator<List<String>> recordIterator = outputRecords.iterator();
            while(recordIterator.hasNext()) {
                List<String> record = (List<String>)recordIterator.next();
                
                Iterator<String> entryIterator = record.iterator();
                StringBuilder stringBuilder = new StringBuilder();
                while(entryIterator.hasNext()){
                    stringBuilder.append((String)entryIterator.next() + ",");
                }
                // Remove trailing comma and write to output
                fileWriter.write(stringBuilder.toString().substring(0,stringBuilder.length()-1));
                fileWriter.write(NEW_LINE_SEPARATOR);
            }
            
            fileWriter.flush();            

            LOGGER.info("Output file <" + filepath + "> was created successfully!");
            
        } catch (Exception e) {
            LOGGER.error("Error in writing output <" + e.getMessage() + ">");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ioe) {
                LOGGER.error("Error while closing fileWriter <" + ioe.getMessage() + ">");
            }
        }
    }
	
	
}
