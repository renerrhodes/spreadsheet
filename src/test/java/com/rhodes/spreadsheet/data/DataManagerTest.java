package com.rhodes.spreadsheet.data;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

	
	@RunWith(PowerMockRunner.class)
	@PrepareForTest({ DataManager.class })
	public class DataManagerTest {

	@Test
	public void test() throws InvalidInputException {
		fail("not implemented!");
	}
	
	/**
	 * Test method for {@link com.rhodes.spreadsheet.rpn.RPNExpressions#isCellAddress(java.lang.String)}.
	 * @throws Exception 
	 */
	public void testGetExcelRecordData() throws Exception {
		// TODO: implement DataManager Unit tests!
	    
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
