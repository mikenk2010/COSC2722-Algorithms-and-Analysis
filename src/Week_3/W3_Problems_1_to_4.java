package Week_3;

import java.util.*;

public class W3_Problems_1_to_4 {

    /**
     * Problem 1: Detect and remove loop in a singly linked list using Floyd's Cycle Detection
     */
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void removeLoop(Node head) {
        Node slow = head, fast = head;
        boolean hasLoop = false;

        // Detect loop
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (hasLoop) {
            slow = head;
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
            fast.next = null; // remove loop
        }
    }

    /**
     * Problem 2: Josephus problem using Circular Linked List
     */
    static class CircularNode {
        int data;
        CircularNode next;
        CircularNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static int josephus(int n, int k) {
        CircularNode head = new CircularNode(1);
        CircularNode prev = head;
        for (int i = 2; i <= n; i++) {
            CircularNode node = new CircularNode(i);
            prev.next = node;
            prev = node;
        }
        prev.next = head; // Complete the circle

        CircularNode ptr = head;
        CircularNode prePtr = prev;

        while (ptr.next != ptr) {
            for (int i = 1; i < k; i++) {
                prePtr = ptr;
                ptr = ptr.next;
            }
            prePtr.next = ptr.next; // Remove kth person
            ptr = ptr.next;
        }
        return ptr.data; // Last survivor
    }

    /**
     * Problem 3: ATM queue simulation for max and average waiting time
     */
    public static int[] atmQueue(int[] arrival, int[] duration) {
        int currentTime = 0;
        int totalWaiting = 0;
        int maxWaiting = 0;

        for (int i = 0; i < arrival.length; i++) {
            if (arrival[i] > currentTime) {
                currentTime = arrival[i];
            }
            int waiting = currentTime - arrival[i];
            totalWaiting += waiting;
            maxWaiting = Math.max(maxWaiting, waiting);
            currentTime += duration[i];
        }

        int averageWaiting = totalWaiting / arrival.length;
        return new int[]{maxWaiting, averageWaiting};
    }

    /**
     * Problem 4: Check for balanced brackets using Stack
     */
    public static boolean isBalanced(String expr) {
        Stack<Character> stack = new Stack<>();
        for (char ch : expr.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else if (ch == ')' || ch == '}' || ch == ']') {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if ((ch == ')' && open != '(') ||
                        (ch == '}' && open != '{') ||
                        (ch == ']' && open != '[')) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // Problem 1: Remove loop
        System.out.println("\nProblem 1");
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next; // loop
        removeLoop(head);
        System.out.print("Linked list after loop removal: ");
        Node temp = head;
        for (int i = 0; i < 10 && temp != null; i++) { // print limited nodes to confirm no loop
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();

        // Problem 2: Josephus
        System.out.println("\nProblem 2");
        System.out.println("Josephus Problem (n=7, k=3): Survivor is person " + josephus(7, 3));

        // Problem 3: ATM Queue Simulation
        System.out.println("\nProblem 3");
        int[] arrival = {0, 2, 4, 5};
        int[] duration = {5, 2, 3, 3};
        int[] result = atmQueue(arrival, duration);
        System.out.println("ATM Queue - Max waiting: " + result[0] + ", Avg waiting: " + result[1]);

        // Problem 4: Balanced brackets
        System.out.println("\nProblem 4");
        String[] tests = {"[]", "{}", "()", "(){{[]}}()", "{)", "{{[[}}]]"};
        for (String test : tests) {
            System.out.println("\"" + test + "\" is balanced? " + isBalanced(test));
        }
    }
}
