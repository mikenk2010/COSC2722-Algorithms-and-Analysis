package Midterm_Test_SAMPLE;

// Class to represent a student in the BST
class Student {
    int id;
    String name;
    double gpa;
    Student left, right; // Left and right children for the BST

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
}

// Main class to manage the BST of students
class Problem2_Student {
    private Student root; // Root of the binary search tree

    // addStudent complexity = O(log N)
    // Inserts a new student based on GPA and returns the depth level where inserted
    public int addStudent(Student student) {
        return insert(root, student, 0);
    }

    // Helper method to recursively insert the student and calculate their level in the tree
    private int insert(Student node, Student student, int level) {
        // If tree is empty, set the student as root
        if (root == null) {
            root = student;
            return 0;
        }
        // Insert into left subtree if GPA is smaller
        if (student.gpa < node.gpa) {
            if (node.left == null) {
                node.left = student;
                return level + 1;
            }
            return insert(node.left, student, level + 1);
        }
        // Insert into right subtree if GPA is greater
        else {
            if (node.right == null) {
                node.right = student;
                return level + 1;
            }
            return insert(node.right, student, level + 1);
        }
    }

    // nearestStudent complexity = O(log N)
    // Finds the student whose GPA is closest to the provided GPA
    public Student nearestStudent(double gpa) {
        Student node = root;
        Student closest = root;
        double minDiff = Math.abs(node.gpa - gpa);

        // Traverse down the BST to find the closest GPA
        while (node != null) {
            double diff = Math.abs(node.gpa - gpa);
            if (diff < minDiff) {
                minDiff = diff;
                closest = node;
            }
            // Decide direction based on comparison
            node = (gpa < node.gpa) ? node.left : node.right;
        }
        return closest;
    }

    public static void main(String[] args) {
        Problem2_Student bst = new Problem2_Student();

        // Adding students and printing their insertion level
        System.out.println("Level: " + bst.addStudent(new Student(1, "Alice", 3.3))); // root
        System.out.println("Level: " + bst.addStudent(new Student(2, "Bob", 3.1)));   // left
        System.out.println("Level: " + bst.addStudent(new Student(3, "Carol", 3.2))); // right of Bob
        System.out.println("Level: " + bst.addStudent(new Student(4, "David", 3.5))); // right of root

        // Finding the closest GPA match to 3.0
        System.out.println("Nearest GPA to 3.0: " + bst.nearestStudent(3.0).name); // Bob
    }
}
