package BinaryTree;
/*
Linked list
 *     public Node create(int[] arr, Node root, int i) {
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;	
			// insert left child
			root.left = create(arr, root.left, 2 * i + 1);
			// insert right child
			root.right = create(arr, root.right, 2 * i + 2);
		}
		return root;
	}

    public void preOrder(Node root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
 */
public class LinkedListBinaryTree {

    static class Node {
        String data;
        Node left, right;
        Node(String data) {
            this.data = data;
            left = right = null;
        }
    }

    public Node create(String[] arr, int i) {
        if (i < arr.length && arr[i] != null) {
            Node root = new Node(arr[i]);
            root.left = create(arr, 2 * i + 1);
            root.right = create(arr, 2 * i + 2);
            return root;
        }
        return null;
    }

    public void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {
        String[] arr = {"D", "A", "F", "E", "B", "R", "T", "G", "Q", null, null, "V", null, "J", "L"};
        LinkedListBinaryTree tree = new LinkedListBinaryTree();
        Node root = tree.create(arr, 0);

        System.out.println("Preorder:");
        tree.preorder(root);

        System.out.println("\nInorder:");
        tree.inorder(root);

        System.out.println("\nPostorder:");
        tree.postorder(root);
    }
}
