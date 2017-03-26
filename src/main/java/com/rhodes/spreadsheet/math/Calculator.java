package com.rhodes.spreadsheet.math;

import static com.rhodes.spreadsheet.rpn.RPNExpressions.DECIMAL;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.INTEGER;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.OPERATOR;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author RRR
 *
 */
public final class Calculator {
	
	private static Calculator instance;
	
	private Calculator(){
		
	}
	
	public static Calculator getInstance(){
		if (instance == null){
			instance = new Calculator();
		}
		return instance;
	}
	
	/**
	 * @param first
	 * @param second
	 * @param operator
	 * @return
	 * @throws CalculationException
	 */
	public Number performCalculation(String first, String second, char operator) throws CalculationException {
			if(!isValidExpression(first, second, operator)){
				throw new CalculationException("Invalid expression <" + first + " " + operator + " " + second +">");
			}
			if(first.matches(DECIMAL) || second.matches(DECIMAL)){
				return performDecimalCalculation(first, second, operator);
			}
			else if(first.matches(INTEGER) && second.matches(INTEGER)){
				return performIntegerCalculation(first, second, operator);
			}
			else {
	        	throw new CalculationException("Unrecognized operands: first: <" + first + "> second: <" + second + ">");
	        }
	}
	
	/**
	 * @param first
	 * @param second
	 * @param operator
	 * @return
	 * @throws CalculationException
	 */
	private Number performDecimalCalculation(String first, String second, char operator) throws CalculationException {
		BigDecimal bigDec1 = new BigDecimal(first);
		BigDecimal bigDec2 = new BigDecimal(second);
		if      (operator == '+') return bigDec1.add(bigDec2);
        else if (operator == '-' || operator == '−') return bigDec1.subtract(bigDec2);
        else if (operator == '*' || operator == '×' || operator == 'x' || operator == 'X') return bigDec1.multiply(bigDec2);
        // Division - round towards positive infinity (ceiling) - consider making other options configurable.
        else if (operator == '/') return bigDec1.divide(bigDec2, BigDecimal.ROUND_CEILING);
		else if (operator == '%')    return bigDec1.remainder(bigDec2);
        
        else {
        	throw new CalculationException("Unrecognized operator string: <" + operator + ">");
        }
	}
	
	/**
	 * @param first
	 * @param second
	 * @param operator
	 * @return
	 * @throws CalculationException
	 */
	private Number performIntegerCalculation(String first, String second, char operator) throws CalculationException {
	    try {
		if      (operator == '+') return BigInteger.valueOf(Long.parseLong(first)).add(BigInteger.valueOf(Long.parseLong(second)));
        else if (operator == '-' || operator == '−') return BigInteger.valueOf(Long.parseLong(first)).subtract(BigInteger.valueOf(Long.parseLong(second)));
        else if (operator == '*' || operator == '×' || operator == 'x' || operator == 'X') return BigInteger.valueOf(Long.parseLong(first)).multiply(BigInteger.valueOf(Long.parseLong(second)));
        else if (operator == '/') return BigInteger.valueOf(Long.parseLong(first)).divide(BigInteger.valueOf(Long.parseLong(second)));
        else if (operator == '%') return BigInteger.valueOf(Long.parseLong(first)).mod(BigInteger.valueOf(Long.parseLong(second)));
        else {
        	throw new CalculationException("Unrecognized operator string: <" + operator + ">");
        }
	    } catch (ArithmeticException ae){
	        throw new CalculationException("Arithmetic Exception! Invalid/unsupported expression <" + ae.getMessage() + ">");
	    }
	}
	
	/**
	 * @param first
	 * @param second
	 * @param operator
	 * @return
	 */
	private boolean isValidExpression(String first, String second, char operator) {
		if(first==null || second==null) return false;
	    if((first.matches(INTEGER) || first.matches(DECIMAL)) && (second.matches(INTEGER) || second.matches(DECIMAL)) && String.valueOf(operator).matches(OPERATOR)){
			return true;
		}
		
		return false;
	}

}