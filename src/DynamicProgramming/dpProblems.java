package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    int val, left, right;
    Node(int val, int left, int right) {
        this.val = val; 
        this.left = left;
        this.right = right;
    }
}

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
  Problem 115
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
  Problem 34/35
  An industrial company has N factories, each producing some pollution every month. The company has
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
  public static int solution34(int[] A){
    PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    double total = 0; //Total polution

    for (int x : A) {
      total += x;
      maxHeap.offer((double) x); //Every factory offered into queue
    }

    double target = total / 2; //Target = half pollution
    double reduced = 0; // How much we have reduced want this less than target
    int filters = 0; // How many filters we need

    while (reduced < target) {
      double largest = maxHeap.poll();     // most polluting factory
      double newVal = largest / 2;         // apply filter

      reduced += largest - newVal;         // pollution reduced
      filters++;

      maxHeap.offer(newVal);              // push back updated pollution
    }

    return filters;
  }

  /*
  Problem 52
  Binary Search Tree: Beautiful Lending Tree
  A lending tree of N people is defined using a binary tree of N nodes, where each node represents a person.
  For each person, the left child is the person from whom one has borrowed money.
  Whereas, the right child is the person to whom one has lent money.
  A node’s value is equal to the amount of money a person currently has.

  A good lending tree has a condition by which the amount of money borrowed by each person should be less 
  than the amount of the person currently has, which in turn should be less than the amount of money one has lent. 
  When this condition is enforced at a subtree level and not just at the node level, the lending tree becomes a beautiful a lending tree.

  Hence, given a lending tree, find if it is beautiful or not. Print 1 if beautiful and print 0 if not

  Input Format
  The first line of the input contains an integer N, denoting the number of people in the lending tree.
  Each line i of the N subsequent lines contains 4 space-separated integers, denoting the index, value, index of the left child, index of right child of the i'th node of the lending tree.
  Note that if there is no left or right child, then the index will be -1. Note that the person with index 1 will always be the root of the tree.

  Sample Input
  6
  1 5 2 3
  2 2 4 -1
  4 1 -1 -1
  3 8 5 6
  5 6 -1 -1
  6 10 -1 -1 

  Sample Output
  1

  Explanation 
  Here, for each node, the amount of money borrowed by each person is less than the amount of the person currently has, which in turn should be less than the amount of money one has lent. 
  Note that is also true at the subtree level. Hence, the given lending tree is beautiful. Hence, the answer is 1.
  */

  static Node[] tree;

  public static int isBeautiful(Node[] nodes){
    tree = nodes;
    return isBST(1, (int) Long.MIN_VALUE, (int) Long.MAX_VALUE) ? 1 : 0;
  }

  // Check left subtree val < node val < right subtree val
  // borrowing val < current val < lending Val
  public static boolean isBST(int index, int borrow, int lend){
    if (index == -1) return true;

    Node node = tree[index];

    if (node.val <= borrow || node.val >= lend) return false;

    return isBST(node.left, borrow, node.val) && isBST(node.right, node.val, lend);
  }

  public static void main(String[] args) {
    // Coin DP problem
    // int[] coins1 = {1, 2, 5};
    // System.out.println(coinCount(coins1, 11)); // Output: 3

    // int[] coins2 = {2};
    // System.out.println(coinCount(coins2, 3));  // Output: -1

    // int[] coins3 = {25, 10, 4};
    // System.out.println(coinCount(coins3, 41)); // Output: 5

    // Parenthesis DP problem
    // System.out.println(allParenthesis(3));

    // Lettercombo problem
    // System.out.println(letterCombo("23"));

    // Problem 34/35
    int[] fac1 = {5, 19, 8, 1};
    System.out.println(solution34(fac1)); // Output: 3

    int[] fac2 = {10, 10};
    System.out.println(solution34(fac2));  // Output: 2

    int[] fac3 = {3, 0, 5};
    System.out.println(solution34(fac3)); // Output: 2

    // Problem 52
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt(); 
    Node[] nodes = new Node[n + 1]; // First line int N

    for (int i = 0; i < n; i++) { // Subsequent lines
      int idx = sc.nextInt();
      int val = sc.nextInt();
      int left = sc.nextInt();
      int right = sc.nextInt();
      nodes[idx] = new Node(val, left, right);
    }

    System.out.println(isBeautiful(nodes));
  }
}
