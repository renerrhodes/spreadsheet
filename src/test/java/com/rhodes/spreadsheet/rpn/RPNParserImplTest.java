/**
 * 
 */
package com.rhodes.spreadsheet.rpn;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.data.InvalidInputException;

import junit.framework.TestCase;


/**
 * @author renerhodes
 *
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataManager.class})
public class RPNParserImplTest extends TestCase {
	
	@Mock
	DataManager dm;

	private RPNParserImpl parser = new RPNParserImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setup() throws InvalidInputException {
		// preparing the data by making the mock
		mockStatic(DataManager.class);
		when(DataManager.getInstance()).thenReturn(dm);
		when(dm.getSpreadsheetCellData("b1")).thenReturn("2 b2 + 3 *");
		when(dm.getSpreadsheetCellData("b2")).thenReturn("5");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionInvalidPredingOperator() {
		String input = "+ 5 1";
		String result = "";
		
		result = parser.parse(input);
		
		if (!result.equals("#ERR")) {
			fail("Incorrect result: <" + result + ">");
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionInvalidNoOperator() {
		String input = "8 2";
		String result = "";
		
		result = parser.parse(input);
		
		if (!result.equals("#ERR")) {
			fail("Incorrect result: <" + result + ">");
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionInvalidGarbageInput() {
		String input = "@*!!@X%";
		String result = "";
		
		result = parser.parse(input);
		
		if (!result.equals("#ERR")) {
			fail("Incorrect result: <" + result + ">");
		}
	}

	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpression() {
		String input = "5 1 2 + 4 × + 3 −";
		String result = "";
		
		result = parser.parse(input);
		
		if (!result.equals("14")) {
			fail("Incorrect result: <" + result + ">");
		}
	}

	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */	
	
	@Test
	public void testParserRPNExpressionWithCellAddressInterpolation() {
		String input = "b1 b2 +";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("26")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionDecimalValues() {
		String input = "3.5 3.5 +";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("7.0")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionBigDecimalAddition() {
		String input = "1238126387123 1213669989183 +";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("2451796376306")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionBigDecimalMultiplication() {
		String input = "1238126387123 1213669989183 *";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("1502676838866758280490509")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	*/	
	
	@Test
	public void testParserRPNExpressionBigDecimalSubtraction() {
		String input = "1238126387123 1213669989183 -";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("24456397940")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	@Test
	public void testParserRPNExpressionBigDecimalDivision()  {
		String input = "1238126387123 1213669989183 /";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("1")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.rhodes.spreadsheet.rpn.RPNParserImpl#parse(java.lang.String)}.
	 */
	
	// Based on Calculator BigDecimal.division CEILING rounding setting
	@Test
	public void testParserRPNExpressionBigDecimalDivision1() {
		String input = "16.000 3.000 /";
		String result = "";		

		result = parser.parse(input);
		
		if (!result.equals("5.334")) {
			String msg = "Incorrect result <" + result + ">";
			fail(msg);
		}
	}
}
