package calculator.exception;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException() {
        super("The expression entered is invalid");
    }
}
