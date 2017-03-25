package com.rhodes.spreadsheet.parsing;

import java.util.List;

public interface ParsingStrategyIF {
    public List<List<String>> parseData(List<String> inputRecords);
}
