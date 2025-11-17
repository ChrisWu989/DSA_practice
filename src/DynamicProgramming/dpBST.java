package DynamicProgramming;

public class dpBST {
    public static int BST(int N) {
        int[] dp = new int[N + 1];
        dp[0] = dp[1] = 1; // Base cases

        for (int n = 2; n <= N; n++) {
            for (int root = 1; root <= n; root++) {
                dp[n] += dp[root - 1] * dp[n - root];
            }
        }

        return dp[N];
    }

    
    public static void main(String[] args) {
        System.out.println(BST(3));
    }
}
