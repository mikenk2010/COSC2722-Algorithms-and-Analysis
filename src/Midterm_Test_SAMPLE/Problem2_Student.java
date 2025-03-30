package Midterm_Test_SAMPLE;

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

class Problem2_Student {
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
        Problem2_Student bst = new Problem2_Student();
        System.out.println("Level: " + bst.addStudent(new Student(1, "Alice", 3.3))); // 0
        System.out.println("Level: " + bst.addStudent(new Student(2, "Bob", 3.1)));   // 1
        System.out.println("Level: " + bst.addStudent(new Student(3, "Carol", 3.2))); // 2
        System.out.println("Level: " + bst.addStudent(new Student(4, "David", 3.5))); // 1

        System.out.println("Nearest GPA to 3.0: " + bst.nearestStudent(3.0).name);     // Bob
    }
}