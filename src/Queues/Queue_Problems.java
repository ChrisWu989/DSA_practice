package Queues;

import java.util.Stack;

public class Queue_Problems {
    /*
    Problem 1
    Given a queue of integers of even length, rearrange the elements by interleaving the first
    half of the queue with the second half of the queue.
    Only a stack can be used as an auxiliary space.
    Examples:
    Input :  1 2 3 4
    Output : 1 3 2 4
    Input : 11 12 13 14 15 16 17 18 19 20
    Output : 11 16 12 17 13 18 14 19 15 20
    Time complexity: O(n).
    Auxiliary Space: O(n).
    */
    public static void interleave(ArrayQueue q) {
        if (q.isEmpty()) return;// Empty Check

        int size = 0;
        ArrayQueue temp = new ArrayQueue(100);

        while (!q.isEmpty()) {
            temp.enqueue(q.dequeue());
            size++;
        }

        // move elements back to q
        while (!temp.isEmpty()) {
            q.enqueue(temp.dequeue());
        }

        if (size % 2 != 0) { // Even Check
            return;
        }

        Stack<Integer> stack = new Stack<>();
        int half = size / 2;

        // Step 1: Push first half into stack
        for (int i = 0; i < half; i++) {
            stack.push(q.dequeue());
        }

        // Step 2: Enqueue back the stack contents
        while (!stack.isEmpty()) {
            q.enqueue(stack.pop());
        }

        // Step 3: Move first half (now second half originally) to back to restore order
        for (int i = 0; i < half; i++) {
            q.enqueue(q.dequeue());
        }

        // Step 4: Again push first half (original first half) into stack
        for (int i = 0; i < half; i++) {
            stack.push(q.dequeue());
        }

        // Step 5: Interleave elements of stack and queue
        while (!stack.isEmpty()) {
            q.enqueue(stack.pop());
            q.enqueue(q.dequeue());
        }
    }

    /*
    Problem 2
    Given an integer k and a queue of integers, we need to reverse the order of the first k elements of the queue,
    leaving the other elements in the same relative order.
    Only following standard operations are allowed on queue.

    enqueue(x) : Add an item x to rear of queue
    dequeue() : Remove an item from front of queue
    size() : Returns number of elements in queue.
    front() : Finds front item.
    Examples:

    Input : Q = [10, 20, 30, 40, 50, 60,
                70, 80, 90, 100]
            k = 5
    Output : Q = [50, 40, 30, 20, 10, 60,
                70, 80, 90, 100]

    Input : Q = [10, 20, 30, 40, 50, 60,
                70, 80, 90, 100]
            k = 4
    Output : Q = [40, 30, 20, 10, 50, 60,
                70, 80, 90, 100]
    The idea is to use an auxiliary stack
    */
    public static void reverseFirstK(ArrayQueue q, int k) {
        // Step 1: Count total elements in queue
        int size = 0;
        ArrayQueue temp = new ArrayQueue(100);
        while (!q.isEmpty()) {
            temp.enqueue(q.dequeue());
            size++;
        }
        while (!temp.isEmpty()) {
            q.enqueue(temp.dequeue());
        }

        // Validation
        if (k > size || k <= 0) {
            System.out.println("Invalid value of k");
            return;
        }

        // Step 2: Push first k elements to stack
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            stack.push(q.dequeue());
        }

        // Step 3: Pop stack elements and enqueue back (reversed part)
        while (!stack.isEmpty()) {
            q.enqueue(stack.pop());
        }

        // Step 4: Move remaining elements to the back
        int rotations = size - k;
        for (int i = 0; i < rotations; i++) {
            q.enqueue(q.dequeue());
        }
    }

    /*
    Problem 3
    We are not allowed to use extra space. The operations allowed on queue are :
    enqueue() : Adds an item to rear of queue.
    dequeue() : Removes an item from front of queue.
    isEmpty() : Checks if a queue is empty.

    Input : A queue with elements
            11  5  4  21
    Output : Modified queue with following elements
            4 5 11 21
    Input : A queue with elements
            3  2  1  2
    Output : Modified queue with following elements
            1 2 2 3
   */
    public static void main(String[] args) {
        // Problem 1
        // ArrayQueue q = new ArrayQueue(20);
        // int[] elements = {11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        // for (int e : elements) q.enqueue(e);

        // q.display();
        // interleave(q);
        // q.display();

        // Problem 2
        // ArrayQueue q = new ArrayQueue(20);
        // int[] elements = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        // for (int e : elements) q.enqueue(e);

        // q.display();
        // reverseFirstK(q, 5);
        // q.display();
    }
}
