package Extra_Code.Tree;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeExample {
    TreeNode root;

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeExample tree = new BinaryTreeExample();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);

        System.out.print("Pre-order: ");
        tree.preOrder(tree.root); // Output: 1 2 4 3
    }
}
