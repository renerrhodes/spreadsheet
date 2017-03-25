package com.rhodes.spreadsheet.rpn;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.After;
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

        // TODO: this doesn't test the dm, it mocks him
        @Mock
        RPNParserImpl parser;

        @Before
        public void setup() throws InvalidInputException {
            // preparing the data by making the mock
            //mockStatic(RPNParserImpl.class);
            //when(DataManager.getInstance()).thenReturn(dm);
            when(parser.parse(anyString())).thenReturn("3");
        }

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
