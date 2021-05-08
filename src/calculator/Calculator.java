package calculator;

import calculator.evaluator.InfixEvaluator;
import calculator.base.Expression;
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
        try {
            ExpressionParser expressionParser = new ExpressionParser(input);
            Expression infixExpression = expressionParser.toInfix();

            InfixEvaluator infixEvaluator = new InfixEvaluator(infixExpression);
            Lexical result = infixEvaluator.execute();

            return result.getValue().toString();
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println("Please enter the expression to evaluate : ");

        Scanner scanner = new Scanner(System.in);
        Calculator calculator = new Calculator(scanner);

        calculator.takeInput();
        String result = calculator.calculate();
        System.out.println("Result : " + result);
    }
}
