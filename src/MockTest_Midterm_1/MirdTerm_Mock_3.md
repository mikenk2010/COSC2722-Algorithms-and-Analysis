**COSC2722 - Algorithms and Analysis**  
**Mock Test - Practice Set 3 with Solutions**  
*Time Allowed: 90 minutes*

---

### Problem 1: Hospital Emergency Queue

**Objective:** Create a queue to manage patients in a hospital emergency room. Patients have `id`, `name`, and `urgency` level: "Low", "Moderate", "Critical". Patients are treated in order of urgency (FIFO within priority).

**Class to implement:** `BaoEmergencyQueue`

**Required Methods:**
- `int admit(Patient patient)` – add to queue, return position
- `Patient treat()` – remove and return next patient to treat
- `int countOvertaken()` – return number of overtaken patients (those bypassed by more urgent arrivals)

```java
class Patient {
    int id;
    String name;
    String urgency;

    public Patient(int id, String name, String urgency) {
        this.id = id;
        this.name = name;
        this.urgency = urgency;
    }
}
```

```java
import java.util.*;

public class BaoEmergencyQueue {
    private List<Patient> queue;
    private int overtaken;

    public BaoEmergencyQueue() {
        queue = new ArrayList<>();
        overtaken = 0;
    }

    private int priority(String u) {
        return switch (u) {
            case "Critical" -> 3;
            case "Moderate" -> 2;
            default -> 1;
        };
    }

    // admit complexity = O(N)
    public int admit(Patient p) {
        int pLevel = priority(p.urgency);
        int pos = queue.size();
        for (int i = 0; i < queue.size(); i++) {
            if (priority(queue.get(i).urgency) < pLevel) {
                pos = i;
                break;
            }
        }
        for (int i = pos; i < queue.size(); i++) {
            if (priority(queue.get(i).urgency) < pLevel) overtaken++;
        }
        queue.add(pos, p);
        return pos;
    }

    public Patient treat() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    public int countOvertaken() {
        return overtaken;
    }

    public static void main(String[] args) {
        BaoEmergencyQueue eq = new BaoEmergencyQueue();
        eq.admit(new Patient(1, "John", "Moderate"));
        eq.admit(new Patient(2, "Doe", "Low"));
        eq.admit(new Patient(3, "Anna", "Critical"));
        System.out.println("Overtaken: " + eq.countOvertaken()); // 2
    }
}
```

---

### Problem 2: Book Library BST

**Objective:** Manage books by unique ISBN values using BST.

**Book Class:** `int isbn`, `String title`, `int pages`

**BST Class:** `BaoBookBST`

**Methods:**
- `int addBook(Book book)` – return the level where book inserted (based on ISBN)
- `Book nearestBook(int isbn)` – find the book with closest ISBN value

```java
class Book {
    int isbn;
    String title;
    int pages;
    Book left, right;

    public Book(int isbn, String title, int pages) {
        this.isbn = isbn;
        this.title = title;
        this.pages = pages;
    }
}
```

```java
public class BaoBookBST {
    private Book root;

    // addBook complexity = O(log N)
    public int addBook(Book b) {
        return insert(root, b, 0);
    }

    private int insert(Book node, Book b, int level) {
        if (root == null) {
            root = b;
            return 0;
        }
        if (b.isbn < node.isbn) {
            if (node.left == null) {
                node.left = b;
                return level + 1;
            }
            return insert(node.left, b, level + 1);
        } else {
            if (node.right == null) {
                node.right = b;
                return level + 1;
            }
            return insert(node.right, b, level + 1);
        }
    }

    // nearestBook complexity = O(log N)
    public Book nearestBook(int isbn) {
        Book node = root, best = root;
        int minDiff = Math.abs(root.isbn - isbn);

        while (node != null) {
            int diff = Math.abs(node.isbn - isbn);
            if (diff < minDiff) {
                minDiff = diff;
                best = node;
            }
            node = (isbn < node.isbn) ? node.left : node.right;
        }
        return best;
    }

    public static void main(String[] args) {
        BaoBookBST bst = new BaoBookBST();
        bst.addBook(new Book(100, "Java Basics", 300));
        bst.addBook(new Book(80, "Python Intro", 250));
        bst.addBook(new Book(120, "C++ Core", 400));
        System.out.println("Closest ISBN to 110: " + bst.nearestBook(110).title);
    }
}
```

---

### Problem 3: Rank Insertion Position in Leaderboard

**Task:** In a sorted leaderboard array `scores[]`, find where a new score X fits in.

**Example:**
```
scores = [20, 40, 60, 80, 100]
X = 75 → position = 3
X = 10 → position = 0
```

**Linear Search Version:**
```java
int insertRank(int[] scores, int X) {
    for (int i = 0; i < scores.length; i++) {
        if (X < scores[i]) return i;
    }
    return scores.length;
}
```
**Time Complexity:** O(N)

**Binary Search Version:**
```java
int binaryInsertRank(int[] scores, int X) {
    int low = 0, high = scores.length - 1, pos = scores.length;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (X < scores[mid]) {
            pos = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    return pos;
}
```
**Time Complexity:** O(log N)

---

Let me know if you'd like another mock focused on recursion, graphs, or dynamic programming next!

