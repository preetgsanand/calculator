package calculator.expression;

import calculator.base.Lexical;
import calculator.evaluator.Executable;

public interface Evaluable {
    void add(Lexical lexical);
    Lexical evaluate(Executable evaluator);
}
