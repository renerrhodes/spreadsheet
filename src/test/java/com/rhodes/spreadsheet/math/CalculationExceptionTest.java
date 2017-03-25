package com.rhodes.spreadsheet.math;

import org.junit.Test;

/**
 * @author RRR
 *
 */
public class CalculationExceptionTest {

    /**
     * @throws CalculationException
     */
    @Test(expected=CalculationException.class)
    public void testInvalidFirstOperand() throws CalculationException {
        String first = "@";
        String second = "9";
        char operator = '/';

        Calculator calculator = Calculator.getInstance();
        calculator.performCalculation(first,
                second, operator);        
    }
    
    /**
     * @throws CalculationException
     */
    @Test(expected=CalculationException.class)
    public void testInvalidSecondOperand() throws CalculationException {
        String first = "1";
        String second = "?";
        char operator = '+';

        Calculator calculator = Calculator.getInstance();
        calculator.performCalculation(first,
                second, operator);
    }
    
    /**
     * @throws CalculationException
     */
    @Test(expected=CalculationException.class)
    public void testInvalidOperator() throws CalculationException {
        String first = "1";
        String second = "3";
        char operator = '(';

        Calculator calculator = Calculator.getInstance();
        calculator.performCalculation(first,
                second, operator);
    }
    
    /**
     * @throws CalculationException
     */
    @Test(expected=CalculationException.class)
    public void testNullFirstOperand() throws CalculationException {
        String first = null;
        String second = "9";
        char operator = '/';

        Calculator calculator = Calculator.getInstance();
        calculator.performCalculation(first,
                second, operator);        
    }
    
    /**
     * @throws CalculationException
     */
    @Test(expected=CalculationException.class)
    public void testNullSecondOperand() throws CalculationException {
        String first = "9";
        String second = null;
        char operator = '/';

        Calculator calculator = Calculator.getInstance();
        calculator.performCalculation(first,
                second, operator);        
    }
}
