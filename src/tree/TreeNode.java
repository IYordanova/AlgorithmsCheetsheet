package tree;

/**
 * Types of trees:
 * <p>
 * 1. Full Binary Tree - every parent node/internal node has either 2 or NO children.
 * 2. Perfect Binary Tree - every internal node has EXACTLY 2 child nodes and all the leaf nodes are at the same level
 * 3. Complete Binary Tree - like a full binary java.tree, but
 * - Every level must be completely filled
 * - All the leaf elements must lean towards the left.
 * - The last leaf element might not have a right sibling i.e. a complete binary java.tree doesn't have to be a full binary java.tree.
 * 4. Degenerate or Pathological Tree - single child either left or right.
 * 5. Skewed Binary Tree - a pathological/degenerate java.tree either dominated by the left nodes or the right nodes => 2 types left or right
 * 6. Balanced Binary Tree - the difference between the height of the left and the right subtree for each node is either 0 or 1.
 */

public class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
}

class Tree {

    private TreeNode root;

    Tree(TreeNode root) {
        this.root = root;
    }


    void insert(int data) {
        TreeNode tempNode = new TreeNode();
        tempNode.data = data;
        tempNode.left = null;
        tempNode.right = null;

        TreeNode current;
        TreeNode parent;

        if (root == null) {
            root = tempNode;
        } else {
            current = root;
            while (true) {
                parent = current;
                if (data < parent.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = tempNode;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = tempNode;
                        return;
                    }
                }
            }
        }
    }

}