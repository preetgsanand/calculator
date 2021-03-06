package calculator.parser;

import calculator.base.Lexical;
import calculator.base.Number;
import calculator.base.Operator;
import calculator.exception.InvalidExpressionException;
import calculator.base.Expression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    private Lexical parse(String lexical) throws InvalidExpressionException {
        if (isOperand(lexical)) {
            return new Number(lexical);
        } else if (isOperator(lexical)) {
            return Operator.of(lexical);
        }
        throw new InvalidExpressionException();
    }

    public Expression toInfix() throws InvalidExpressionException {
        String delimitedExpression = surroundOperatorsWithDelimiter(expression);
        String[] splitExpression = splitDelimitedExpression(delimitedExpression);

        List<Lexical> lexicals = Arrays.stream(splitExpression)
                .map(this::parse)
                .collect(Collectors.toList());
        return new Expression(lexicals);
    }
}
