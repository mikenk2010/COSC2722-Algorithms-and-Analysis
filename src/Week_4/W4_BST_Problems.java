package Week_4;

import java.util.*;

public class W4_BST_Problems {

    // Problem 1: Implement tree traversals
    public static <T extends Comparable<T>> void preOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static <T extends Comparable<T>> void inOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    public static <T extends Comparable<T>> void postOrder(BinaryTreeNode<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }

    public static <T extends Comparable<T>> void breadthFirst(BinaryTreeNode<T> root) {
        if (root == null) return;
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            System.out.print(node.data + " ");
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }
    }

    // Problem 2: Check if a tree is a BST
    public static <T extends Comparable<T>> boolean isBST(BinaryTreeNode<T> node, T min, T max) {
        if (node == null) return true;
        if ((min != null && node.data.compareTo(min) <= 0) ||
                (max != null && node.data.compareTo(max) >= 0)) {
            return false;
        }
        return isBST(node.left, min, node.data) && isBST(node.right, node.data, max);
    }

    // Problem 3: Calculate level of a node and distance between two nodes
    public static <T extends Comparable<T>> int getLevel(BinaryTreeNode<T> root, T value, int level) {
        if (root == null) return -1;
        if (root.data.equals(value)) return level;

        int left = getLevel(root.left, value, level + 1);
        if (left != -1) return left;
        return getLevel(root.right, value, level + 1);
    }

    public static <T extends Comparable<T>> BinaryTreeNode<T> findLCA(BinaryTreeNode<T> root, T a, T b) {
        if (root == null) return null;
        if (root.data.equals(a) || root.data.equals(b)) return root;
        BinaryTreeNode<T> left = findLCA(root.left, a, b);
        BinaryTreeNode<T> right = findLCA(root.right, a, b);
        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    public static <T extends Comparable<T>> int distance(BinaryTreeNode<T> root, T a, T b) {
        BinaryTreeNode<T> lca = findLCA(root, a, b);
        return getLevel(lca, a, 0) + getLevel(lca, b, 0);
    }

    // Problem 4: Find minimum node Y such that Y >= X in BST
    public static <T extends Comparable<T>> BinaryTreeNode<T> findMinGreaterEqual(BinaryTreeNode<T> node, T x) {
        BinaryTreeNode<T> result = null;
        while (node != null) {
            if (node.data.compareTo(x) >= 0) {
                result = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int val : values) tree.add(val);

        System.out.println("\nProblem 1: Tree Traversals");
        System.out.print("Pre-order: "); preOrder(tree.root);
        System.out.print("\nIn-order: "); inOrder(tree.root);
        System.out.print("\nPost-order: "); postOrder(tree.root);
        System.out.print("\nBreadth-first: "); breadthFirst(tree.root);

        System.out.println("\n\nProblem 2: Is BST? " + isBST(tree.root, null, null));

        System.out.println("\nProblem 3: Distance between 20 and 80: " + distance(tree.root, 20, 80));

        int x = 65;
        BinaryTreeNode<Integer> res = findMinGreaterEqual(tree.root, x);
        System.out.println("\nProblem 4: Min node >= " + x + " is: " + (res != null ? res.data : "null"));
    }
}
