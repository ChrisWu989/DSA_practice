package Stacks;
/*
 * Write a stack implemented as a linked list pop, push, isfull, isempty, peek
 * check parenthesis leetcode question 
 * 
 * A-B+C -> AB-C+
 * A+B*C -> ABC*+
 * (A+B)*(C-D) -> AB+CD-*
 * ((A+B)+(C-D)+E)/(F+G) -> AB+CD-+E+FG+/
 * (A+B*(C-D))/E -> ABCD-*+E/
 * (A-B)-C+D*E+F -> AB-C-DE*+F+
 */
public class StackLinkedList {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack
    private int size;

    public StackLinkedList() {
        this.top = null;
        this.size = 0;
    }

    public void push(int data){
        Node n = new Node(data);
        n.next = top;
        top = n;
        size++;
        System.out.println("Pushed " + data);
    }

    public int pop(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        else {
            int popped = top.data;
            top = top.next;
            size--;
            System.out.println("Popped " + popped);
            return popped;
        }
    }

    public boolean isEmpty(){
        return top == null;
    }

    public int peek(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return -1;
        }
        else{
            System.out.println("Top element is " + top.data);
            return top.data;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is EMPTY.");
            return;
        }
        else {
            Node curr = top;
            System.out.print("Stack elements: ");
            while (curr != null) {
                System.out.print(curr.data + " ");
                curr = curr.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        StackLinkedList stack = new StackLinkedList();

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);

        stack.display();

        stack.peek();

        stack.pop();
        stack.pop();
        stack.display();

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop(); // should show stack empty
    }
}
