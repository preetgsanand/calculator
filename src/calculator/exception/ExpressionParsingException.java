package calculator.exception;

public class ExpressionParsingException extends RuntimeException {
    public ExpressionParsingException() {
        super("There was a problem in parsing the expression");
    }
}
