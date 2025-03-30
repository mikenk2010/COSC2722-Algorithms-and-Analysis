package Extra_Code.Stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top: " + stack.peek()); // Output: 30
        System.out.println("Popped: " + stack.pop()); // Output: 30
        System.out.println("Stack now: " + stack); // Output: [10, 20]
    }
}
