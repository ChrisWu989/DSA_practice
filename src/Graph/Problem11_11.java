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
    Problem 30
    There are N houses along the street. Carbon filters are already installed in some of them.
    We would like to install filters in the remaining houses. Two types of filters 'a' and 'b' are being used
    The filters work best if no three adjacent houses have the same type of filters. The houses are represented
    as a string of characters 'a', 'b', and '?' ('a' and 'b' denote a house with a filter of a given type installed,
    '?' represents a house with no filter). Task is to make a plan of the filter types to be installed in houses without them

    Write a function class Solution { public String solution(String S); } that given a string S of length N,
    returns a string that is the result of replacing each '?' in string S with an 'a' or a 'b' character
    and does not contain three identical consecutive integers (No 'aaa' or 'bbb' is allowed)

    Examples
    S = 'a?bb' return 'aabb'
    S = '??abb' return 'ababb', 'bbabb' or 'baabb'
    S = 'a?b?aa' return 'aabbaa'
    S = 'aa??aa' return 'aabbaa'
    */
    public static String solution30(String S) {
        char[] arr = S.toCharArray();
        int n = arr.length;
        char prev, next;
        for (int i = 0; i < n; i++){
            if (arr[i] == '?'){
                // For prev
                if (i > 0) { 
                    prev = arr[i - 1]; 
                } else {
                    prev = '\0';
                }

                // For Next
                if (i < n - 1) {
                    next = arr[i + 1];
                } else {
                    next = '\0';
                }

                // Checking prev and next no three of same
                if (prev == 'a' || next == 'a') {
                    arr[i] = 'b';
                } else {
                    arr[i] = 'a';
                }
            }
        }

        return new String(arr);
    }

    /*
    Problem 31
    A car manufacturer has data about the production processes of N different cars (numbered 0 to N-1) and wants to memorize the 
    number of cars produced in the upcoming month. Info is described by an array H, where H[K] denotes the number of hours
    required to produce the K-th car

    There are two assembly lines in the factory, the first of which works for X, and the second Y, hours in a month. Every car can be 
    constructed using either one of these lines. Only one car can be produced at a time on each assembly line and we cannot switch lines
    
    What is the maximum number of different cars that can be produced in the upcoming month

    Write a function class Solution { public int solution(int[] H, int X, int Y); } that given an array H of N integers and two integers
    X and Y, returns the maximum number of different cars that can be produced in the upcoming month by assigning cars to assembly lines

    Examples
    H = [1, 1, 3], X = 1 and Y = 1, the answer should be 2
    H = [6, 5, 5, 4, 3], X = 8 and Y = 9, the answer should be 4 (5 and 3 made on X while 5 and 4 made on Y)
    H = [6, 5, 2, 1, 8], X = 17 and Y = 5, the answer should be 5 (5 made on Y while 8, 6, 2, 1 made on x)
    H = [5, 5, 4, 6], X = 8 and Y = 8, the answer should be 2 (1 car on each line)
    */
    public static int solution31(int[] H, int X, int Y) {
        int[][] dp = new int[X + 1][Y + 1];

        for (int h : H) {
            int[][] newDp = new int[X + 1][Y + 1]; // For storing updated max cars

            for (int x = 0; x <= X; x++) { // Remaining hours on X
                for (int y = 0; y <= Y; y++) { // Remaining hours on Y
                    // carry forward previous state of inserted cars (dont lose previous cars)
                    newDp[x][y] = Math.max(newDp[x][y], dp[x][y]);

                    // try assigning car to X
                    if (h <= x) {
                        newDp[x - h][y] = Math.max(newDp[x - h][y], dp[x][y] + 1);
                    }

                    // try assigning car to Y
                    if (h <= y) {
                        newDp[x][y - h] = Math.max(newDp[x][y - h], dp[x][y] + 1);
                    }
                }
            }

            dp = newDp;
        }

        // find max in dp table
        int maxCars = 0;
        for (int x = 0; x <= X; x++)
            for (int y = 0; y <= Y; y++)
                maxCars = Math.max(maxCars, dp[x][y]);

        return maxCars;
    }
    /*
    Problem 32
    There is an array consisting of N two-digit numbers. A group of numbers can be chosen from the array if every pair of chosen
    numbers has at least one common digit. For example, numbers 25 and 56 can be chosen together as they have a common digit 5,
    but 11 and 12 cannot be chosen together. What is the maximum number of array elements that can be chosen together?

    Write a function class Solution { public int solution(int[] numbers); } that, given an array numbers made of N integers
    returns the maximum number of its elements that can be chosen

    Example 1
    numbers = [50, 30, 15, 51, 10, 20, 15] numbers 50, 15, 51, 10, and 15 can be chosen. The function should return 5
    50 -> 15 shares 5
    15 -> 51 shares 1 and 5
    51 - 10 shares 1
    10 -> 50 shares 0


    Example 2
    numbers = [11, 33, 55] no two numbers have a common digit we return 1
    */

    public static int solution32(int[] numbers) {
        int n = numbers.length;

        // Build digit sets
        // numbers[i] = 51 then digits[i] = [5,1]
        int[][] digits = new int[n][2];
        for (int i = 0; i < n; i++) {
            int a = numbers[i] / 10; // Stores ones val Ex: 51 stores 1
            int b = numbers[i] % 10; // Stores tens val Ex: 51 stores 5
            digits[i][0] = a;
            digits[i][1] = b;
        }

        // Build adjacency matrix
        // If numbers[i] and numbers[j] share at least one digit update share
        boolean[][] share = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (digits[i][0] == digits[j][0] ||
                    digits[i][0] == digits[j][1] ||
                    digits[i][1] == digits[j][0] ||
                    digits[i][1] == digits[j][1]) {
                    share[i][j] = share[j][i] = true;
                }
            }
        }

        // Brute force maximum clique (n ≤ 30 realistic)
        int max = 1;
        int total = 1 << n; // num of all subsets of n vertices

        for (int mask = 1; mask < total; mask++) {
            int count = Integer.bitCount(mask);

            if (count <= max) continue;

            if (isClique(mask, share, n)) {
                max = count;
            }
        }

        return max;
    }

    private static boolean isClique(int mask, boolean[][] share, int n) {
        // Checks if bit i is in subset (i in subset)
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) continue;

            for (int j = i + 1; j < n; j++) {
                //checks if bit j is in subset (j in subset)
                if ((mask & (1 << j)) == 0) continue;

                //checks if shared digit
                if (!share[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // Problem 29
        // int[] A1 = {2, 1, 1, 0, 1};
        // System.out.println(solution29(3, 2, A1));

        // int[] A2 = {0, 0, 1, 1, 2};
        // System.out.println(solution29(2, 3, A2));

        // int[] A3 = {2, 0, 2, 0};
        // System.out.println(solution29(2, 2, A3));

        // Problem 30
        // System.out.println(solution30("a?bb"));     // aabb
        // System.out.println(solution30("??abb"));    // ababb / bbabb / baabb
        // System.out.println(solution30("a?b?aa"));   // aabbaa
        // System.out.println(solution30("aa??aa"));   // aabbaa
        // System.out.println(solution30("???"));      // aba or bab

        // Problem 31
        // int[] A1 = {1, 1, 3};
        // System.out.println(solution31(A1, 1, 1)); // ➜ 2

        // int[] A2 = {6, 5, 5, 4, 3};
        // System.out.println(solution31(A2, 8, 9)); // ➜ 4

        // int[] A3 = {6, 5, 2, 1, 8};
        // System.out.println(solution31(A3, 17, 5)); // ➜ 5

        // int[] A4 = {5, 5, 4, 6};
        // System.out.println(solution31(A4, 8, 8)); // ➜ 2

        // Problem 32
        int[] nums1 = {50, 30, 15, 51, 10, 20, 15};
        System.out.println(solution32(nums1)); // ➜ 5

        int[] nums2 = {11, 33, 55};
        System.out.println(solution32(nums2)); // ➜ 1
    }
}
