import java.util.*;

/**
 * Author:  Joshua Cohen
 * Date:    8 May 2025
 * Class:   CMSC 315 6383
 * Purpose: Do Math! ... (Directions per Auto-Graded Assignment)
 *          Modify LiveExample 20.12, EvaluateExpression.java to add operators ^ for exponent and % for remainder.
 *          For example, 3 ^ 2 is 9 and 3 % 2 is 1. The ^ operator has the highest precedence and the % operator
 *          has the same precedence as the * and / operators. Your program should prompt the user to enter an expression.
 */

public class Exercise {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter expression: ");
        String expr = input.nextLine();

        try {
            int result = evaluate(expr);
            System.out.println(expr + " = " + result);
        } catch (Exception ex) {
            System.out.println("Invalid expression");
        }
    }

    public static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        expr = expr.replaceAll("\\s+", "");

        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);

            if (ch == '(') {
                ops.push(ch);
            } else if (Character.isDigit(ch)) {
                int val = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    val = val * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                values.push(val);
                i--;
            } else if (ch == ')') {
                while (ops.peek() != '(') {
                    applyTopOperator(values, ops);
                }
                ops.pop();
            } else if (isOperator(ch)) {
                while (!ops.isEmpty()
                        && ops.peek() != '('
                        && precedence(ops.peek()) >= precedence(ch)) {
                    applyTopOperator(values, ops);
                }
                ops.push(ch);
            } else {
                throw new IllegalArgumentException("Unexpected character: " + ch);
            }
        }

        while (!ops.isEmpty()) {
            applyTopOperator(values, ops);
        }

        return values.pop();
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }

    private static int precedence(char op) {
        switch (op) {
            case '^':
                return 3;
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return 0;
        }
    }

    private static void applyTopOperator(Stack<Integer> values, Stack<Character> ops) {
        char op = ops.pop();
        int right = values.pop();
        int left = values.pop();
        int result;

        switch (op) {
            case '+':
                result = left + right;
                break;
            case '-':
                result = left - right;
                break;
            case '*':
                result = left * right;
                break;
            case '/':
                result = left / right;
                break;
            case '%':
                result = left % right;
                break;
            case '^':
                result = (int) Math.pow(left, right);
                break;
            default:
                throw new IllegalStateException("Unknown op " + op);
        }
        values.push(result);
    }
}