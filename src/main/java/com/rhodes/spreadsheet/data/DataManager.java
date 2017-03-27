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

/**
* The singleton DataManager class handles the I/O operations, reading in the input data into
* a list of string and writing the data out to the output directory after processing.
* In addition, the class provides some utility methods for accessing particular
* records in the data set.
* 
* @author  R. Rhodes
* @version 1.0
* @since   2017-03-24
*/

// Singleton
public final class DataManager {
	
	private static final Logger LOGGER = Logger.getLogger(DataManager.class);
	
	// Singleton instance
	private static DataManager instance = new DataManager();
	// Data structure to contain data records
	private static List<String> records = new ArrayList<String>();
	// New line separator
	private static final String NEW_LINE_SEPARATOR = "\n";
        
    // private constructor for singleton
	private DataManager(){
		
	}
	// Accessor method for singleton instance
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
	public String getSpreadsheetCellData(String input) throws InvalidInputException {
	    String record;
	    // Split Excel-style cell address (e.g. 'A1') into alphabetic and numberical parts
		String[] cellAddress = RPNExpressions.splitCellAddressIntoAlphabeticAndNumeric(input);
		// If it is not well-formed, throw an exception
		if (cellAddress == null || cellAddress[0] == null || cellAddress[1] == null){
			throw new InvalidInputException("Invalid Excel-style cell address!");
		}		
		
		int recordNumber = Integer.valueOf(cellAddress[1]) -1;	    
	    String columnName = cellAddress[0];	    
	    int columnNumber;
	    
	    // Access the desired record and transform the alphabetical part into a numerical index
	    try {
	        
	        record = DataManager.getInstance().getRecord(recordNumber);	   	        
	        // Subtract 1 since records start with 0, not 1
	        columnNumber = RPNParsingStrategy.getExcelColumnNumber(columnName) -1;
	        
	    } catch (IndexOutOfBoundsException ioobe){
	        LOGGER.error("Error referencing cell address <" + input + ">");
	        throw new InvalidInputException("Invalid Cell Reference in input data <" + input + ">");
	    }	    
	    // Return the data in the cell specified by the index (column number)
	    String value = getRecordValueFromCSString(record, columnNumber);	    
	    return value;
	}

    /**
     * Utility method to extract an entry from a comma-separated string (record), 
     * starting with index 0.
     * 
     * @param record
     * @param index
     * @return
     * @throws InvalidInputException
     */
    private String getRecordValueFromCSString(String record, int index) throws InvalidInputException {
        int pos = 0, end;
        int count = 0;
        
        while ((end = record.indexOf(',', pos)) >= 0) {
            // Return value from entry matching index
            if(index==count) return record.substring(pos, end);
            count++;
            pos = end + 1;
        }
         
        String lastEntry = record.substring(pos).trim();
        if (index!=count) throw new InvalidInputException("No entry in record matching index <" + index + ">");
        return lastEntry;
    }

    /**
     * Accessor method for data records.
     *  
     * @param recordNumber
     * @return
     * @throws IndexOutOfBoundsException
     */
    private String getRecord(int recordNumber) throws IndexOutOfBoundsException {
        return records.get(recordNumber);
    }

    /**
     * Loads the data line by line from the input file and stores it in the records
     * data structure.
     * 
     * @param inputFilePath
     * @return
     * @throws IOException
     */
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
        
        // Dummy method for record validation (e.g. circular cell address reference checking).
        // Not currently implemented.
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

    /**
     * Preliminary validation method for evaluating well-formedness of input data.
     * 
     * @param records
     */
    private void validateRecords(List<String> records) {
        // not implemented
        LOGGER.warn("Cyclical Spreadsheet address references are not supported");
    }

    /**
     * Writes output in the form of a comma separated value (CSV) 'spreadsheet'
     * to disk in the specified output file path. The output file corresponds
     * exactly to the input file in terms of the number of rows and columns. The
     * contents of the cells in the output file are the resolved expressions
     * contained withing the input file cells, or the string '#ERR', if the data
     * was invalid or not well-formed.
     * 
     * @param outputRecords
     * @param filepath
     */
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
