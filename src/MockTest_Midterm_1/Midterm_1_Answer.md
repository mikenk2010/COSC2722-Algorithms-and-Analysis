**COSC2722 - Algorithms and Analysis**  
**Midterm Test - Comprehensive Practice Paper with Solutions**  
*Time Allowed: 90 minutes*

---

### ✅ SECTION A: Short Answer (with Solutions)

1. **Time Complexity Concept:**  
   Big-O notation describes the upper bound of an algorithm’s running time, helping us understand the worst-case performance. Examples:
- O(1): Accessing an array element by index – constant time.
- O(log N): Binary search – cuts the problem size in half each step.
- O(N^2): Bubble sort – compares every pair of elements.

2. **Algorithm Design:**  
   Scan the array, maintaining two variables: `max` and `secondMax`. Update them as you find larger numbers.  
   **Time complexity:** O(N)

3. **Proof Question:**  
   T(N) = 999N - sqrt(N). We know that -sqrt(N) grows slower than N. So:
   T(N) ≤ 999N for all N ≥ 1 ⇒ T(N) ∈ O(N)

4. **Permutation Logic:**  
   Create two sets, add all elements from each sequence. If the sets are equal and have the same size, they are permutations. This ensures uniqueness and matching.

5. **BST Property Validation:**  
   A binary search tree (BST) must satisfy the property: left subtree values < root < right subtree values. Validate recursively with min/max bounds.

6. **Difference Between Stack and Queue:**  
   Stack: LIFO (Last In First Out), used in recursion, backtracking.  
   Queue: FIFO (First In First Out), used in task scheduling, BFS traversal.

7. **Give an example where merge sort performs better than bubble sort:**  
   Merge sort performs better on large datasets (e.g., sorting 10,000 integers) due to O(N log N) complexity compared to O(N²) of bubble sort.

8. **What is the purpose of AVL trees in data structures?**  
   AVL trees are self-balancing BSTs. They ensure O(log N) time complexity for insertions, deletions, and lookups by maintaining height balance.

---

### ✅ SECTION B–D: Java Solutions with `main()` Methods

**Question: Implement insertion and in-order traversal in an AVL Tree.**
```java
class AVLNode {
    int val, height;
    AVLNode left, right;
    AVLNode(int val) { this.val = val; this.height = 1; }
}

public class AVLTree {
    public static int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    public static int getBalance(AVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    public static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public static AVLNode insert(AVLNode node, int key) {
        if (node == null) return new AVLNode(key);
        if (key < node.val) node.left = insert(node.left, key);
        else if (key > node.val) node.right = insert(node.right, key);
        else return node; // duplicate keys not allowed

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Rotate cases
        if (balance > 1 && key < node.left.val) return rotateRight(node);
        if (balance < -1 && key > node.right.val) return rotateLeft(node);
        if (balance > 1 && key > node.left.val) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.val) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    public static void inOrder(AVLNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.val + " ");
            inOrder(root.right);
        }
    }

    public static void main(String[] args) {
        AVLNode root = null;
        int[] values = {10, 20, 30, 40, 50, 25};
        for (int val : values) root = insert(root, val);
        System.out.print("In-order traversal: ");
        inOrder(root); // Should print sorted values
    }
}
```

**Question: Evaluate a postfix expression using a stack.**
```java
import java.util.*;

public class PostfixEvaluator {
    public static int evaluate(String expr) {
        Stack<Integer> stack = new Stack<>();
        for (char c : expr.toCharArray()) {
            if (Character.isDigit(c)) stack.push(c - '0');
            else {
                int b = stack.pop();
                int a = stack.pop();
                switch (c) {
                    case '+': stack.push(a + b); break;
                    case '-': stack.push(a - b); break;
                    case '*': stack.push(a * b); break;
                    case '/': stack.push(a / b); break;
                }
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String expr = "23*54*+9-"; // (2*3 + 5*4) - 9 = 17
        System.out.println("Evaluated result: " + evaluate(expr));
    }
}
```

**Question: Implement quicksort in Java.**
```java
public class QuickSortExample {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        quickSort(arr, 0, arr.length - 1);
        System.out.print("Sorted array: ");
        for (int i : arr) System.out.print(i + " ");
    }
}
```

