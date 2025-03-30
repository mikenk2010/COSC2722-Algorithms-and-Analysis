package Extra_Code.LinkedList;
class ReverseNode {
    int data;
    ReverseNode next;
    ReverseNode(int data) {
        this.data = data;
    }
}

public class ReverseLinkedList {
    public static ReverseNode reverse(ReverseNode head) {
        ReverseNode prev = null;
        while (head != null) {
            ReverseNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ReverseNode head = new ReverseNode(1);
        head.next = new ReverseNode(2);
        head.next.next = new ReverseNode(3);

        System.out.print("Original: 1 2 3\nReversed: ");
        head = reverse(head);
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
}
