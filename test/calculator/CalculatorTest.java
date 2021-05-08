package calculator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class CalculatorTest {

    private void setSystemWithExpression(String expression) {
        InputStream in = new ByteArrayInputStream(expression.getBytes());
        System.setIn(in);
    }

    @Test
    void shouldEvaluateAdditionExpression() {
        setSystemWithExpression("1+2");
        Calculator calculator = new Calculator(new Scanner(System.in));

        calculator.takeInput();
        String result = calculator.calculate();

        assertEquals("3.0", result);
    }

    @Test
    void shouldEvaluateAdditionSubtractionExpression() {
        setSystemWithExpression("1+2-3");
        Calculator calculator = new Calculator(new Scanner(System.in));

        calculator.takeInput();
        String result = calculator.calculate();

        assertEquals("0.0", result);
    }

    @Test
    void shouldEvaluateExpressionWithBrackets() {
        setSystemWithExpression("1+(2-3)");
        Calculator calculator = new Calculator(new Scanner(System.in));

        calculator.takeInput();
        String result = calculator.calculate();

        assertEquals("0.0", result);
    }

    @Test
    void shouldEvaluateExpressionFollowingBODMAS() {
        setSystemWithExpression("1+(2-3)*4/5+(2-5)");
        Calculator calculator = new Calculator(new Scanner(System.in));

        calculator.takeInput();
        String result = calculator.calculate();

        assertEquals("-2.8", result);
    }

    @Test
    void shouldEvaluateExpressionWithDecimalNumbers() {
        setSystemWithExpression("1.0+(2.8-3)*4.5/5+(2-5)");
        Calculator calculator = new Calculator(new Scanner(System.in));

        calculator.takeInput();
        String result = calculator.calculate();

        assertEquals("-2.18", result);
    }
}