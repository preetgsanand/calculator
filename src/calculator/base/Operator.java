package calculator.base;

import calculator.exception.UnsupportedOperatorException;

import java.util.Arrays;
import java.util.List;

import static calculator.base.Lexicon.OPERATOR;

public enum Operator implements Lexical {
    OPEN_BRACKET('(', 3),
    CLOSE_BRACKET(')', 3),
    ADD('+', 2),
    SUBTRACT('-', 2),
    DIVIDE('/', 1),
    MULTIPLY('*', 1),
    INVALID(null, null);

    private final Character value;
    private final Integer precedence;

    Operator(Character value, Integer precedence) {
        this.value = value;
        this.precedence = precedence;
    }

    @Override
    public List<Lexical> combine(Lexical lexical) {
        return List.of(this, lexical);
    }

    @Override
    public Lexicon getType() {
        return OPERATOR;
    }

    public Character getValue() {
        return value;
    }

    public Integer getPrecedence() {
        return precedence;
    }

    public Number operate(Number firstNumber, Number secondNumber) {
        switch (this) {
            case ADD: return firstNumber.add(secondNumber);
            case SUBTRACT: return firstNumber.subtract(secondNumber);
            case DIVIDE: return firstNumber.divide(secondNumber);
            case MULTIPLY: return firstNumber.multiply(secondNumber);
        }
        throw new UnsupportedOperatorException(value);
    }

    public static Operator of(Character value) {
        return Arrays.stream(Operator.values())
                .filter(operator -> operator.getValue() != null && operator.getValue().equals(value))
                .findFirst()
                .orElse(INVALID);
    }
}
