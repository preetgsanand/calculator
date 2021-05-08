package calculator.base;

import org.junit.jupiter.api.Test;

import java.util.List;

import static calculator.base.Lexicon.OPERAND;
import static calculator.base.Operator.ADD;
import static org.junit.Assert.assertEquals;

class NumberTest {

    @Test
    public void shouldReturnOneAsTheValueOfNumber() {
        Number one = new Number("1");

        assertEquals(one.getValue(), Double.valueOf(1));
    }

    @Test
    public void shouldReturnOperandAsTypeOfNumber() {
        Number one = new Number("1");

        assertEquals(one.getType(), OPERAND);
    }

    @Test
    void shouldReturnThreeByAddingOneAndTwo() {
        Number one = new Number("1");
        Number two = new Number("2");

        Number result = one.add(two);

        assertEquals(result.getValue(), Double.valueOf(3));
    }

    @Test
    void shouldReturnThreeBySubtractingFiveAndTwo() {
        Number five = new Number("5");
        Number two = new Number("2");

        Number result = five.subtract(two);

        assertEquals(result.getValue(), Double.valueOf(3));
    }


    @Test
    void shouldReturnThreeByMultiplyingOnePointFiveAndTwoPointZero() {
        Number onePointFive = new Number(1.5);
        Number twoPointZero = new Number(2.0);

        Number result = onePointFive.multiply(twoPointZero);

        assertEquals(result.getValue(), Double.valueOf(3));
    }

    @Test
    void shouldReturnThreeByDividingSixAndTwoPointZero() {
        Number six = new Number("6");
        Number twoPointZero = new Number(2.0);

        Number result = six.divide(twoPointZero);

        assertEquals(result.getValue(), Double.valueOf(3));
    }
}