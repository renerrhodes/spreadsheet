package com.rhodes.spreadsheet.rpn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rhodes.spreadsheet.parsing.ParsingStrategyIF;

public class RPNParsingStrategy implements ParsingStrategyIF {

    // Reverse Polish nation parser 
    private RPNParserImpl parser = new RPNParserImpl();        

    /**
     * Iterates over each entry in each record of the input file and delegates the
     * expression to a Postfix parser and stores the result in a list for a
     * similarly structured output file.
     * 
     * @see com.rhodes.spreadsheet.parsing.ParsingStrategyIF#parseData(java.util.List)
     */
    public List<List<String>> parseData(List<String> inputRecords)
            /*throws ParsingException*/ {
        List<List<String>> outputRecords = new ArrayList<List<String>>();

        // Iterate over records in input file
        for (int i = 0; i < inputRecords.size(); i++) {
            String record = inputRecords.get(i);

            List<String> outputValues = new ArrayList<String>();
            // Iterate over expressions in record
            int pos = 0, end;
            while ((end = record.indexOf(',', pos)) >= 0) {
                // Parse each element in record
                String value = parser.parse(record.substring(pos, end));
                outputValues.add(value);
                pos = end + 1;
            }
            
            // retrieve record after last comma
            String value = parser.parse(record.substring(pos));
            outputValues.add(value);

            List<String> output = new ArrayList<String>();
            Iterator<String> iterator = outputValues.iterator();
            while (iterator.hasNext()) {
                output.add((String) iterator.next());
            }
            outputRecords.add(output);
        }

        return outputRecords;
    }

    // returns an integer corresponding to the alphabetical
    // Excel-style column number of the input, e.g. 1 for 'a',
    // 2 for 'b', etc.
    public static int getExcelColumnNumber(String column) {

        int number = 0;
        char[] name = column.toUpperCase().toCharArray();

        for (int i = 0; i < name.length; i++) {
            number *= 26;
            number += name[i] - 'A' + 1;
        }

        return number;
    }
}