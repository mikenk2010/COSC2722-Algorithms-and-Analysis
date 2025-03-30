**COSC2722 - Algorithms and Analysis**  
**Mock Test - Practice Set 2 with Solutions**  
*Time Allowed: 90 minutes*

---

### Problem 1: Support Ticket Priority Queue (Array Implementation)

**Objective:** Implement a priority-based support system. Each ticket has an ID, description, and priority (Low, Medium, High). Higher priority tickets are served first.

**Instructions:**
- Implement a class `BaoTicketQueue`
- Implement the methods:
    - `int enqueue(Ticket ticket)` – return position inserted
    - `Ticket dequeue()` – return and remove the highest priority ticket (FIFO within priority)
    - `int countDelayed()` – return number of delayed tickets
- Use an array list

**Java Classes:**
```java
class Ticket {
    int id;
    String description;
    String priority; // "Low", "Medium", "High"

    public Ticket(int id, String desc, String priority) {
        this.id = id;
        this.description = desc;
        this.priority = priority;
    }
}
```

```java
import java.util.*;

public class BaoTicketQueue {
    private List<Ticket> queue;
    private int delayed;

    public BaoTicketQueue() {
        queue = new ArrayList<>();
        delayed = 0;
    }

    private int getPriority(String p) {
        return switch (p) {
            case "High" -> 3;
            case "Medium" -> 2;
            default -> 1;
        };
    }

    // enqueue complexity = O(N)
    public int enqueue(Ticket ticket) {
        int priority = getPriority(ticket.priority);
        int pos = queue.size();
        for (int i = 0; i < queue.size(); i++) {
            if (getPriority(queue.get(i).priority) < priority) {
                pos = i;
                break;
            }
        }
        for (int i = pos; i < queue.size(); i++) {
            if (getPriority(queue.get(i).priority) < priority) delayed++;
        }
        queue.add(pos, ticket);
        return pos;
    }

    public Ticket dequeue() {
        return queue.isEmpty() ? null : queue.remove(0);
    }

    public int countDelayed() {
        return delayed;
    }

    public static void main(String[] args) {
        BaoTicketQueue tq = new BaoTicketQueue();
        tq.enqueue(new Ticket(1, "Login issue", "Medium"));
        tq.enqueue(new Ticket(2, "Server crash", "High"));
        tq.enqueue(new Ticket(3, "Feature request", "Low"));
        System.out.println("Delayed: " + tq.countDelayed());
    }
}
```

---

### Problem 2: Movie Ratings in BST

**Objective:** Use a BST to manage movies by unique rating scores.

**Requirements:**
- Class `Movie {int id, String title, double rating}`
- BST class: `BaoMovieBST`
- Add methods:
    - `int addMovie(Movie movie)` – returns depth level
    - `Movie closestMatch(double rating)` – return closest rating

**Java Code:**
```java
class Movie {
    int id;
    String title;
    double rating;
    Movie left, right;

    public Movie(int id, String title, double rating) {
        this.id = id;
        this.title = title;
        this.rating = rating;
    }
}

public class BaoMovieBST {
    private Movie root;

    // addMovie complexity = O(log N)
    public int addMovie(Movie m) {
        return insert(root, m, 0);
    }

    private int insert(Movie node, Movie m, int level) {
        if (root == null) {
            root = m;
            return 0;
        }
        if (m.rating < node.rating) {
            if (node.left == null) {
                node.left = m;
                return level + 1;
            }
            return insert(node.left, m, level + 1);
        } else {
            if (node.right == null) {
                node.right = m;
                return level + 1;
            }
            return insert(node.right, m, level + 1);
        }
    }

    // closestMatch complexity = O(log N)
    public Movie closestMatch(double target) {
        Movie node = root;
        Movie best = root;
        double minDiff = Math.abs(root.rating - target);

        while (node != null) {
            double diff = Math.abs(node.rating - target);
            if (diff < minDiff) {
                best = node;
                minDiff = diff;
            }
            node = target < node.rating ? node.left : node.right;
        }
        return best;
    }

    public static void main(String[] args) {
        BaoMovieBST bst = new BaoMovieBST();
        bst.addMovie(new Movie(1, "Inception", 8.8));
        bst.addMovie(new Movie(2, "Dunkirk", 7.5));
        bst.addMovie(new Movie(3, "Tenet", 7.8));
        System.out.println("Closest to 8.0: " + bst.closestMatch(8.0).title);
    }
}
```

---

### Problem 3: Find Insertion Index in Sorted Grades

**Task:** Return the position where a student score X can be inserted in a sorted array of unique scores.

**Linear Search (given):**
```java
int position(int[] A, int X) {
    int N = A.length;
    for (int i = 0; i < N; i++) {
        if (X < A[i]) return i;
    }
    return N;
}
```
**Answer for blank:** `i`
**Time Complexity:** O(N)

**Improved Binary Search:**
```java
int binaryPosition(int[] A, int X) {
    int low = 0, high = A.length - 1, pos = A.length;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (X < A[mid]) {
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

Let me know if you'd like additional problems like heaps, graphs, or recursion-based challenges!

