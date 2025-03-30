package TriDang_2025.test1_sample;

public class StudentBST {
  static class Student {
    int id;
    String name;
    double GPA;
  
    public Student(int id, String name, double GPA) {
      this.id = id;
      this.name = name;
      this.GPA = GPA;
    }
  
    @Override
    public String toString() {
      return "id: " + id + ", name: " + name + ", GPA: " + GPA;
    }
  }
  
  static class StudentNode {
    Student data;
    StudentNode left, right;
  
    public StudentNode(Student student) {
      data = student;
      left = right = null;
    }
  }

  StudentNode root;

  public StudentBST() {
    root = null;
  }

  // addStudent complexity = O(lg(N)) (because the tree is balanced)
  public int addStudent(Student student) {
    int level = 0;
    if (root == null) {
      root = new StudentNode(student);
      return level;
    }
    StudentNode parent = null;
    StudentNode current = root;

    while (current != null) {
      if (student.GPA < current.data.GPA) {
        parent = current;
        current = current.left;
      } else {
        parent = current;
        current = current.right;
      }
      level++;
    }
    StudentNode node = new StudentNode(student);
    if (student.GPA < parent.data.GPA) {
      parent.left = node;
    } else {
      parent.right = node;
    }
    return level;
  }

  // complexity = O(N)
  public Student nearestStudent(double GPA) {
    return nearestStudent(root, GPA);
  }

  // complexity = O(N) (check both left and right branches)
  // calculate nearest of left subtree and right subtree
  // and compare with the current node
  private Student nearestStudent(StudentNode node, double GPA) {
    if (node == null) {
      return null;
    }
    Student nearest = node.data;
    Student left = nearestStudent(node.left, GPA);
    if (left != null && Math.abs(left.GPA - GPA) < Math.abs(nearest.GPA - GPA)) {
      nearest = left;
    }
    Student right = nearestStudent(node.right, GPA);
    if (right != null && Math.abs(right.GPA - GPA) < Math.abs(nearest.GPA - GPA)) {
      nearest = right;
    }
    return nearest;
  }

  public static void main(String[] args) {
    StudentBST tree = new StudentBST();
    System.out.printf("\nAlice (GPA 3.3) level: %d", tree.addStudent(new Student(1, "Alice", 3.3)));  // 0 (root)
    System.out.printf("\nBob (GPA 3.1) level: %d", tree.addStudent(new Student(2, "Bob", 3.1)));  // 1 (left child of root)
    System.out.printf("\nCarol (GPA 3.2) level: %d", tree.addStudent(new Student(3, "Carol", 3.2)));  // 2 (right child of Bob)
    System.out.printf("\nDavid (GPA 3.5) level: %d", tree.addStudent(new Student(4, "David", 3.5)));  // 1 (right child of root)

    System.out.printf("\nNearest to 3.0: %s", tree.nearestStudent(3.0));  // Bob
    System.out.printf("\nNearest to 3.2: %s", tree.nearestStudent(3.2));  // Carol
    System.out.printf("\nNearest to 3.4: %s", tree.nearestStudent(3.4));  // Alice or David is OK
  }
}
