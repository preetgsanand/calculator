package calculator.evaluator;

import calculator.base.Lexical;

import java.util.List;

public interface Executable {
    Lexical execute(List<Lexical> lexicals);
}
