package Extra_Code.Tree;

public class LowestCommonAncestor {
    public static TreeNode findLCA(TreeNode root, int a, int b) {
        if (root == null) return null;
        if (root.val == a || root.val == b) return root;

        TreeNode left = findLCA(root.left, a, b);
        TreeNode right = findLCA(root.right, a, b);

        if (left != null && right != null) return root;
        return (left != null) ? left : right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode lca = findLCA(root, 6, 2);
        System.out.println("LCA of 6 and 2: " + (lca != null ? lca.val : "null")); // Output: 5
    }
}
