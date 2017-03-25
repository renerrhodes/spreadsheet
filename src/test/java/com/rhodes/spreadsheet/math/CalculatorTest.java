/**
 * 
 */
package com.rhodes.spreadsheet.math;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

/**
 * @author RRR
 *
 */
public class CalculatorTest {

    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformIntegerCalculationAddition()
            throws CalculationException {
        String first = "5";
        String second = "-5";
        char operator = '+';

        Calculator calculator = Calculator.getInstance();
        BigInteger result = (BigInteger) calculator.performCalculation(first,
                second, operator);
        if (result.intValue() != 0) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformIntegerCalculationSubtraction()
            throws CalculationException {
        String first = "7";
        String second = "3";
        char operator = '-';

        Calculator calculator = Calculator.getInstance();
        BigInteger result = (BigInteger) calculator.performCalculation(first,
                second, operator);
        if (result.intValue() != 4) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformIntegerCalculationMultiplication()
            throws CalculationException {
        String first = "6";
        String second = "6";
        char operator = '*';

        Calculator calculator = Calculator.getInstance();
        BigInteger result = (BigInteger) calculator.performCalculation(first,
                second, operator);
        if (result.intValue() != 36) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformIntegerCalculationDivision()
            throws CalculationException {
        String first = "49";
        String second = "7";
        char operator = '/';

        Calculator calculator = Calculator.getInstance();
        BigInteger result = (BigInteger) calculator.performCalculation(first,
                second, operator);
        if (result.intValue() != 7) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformDecimalCalculationAddition()
            throws CalculationException {
        String first = "350.50";
        String second = "759.50";
        char operator = '+';

        Calculator calculator = Calculator.getInstance();
        BigDecimal result = (BigDecimal) calculator.performCalculation(first,
                second, operator);
        if (!result.equals(new BigDecimal("1110.00"))) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformDecimalCalculationSubtraction()
            throws CalculationException {
        String first = "5.6754";
        String second = "0.5643";
        char operator = '-';

        Calculator calculator = Calculator.getInstance();
        BigDecimal result = (BigDecimal) calculator.performCalculation(first,
                second, operator);
        if (!result.equals(new BigDecimal("5.1111"))) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformDecimalCalculationMultiplication()
            throws CalculationException {
        String first = "5.5";
        String second = "7.289";
        char operator = '*';

        Calculator calculator = Calculator.getInstance();
        BigDecimal result = (BigDecimal) calculator.performCalculation(first,
                second, operator);
        if (!result.equals(new BigDecimal("40.0895"))) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformDecimalCalculationDivision()
            throws CalculationException {
        String first = "9.99999";
        String second = "2.22222";
        char operator = '/';

        Calculator calculator = Calculator.getInstance();
        BigDecimal result = (BigDecimal) calculator.performCalculation(first,
                second, operator);
        if (!result.equals(new BigDecimal("4.50000"))) {
            fail("Incorrect result <" + result + ">");
        }
    }
    
    /**
     * @throws CalculationException
     */
    @Test
    public void testPerformIntegerCalculationAdditionHugeNumber()
            throws CalculationException {
        String first = "99999";
        String second = "99999";
        char operator = '+';

        Calculator calculator = Calculator.getInstance();
        BigInteger result = (BigInteger) calculator.performCalculation(first,
                second, operator);
        if (!result.equals(new BigInteger("199998"))) {
            fail("Incorrect result <" + result + ">");
        }
    }

}
