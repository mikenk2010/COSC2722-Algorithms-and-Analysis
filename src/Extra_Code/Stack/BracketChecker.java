package Extra_Code.Stack;

import java.util.Stack;

public class BracketChecker {
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expr.toCharArray()) {
            switch (ch) {
                case '(': case '{': case '[':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String expr = "({[()]})";
        System.out.println("Expression \"" + expr + "\" is balanced: " + isBalanced(expr)); // true
    }
}
