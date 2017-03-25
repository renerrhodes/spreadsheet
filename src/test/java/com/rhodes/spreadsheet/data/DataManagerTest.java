package com.rhodes.spreadsheet.data;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

	
	@RunWith(PowerMockRunner.class)
	@PrepareForTest({ DataManager.class })
	public class DataManagerTest {

		// TODO: this doesn't test the dm, it mocks him
		@Mock
		DataManager dm;

		@Before
		public void setup() throws InvalidInputException {
			// preparing the data by making the mock
			mockStatic(DataManager.class);
			when(DataManager.getInstance()).thenReturn(dm);
			when(dm.getSpreadsheetCellData("b1")).thenReturn("5");
		}

		@Test
		public void test() throws InvalidInputException {

			String input = "b1";
			String output = dm.getSpreadsheetCellData(input);
			assertEquals(output, "5");
		}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 * @throws Exception 
	 */
	public void testGetExcelRecordData() throws Exception {
		/*DataManager sm = DataManager.getInstance();
		String data = null;
		
		data = sm.getExcelRecordData("A1");
		if(data == null || data.equals("")) {			
			fail("Error retrieving CSV record data with Excel-style alphabetic column!");
		}
		
		data = sm.getExcelRecordData("B1");
		if(data == null || data.equals("")) {			
			fail("Error retrieving CSV record data with Excel-style alphabetic column!");
		}
		
		data = sm.getExcelRecordData("C3");
		if(data == null || data.equals("")) {			
			fail("Error retrieving CSV record data with Excel-style alphabetic column!");
		}*/
	}

}
