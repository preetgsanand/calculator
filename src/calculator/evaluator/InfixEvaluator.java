package calculator.evaluator;

import calculator.base.Lexical;
import calculator.base.Number;
import calculator.base.Operator;

import java.util.List;
import java.util.Stack;

import static calculator.base.Lexicon.OPERAND;
import static calculator.base.Operator.OPEN_BRACKET;

public class InfixEvaluator implements Executable {
    private final Stack<Operator> operators;
    private final Stack<Number> operands;

    public InfixEvaluator() {
        this.operators = new Stack<>();
        this.operands = new Stack<>();
    }

    @Override
    public Lexical execute(List<Lexical> lexicals) {
        reset();
        lexicals.forEach(this::handleLexical);
        finalizeRemainingOperations();
        return operands.pop();
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
}
