package com.rhodes.spreadsheet;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import com.carrotsearch.junitbenchmarks.AbstractBenchmark;
import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.rpn.RPNParsingStrategy;

public class PerformanceTests extends AbstractBenchmark {
	
	private static RPNParsingStrategy strategy = new RPNParsingStrategy();	
	private static List<String> inputData0 = null;	
	private static List<String> inputData1 = null;
	private static List<String> inputData2 = null;
	private static List<String> inputData3 = null;
	private static List<String> inputData4 = null;
	private static List<String> inputData5 = null;	    
	
	@BeforeClass
    public static void prepare() throws Exception
    {
        Configuration properties = new Configuration();
		
		properties.getPropValues();
		
		String inputFile = Configuration.getProperty(Properties.INPUT_FILE);
		
		inputData0 = DataManager.getInstance().loadSpreadsheet(inputFile);
		
		inputData1 = multiplyInputRecords(inputData0, 1);
		inputData2 = multiplyInputRecords(inputData0, 10);
		inputData3 = multiplyInputRecords(inputData0, 100);
		inputData4 = multiplyInputRecords(inputData0, 1000);
		inputData5 = multiplyInputRecords(inputData0, 10000);
		
    }
	
	private static List<String> multiplyInputRecords(List<String> input, long multiplyer){
		List<String> multipliedInput = new ArrayList<String>();
		for (long i=0; i<multiplyer;i++){
			multipliedInput.addAll(input);
		}
		return multipliedInput;
	}
	
	@Rule
	public TestRule benchmarkRun = new BenchmarkRule();
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testDataSet1() throws Exception {
		strategy.parseData(inputData1);
	}
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testDataSet2() throws Exception {
		strategy.parseData(inputData2);
	}
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testDataSet3() throws Exception {
		strategy.parseData(inputData3);
	}
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testDataSet4() throws Exception {
		strategy.parseData(inputData4);
	}
	
	@BenchmarkOptions(benchmarkRounds = 1, warmupRounds = 0)
	@Test
	public void testDataSet5() throws Exception {
		strategy.parseData(inputData5);
	}

}