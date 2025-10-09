package Queues;

public class LinkedListQueue {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;  // points to the front of the queue
    private Node rear;   // points to the rear of the queue

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(int data) {
        Node n = new Node(data);

        if (rear == null) { // empty queue
            front = rear = n;
        } else {
            rear.next = n;
            rear = n;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }

        int removed = front.data;
        front = front.next;

        // if queue becomes empty after dequeue
        if (front == null) {
            rear = null;
        }
        return removed;
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return front == null;
    }

    // Display all elements in the queue
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is EMPTY.");
            return;
        }

        System.out.print("Queue elements: ");
        Node curr = front;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        LinkedListQueue queue = new LinkedListQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);

        queue.display();

        queue.dequeue();
        queue.display();

    }
}
