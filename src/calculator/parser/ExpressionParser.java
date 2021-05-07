package calculator.parser;

import calculator.base.Lexical;
import calculator.base.Number;
import calculator.base.Operator;
import calculator.exception.ExpressionParsingException;
import calculator.expression.InfixExpression;

import java.util.Arrays;

import static calculator.base.Operator.INVALID;
import static calculator.parser.ParserUtils.splitDelimitedExpression;
import static calculator.parser.ParserUtils.surroundOperatorsWithDelimiter;

public class ExpressionParser {
    private final String expression;

    public ExpressionParser(String expression) {
        this.expression = expression;
    }

    private boolean isOperand(String operand) {
        try {
            Double.parseDouble(operand);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private boolean isOperator(String operator) {
        Operator parsedOperator = Operator.of(operator);
        return !parsedOperator.equals(INVALID);
    }

    private Lexical parse(String lexical) {
        if (isOperand(lexical)) {
            return new Number(lexical);
        } else if (isOperator(lexical)) {
            return Operator.of(lexical);
        }
        throw new ExpressionParsingException();
    }

    public InfixExpression toInfix() {
        InfixExpression parsedInfixExpression = new InfixExpression();
        String delimitedExpression = surroundOperatorsWithDelimiter(expression);

        Arrays.stream(splitDelimitedExpression(delimitedExpression))
                .map(this::parse)
                .forEach(parsedInfixExpression::add);
        return parsedInfixExpression;
    }
}
