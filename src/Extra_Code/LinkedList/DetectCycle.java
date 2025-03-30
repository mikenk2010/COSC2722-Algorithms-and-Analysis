package Extra_Code.LinkedList;

public class DetectCycle {
    static class Node {
        int data;
        Node next;
        Node(int d) { data = d; }
    }

    public static boolean hasCycle(Node head) {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head; // Cycle

        System.out.println("Has cycle: " + hasCycle(head)); // Output: true
    }
}
