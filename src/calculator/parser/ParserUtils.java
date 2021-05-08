package calculator.parser;

import calculator.base.Operator;

public class ParserUtils {
    private static final String OPERATOR_DELIMITER = "#";

    public static String surroundOperatorsWithDelimiter(String expression) {
        for (Operator operator : Operator.values()) {
            if (operator.getValue() != null) {
                String s = operator.getValue();
                expression = expression.replace(s, String.format("%s%s%s", OPERATOR_DELIMITER, s, OPERATOR_DELIMITER));
            }
        }
        expression = expression.replace(String.format("%s%s", OPERATOR_DELIMITER, OPERATOR_DELIMITER),
                OPERATOR_DELIMITER);
        return expression;
    }

    public static String[] splitDelimitedExpression(String expression) {
        if (expression.length() == 0) {
            return new String[]{};
        }
        return expression.split(OPERATOR_DELIMITER);
    }
}
