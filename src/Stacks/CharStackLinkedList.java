package Stacks;

class CharStackLinkedList{
    class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public CharStackLinkedList() {
        this.top = null;
    }

    public void push(char data){
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public char pop(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return '\0';
        }
        else {
            char popped = top.data;
            top = top.next;
            return popped;
        }
    }

    public boolean isEmpty(){
        return top == null;
    }

    public char peek(){
        if (isEmpty()){
            System.out.println("Stack Underflow");
            return '\0';
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
}
