package Assessment;

import java.util.*;

public class OctAssessment2 {
    /*
    You are given the names of N people and the time at which they arrive.   
    You have an empty queue. 
    As soon as a person arrives, you add their name to the queue. 
    Given the names of the people and their arrival times print the final queue after all N people have arrived. 
    Note 
    1.        No two people have the same name. 
    2.        No two people have the same arrival time. 
    3.        The input is arrival time is not sorted (See sample input). 

    Input: 
    Name={Jim ,Sam, Roy ,John} 
    Time={5, 1,4 ,2} 
    Output 
    Sam John Roy Jim  
    */

    public static void printSortedQueue(String[] names, int[] times){

        boolean[] added = new boolean[names.length]; //boolean array tracks which people added
        Queue<String> queue = new LinkedList<>(); // final sorted queue of names

        for (int count = 0; count < names.length; count++) {
            int earliestIndex = -1;
            int earliestTime = Integer.MAX_VALUE;

            // find next person who arrives earliest and not yet added
            for (int i = 0; i < names.length; i++) {
                if (!added[i] && times[i] < earliestTime) {
                    earliestTime = times[i];
                    earliestIndex = i;
                }
            }

            // simulate arrival — enqueue that person
            queue.add(names[earliestIndex]);
            added[earliestIndex] = true;
        }

        //final print
        System.out.print("Final Queue: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] names = {"Jim", "Sam", "Roy", "John"};
        int[] times = {5, 1, 4, 2};

        printSortedQueue(names, times);
    }
}