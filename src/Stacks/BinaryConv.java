package Stacks;

public class BinaryConv {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public BinaryConv() {
        this.top = null;
    }

    public void push(int data){
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public int pop(){
        int popped = top.data;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public static String convert(int num){
        BinaryConv stack = new BinaryConv();
        while (num != 0){
            int remainder = num % 2; // 0 0 0 1 1
            num = num / 2; // 12 6 3 1 0
            stack.push(remainder);
        }

        StringBuilder converted = new StringBuilder();
        while (!stack.isEmpty()){
            converted.append(stack.pop());
        }

        return converted.toString();
    }
    public static void main(String[] args) {
        int num = 24;
        String bin = convert(num);
        System.out.println("Binary of " + num + " is: " + bin);
    }
}
