package Stacks;
/*
 Write stack implemented as an array for 5 elements pop, push, isfull, isempty, peek
 * check parenthesis leetcode question
 */
public class StackArray {
    private int maxSize;
    private int top;
    private int[] stack;

    public StackArray(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1; //empty
    }

    public void push(int val){
        if (isFull()){
            System.out.println("Stack Overflow");
        }
        else{
            top++;
            stack[top] = val;
            System.out.println("Pushed " + val);
        }
    }

    public int pop(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            int popped = stack[top];
            top--;
            System.out.println("Popped " + popped);
            return popped;
        }
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    
    public int peek(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            System.out.println("Top element is " + stack[top]);
            return stack[top];
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is EMPTY.");
        } else {
            System.out.print("Stack elements: ");
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        StackArray s = new StackArray(5);

        s.push(10);
        s.push(20);
        s.push(30);
        s.push(40);
        s.push(50);
        s.push(60); // should show stack full

        s.display();

        s.peek();

        s.pop();
        s.pop();
        s.display();

        s.pop();
        s.pop();
        s.pop();
        s.pop(); // should show stack empty
    }
}
