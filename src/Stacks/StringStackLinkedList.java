package Stacks;

class StringStackLinkedList {
    class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public StringStackLinkedList() {
        this.top = null;
    }

    public void push(String data) {
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public String pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return "";
        } else {
            String popped = top.data;
            top = top.next;
            return popped;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public String peek() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return "";
        } else {
            System.out.println("Top element is " + top.data);
            return top.data;
        }
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is EMPTY.");
            return;
        } else {
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