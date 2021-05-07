package calculator.exception;

public class UnsupportedOperatorException extends RuntimeException {
    public UnsupportedOperatorException(String operator) {
        super(String.format("The following operator is not supported : %s", operator));
    }
}
