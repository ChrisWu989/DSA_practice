package Queues;

public class ArrayDeque {
    private int front, rear, maxSize;
    private int[] deque;

    public ArrayDeque(int size) {
        maxSize = size;
        deque = new int[maxSize];
        front = -1;
        rear = -1;
    }

    // Check if deque is full
    public boolean isFull() {
        return (front == 0 && rear == maxSize - 1) || (front == rear + 1);
    }

    // Check if deque is empty
    public boolean isEmpty() {
        return front == -1;
    }

    // Insert at front
    public void insertFront(int val) {
        if (isFull()) {
            System.out.println("Deque Overflow (Front)");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        } 
        else if (front == 0) {
            front = maxSize - 1;
        } 
        else {
            front--;
        }

        deque[front] = val;
    }

    // Insert at rear
    public void insertRear(int val) {
        if (isFull()) {
            System.out.println("Deque Overflow (Rear)");
            return;
        }

        if (isEmpty()) {
            front = 0;
            rear = 0;
        }
        else if (rear == maxSize - 1) {
            rear = 0;
        } 
        else {
            rear++;
        }

        deque[rear] = val;
    }

    // Delete from front
    public int deleteFront() {
        if (isEmpty()) {
            System.out.println("Deque Underflow (Front)");
            return -1;
        }

        int val = deque[front];

        if (front == rear) 
        { // single element
            front = -1;
            rear = -1;
        } 
        else if (front == maxSize - 1) {
            front = 0;
        } 
        else {
            front++;
        }

        return val;
    }

    // Delete from rear
    public int deleteRear() {
        if (isEmpty()) {
            System.out.println("Deque Underflow (Rear)");
            return -1;
        }

        int val = deque[rear];

        if (front == rear) { // single element
            front = -1;
            rear = -1;
        } 
        else if (rear == 0) {
            rear = maxSize - 1;
        } 
        else {
            rear--;
        }

        return val;
    }

    // Get front element
    public int getFront() {
        if (isEmpty()) {
            System.out.println("Deque is EMPTY");
            return -1;
        }
        return deque[front];
    }

    // Get rear element
    public int getRear() {
        if (isEmpty()) {
            System.out.println("Deque is EMPTY");
            return -1;
        }
        return deque[rear];
    }

    // Display deque
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is EMPTY");
            return;
        }

        System.out.print("Deque elements: ");
        int i = front;
        while (true) {
            System.out.print(deque[i] + " ");
            if (i == rear)
                break;
            i = (i + 1) % maxSize;
        }
        System.out.println();
    }

    // Test
    public static void main(String[] args) {
        ArrayDeque dq = new ArrayDeque(5);

        dq.insertRear(10);
        dq.insertRear(20);
        dq.insertFront(5);
        dq.insertFront(2);
        dq.display(); // 2 5 10 20

        dq.deleteRear();
        dq.display(); // 2 5 10

        dq.deleteFront();
        dq.display(); // 5 10

        System.out.println("Front: " + dq.getFront());
        System.out.println("Rear: " + dq.getRear());
    }
}