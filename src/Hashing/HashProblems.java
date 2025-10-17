package Hashing;
import java.util.*;

public class HashProblems {
    /* 
    Leetcode 15: 3Sum
    Problem 1
    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
    Find all unique triplets in the array which gives the sum of zero.
    The solution set must not contain duplicate triplets.

    Example:
    Given array nums = [-1, 0, 1, 2, -1, -4],

    A solution set is:
    [
    [-1, 0, 1],
    [-1, -1, 2]
    ]
    */
    public static List<List<Integer>> threeSum(int[] nums){
        Set<List<Integer>> result = new HashSet<>(); // Hashset to store our unique triplets

        for (int i = 0; i < nums.length - 2; i++) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int j = i + 1; j < nums.length; j++) {
                int target = -nums[i] - nums[j]; // target = -nums[i] - nums[j] or c = -a - b

                if (map.containsKey(target)) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(triplet); // ensure uniqueness
                    result.add(triplet);
                } else {
                    map.put(nums[j], j); //add val mapped to index j for future
                }
            }
        }

        return new ArrayList<>(result);
    }
    /*
    Leetcode 316: Remove Duplicate Letters
    Problem 2
    Given a string s, remove duplicate letters so that every letter appears once and only once. 
    You must make sure your result is the smallest in lexicographical order among all possible results.

    Example 1:
    Input: s = "bcabc"
    Output: "abc"

    Example 2:
    Input: s = "cbacdcbc"
    Output: "acdb"
    */
    public static String removeDuplicateLetters(String s) {
        Map<Character, Integer> freq = new HashMap<>(); // Track characters that appeared in string
        Set<Character> seen = new HashSet<>(); // Track what characters are already seen
        StringBuilder result = new StringBuilder(); // Final string being returned

        // Step 1: Count frequency of each character
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1); // every char mapped to int that is how frequent they are
        }

        // Step 2: Iterate over the string
        for (char c : s.toCharArray()) {
            freq.put(c, freq.get(c) - 1); // reduce remaining count

            if (seen.contains(c)) continue; // skip if already used

            // Step 3: Remove bigger chars if they appear again later
            while (result.length() > 0) {
                char last = result.charAt(result.length() - 1);

                if (last > c && freq.get(last) > 0) {
                    result.deleteCharAt(result.length() - 1);
                    seen.remove(last);
                } else {
                    break;
                }
            }

            // Step 4: Add current char
            result.append(c);
            seen.add(c);
        }

        return result.toString();
    }

    /*
    Leetcode 202: Happy Number
    Problem 3
    A happy number is a number defined by the following process:
    Starting with any positive integer, replace the number by the sum of the squares of its digits,
    and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
    Example: 
    Input: 19
    Output: true
    Explanation: 
    12 + 92 = 82
    82 + 22 = 68
    62 + 82 = 100
    12 + 02 + 02 = 1
    */
    public static boolean isHappy(int n) {
        HashSet<Integer> seen = new HashSet<>(); // check for dupes

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }

        return n == 1;
    }

    private static int getNext(int n) {
        int total = 0;
        while (n > 0) {
            int digit = n % 10;
            total += digit * digit;
            n /= 10;
        }
        return total;
    }
    /*
    Problem 4
    Given a string, find the length of the longest substring without repeating characters.
    Example 1:
    Input: "abcabcbb"
    Output: 3 
    Explanation: The answer is "abc", with the length of 3. 
    */
    public static int longestSub(String s){
        HashSet<Character> set = new HashSet<>();
        int start = 0, end = 0, maxLen = 0;

        while (end < s.length()) {
            char c = s.charAt(end);

            // Duplicate found → shrink window from left
            while (set.contains(c)) {
                set.remove(s.charAt(start));
                start++;
            }

            set.add(c);
            maxLen = Math.max(maxLen, end - start + 1);
            end++;
        }

        return maxLen;
    }

    /*
    Leetcode 128: Longest Consecutive Sequence problem
    Problem 5
    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
    Your algorithm should run in O(n) complexity.
    Example:
    Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
    */
    public static int longestConsecutive(int[] nums) {
        // Empty Array
        if (nums == null || nums.length == 0) 
            return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        for (int num : nums) {
            // Only start counting if it's the beginning of a sequence
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // Count forward
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longest = Math.max(longest, currentStreak);
            }
        }

        return longest;
    }
    public static void main(String[] args) {
        // Problem 1
        // int[] nums = {-1, 0, 1, 2, -1, -4};
        // List<List<Integer>> triplets = threeSum(nums);

        // for (List<Integer> triplet : triplets) {
        //     System.out.println(triplet);
        // }

        // Problem 2
        // String s1 = "bcabc";
        // String s2 = "cbacdcbc";
        // System.out.println("Input: " + s1 + " → Output: " + removeDuplicateLetters(s1));
        // System.out.println("Input: " + s2 + " → Output: " + removeDuplicateLetters(s2));

        // Problem 3
        // int num1 = 19;
        // int num2 = 2;

        // System.out.println(num1 + " is happy? " + isHappy(num1)); // true
        // System.out.println(num2 + " is happy? " + isHappy(num2)); // false

        // Problem 4
        // System.out.println(longestSub("abcabcbb"));

        // Problem 5
        // int[] nums = {100, 4, 200, 1, 3, 2};
        // System.out.println(longestConsecutive(nums)); // Output: 4 (1,2,3,4)

    }
}
