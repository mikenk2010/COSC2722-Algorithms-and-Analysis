**COSC2722 - Algorithms and Analysis**  
**Midterm Test - Comprehensive Practice Paper with Solutions**  
*Time Allowed: 90 minutes*

---

## ✅ Assessment Problems and Detailed Answers

### Problem 1: Priority Queue for Bank Customers

**Objective:** Implement a priority queue using arrays, serving higher priority customers first. Track how many customers become unhappy when bypassed.

```java
// Customer.java
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
```

```java
// BaoBankQueue.java
import java.util.*;

public class BaoBankQueue {
    private List<Customer> queue;
    private int unhappyCount;

    public BaoBankQueue() {
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
        BaoBankQueue bank = new BaoBankQueue();
        System.out.println("Position: " + bank.enQueue(new Customer(123, "Alice", "Gold"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        System.out.println("Position: " + bank.enQueue(new Customer(456, "Bob", "Silver"))); // 1
        System.out.println("Unhappy: " + bank.countUnhappy()); // 0

        System.out.println("Position: " + bank.enQueue(new Customer(789, "Carol", "Diamond"))); // 0
        System.out.println("Unhappy: " + bank.countUnhappy()); // 2
    }
}
```

---

### Problem 2: Binary Search Tree for Student Records

```java
// Student.java
class Student {
    int id;
    String name;
    double gpa;
    Student left, right;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}
```

```java
// BaoStudentBST.java
public class BaoStudentBST {
    private Student root;

    // addStudent complexity = O(log N)
    public int addStudent(Student student) {
        return insert(root, student, 0);
    }

    private int insert(Student node, Student student, int level) {
        if (root == null) {
            root = student;
            return 0;
        }
        if (student.gpa < node.gpa) {
            if (node.left == null) {
                node.left = student;
                return level + 1;
            }
            return insert(node.left, student, level + 1);
        } else {
            if (node.right == null) {
                node.right = student;
                return level + 1;
            }
            return insert(node.right, student, level + 1);
        }
    }

    // nearestStudent complexity = O(log N)
    public Student nearestStudent(double gpa) {
        Student node = root;
        Student closest = root;
        double minDiff = Math.abs(node.gpa - gpa);

        while (node != null) {
            double diff = Math.abs(node.gpa - gpa);
            if (diff < minDiff) {
                minDiff = diff;
                closest = node;
            }
            node = (gpa < node.gpa) ? node.left : node.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        BaoStudentBST bst = new BaoStudentBST();
        System.out.println("Level: " + bst.addStudent(new Student(1, "Alice", 3.3))); // 0
        System.out.println("Level: " + bst.addStudent(new Student(2, "Bob", 3.1)));   // 1
        System.out.println("Level: " + bst.addStudent(new Student(3, "Carol", 3.2))); // 2
        System.out.println("Level: " + bst.addStudent(new Student(4, "David", 3.5))); // 1

        System.out.println("Nearest GPA to 3.0: " + bst.nearestStudent(3.0).name);     // Bob
    }
}
```

---

### Problem 3: Insert Position in Sorted Array

**Original Code (Corrected):**
```java
int position(int[] A, int X) {
    int N = A.length;
    for (int i = 0; i < N; i++) {
        if (X < A[i]) return i;
    }
    return N;
}
```
**Answer for ___:** `i`

**Time Complexity:** O(N) in the worst case (linear search)

**Improved Approach (Binary Search):**
```java
int binaryInsertPosition(int[] A, int X) {
    int low = 0, high = A.length - 1, ans = A.length;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (X < A[mid]) {
            ans = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return ans;
}
```
**Time Complexity:** O(log N) due to binary search.
