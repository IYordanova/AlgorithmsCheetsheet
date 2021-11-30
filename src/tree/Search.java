package tree;

public class Search {

    public static TreeNode search(int data, TreeNode node) {
        TreeNode current = node;
        System.out.println("Visiting elements: ");

        while (current.data != data) {
            if (current != null) {
                System.out.printf("%d ", current.data);

                if (current.data > data) {
                    current = current.left;
                } else {
                    current = current.right;
                }

                if (current == null) {
                    return null;
                }
            }
        }

        return current;
    }
}
