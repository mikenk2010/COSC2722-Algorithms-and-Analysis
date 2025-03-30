package TriDang_2025.test1_sample;

public class BankQueue {
  private int size;

  // Assume the number of customers in the queue will never exceed CAPACITY
  private static int CAPACITY = 100;
  private Customer[] items;
  private boolean[] unhappy;  // to keep track of unhappy customers

  public BankQueue() {
    size = 0;
    items = new Customer[CAPACITY];
    unhappy = new boolean[CAPACITY];
  }

  // complexity = O(N)
  public int enQueue(Customer cust) {
    // no need to check if the queue is full
    // because the queue will never exceed CAPACITY
    int pos = size;  // the position to insert the new customer
    // find the correct position to insert the new customer
    while (pos > 0 && cust.isHigherPriority(items[pos - 1])) {
      items[pos] = items[pos - 1];
      // customer at pos - 1 is moved to pos and become unhappy
      unhappy[pos] = true;
      // the position pos - 1 is now empty, so update the unhappy status
      unhappy[pos - 1] = false;
      pos--;
    }
    items[pos] = cust;
    size++;
    return pos;
  }

  // complexity = O(N)
  public int countUnhappy() {
    int total = 0;
    for (int i = 0; i < size; i++) {
      if (unhappy[i]) {
        total++;
      }
    }
    return total;
  }

  static class Customer {
    int id;
    String name;
    String type;

    public Customer(int id, String name, String type) {
      this.id = id;
      this.name = name;
      this.type = type;
    }

    // utility methods to compare customers based on type
    private int getTypeValue() {
      if (type.equals("Diamond")) {
        return 100;
      }
      if (type.equals("Gold")) {
        return 10;
      }
      return 1;  // Silver
    }

    private boolean isHigherPriority(Customer other) {
      return getTypeValue() > other.getTypeValue();
    }
  }

  // client code
  public static void main(String[] args) {
    BankQueue queue = new BankQueue();
    System.out.printf("\nAlice (Gold) position: %d", queue.enQueue(new Customer(123, "Alice", "Gold")));  // 0 (Alice is the first customer)
    System.out.printf("\nNumber of unhappy: %d", queue.countUnhappy());  // 0 (no unhappy customer)
    System.out.printf("\nBob (Silver) position: %d", queue.enQueue(new Customer(456, "Bob", "Silver")));  // 1 (Bob is positiioned after Alice)
    System.out.printf("\nNumber of unhappy: %d", queue.countUnhappy());  // 0 (no unhappy customer)
    System.out.printf("\nCarol (Diamond) position: %d", queue.enQueue(new Customer(789, "Carol", "Diamond")));  // 0 (Carol has the highest priority)
    System.out.printf("\nNumber of unhappy: %d", queue.countUnhappy());  // 2 (Alice and Bob are unhappy)
    System.out.printf("\nDavid (Gold) position: %d", queue.enQueue(new Customer(111, "David", "Gold")));  // 2 (David bypasses Bob)
    System.out.printf("\nNumber of unhappy: %d", queue.countUnhappy());  // 2 (Bob is unhappy already)
    System.out.printf("\nElse (Diamond) position: %d", queue.enQueue(new Customer(333, "Elsa", "Diamond")));  // 1 (Elsa bypasses all except Carol)
    System.out.printf("\nNumber of unhappy: %d", queue.countUnhappy());  // 3 (David is now unhappy, too)
  }
}
