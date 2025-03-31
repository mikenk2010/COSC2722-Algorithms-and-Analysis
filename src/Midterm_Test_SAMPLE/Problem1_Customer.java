package Midterm_Test_SAMPLE;
import java.util.*;

// Class representing a customer in the bank queue
class Customer {
    int id;
    String name;
    String type; // "Silver", "Gold", or "Diamond"
    boolean unhappy = false; // Flag to track if customer has already been bypassed

    public Customer(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}

public class Problem1_Customer {
    // List to maintain the queue of customers
    private List<Customer> queue;
    // Tracks number of unhappy customers who got bypassed (only once per person)
    private int unhappyCount;

    // Constructor initializes empty queue and unhappy counter
    public Problem1_Customer() {
        queue = new ArrayList<>();
        unhappyCount = 0;
    }

    // Convert customer type to priority value (higher number = higher priority)
    private int getPriority(String type) {
        return switch (type) {
            case "Diamond" -> 3;
            case "Gold" -> 2;
            default -> 1; // Silver
        };
    }

    // enQueue complexity = O(N)
    // Insert customer into queue based on priority (Diamond > Gold > Silver)
    public int enQueue(Customer customer) {
        int priority = getPriority(customer.type);
        int position = queue.size(); // Default insert at end

        // Find first position where this customer should be inserted
        for (int i = 0; i < queue.size(); i++) {
            if (getPriority(queue.get(i).type) < priority) {
                position = i;
                break;
            }
        }

        // Count how many lower-priority customers get overtaken (only if they haven't been counted before)
        for (int i = position; i < queue.size(); i++) {
            Customer other = queue.get(i);
            if (getPriority(other.type) < priority && !other.unhappy) {
                unhappyCount++;
                other.unhappy = true;
            }
        }

        // Insert the customer at calculated position
        queue.add(position, customer);
        return position;
    }

    // dequeue complexity = O(1)
    // Serve and remove the first customer in queue
    public Customer dequeue() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    // Return current count of unhappy customers
    public int countUnhappy() {
        return unhappyCount;
    }

    public static void main(String[] args) {
        Problem1_Customer bank = new Problem1_Customer();

        // Add Gold customer first
        System.out.println("Alice: " + "Gold");
        System.out.println("Position: " + bank.enQueue(new Customer(123, "Alice", "Gold"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        // Add Silver customer next
        System.out.println("Bob: " + "Silver");
        System.out.println("Position: " + bank.enQueue(new Customer(456, "Bob", "Silver"))); // 1
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        // Add Diamond customer, who bypasses both previous customers
        System.out.println("Carol: " + "Diamond");
        System.out.println("Position: " + bank.enQueue(new Customer(789, "Carol", "Diamond"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 2

        // Add Gold customer who does not bypass anyone new
        System.out.println("David: " + "Gold");
        System.out.println("Position: " + bank.enQueue(new Customer(123, "David", "Gold"))); // 1
        System.out.println("Unhappy: " + bank.countUnhappy()); // 2

        // Add Diamond customer, who bypasses David only (Alice & Bob already unhappy)
        System.out.println("Elsa: " + "Diamond");
        System.out.println("Position: " + bank.enQueue(new Customer(235, "Elsa", "Diamond"))); // 1
        System.out.println("Unhappy: " + bank.countUnhappy()); // 3
    }
}