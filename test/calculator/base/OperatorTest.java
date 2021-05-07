package calculator.base;

import calculator.exception.UnsupportedOperatorException;
import org.junit.jupiter.api.Test;

import static calculator.base.Operator.ADD;
import static calculator.base.Operator.INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperatorTest {
    @Test
    void shouldReturnOperatorAsOperatorType() {
        assertEquals(ADD.getType(), Lexicon.OPERATOR);
    }

    @Test
    void shouldReturnAddOperatorForPlusSign() {
        Character character = '+';

        Operator result = Operator.of(character);

        assertEquals(result, ADD);
    }

    @Test
    void shouldReturnAddInvalidForUnsupportedOperator() {
        Character character = '~';

        Operator result = Operator.of(character);

        assertEquals(result, INVALID);
    }

    @Test
    void shouldReturnAddTwoNumberForAddOperator() {
        Number one = new Number('1');
        Number two = new Number('2');

        Number result = ADD.operate(one, two);

        assertEquals(result.getValue(), 3);
    }

    @Test
    void shouldThrowUnsupportedOperatorExceptionForInvalidOperator() {
        Number one = new Number('1');
        Number two = new Number('2');

        assertThrows(UnsupportedOperatorException.class, () -> INVALID.operate(one, two));
    }
}