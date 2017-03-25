/**
 * 
 */
package com.rhodes.spreadsheet.rpn;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author renerhodes
 *
 */

public class RPNExpressionsTest extends TestCase {
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#ERROR}.
	 */
	@Test
	public void testErrorRegex() {
		String error = "#ERR";
		String integer = "8";
		
		boolean match = error.matches(RPNExpressions.ERROR);
		boolean noMatch = integer.matches(RPNExpressions.ERROR);
		if(!match || noMatch) fail("Error with regex match for #ERR string!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#EXCEL_COLUMN}.
	 */
	@Test
	public void testSpreadsheetAddressRegex() {
		String excelColumn = "AAZZ1";
		String integer = "8";
		
		boolean match = excelColumn.matches(RPNExpressions.CELL_ADDRESS);
		boolean noMatch = integer.matches(RPNExpressions.CELL_ADDRESS);
		if(!match || noMatch) fail("Error with regex match for EXCEL_COLUMN string!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#INTEGER}.
	 */
	@Test
	public void testIntegerRegex() {
		String error = "#ERR";
		String integer = "8";
		
		boolean match = integer.matches(RPNExpressions.INTEGER);
		boolean noMatch = error.matches(RPNExpressions.INTEGER);
		if(!match || noMatch) fail("Error with regex match for INTEGER string!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#OPERATOR}.
	 */
	@Test
	public void testOperandRegex() {
		String addition = "+";
		String subtraction = "-";
		String multiplicationStar = " *";	
		String multiplicationX = " x ";
		String division = " /";
		String modulo = " % ";
		String integer = "8";
		
		boolean matchAddition = addition.matches(RPNExpressions.OPERATOR);
		boolean matchSubtraction = subtraction.matches(RPNExpressions.OPERATOR);
		boolean matchMultiplicationStar = multiplicationStar.matches(RPNExpressions.OPERATOR);
		boolean matchMultiplicationX = multiplicationX.matches(RPNExpressions.OPERATOR);
		boolean matchDivision = division.matches(RPNExpressions.OPERATOR);
		boolean matchModulo = modulo.matches(RPNExpressions.OPERATOR);
		boolean noMatch = integer.matches(RPNExpressions.ERROR);
		if(!matchAddition || !matchSubtraction || !matchMultiplicationStar || !matchMultiplicationX || !matchDivision || !matchModulo || noMatch) fail("Error with regex match for OPERAND string!");
	}

	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 */
	@Test
	public void testIsCellAddressCorrect() {
		String input = "BA7";
		boolean result = RPNExpressions.isCellAddress(input);
		if(!result) fail("Not a valid cell address");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 */
	@Test
	public void testIsCellAddressOperandIncorrect() {
		String input = "+";
		boolean result = RPNExpressions.isCellAddress(input);
		if(result) fail("Incorrect validation of non cell-address string!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 */
	@Test
	public void testIsCellAddressIntegerIncorrect() {
		String input = "5";
		boolean result = RPNExpressions.isCellAddress(input);
		if(result) fail("Incorrect validation of non cell-address string!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 */
	@Test
	public void testIsCellAddressSpaceIncorrect() {
		String input = " ";
		boolean result = RPNExpressions.isCellAddress(input);
		if(result) fail("Incorrect validation of non cell-address string!");
	}
	
	/**
     * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
     */
    @Test
    public void testLongIntegerMisidentifiedAsCellAddressBug() {
        String input = "1238126387123";
        boolean result = RPNExpressions.isCellAddress(input);
        if(result) fail("False positive validation of non cell-address string!");
    }

}
