package calculator.expression;

import calculator.base.Lexical;
import calculator.evaluator.Executable;

public interface Evaluable {
    Lexical evaluate(Executable evaluator);
}
