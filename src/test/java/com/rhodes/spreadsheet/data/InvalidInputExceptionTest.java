package com.rhodes.spreadsheet.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DataManager.class })
public class InvalidInputExceptionTest {
    
    /**
     * @throws InvalidInputException
     */
    @Test(expected=InvalidInputException.class)
    public void testDataManagerSpreadsheetCellData() throws InvalidInputException {
        DataManager dm = DataManager.getInstance();
        dm.getSpreadsheetCellData("a4");
    }

}
