package calculator.common;

import java.util.List;

import static java.lang.Double.parseDouble;
import static calculator.common.Lexicon.OPERAND;

public class Number implements Lexical {
    private final Double value;

    public Number(Double value) {
        this.value = value;
    }

    public Number(Character character) {
        this.value = parseDouble(String.valueOf(character));
    }

    public Double getValue() {
        return value;
    }

    public Number add(Number number) {
        return new Number(value + number.getValue());
    }

    public Number subtract(Number number) {
        return new Number(value - number.getValue());
    }

    public Number divide(Number number) {
        return new Number(value / number.getValue());
    }

    public Number multiply(Number number) {
        return new Number(value * number.getValue());
    }

    @Override
    public List<Lexical> combine(Lexical lexical) {
        if (lexical.getType().equals(OPERAND)) {
            Double newValue = value * 10 + (Double) lexical.getValue();
            return List.of(new Number(newValue));
        }
        return List.of(this, lexical);
    }

    @Override
    public Lexicon getType() {
        return OPERAND;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
