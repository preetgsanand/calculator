package calculator;

import calculator.evaluator.InfixEvaluator;
import calculator.expression.InfixExpression;
import calculator.base.Lexical;
import calculator.parser.ExpressionParser;

import java.util.Scanner;

public class Calculator {
    private final Scanner scanner;
    private String input;

    public Calculator(Scanner scanner) {
        this.scanner = scanner;
    }

    public void takeInput() {
        input = scanner.nextLine();
    }

    public String calculate() {
        ExpressionParser expressionParser = new ExpressionParser(input);
        InfixExpression parsedInfixExpression = expressionParser.toInfix();

        InfixEvaluator infixEvaluator = new InfixEvaluator();
        Lexical result = parsedInfixExpression.evaluate(infixEvaluator);

        return result.getValue().toString();
    }

    public static void main(String[] args) {
        System.out.println("Please enter the expression to evaluate : ");

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(scanner);

        calculator.takeInput();
        String result = calculator.calculate();
        System.out.println("Result is : " + result);
    }
}
