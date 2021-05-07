package calculator.base;

import java.util.List;
import java.util.Objects;

import static calculator.base.Lexicon.OPERAND;
import static java.lang.Double.parseDouble;

public class Number implements Lexical {
    private final Double value;

    public Number(Double value) {
        this.value = value;
    }

    public Number(String value) {
        this.value = parseDouble(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number = (Number) o;
        return Objects.equals(value, number.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
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
}
