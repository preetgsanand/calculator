package calculator.common;

import java.util.List;

public interface Lexical {
    List<Lexical> combine(Lexical lexical);
    Lexicon getType();
    Object getValue();
}
