package calculator.base;

import java.util.List;
import java.util.Objects;

public class Expression {
    private final List<Lexical> lexicals;

    public Expression(List<Lexical> lexicals) {
        this.lexicals = lexicals;
    }

    public List<Lexical> getLexicals() {
        return lexicals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expression that = (Expression) o;
        return Objects.equals(lexicals, that.lexicals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexicals);
    }
}
