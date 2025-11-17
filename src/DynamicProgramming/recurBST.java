package DynamicProgramming;

// calculate max bst if given N using DP and recursion
public class recurBST {
    public static int BST(int n) {
        return count(n);
    }

    public static int count(int n){
        if (n <= 1) return 1;       // base case: 1 or empty subtree

        int counts = 0;

        for (int root = 1; root <= n; root++) {
            int left = count(root - 1);
            int right = count(n - root);
            counts += left * right;
        }

        return counts;
    }

    public static void main(String[] args) {
        System.out.println(BST(3));
    }
}
