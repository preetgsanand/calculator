package calculator.expression;

import calculator.base.Lexical;
import calculator.evaluator.Executable;
import calculator.exception.ExpressionEvaluationException;

import java.util.Stack;

public class InfixExpression implements Evaluable {
    private final Stack<Lexical> lexicals;

    public InfixExpression() {
        this.lexicals = new Stack<>();
    }

    @Override
    public void add(Lexical lexical) {
        if (lexicals.isEmpty()) {
            lexicals.add(lexical);
            return;
        }
        Lexical lastLexical = lexicals.pop();
        lexicals.addAll(lastLexical.combine(lexical));
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
