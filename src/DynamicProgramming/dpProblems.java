package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class dpProblems {
  /*
  You are given coins of different denominations and a total amount of money amount. 
  Write a function to compute the fewest number of coins that you need to make up that amount. 
  If that amount of money cannot be made up by any combination of the coins, return -1.
  Example 1:
  Input: coins = [1, 2, 5], amount = 11
  Output: 3 
  Explanation: 11 = 5 + 5 + 1

  Example 2:
  Input: coins = [2], amount = 3
  Output: -1

  Example 3:
  Input: coins = [25,10,4], amount = 41
  Output: 5
  Explanation: 41=25+4+4+4+4
                                                            
  Note:
  You may assume that you have an infinite number of each kind of coin.
  */
  public static int coinCount(int[] coins, int amount) {
    // dp[i] = minimum number of coins needed to make amount i
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1); // initialize with a value larger than any possible answer
    dp[0] = 0; // zero coins to make amount 0

    // Go through the amounts one by one
    for (int i = 1; i <= amount; i++) {
      // go through every coin in coins
      for (int coin : coins) {
        // coin less then amount
        if (coin <= i) {
          //Keep current best or add coin to dp[i-c] 
          dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
      }
    }
    // no such combination return -1 else return dp[amoount]
    return dp[amount] > amount ? -1 : dp[amount];
  }

  /*
  Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
  For example, given n = 3, a solution set is:
  [
    "((()))",
    "(()())",
    "(())()",
    "()(())",
    "()()()"
  ]
  */
  public static List<String> allParenthesis(int n){
    List<String> result = new ArrayList<>();
    backtrack(result, "", 0, 0, n);
    return result;
  }

  private static void backtrack(List<String> res, String curr, int left, int right, int n) {
    // maxed out '(' and'')' appends parenthesis set
    if (curr.length() == 2 * n) {
      res.add(curr);
      return ;
    }

    // Add '(' if possible
    if (left < n) {
      backtrack(res, curr + "(", left + 1, right, n);
    }

    // Add ')' if possible
    if (right < left) {
      backtrack(res, curr + ")", left, right + 1, n);
    }
  }

  /*
  Letter Combinations of a Phone Number
  Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
  A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
  Although the answer is in lexicographical order, your answer could be in any order you want.

  Example:
  Input: "23"
  Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]. 
  */

  public static List<String> letterCombo(String digits){
    List<String> result = new ArrayList<>();
    // Phone mappings
    String[] map = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    backtrack(result, "", map, 0, digits);
    return result;
  }
  private static void backtrack(List<String> res, String curr, String[] map, int index, String digits) {
    if (digits.length() == index){
      res.add(curr);
      return;
    }

    String letters = map[digits.charAt(index) - '0'];

    for (char c : letters.toCharArray()) {
      backtrack(res, curr + c, map, index + 1, digits);
    }
  }

  /*
  Problem 34
  An industrial company has N factories, each producinf some pollution every month. The company has
  decided to reduce its total fume emissions by equipping some of the factories with one or more filters

  Every such filter reduces the pollution of a factory by half. when a subsequent filter is applied, it
  again reduces by half the remaining pollution emitted after filling the existing filters. For example, a
  factory that intially produces 6 units of pollution will generate 3 with one filter, 1.5 with 2, 0.75 with 3

  You are given an array of N integers describing the amount of pollution produced by thef actories. Your task
  is to find the min number of filters needed to decrease the total sum of pollution by at least half

  Write a function class Solution { public int solution(int[] A);} that given an array of integers A of
  length N returns the minimum number of filters needed tor educe the total pollution by at least half

  Example 1
  Given A = [5, 19, 8, 1] returns 3
  Two filters at 19 and 1 filter at 8

  Example 2
  Given A = [10, 10] returns 2
  One filter at each factory

  Example 3:
  Given A = [3, 0, 5] returns 2
  One filter at each nonzero factory
  */

  public static void main(String[] args) {
    // int[] coins1 = {1, 2, 5};
    // System.out.println(coinCount(coins1, 11)); // Output: 3

    // int[] coins2 = {2};
    // System.out.println(coinCount(coins2, 3));  // Output: -1

    // int[] coins3 = {25, 10, 4};
    // System.out.println(coinCount(coins3, 41)); // Output: 5

    System.out.println(allParenthesis(3));

    System.out.println(letterCombo("23"));
  }
}
