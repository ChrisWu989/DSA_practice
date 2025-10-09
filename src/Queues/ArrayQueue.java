package Queues;

public class ArrayQueue {
    private int front, rear, maxSize;
    private int[] queue;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = new int[maxSize];
        front = -1; //empty
        rear = -1;
    }

    public void enqueue(int val){
        if (isFull()){
            System.out.println("Queue Overflow");
            return;
        }
        if (front == -1){
            front = 0;
        }
        rear = (rear + 1) % maxSize;
        queue[rear] = val;
    }

    public int dequeue(){
        if (isEmpty()){
            System.out.println("Queue Underflow");
            return -1;
        }

        int val = queue[front];

        if (front == rear) {
            front = -1;
            rear = -1;
        } 
        else {
            front = (front + 1) % maxSize;
        }

        return val;
    }

    public boolean isEmpty(){
        return front == -1;
    }

    public boolean isFull(){
        return ((rear + 1) % maxSize) == front;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is EMPTY");
            return;
        }

        System.out.print("Queue elements: ");
        int i = front;
        while (true) {
            System.out.print(queue[i] + " ");
            if (i == rear) break;
            i = (i + 1) % maxSize;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);  // Queue full here
        q.display();

        q.enqueue(50);
        q.dequeue();
        q.dequeue();
        q.display();

        q.enqueue(60);
        q.enqueue(70);
        q.display();
    }
}
