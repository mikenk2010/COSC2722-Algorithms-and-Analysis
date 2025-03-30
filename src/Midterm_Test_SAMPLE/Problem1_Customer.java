package Midterm_Test_SAMPLE;
import java.util.*;

class Customer {
    int id;
    String name;
    String type; // "Silver", "Gold", "Diamond"

    public Customer(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

public class Problem1_Customer {
    private List<Customer> queue;
    private int unhappyCount;

    public Problem1_Customer() {
        queue = new ArrayList<>();
        unhappyCount = 0;
    }

    private int getPriority(String type) {
        return switch (type) {
            case "Diamond" -> 3;
            case "Gold" -> 2;
            default -> 1; // Silver
        };
    }

    // enQueue complexity = O(N)
    public int enQueue(Customer customer) {
        int priority = getPriority(customer.type);
        int position = queue.size();

        for (int i = 0; i < queue.size(); i++) {
            if (getPriority(queue.get(i).type) < priority) {
                position = i;
                break;
            }
        }

        for (int i = position; i < queue.size(); i++) {
            if (getPriority(queue.get(i).type) < priority) {
                unhappyCount++;
            }
        }

        queue.add(position, customer);
        return position;
    }

    // dequeue complexity = O(1)
    public Customer dequeue() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    public int countUnhappy() {
        return unhappyCount;
    }

    public static void main(String[] args) {
        Problem1_Customer bank = new Problem1_Customer();
        System.out.println("Position: " + bank.enQueue(new Customer(123, "Alice", "Gold"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        System.out.println("Position: " + bank.enQueue(new Customer(456, "Bob", "Silver"))); // 1
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        System.out.println("Position: " + bank.enQueue(new Customer(789, "Carol", "Diamond"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 2
    }
}