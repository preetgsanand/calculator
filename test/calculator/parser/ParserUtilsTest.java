package calculator.parser;

import org.junit.jupiter.api.Test;

import static calculator.parser.ParserUtils.splitDelimitedExpression;
import static calculator.parser.ParserUtils.surroundOperatorsWithDelimiter;
import static org.junit.Assert.assertEquals;

class ParserUtilsTest {
    @Test
    void shouldSurroundAddOperatorInExpressionWithHashDelimiter() {
        String expression = "1+2";

        String result = surroundOperatorsWithDelimiter(expression);

        assertEquals( "1#+#2", result);
    }

    @Test
    void shouldSurroundAddAndSubtractOperatorInExpressionWithHashDelimiter() {
        String expression = "1+2-3";

        String result = surroundOperatorsWithDelimiter(expression);

        assertEquals( "1#+#2#-#3", result);
    }

    @Test
    void shouldSurroundAddAndSubtractAndBracketOperatorInExpressionWithHashDelimiter() {
        String expression = "2*(3+4)";

        String result = surroundOperatorsWithDelimiter(expression);

        assertEquals( "2#*#(#3#+#4#)#", result);
    }

    @Test
    void shouldSurroundMultipleAddOperatorsWithHashDelimiter() {
        String expression = "2+3+4";

        String result = surroundOperatorsWithDelimiter(expression);

        assertEquals( "2#+#3#+#4", result);
    }

    @Test
    void shouldSplitDelimitedExpressionIntoLexicals() {
        String expression = "2#*#(#3#+#4#)#";

        String[] result = splitDelimitedExpression(expression);

        assertEquals(new String[]{"2", "*", "(", "3", "+", "4", ")"}, result);
    }
}