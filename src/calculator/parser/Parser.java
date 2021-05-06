package calculator.parser;

import calculator.expression.InfixExpression;
import calculator.common.Lexical;
import calculator.common.Number;
import calculator.common.Operator;

import static java.lang.Character.isDigit;
import static calculator.common.Operator.INVALID;

public class Parser {
    private final String expression;

    public Parser(String expression) {
        this.expression = expression;
    }

    private boolean isOperand(Character operand) {
        return isDigit(operand);
    }

    private boolean isOperator(Character operator) {
        Operator parsedOperator = Operator.of(operator);
        return !parsedOperator.equals(INVALID);
    }

    private Lexical parse(Character character) {
        if (isOperand(character)) {
            return new Number(character);
        } else if (isOperator(character)) {
            return Operator.of(character);
        }
        throw new RuntimeException("The following expression cannot be parsed");
    }

    public InfixExpression parseToInfix() {
        InfixExpression parsedInfixExpression = new InfixExpression();
        expression.chars()
                .mapToObj(character -> (char) character)
                .map(this::parse)
                .forEach(parsedInfixExpression::add);
        return parsedInfixExpression;
    }
}
