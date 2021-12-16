package tree;

public class AVLTree {

    static class Node {
        int key;
        Node left;
        Node right;
        int height;

        Node(int value) {
            key = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    Node root;

    int height(Node root) {
        if (root == null)
            return 0;

        return root.height;
    }

    int findHeight() {
        return height(root);
    }

    int findHeightFrom(int value) {
        Node node = search(root, value);
        if (node == null)
            return -1;

        return node.height;
    }

    Node search(Node root, int value) {
        if (root == null)
            return null;
        else {
            if (value == root.key)
                return root;
            else if (value < root.key)
                return search(root.left, value);
            else
                return search(root.right, value);
        }
    }

    boolean find(int value) {
        Node node = search(root, value);
        return node != null;
    }

    int max(int one, int two) {
        return Math.max(one, two);
    }

    Node rightRotate(Node root) {
        Node rootLeftChild = root.left;
        root.left = rootLeftChild.right;
        rootLeftChild.right = root;

        root.height = max(height(root.left), height(root.right)) + 1;
        rootLeftChild.height = max(height(rootLeftChild.left), height(rootLeftChild.right)) + 1;

        return rootLeftChild;
    }

    Node leftRotate(Node root) {
        Node rootRightChild = root.right;
        root.right = rootRightChild.left;
        rootRightChild.left = root;

        root.height = max(height(root.left), height(root.right)) + 1;
        rootRightChild.height = max(height(rootRightChild.left), height(rootRightChild.right)) + 1;

        return rootRightChild;
    }

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = leftRotate(z);
            } else {
                z.right = rightRotate(z.right);
                z = leftRotate(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rightRotate(z);
            else {
                z.left = leftRotate(z.left);
                z = rightRotate(z);
            }
        }
        return z;
    }

    Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key);
        } else if (node.key > key) {
            node.left = insertNode(node.left, key);
        } else if (node.key < key) {
            node.right = insertNode(node.right, key);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return rebalance(node);
    }

    void insert(int value) {
        root = insertNode(root, value);
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    void preorderTraversal() {
        preorder(root);
        System.out.println();
    }

    public static void main(String[] args) {
        AVLTree avl = new AVLTree();
        avl.insert(10);
        avl.insert(20);
        avl.insert(30);
        avl.insert(40);
        avl.insert(50);
        avl.insert(25);

        System.out.print("Inorder Traversal : ");
        avl.inorderTraversal();
        System.out.print("Preorder Traversal : ");
        avl.preorderTraversal();
        System.out.println("Searching for 10 : " + avl.find(10));
        System.out.println("Searching for 11 : " + avl.find(11));
        System.out.println("Searching for 20 : " + avl.find(20));
        System.out.println("Height of the java.tree : " + avl.findHeight());
        System.out.println("Finding height from 10 : " + avl.findHeightFrom(10));
        System.out.println("Finding height from 20 : " + avl.findHeightFrom(20));
        System.out.println("Finding height from 25 : " + avl.findHeightFrom(25));
    }
}
