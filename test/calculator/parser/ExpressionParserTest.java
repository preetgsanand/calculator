package calculator.parser;

import calculator.base.Number;
import calculator.exception.InvalidExpressionException;
import calculator.expression.InfixExpression;
import org.junit.jupiter.api.Test;

import java.util.List;

import static calculator.base.Operator.ADD;
import static calculator.base.Operator.CLOSE_BRACKET;
import static calculator.base.Operator.MULTIPLY;
import static calculator.base.Operator.OPEN_BRACKET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ExpressionParserTest {
    @Test
    void shouldConvertAdditionExpressionToInfixNotation() {
        String expression = "1+2";
        ExpressionParser expressionParser = new ExpressionParser(expression);

        InfixExpression result = expressionParser.toInfix();
        InfixExpression expected = new InfixExpression(
                List.of(
                        new Number(1.0),
                        ADD,
                        new Number(2.0)
                ));

        assertEquals(expected, result);
    }

    @Test
    void shouldConvertMultipleOperatorExpressionToInfixNotation() {
        String expression = "1+2*(3+4)";
        ExpressionParser expressionParser = new ExpressionParser(expression);

        InfixExpression result = expressionParser.toInfix();
        InfixExpression expected = new InfixExpression(
                List.of(
                        new Number(1.0),
                        ADD,
                        new Number(2.0),
                        MULTIPLY,
                        OPEN_BRACKET,
                        new Number(3.0),
                        ADD,
                        new Number(4.0),
                        CLOSE_BRACKET
                ));

        assertEquals(expected, result);
    }

    @Test
    void shouldThrowExceptionIfExpressionHasUnsupportedOperator() {
        String expression = "1~1";
        ExpressionParser expressionParser = new ExpressionParser(expression);

        assertThrows(InvalidExpressionException.class, expressionParser::toInfix);
    }


    @Test
    void shouldThrowExceptionIfExpressionIsInvalid() {
        String expression = "NULL";
        ExpressionParser expressionParser = new ExpressionParser(expression);

        assertThrows(InvalidExpressionException.class, expressionParser::toInfix);
    }

    @Test
    void shouldHandleEmptyExpression() {
        String expression = "";
        ExpressionParser expressionParser = new ExpressionParser(expression);

        InfixExpression result = expressionParser.toInfix();
        InfixExpression expected = new InfixExpression(List.of());

        assertEquals(expected, result);
    }
}