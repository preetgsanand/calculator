package calculator.evaluator;

import calculator.base.Expression;
import calculator.base.Lexical;
import calculator.base.Number;
import calculator.base.Operator;
import calculator.exception.ExpressionEvaluationException;

import java.util.Stack;

import static calculator.base.Lexicon.OPERAND;
import static calculator.base.Operator.OPEN_BRACKET;

public class InfixEvaluator implements Executable {
    private final Stack<Operator> operators;
    private final Stack<Number> operands;
    private final Expression expression;

    public InfixEvaluator(Expression expression) {
        this.expression = expression;
        this.operators = new Stack<>();
        this.operands = new Stack<>();
    }

    @Override
    public Lexical execute() throws ExpressionEvaluationException {
        try {
            reset();
            expression.getLexicals().forEach(this::handleLexical);
            finalizeRemainingOperations();
            return result();
        } catch (Exception exception) {
            throw new ExpressionEvaluationException();
        }
    }

    private void reset() {
        operators.clear();
        operands.clear();
    }

    private void handleLexical(Lexical lexical) {
        if (lexical.getType().equals(OPERAND)) {
            operands.add((Number)lexical);
            return;
        }
        handleOperator((Operator) lexical);
    }

    private void handleOperator(Operator operator) {
        switch (operator) {
            case OPEN_BRACKET:
                operators.push(operator);
                break;
            case CLOSE_BRACKET:
                handleClosingBracket();
                break;
            default:
                performOperation(operator);
                break;

        }
    }

    private void handleClosingBracket() {
        while (!operators.peek().equals(OPEN_BRACKET)) {
            performBinaryOperation();
        }
        operators.pop();
    }

    private void performOperation(Operator operator) {
        performOperationTillOperatorPrecedes(operator);
        operators.push(operator);
    }

    private void performOperationTillOperatorPrecedes(Operator operator) {
        if (!operators.isEmpty() && operators.peek().getPrecedence() <= operator.getPrecedence()) {
            performBinaryOperation();
        }
    }

    private void finalizeRemainingOperations() {
        while (!operators.isEmpty()) {
            performBinaryOperation();
        }
    }

    private void performBinaryOperation() {
        Operator operator = operators.pop();
        Number secondOperand = operands.pop();
        Number firstOperand = operands.pop();
        Number result = operator.operate(firstOperand, secondOperand);
        operands.push(result);
    }

    private Lexical result() {
        if (!operands.isEmpty()) {
            return operands.pop();
        }
        return new Number(0.0);
    }
}
