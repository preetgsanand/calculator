package calculator.expression;

import calculator.common.Lexical;
import calculator.common.Number;
import calculator.common.Operator;

import java.util.Stack;

import static calculator.common.Lexicon.OPERAND;
import static calculator.common.Operator.OPEN_BRACKET;

public class InfixExpression implements Evaluable {
    private final Stack<Lexical> lexicals;
    private final Stack<Operator> operators;
    private final Stack<Number> operands;

    public InfixExpression() {
        this.lexicals = new Stack<>();
        this.operands = new Stack<>();
        this.operators = new Stack<>();
    }

    @Override
    public void add(Lexical lexical) {
        if (lexicals.isEmpty()) {
            lexicals.add(lexical);
            return;
        }
        Lexical lastLexical = lexicals.pop();
        lexicals.addAll(lastLexical.combine(lexical));
    }

    @Override
    public Lexical evaluate() {
        reset();
        try {
            lexicals.forEach(this::handleLexical);
            finalizeRemainingOperations();
            return operands.pop();
        } catch (Exception exception) {
            throw new RuntimeException("The following expression cannot be evaluated");
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
        if (!operators.isEmpty() && operators.peek().getPrecedence() >= operator.getPrecedence()) {
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
