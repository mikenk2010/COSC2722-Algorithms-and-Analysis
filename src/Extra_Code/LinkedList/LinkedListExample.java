package Extra_Code.LinkedList;

class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
    }
}

public class LinkedListExample {
    Node head;

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) head = newNode;
        else {
            Node temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedListExample list = new LinkedListExample();
        list.insert(10);
        list.insert(20);
        list.insert(30);
        System.out.print("Linked List: ");
        list.display(); // Output: 10 20 30
    }
}
