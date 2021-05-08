package calculator.evaluator;

import calculator.base.Expression;
import calculator.base.Lexical;
import calculator.base.Number;
import calculator.exception.ExpressionEvaluationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static calculator.base.Operator.ADD;
import static calculator.base.Operator.CLOSE_BRACKET;
import static calculator.base.Operator.MULTIPLY;
import static calculator.base.Operator.OPEN_BRACKET;
import static calculator.base.Operator.SUBTRACT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InfixEvaluatorTest {
    @Test
    void shouldEvaluateAdditionInfixExpression() {
        Expression expression = new Expression(List.of(
                new Number(1.0),
                ADD,
                new Number(2.0)
        ));
        InfixEvaluator infixEvaluator = new InfixEvaluator(expression);

        Lexical result = infixEvaluator.execute();

        assertEquals(new Number(3.0), result);
    }

    @Test
    void shouldEvaluateComplexOperatorInfixExpression() {
        Expression expression = new Expression(List.of(
                new Number(1.0),
                ADD,
                new Number(2.0),
                MULTIPLY,
                OPEN_BRACKET,
                new Number(5.0),
                SUBTRACT,
                new Number(2.0),
                CLOSE_BRACKET
        ));
        InfixEvaluator infixEvaluator = new InfixEvaluator(expression);

        Lexical result = infixEvaluator.execute();

        assertEquals(new Number(7.0), result);
    }

    @Test
    void shouldThrowEvaluationExceptionIfExpressionIsInvalid() {
        Expression expression = new Expression(List.of(
                new Number(1.0),
                ADD
        ));
        InfixEvaluator infixEvaluator = new InfixEvaluator(expression);

        assertThrows(ExpressionEvaluationException.class, infixEvaluator::execute);
    }

    @Test
    void shouldHandleEmptyExpression() {
        Expression expression = new Expression(List.of());
        InfixEvaluator infixEvaluator = new InfixEvaluator(expression);

        Lexical result = infixEvaluator.execute();

        assertEquals(new Number(0.0), result);
    }
}