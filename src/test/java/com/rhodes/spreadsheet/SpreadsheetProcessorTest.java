package com.rhodes.spreadsheet;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.data.InvalidInputException;
import com.rhodes.spreadsheet.parsing.ParsingStrategyIF;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DataManager.class })
public class SpreadsheetProcessorTest {
    @Mock
    DataManager dm;
    @Mock
    ParsingStrategyIF strategy; 
    
    List<String> inputRecords = new ArrayList<String>();
    List<List<String>> outputRecords = new ArrayList<List<String>>();
    
    @Before
    public void setup() throws InvalidInputException, IOException {
        // preparing the data by making the mock
        mockStatic(DataManager.class);
        when(DataManager.getInstance()).thenReturn(dm);
        when(dm.loadSpreadsheet(anyString())).thenReturn(inputRecords);
        when(strategy.parseData(inputRecords)).thenReturn(outputRecords)
;    }

    @SuppressWarnings("unchecked")
    @Test
    public void testProcessSpreadsheet() throws IOException {
        SpreadsheetProcessor sp = new SpreadsheetProcessor("inpath","outpath", strategy);
        sp.processSpreadsheet();
        verify(dm, times(1)).loadSpreadsheet(anyString());
        verify(strategy, times(1)).parseData(inputRecords);
        verify(dm, times(1)).writeOutput(any(List.class), anyString());
    }
}
