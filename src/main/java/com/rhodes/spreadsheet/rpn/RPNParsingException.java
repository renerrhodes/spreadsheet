package com.rhodes.spreadsheet.rpn;

public class RPNParsingException extends Exception {

    private static final long serialVersionUID = 1L;
    
    /**
     *  Parsing exception for Reverse Polish notation parser
     */
    public RPNParsingException() {
        super();
    }
    
    /**
     * Constructor
     *       
     * @param msg
     */
    public RPNParsingException(String msg) {
        super(msg);
    }

    

}
