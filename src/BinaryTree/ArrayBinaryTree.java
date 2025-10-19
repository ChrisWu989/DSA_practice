package BinaryTree;

public class ArrayBinaryTree {
    static String[] arr = {"D", "A", "F", "E", "B", "R", "T", "G", "Q", null, null, "V", null, "J", "L"};

    public static int getLeftChild(int index) {
        int left = 2 * index + 1;
        if (left < arr.length && arr[left] != null) //left child exists and 
            return left;
        return -1;
    }

    public static int getRightChild(int index) {
        int right = 2 * index + 2;
        if (right < arr.length && arr[right] != null)
            return right;
        return -1;
    }

    public static int getParent(int index) {
        if (index == 0) return -1; // root has no parent
        return (index - 1) / 2;
    }

    public static void preorder(int index) {
        if (index == -1 || index >= arr.length || arr[index] == null)
            return;
        System.out.print(arr[index] + " "); // visit root
        preorder(getLeftChild(index));      // left
        preorder(getRightChild(index));     // right
    }

    public static void inorder(int index) {
        if (index == -1 || index >= arr.length || arr[index] == null)
            return;
        inorder(getLeftChild(index));       // left
        System.out.print(arr[index] + " "); // visit root
        inorder(getRightChild(index));      // right
    }

    public static void postorder(int index) {
        if (index == -1 || index >= arr.length || arr[index] == null)
            return;
        postorder(getLeftChild(index));     // left
        postorder(getRightChild(index));    // right
        System.out.print(arr[index] + " "); // visit root
    }
    
    public static void main(String[] args) {
        System.out.println("Parent of index 4: " + arr[getParent(4)]);
        System.out.println("Left child of index 0: " + arr[getLeftChild(0)]);
        System.out.println("Right child of index 0: " + arr[getRightChild(0)]);

        System.out.println("\nPreorder:");
        preorder(0);

        System.out.println("\n\nInorder:");
        inorder(0);

        System.out.println("\n\nPostorder:");
        postorder(0);
    }
}