package com.rhodes.spreadsheet.rpn;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.rhodes.spreadsheet.data.InvalidInputException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ RPNParserImpl.class })
public class RPNParsingStrategyTest {

    @Mock
    RPNParserImpl parser;

    @Before
    public void setup() throws InvalidInputException {
        when(parser.parse(anyString())).thenReturn("3");
    }

    @Test
    public void testParseData(){
        RPNParsingStrategy strategy = new RPNParsingStrategy();
        List<String> inputRecord = new ArrayList<String>();
        inputRecord.add("1 1 +, 2 2 +, 3 3 +");
        List<List<String>> outputRecords = strategy.parseData(inputRecord);
        assertEquals(outputRecords.get(0).get(0),"2");        
    }
}
