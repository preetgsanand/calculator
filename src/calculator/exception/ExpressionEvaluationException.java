package calculator.exception;

public class ExpressionEvaluationException extends RuntimeException {
    public ExpressionEvaluationException() {
        super("There was a problem in evaluating the expression");
    }
}
