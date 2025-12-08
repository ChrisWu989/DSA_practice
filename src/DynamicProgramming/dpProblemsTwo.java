package DynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class dpProblemsTwo {
    /*
    Problem 36
    Below there is code that finds the maximum even element in list numbers and prints it

    def find_maximum_even(numbers):
        maximum = 0
        for x in numbers:
            if x % 2 == 0 and x > maximum:
                maximum = x
        print(maximum)
    
    write a function that given a list, prints the sum of the largest odd # and the largest even # in list numbers
    If there are no odd or no even numbers in list, you can assume that the largest one in the group is 0

    Examples
    Given numbers = [5, 3, 10, 6, 11] we print 21
    Given numbers = [20, 10, 7, 5] we print 27
    Given numbers = [7, 13, 15, 13] we print 15
    Given numbers = [2, 6, 4, 6] we print 6
    */
    public static void solution36(int[] numbers){
        System.out.println(find_max_even(numbers) + find_max_odd(numbers));
    }
    
    public static int find_max_even(int[] numbers){
        int maximum = 0;
        for (int num : numbers){
            if (num % 2 == 0 && num > maximum){
                maximum = num;
            }
        }
        return maximum;
    }

    public static int find_max_odd(int[] numbers){
        int maximum = 0;
        for (int num : numbers){
            if (num % 2 != 0 && num > maximum){
                maximum = num;
            }
        }
        return maximum;
    }

    /*
    Problem 37
    There is an array A of N integers. What is the largest sum of two numbers which do not have any common digits
    Write a function that given an array of length N, returns largest possible sum of two numbers from A which do not
    have any digits in common. If it is impossible to choose two such numbers, the funciton should return -1

    Examples
    Given A = [53, 1, 36, 103, 53, 5] we return 108
    Given A = [9, 19, 99, 29] we return -1
    Given A = [6, 17, 71, 66, 17, 6] we return 137
    Given A = [15, 0, 105] we return 15
    */
    public static int solution37(int[] A) {
        int n = A.length;
        int maxSum = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!shareDigits(A[i], A[j])) {
                    maxSum = Math.max(maxSum, A[i] + A[j]);
                }
            }
        }
        return maxSum;
    }

    public static boolean shareDigits(int a, int b) {
        boolean[] seen = new boolean[10];

        // mark digits of 'a'
        if (a == 0) seen[0] = true;
        while (a > 0) {
            seen[a % 10] = true;
            a /= 10;
        }

        // check digits of 'b'
        if (b == 0 && seen[0]) return true;
        while (b > 0) {
            if (seen[b % 10]) return true;
            b /= 10;
        }

        return false;
    }
    /*
    Problem 38
    Given an array A consisting of N numbers. In one move you can delete either the first two, the last two, or the first 
    and last elements of A. No move can be performed if the length of A < 2. Result is sum of deleted elements.Write a function 
    that given an array of N integers returns max number of moves that can be performed on A, such that all the moves have the same result

    Examples
    A = [3, 1, 5, 3, 3, 4, 2] returns 3 --> [4, 2], [3, 3], [1, 5]
    A = [4, 1, 4, 3, 3, 2, 5, 2] returns 4
    A = [1, 9, 1, 1, 1, 1, 1, 1, 8, 1] returns 1
    A = [1, 1, 2, 3, 1, 2, 2, 1, 1, 2] returns 4
    */
    public static int solution38(int[] A){
        if (A.length < 2) return 0;

        Set<Integer> most = new HashSet<>();

        int n = A.length;

        // First two
        most.add(A[0] + A[1]);

        // Last two
        most.add(A[n - 1] + A[n - 2]);

        // First and Last
        most.add(A[0] + A[n - 1]);

        int res = 0;
        for (int target : most) {
            res = Math.max(res, dfs(A, 0, A.length - 1, target));
        }
        return res;
   }

    private static int dfs(int[] A, int left, int right, int target) {
        if (right - left + 1 < 2) return 0;

        int best = 0;

        // First two
        if (left + 1 <= right && A[left] + A[left + 1] == target) {
            best = Math.max(best, 1 + dfs(A, left + 2, right, target));
        }

        // Last two
        if (right - 1 >= left && A[right] + A[right - 1] == target) {
            best = Math.max(best, 1 + dfs(A, left, right - 2, target));
        }

        // First and Last
        if (left < right && A[left] + A[right] == target) {
            best = Math.max(best, 1 + dfs(A, left + 1, right - 1, target));
        }

        return best;
    }
    /*
    Problem 39
    Given a string S, in one move you can erase from S a pair of identical letters. Find the shortest possible string created this way.
    If there are multiple, choose the alphabetically smallest one. Note that there is no limit to the number of moves
    Write a function that given a string S of length N returns the shortest string created by erasing pairs of identical letters from S

    Examples
    Given S = "CBCAAXA"
        - Remove pair of "C" --> "BAAXA"
        - Remove pair of "A" --> "BAX"
    
    Given S = "ZYXZYZY"
        - Remove pair of "Y" --> "ZYXZYZY" --> "ZXZYZ"
        - Remove pair of "Z" --> "ZXZYZ" --> "XYZ"
    
    Given S = "ABCBACDDAA" --> ""

    Given S = "AKFXFMOGKFB" --> "AFKMOGB"
    */
    public static String solution39(String S) {
        int n = S.length();
        Map<String, String> memo = new HashMap<>();
        return erasePairs(S, 0, n - 1, memo);
    }

    // S is our string
    // i is index 0
    // j is last index
    // memo aches solved i and j ranges
    public static String erasePairs(String s, int i, int j, Map<String, String> memo) {
        // Base cases
        if (i > j) return "";
        if (i == j) return s.substring(i, i + 1); // Only one letter string

        String key = s + "|" + i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);

        // Option 1: keep s[i]
        String best = s.substring(i, i + 1) + erasePairs(s, i + 1, j, memo);

        // Option 2: pair s[i] with s[k]
        // Remove a pair
        for (int k = i + 1; k <= j; k++) {
            // equal pair we can remove
            if (s.charAt(i) == s.charAt(k)) {
                String left = erasePairs(s, i + 1, k - 1, memo); // whats left inbetween our removed pair
                String right = erasePairs(s, k + 1, j, memo); // whats on the right of our removed pair
                String combined = left + right; // add up combined val

                // Choose smallest by length, then alphabetically
                if (combined.length() < best.length() ||
                   (combined.length() == best.length() && combined.compareTo(best) < 0)) {
                    best = combined;
                }
            }
        }

        memo.put(key, best);
        return best;
    }
    /*
    Problem 40
    Consider infinite sequence 0, 1, 1, 2, 3, 5, 8, 13, 12, 7, 10, 8, 9, ...
    The 0th element is 0 and the 1st element is 1. The successive elements are defined recursively.
    Each of them is the sum of the separate digits of the two previous elements
    
    Write a function that iven an integer N returns the N-th element of the above sequence
    N = 2 returns 1
    N = 6 returns 8
    N = 10 returns 10
    */
   public static int solution40(int N){
        if (N == 0) return 0;
        if (N == 1) return 1;

        int a = 0; // a[0]
        int b = 1; // a[1]

        for (int i = 2; i <= N; i++) {
            int next = sumDigits(a) + sumDigits(b);
            a = b;
            b = next;
        }
        return b;
    }

    private static int sumDigits(int x) {
        int sum = 0;
        x = Math.abs(x);
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        // Problem 36
        // int[] num1 = {5, 3, 10, 6, 11};
        // solution36(num1);   // 21

        // int[] num2 = {20, 10, 7, 5};
        // solution36(num2);   // 27

        // int[] num3 = {7, 13, 15, 13};
        // solution36(num3);   // 15

        // int[] num43 = {2, 6, 4, 6};
        // solution36(num43);  // 6

        // Problem 37
        // int[] A1 = {53, 1, 36, 103, 53, 5};
        // int[] A2 = {9, 19, 99, 29};
        // int[] A3 = {6, 17, 71, 66, 17, 6};
        // int[] A4 = {15, 0, 105};

        // System.out.println("Test 1 Expected: 108   Result: " + solution37(A1));
        // System.out.println("Test 2 Expected: -1    Result: " + solution37(A2));
        // System.out.println("Test 3 Expected: 137   Result: " + solution37(A3));
        // System.out.println("Test 4 Expected: 15    Result: " + solution37(A4));

        // Problem 38
        // System.out.println(solution38(new int[]{3, 1, 5, 3, 3, 4, 2})); // 3
        // System.out.println(solution38(new int[]{4, 1, 4, 3, 3, 2, 5, 2})); // 4
        // System.out.println(solution38(new int[]{1, 9, 1, 1, 1, 1, 1, 1, 8, 1})); // 1
        // System.out.println(solution38(new int[]{1, 1, 2, 3, 1, 2, 2, 1, 1, 2})); // 4

        // Problem 39
        // System.out.println(solution39("CBCAAXA"));      // "BAX"
        // System.out.println(solution39("ZYXZYZY"));      // "XYZ"
        // System.out.println(solution39("ABCBACDDAA"));   // ""
        // System.out.println(solution39("AKFXFMOGKFB"));  // "AKFMOGB"

        // Problem 40
        System.out.println(solution40(2));
        System.out.println(solution40(6));
        System.out.println(solution40(10));
    }
}
