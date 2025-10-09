package Stacks;
/*
4)Create a data structure twoStacks that represents two stacks.

Implementation of twoStacks should use only one array,
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
public class TwoStacks {
    private int maxSize;
    private int top1;
    private int top2;
    private int[] stack;

    public TwoStacks(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top1 = -1; 
        top2 = maxSize;
    }

    public void push1(int val){
        if (top1 + 1 == top2){
            System.out.println("Stack Overflow");
        }
        else{
            top1++;
            stack[top1] = val;
        }
    }

    public int pop1(){
        if (top1 == -1){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            int popped = stack[top1];
            top1--;
            return popped;
        }
    }

    public void push2(int val){
        if (top1 + 1 == top2){
            System.out.println("Stack Overflow");
        }
        else{
            top2--;
            stack[top2] = val;
        }
    }

    public int pop2(){
        if (top2 == maxSize){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            int popped = stack[top2];
            top2++;
            return popped;
        }
    }

    public void display() {
        System.out.print("Stack 1: ");
        for (int i = 0; i <= top1; i++) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();

        System.out.print("Stack 2: ");
        for (int i = maxSize - 1; i >= top2; i--) {
            System.out.print(stack[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TwoStacks ts = new TwoStacks(5);
        ts.push1(5);
        ts.push1(10);
        ts.push1(16);

        ts.push2(15);
        ts.push2(20);
        ts.display();

        ts.push2(99);

        ts.pop1();
        ts.pop2();
        ts.display();
    }
}
