package Extra_Code.Stack;
import java.util.*;

public class InfixToPostfix {
    public static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
        }
        return -1;
    }

    public static String convert(String expr) {
        StringBuilder output = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (Character.isLetterOrDigit(c)) output.append(c);
            else if (c == '(') stack.push(c);
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') output.append(stack.pop());
                stack.pop(); // pop '('
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) output.append(stack.pop());
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) output.append(stack.pop());
        return output.toString();
    }

    public static void main(String[] args) {
        String infix = "a+b*(c-d)";
        System.out.println("Infix: " + infix);
        System.out.println("Postfix: " + convert(infix)); // Output: abcd-*+
    }
}
