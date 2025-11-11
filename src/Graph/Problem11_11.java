package Graph;

public class Problem11_11 {
    /*
    Problem 29
    There is a board with 2 rows and N columns, represented by a matrix M
    Rows are numbered from 0 to 1 from top to bottom and columns are numbered
    from 0 to N-1 from left to right. Each cell contains either a 0 or a 1
        - The sum of integers in the 0-th (upper) row is equal to U
        - The sum of integers in the 1-st (lower) row is equal to L
        - The sum of integers in the k-th column is equal to C[K]
    Your job is to recover M based on this information

    Write a function class Solution { public String solution(int U, int L, int[] C) } that given integers 
    U, L and an array C of N integers returns a string describing the matrix M in the following format
    The first part of the string should be the description of the upper row (N characters: 0 or 1), then there should
    be a comma (,), and finally there should be the description of the lower row (N Characters: 0 or 1)

    If there exist multiple valid Ms, your function may return any of of them
    If no valid M exists, your function should return the word IMPOSSIBLE
    
    Example 1
    Input: U = 3, L = 2, C = [2, 1, 1, 0, 1]
    Output: 11001,10100

    Example 2
    Input: U = 2, L = 3, C = [0, 0, 1, 1, 2]
    Output: IMPOSSIBLE

    Example 3
    Input: U = 2, L = 2, C = [2, 0, 2, 0]
    Output: 1010,1010
    */
    public static String solution29(int U, int L, int[] C){
        if (U + L > C.length){
            return "IMPOSSIBLE";
        }

        int[] top = new int[C.length];
        int[] bot = new int[C.length];
        int upper = U;
        int lower = L;

        for (int i = 0; i < C.length; i++){
            if (C[i] == 1) {
                if (lower > 0) {
                    top[i] = 0;
                    bot[i] = 1;
                    lower--;
                } else if (upper > 0) {  // fallback
                    top[i] = 1;
                    bot[i] = 0;
                    upper--;
                }
            }
            else if (C[i] == 2){
                top[i] = 1;
                bot[i] = 1;
                upper--;
                lower--;
            }
        }

        if (upper != 0 || lower != 0){
            return "IMPOSSIBLE";
        }

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();
        for (int i = 0; i < C.length; i++) {
            s1.append(top[i]);
            s2.append(bot[i]);
        }
        return s1 + "," + s2;
    }
    /*
    Problem 30 and 31
    */
    public static void main(String[] args) {
        // Problem 29
        int[] A1 = {2, 1, 1, 0, 1};
        System.out.println(solution29(3, 2, A1));

        int[] A2 = {0, 0, 1, 1, 2};
        System.out.println(solution29(2, 3, A2));

        int[] A3 = {2, 0, 2, 0};
        System.out.println(solution29(2, 2, A3));
    }
}
