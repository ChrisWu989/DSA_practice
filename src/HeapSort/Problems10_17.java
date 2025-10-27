package HeapSort;

import java.util.*;
import java.io.*;
import java.lang.Math;

public class Problems10_17 {
    /*
    Problem 14
    Write a function solution that given a string S of length N, replaces all occurences of "plus" with "+" and all occurences "minus" with "-"

    EX: S = "minusplusminus" should return "-+-"
    EX: S = "plusminusminusplus" should return "+--+"
    */
    public static String solution(String S) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < S.length()) {
            // Check if substring starting at i is "plus"
            if (i + 4 <= S.length() && S.substring(i, i + 4).equals("plus")) {
                result.append('+');
                i += 4; // Skip the 4 characters
            }
            // Check if substring starting at i is "minus"
            else if (i + 5 <= S.length() && S.substring(i, i + 5).equals("minus")) {
                result.append('-');
                i += 5; // Skip the 5 characters
            }
        }
        return result.toString();
    }

    /*
    Problem 15
    Your are given a string S, which consists entirely of decimal degits (0-9).
    Using digits from S create a palindromic number with the largest decimal value.
    You should use at least one digit and you can reorder the digits.
    A palindromic number remains the same when its digits are reversed such as 7, 44, or 83238.
    Any Palindromic number you create should not; however, have any leading zeros such as 0990 or 010.
    Write a function that given a string S of N digits, returns the tstring representing the palindromic number with the largest value.

    Examples:
    Given "39878" we return "898"
    Given "00900" we return "9"
    Given "0000" we return "0"
    Given "54321" we return "5"
    */
    public static String largestPalindrome(String S) {
        //stores frequency of each digit 0-9
        int[] freq = new int[10];
        for (char c : S.toCharArray()) {
            freq[c - '0']++;
        }

        StringBuilder left = new StringBuilder();
        String middle = "";

        // Step 1: Build left half greedily from 9 down to 0
        for (int digit = 9; digit >= 0; digit--) {
            if (freq[digit] >= 2) {             // if a digit has 2 or more
                int pairs = freq[digit] / 2;    // how many pairs

                // Skip leading zeros for now; only use them if all digits are zero
                if (digit == 0 && left.length() == 0) {
                    continue;
                }

                for (int i = 0; i < pairs; i++) {
                    left.append(digit);
                }
                freq[digit] -= 2 * pairs;
            }
        }

        // Step 2: Find middle (highest remaining odd digit)
        for (int digit = 9; digit >= 0; digit--) {
            if (freq[digit] > 0) {
                middle = String.valueOf(digit);
                break;
            }
        }

        // Step 3: Form right half as reverse of left
        String right = new StringBuilder(left).reverse().toString();

        // Step 4: Remove leading zeros, unless all zeros
        while (left.length() > 0 && left.charAt(0) == '0') {
            left.deleteCharAt(0);
        }

        // For zeros
        if (left.length() == 0 && middle.equals("")) {
            return "0";
        }

        // Step 5: Combine
        return left.toString() + middle + right;
    }

    /*
    Problem 20
    A string made of an even number of characters ("<" and/or ">") is called symmetric 
    if all its characters in its first half are "<" and all its characters in its second half are ">"

    Write a function that given a string S made of N characters ("<", ">" and/or "?") returns the length of the 
    longest symmetric substring that can be obtained after replacing question marks with "<" or ">" characters

    Examples:
    S = "<><??>>" turns into " <><<><>" and the longest symmetric substring is "<<>>" so we return 4
    S = "??????" turns into "<<<>>>" and the longest symmetric substring is "<<<>>>" so we return 6
    S = "<<?" turns into "<<>" and the longest symmetric substring is "<>" so we return 2
    */
    public static int longestSymmetric(String S) {
        int n = S.length();
        int maxLen = 0;

        for (int center = 0; center < n - 1; center++) {
            int left = center;
            int right = center + 1;
            int currLen = 0;

            // Expand while characters can form a symmetric pair
            while (left >= 0 && right < n && isMatch(S.charAt(left), S.charAt(right))) {
                currLen += 2;
                maxLen = Math.max(maxLen, currLen);
                left--;
                right++;
            } // <<>>
        }

        return maxLen;
    }

    private static boolean isMatch(char left, char right) {
        // Left must be '<' or '?'
        // Right must be '>' or '?'
        return (left == '<' || left == '?') && (right == '>' || right == '?');
    }

    /*
    Problem 21
    In base -2, integers are represented by sequences of bits in the following way. bits are ordered from the least to the most significant.
    A sequence B of N bits represents the number sum( B[i]*(-2)^i for i = 0 ... N-1 ). The empty sequence represents 0.

    Examples
    100111 represents -23
    001011 represents -12
    10011 represents 0
    0011 represents 4

    Write a function that given an array A of M bits, containing a sequence representing some integer X, returns 
    the shortest sequence of bits representing ceiling(X/2) wehre ceiling(Y) is the smallest integer that is bigger than or equal to Y

    Examples:
    Given A = [1, 0, 0, 1, 1] and (X= 9) the function should return [1, 0, 1] and (ceiling(X/2) == 5)
    Given A = [1, 0, 0, 1, 1, 1] and (X= -23) the function should return [1, 0, 1, 0, 1, 1] and (ceiling(X/2) == -11)
    */

    // Turns bits array into a decimal integer
    public static int fromBaseNeg2(int[] bits) {
        int val = 0;
        int power = 1;
        for (int bit : bits) {
            val += bit * power;
            power *= -2;
        }
        return val;
    }

    // Turns decimal integer into a bits array
    public static List<Integer> toBaseNeg2(int num) {
        if (num == 0) return Arrays.asList(0);

        List<Integer> result = new ArrayList<>();

        while (num != 0) {
            int remainder = num % -2;
            num /= -2;

            if (remainder < 0) {
                remainder += 2;
                num += 1;
            }

            result.add(remainder);
        }

        // remove trailing zeros if any
        while (result.size() > 1 && result.get(result.size() - 1) == 0) {
            result.remove(result.size() - 1);
        }

        return result;
    }

    public static List<Integer> solve(int[] A) {
        int val = fromBaseNeg2(A);
        int target = (int)Math.ceil(val / 2.0);
        return toBaseNeg2(target);
    }

    /* 
    Problem 22
    Given are an array A of size N and an integer K
    Perform the following operations until the array size becomes less than K
        - Remove K smallest integers from the array nums and calculate their average
        - add the floor value of the caluclated avg to the array
            - floor value prints closest integer less than or equal to a given number
    Find all the array elements in ascending order in a single line
    */
    public static int arrayReduction(int N, int K, int[] A) {
        //this is default OUTPUT. You can Change it
        int size = N;

        while (size >= K) {
            // Sort only the valid part of the array so begining
            Arrays.sort(A, 0, size);

            // Compute sum of first K (smallest) elements
            int sum = 0;
            for (int i = 0; i < K; i++) {
                sum += A[i];
            }

            // Compute floor of average
            int avgFloor = (int) Math.floor(sum / (double) K);

            // Shift the remaining elements left to remove first K
            for (int i = K; i < size; i++) {
                A[i - K] = A[i];
            }

            // Place the average at the end
            size = size - K + 1;
            A[size - 1] = avgFloor;
        }

        // Sort the final remaining array
        Arrays.sort(A, 0, size);

        // Print final elements in ascending order
        for (int i = 0; i < size; i++) {
            System.out.print(A[i]);
            if (i < size - 1) System.out.print(" ");
        }
        System.out.println();

        return size;
    }

    public static void main(String[] args) {
        // Problem 14
        // System.out.println(solution("minusplusminus")); // Output: -+-
        // System.out.println(solution("plusminusminusplus")); // Output: +--+
        // System.out.println(solution("plusplusminusminus")); // Output: ++--

        // Problem 15
        // System.out.println(largestPalindrome("39878")); // 898
        // System.out.println(largestPalindrome("00900")); // 9
        // System.out.println(largestPalindrome("0000"));  // 0
        // System.out.println(largestPalindrome("54321")); // 5
        // System.out.println(largestPalindrome("444947137")); // 7449447

        // Problem 20
        // System.out.println(longestSymmetric("<><??>>"));  // 4
        // System.out.println(longestSymmetric("??????"));   // 6
        // System.out.println(longestSymmetric("<<?"));      // 2

        // Problem 21
        // int[] A1 = {1, 0, 0, 1, 1};       // X = 9
        // int[] A2 = {1, 0, 0, 1, 1, 1};    // X = -23

        // System.out.println(solve(A1));  // Expected [1, 0, 1]  (ceiling(X/2) == 5)
        // System.out.println(solve(A2));  // Expected [1, 0, 1, 0, 1, 1] (ceiling(X/2) == -11)

        // Problem 22
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        
        sc.close();
        arrayReduction(N, K, A);
    }
}

 //queue stack linkedlist assessment MONDAY