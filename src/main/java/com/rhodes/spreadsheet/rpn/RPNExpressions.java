package com.rhodes.spreadsheet.rpn;

public class RPNExpressions {
	
	public static final String ERROR = "#ERR";
	// Excel-style cell address, e.g. 'AA1', 'B2', etc.
	//public static final String CELL_ADDRESS = "\\w+\\d+"; ERROR: false positive for "1238126387123"
	public static final String CELL_ADDRESS = "\\D+\\d+";
    
	//public static final String INTEGER = "^\\d+$";
	public static final String INTEGER = "^[\\-\\s]?\\d+$";
	
	//public static final String DECIMAL = "^[-+]?\\d+(\\.\\d+)+$";
	public static final String DECIMAL = "[-]?\\d+(\\.)+\\d+";
	//public static final String OPERATOR = "^[\\s]*[\\+\\-−\\/\\*×xX\\%][\\s]*$";
	public static final String OPERATOR = "^[\\s]*[\\+\\-−\\/\\*×xX\\%]+[\\s]*$";

		
	public static boolean isCellAddress(String input){
		if (input.matches(CELL_ADDRESS)){
			return true;
		}		
		return false;		
	}
	
	public static String[] splitCellAddressIntoAlphabeticAndNumeric(String input){
		// TODO: fix - do split with one regex command
		String alphabetic = input.split("\\d")[0];
		String numeric = input.split("\\D")[1];
		return new String[] {alphabetic, numeric};
	}

}