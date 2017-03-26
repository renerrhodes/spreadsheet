package com.rhodes.spreadsheet.rpn;

import static com.rhodes.spreadsheet.rpn.RPNExpressions.CELL_ADDRESS;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.DECIMAL;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.ERROR;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.INTEGER;
import static com.rhodes.spreadsheet.rpn.RPNExpressions.OPERATOR;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.rhodes.spreadsheet.data.DataManager;
import com.rhodes.spreadsheet.data.InvalidInputException;
import com.rhodes.spreadsheet.math.CalculationException;
import com.rhodes.spreadsheet.math.Calculator;
import com.rhodes.spreadsheet.parsing.ParserIF;

public class RPNParserImpl implements ParserIF {
    private static final Logger LOGGER = Logger.getLogger(RPNParserImpl.class);

    Scanner sc;
    Calculator calculator = Calculator.getInstance();
    Stack<Number> stack = new Stack<Number>();
	
	public String parse(String input) {
        sc = new Scanner(input.trim());

        while (sc.hasNext()) {

            // CASE 1:integer - add it to the stack!
            if (sc.hasNextInt()) {
                doInteger(sc.nextInt());
                continue;
            } else if (sc.hasNextBigInteger()) {
                doBigInteger(sc.nextBigInteger());
                continue;
            } else if (sc.hasNext(DECIMAL)) {
                doDecimal(sc.nextBigDecimal());
                continue;

                // CASE 2: cell address - resolve recursively to integer & add
                // to stack!
            } else if (sc.hasNext(CELL_ADDRESS)) {
                String cellAddress = sc.next();
                try {
                    doCellAddress(cellAddress);
                } catch (RPNParsingException e) {
                    LOGGER.error(
                            "Error processing expression at cell address Reference <"
                                    + cellAddress + ">");
                    return ERROR;
                }

                // CASE 3: binary operator - evaluate and perform calculation
                // with last two integer on stack!

                // TODO: regex for operator doesn't work in hasNext
            } else if (sc.hasNext("[\\+\\-−\\/\\*×xX\\%]")) {
                try {
                    doCalcuation(sc.next());
                } catch (RPNParsingException e) {
                    LOGGER.error("Parsing error during calculation");
                    return ERROR;
                }
                continue;
            } else {
                LOGGER.error("Ignoring unexpected token in expression! <"
                        + sc.next() + ">");
            }
        }

        // DONE: Return remaining value (solution) or #ERR, if stack is empty
        return (stack.size() == 1) ? stack.pop().toString() : ERROR;
    }

    private void doCalcuation(String operator) throws RPNParsingException {
        if (operator == null || !operator.matches(OPERATOR)) {
            LOGGER.error("Error parsing expression: <" + operator
                    + "> should be an operator. Expression: <" + operator
                    + ">");
            LOGGER.error("Parsing error expected operator but got <" + operator
                    + ">");
            throw new RPNParsingException();
        }
        String last = null, secondToLast = null;
        try {
            char op = operator.charAt(0);

            last = stack.pop().toString();
            secondToLast = stack.pop().toString();
            stack.push(calculator.performCalculation(secondToLast, last, op));
        } catch (EmptyStackException ese) {
            LOGGER.error("Parsing error expected two operands but got <" + last
                    + " and " + secondToLast + ">");
            throw new RPNParsingException();
        } catch (CalculationException e) {
            LOGGER.error("Parsing error expected operator but got <" + operator
                    + ">");
            throw new RPNParsingException();
        }
    }

    private void doCellAddress(String cellAddress) throws RPNParsingException {
        String data;
        try {
            data = DataManager.getInstance().getSpreadsheetCellData(cellAddress)
                    .trim();

            String result = new RPNParserImpl().parse(data);
            // If result is valid (an integer/decimal), add to stack, otherwise
            // write #ERR
            if (result.matches(INTEGER)) {
                stack.push(Integer.valueOf(result));

            // Invalid cell referenced --> entire expression is invalid
            } else if (result.matches(DECIMAL)) {
                stack.push(new BigDecimal(result));
            } else if (result.equals(ERROR)) {
                throw new RPNParsingException();
            }

        } catch (InvalidInputException e) {
            LOGGER.error(
                    "Invalid Cell Address Reference <" + cellAddress + ">");
            throw new RPNParsingException();
        }
    }
    
    private void doBigInteger(BigInteger bigInteger) {
        stack.push(bigInteger);
    }

    private void doDecimal(BigDecimal bigDecimal) {
        stack.push(bigDecimal);
    }

    private void doInteger(int integer) {
        stack.push(integer);
    }
}