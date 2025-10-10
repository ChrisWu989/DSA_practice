package Stacks;

public class Parenthesis {
    // Leetcode 20: Valid Parenthesis
    public static boolean isValid(String str) {
        CharStackLinkedList stack = new CharStackLinkedList();

        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);

            // Push opening brackets
            if (s == '(' || s == '[' || s == '{') {
                stack.push(s);
            }

            // Handle closing brackets
            else if (s == ')' || s == ']' || s == '}') {
                if (stack.isEmpty()){
                    return false; // no match
                }

                if (!isMatchingPair(stack.pop(), s)) {
                    return false; // mismatch
                }
            }
        }

        // Stack should be empty at the end
        return stack.isEmpty();
    }

    /*
    Leetcode 32: Longest Valid Parentheses
    Problem 6
    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    Example 1:
    Input: s = "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()".

    Example 2:
    Input: s = ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()".

    Example 3:
    Input: s = ""
    Output: 0
    */
    public static int longestValidParentheses(String s) {
        StackLinkedList stack = new StackLinkedList();
        int max = 0;
        int last = -1; // keeps track of last unmatched ')'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i); // push index of '('
            } 
            else { // c == ')'
                if (stack.isEmpty()) {
                    last = i; // unmatched ')' yes
                } 
                else {
                    stack.pop(); // match found
                    if (stack.isEmpty()) {
                        max = Math.max(max, i - last);
                    } 
                    else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }
        return max;
    }

    /*
    Problem 7
    Minimum Insertions to Balance a Parentheses String
    Example 1:
    Input: s = "(()))"
    Output: 1
    Example 2:
    Input: s = "())"
    Output: 1
    Input: s = "))())("
    Output: 4
    */
    public static int minInsertions(String s){
        CharStackLinkedList stack = new CharStackLinkedList();
        int insertions = 0;

        for(char c : s.toCharArray()){
            if (c == '('){ // Open Paren
                stack.push(c);
            } 
            else if (c == ')'){ // Closed Paren
                if(stack.isEmpty()){
                    insertions++; // need '('
                } 
                else {
                    stack.pop(); // matched
                }
            }
        }

        // unmatched '(' left in stack
        while(!stack.isEmpty()){
            stack.pop();
            insertions++;
        }

        return insertions;
    }

    //check every ({[ matches corresponding ]})
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        // Valid parenthesis
        // if (isValid("{(a + b) * (c + d) + (c * d)]}")) {
        //     System.out.println("VALID");
        // } else {
        //     System.out.println("INVALID");
        // }

        // Problem 6
        System.out.println(longestValidParentheses("(()"));     
        System.out.println(longestValidParentheses(")()())"));   
        System.out.println(longestValidParentheses(""));    
        
        // Problem 7
        // System.out.println(minInsertions("(()))"));   
        // System.out.println(minInsertions("())"));     
        // System.out.println(minInsertions("))())(")); 
    }
}
