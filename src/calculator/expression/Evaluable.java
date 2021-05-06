package calculator.expression;

import calculator.common.Lexical;

public interface Evaluable {
    void add(Lexical lexical);
    Lexical evaluate();
}
