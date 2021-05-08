package calculator.expression;

import calculator.base.Lexical;
import calculator.evaluator.Executable;
import calculator.exception.ExpressionEvaluationException;

import java.util.List;
import java.util.Objects;

public class InfixExpression implements Evaluable {
    private final List<Lexical> lexicals;

    public InfixExpression(List<Lexical> lexicals) {
        this.lexicals = lexicals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfixExpression that = (InfixExpression) o;
        return Objects.equals(lexicals, that.lexicals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexicals);
    }

    @Override
    public Lexical evaluate(Executable evaluator) {
        try {
            return evaluator.execute(lexicals);
        } catch (Exception exception) {
            throw new ExpressionEvaluationException();
        }
    }
}
