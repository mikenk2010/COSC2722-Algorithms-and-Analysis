package Extra_Code.Tree;

class BSTNode {
    int val;
    BSTNode left, right;

    BSTNode(int val) {
        this.val = val;
    }
}

public class BinarySearchTreeInsertSearch {
    BSTNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private BSTNode insertRec(BSTNode node, int val) {
        if (node == null) return new BSTNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else node.right = insertRec(node.right, val);
        return node;
    }

    public boolean search(int val) {
        return searchRec(root, val);
    }

    private boolean searchRec(BSTNode node, int val) {
        if (node == null) return false;
        if (node.val == val) return true;
        return val < node.val ? searchRec(node.left, val) : searchRec(node.right, val);
    }

    public static void main(String[] args) {
        BinarySearchTreeInsertSearch tree = new BinarySearchTreeInsertSearch();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        System.out.println("Search 70: " + tree.search(70)); // true
        System.out.println("Search 25: " + tree.search(25)); // false
    }
}
