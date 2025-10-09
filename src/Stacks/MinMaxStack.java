package Stacks;
/* 
Leetcode 155: Min Stack + Max Stack
Problem 2
Design a stack that supports push, pop, top(peek), and retrieving the minimum and maximum element in constant time.
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

Essentially same as a regular linkedlist stack with 2 more conditions max and min elements
Compare min and max of stack to data we are pushing
Store the new min and max so the top will always be updated to have min and max
Peek already looks at top element
getMin getMax look at top elements min and max 
 */
public class MinMaxStack {
    class Node {
        int data;
        int min;
        int max;
        Node next;

        Node(int data, int min, int max) {
            this.data = data;
            this.min = min;
            this.max = max;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public MinMaxStack() {
        this.top = null;
    }

    public void push(int data){
        if (isEmpty()) {
            top = new Node(data, data, data);
        }
        else {
            int newMin = Math.min(data, top.min);       // min val
            int newMax = Math.max(data,top.max);        // max val
            Node n = new Node(data, newMin, newMax);    // insert new node tracking min and max
            n.next = top;
            top = n;
        }
    }

    // Same pop as LinkedList Stack
    public int pop(){
        if (isEmpty()){
            return -1;
        }
        else {
            int popped = top.data;
            top = top.next;
            return popped;
        }
    }

    // Same peek as LinkedList Stack
    public int peek(){
        if (isEmpty()){
            return -1;
        }
        else{
            return top.data;
        }
    }

    public int getMin(){
        if (isEmpty()){
            return -1;
        }
        else{
            return top.min;
        }
    }

    public int getMax(){
        if (isEmpty()){
            return -1;
        }
        else{
            return top.max;
        }
    }

    // Same isEmpty as LinkedList Stack
    public boolean isEmpty(){
        return top == null;
    }

    public static void main(String[] args) {
        MinMaxStack stack = new MinMaxStack();
        int[] arr = {1, 2, 5, 0, 4, 3, 6, -2, 4, 8};
        for (int x : arr) stack.push(x);

        // Example 1
        stack.pop(); // Pop 8
        System.out.println("Min: " + stack.getMin() + ", Max: " + stack.getMax());

        // Example 2
        stack.pop(); // Pop 4
        stack.pop(); // Pop -2
        stack.pop(); // Pop 6
        System.out.println("Min: " + stack.getMin() + ", Max: " + stack.getMax());
    }
}
