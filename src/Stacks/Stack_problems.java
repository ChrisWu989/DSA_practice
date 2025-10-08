package Stacks;
/* 
2)Design a stack that supports push, pop, top(peek), and retrieving the minimum and maximum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
peek() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
getMax()--Retrieve the maximum element in the stack.
Input: [1, 2, 5,0, 4, 3, 6,-2,4, 8]
pop()
Output:[ min:-2,max:6]

Input: [1, 2, 5,0, 4, 3, 6,-2,4, 8]
pop(),pop(),pop(),pop()
Output:[ min:0,max:5]
 */

/*
 * 4)Create a data structure twoStacks that represents two stacks.
      Implementation of twoStacks should use only one array, i.e.,
       both stacks should use the same array for storing elements. 
       Following functions must be supported by twoStacks.
push1(int x) –> pushes x to first stack 
push2(int x) –> pushes x to second stack
pop1() –> pops an element from first stack and return the popped element 
pop2() –> pops an element from second stack and return the popped element
Implementation of twoStack should be space efficient.
The idea is to start two stacks from two extreme corners of arr[].
 stack1 starts from the leftmost element, the first element in stack1 is pushed at index 0. 
 The stack2 starts from the rightmost corner, the first element in stack2 is pushed at index (n-1).
  Both stacks grow (or shrink) in opposite direction.
   To check for overflow, all we need to check is for space between top elements of both stacks
 */

 /*
5)Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
Example 4:
Input: s = "abc3[cd]xyz"
Output: "abccdcdcdxyz"
*/

/*
6)Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

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
/*
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

 /*
8)Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced iny left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
In other words, we treat '(' as openning parenthesis and '))' as closing parenthesis.
For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
You can insert the characters '(' and ')' at any position of the string to balance it if needed.
Return the minimum number of insertions needed to make s balanced.
Example 1:
Input: s = "(()))"
Output: 1
Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching. We need to to add one more ')' at the end of the string to be "(())))" which is balanced.
Example 2:

Input: s = "())"
Output: 0
Explanation: The string is already balanced.
Example 3:

Input: s = "))())("
Output: 3
Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
Example 4:

Input: s = "(((((("
Output: 12
Explanation: Add 12 ')' to balance the string.
Example 5:

Input: s = ")))))))"
Output: 5
Explanation: Add 4 '(' at the beginning of the string and one ')' at the end. The string becomes "(((())))))))".
  */

/*
 * 9)Find the sum of the ranges of all the pairs that are present in the stack.

Given 

-    An array of number pairs represents a range in the number line.
-    The pairs are sorted in the ascending order of the first number in the pairs.
-    The pairs are added to stack by checking the following conditions.

Case 1. There is only partial overlap (for e.g. (2, 10) and (6, 12) overlaps partially). For such cases, we update the second value of the top pair in the stack with the second value of the current pair. E.g. 10 in (2, 10) is replaced with 12 from (6, 12) and the top pair in the stack becomes (2, 12).

Case 2. When there is complete overlap, (2, 10) and (4, 8). The second pair range is within the first range. For such cases, we skip the current pair.

-    If there is no overlapping with the top pair in the stack, push the current pair into the stack.
   int[][] pairs={{120 ,199},{120,233},{121,233},{120,235},{238,270}};
So sum of the ranges of pairs in stack are
Range: 235 – 120 = 115 and
Range: 270 – 238 = 32

So 115 + 32 = 147
Hence the output is 147.
 */
public class Stack_problems {
    /*
    Problem 1
    Grace has a string S and a stack A.
    She pushes each character of string S whose ASCII value is divisible by 3 on the stack.
    1)Given a string S as input print the contents of the stack by popping each character from the stack.
    Sample Input
    abcfgh  - denotes the string S
    Output Format
    Print the contents of the stack by popping each character from the stack as per the conditions.
    Sample Output
    fc
    Explanation
    The ASCII values of a,b,c,f,g,h are 97,98,99,102,103,104 respectively out of which 99 and 102 are divisible by 3.
    Therefore, first c then f is pushed on the stack. The contents are c,f.
    Printing the stack by popping each element from the stack we get fc.
    */
    public static void problem1(String str){
        CharStackLinkedList stack = new CharStackLinkedList();
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if (c % 3 == 0){
                stack.push(c);
            }
        }

        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append(stack.pop());
        }

        System.out.println(res.toString());
    }

    /*
    Problem 3
    Given a stack of integers, sort it in ascending order using another temporary stack.
    Examples: 
    Input : [34, 3, 31, 98, 92, 23]
    Output : [3, 23, 31, 34, 92, 98]
    */
    public static StackLinkedList problem3(StackLinkedList stack){
        StackLinkedList tempStack = new StackLinkedList();
        while (!stack.isEmpty()) {
            int temp = stack.pop();

            // Move elements back to input if they're greater than temp
            while (!tempStack.isEmpty() && tempStack.peek() < temp) {
                stack.push(tempStack.pop());
            }

            // Place temp in correct position
            tempStack.push(temp);
        }
        return tempStack;
    }
    public static void main(String[] args) {
        // Problem 1
        // problem1("abcfgh");

        // Problem 2
        StackLinkedList stack = new StackLinkedList();
        stack.push(34);
        stack.push(23);
        stack.push(92);
        stack.push(98);
        stack.push(31);
        stack.push(3);
        stack.push(34);
        stack.display();
        problem3(stack).display();
    }

}

