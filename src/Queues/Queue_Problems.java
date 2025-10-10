package Queues;
/*
 * Given a queue of integers of even length, rearrange the elements by interleaving the first
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

 /*
  * 2)Given an integer k and a queue of integers, we need to reverse the order of the first k elements of the queue,
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

  /*
   * elements, we need to sort it.
We are not allowed to use extra space. The operations allowed on queue are :
enqueue() : Adds an item to rear of queue.
dequeue() : Removes an item from front of queue.
isEmpty() : Checks if a queue is empty.
Examples :
Input : A queue with elements
        11  5  4  21
Output : Modified queue with
         following elements
        4 5 11 21
Input : A queue with elements
        3  2  1  2
Output : Modified queue with
         following elements
        1 2 2 3
   */
public class Queue_Problems {

}
