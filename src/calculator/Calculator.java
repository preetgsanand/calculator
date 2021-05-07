package calculator;

import calculator.evaluator.InfixEvaluator;
import calculator.expression.InfixExpression;
import calculator.base.Lexical;
import calculator.parser.Parser;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        System.out.println("Please enter the expression to evaluate : ");

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        Parser parser = new Parser(expression);
        InfixExpression parsedInfixExpression = parser.parseToInfix();

        InfixEvaluator infixEvaluator = new InfixEvaluator();
        Lexical result = parsedInfixExpression.evaluate(infixEvaluator);
        System.out.println("Result is : " + result.getValue());
    }
}
