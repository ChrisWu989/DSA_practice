package Sorting;

public class ShellSort {
    public static void shellSort(int[] arr) {
        int comparisons = 0;

        int n = arr.length;

        //sublisting rec use a prime number
        for(int increment = 3; increment > 0; increment--){

            // insertion sort with sublisting
            for(int i = increment; i < n; i = i + increment){
                int temp = arr[i];
                int j = i;

                while (j >= increment){
                    comparisons++;
                    if (arr[j - increment] > temp){
                        arr[j] = arr[j - increment];
                        j -= increment;
                    } else {
                        break;
                    }
                }
                arr[j] = temp;
            }
        }
        System.out.println("Comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        int[] numbers = {70, 30, 40, 10, 80, 20, 90, 110, 75, 60, 45};

        shellSort(numbers);

        System.out.println("\nAfter sorting:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}



/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements. 
Example1: 
Input: int[] nums = [0, 1, 0, 3, 12], 
Output: [1, 3, 12, 0, 0]. 
Note: 
You must do this in-place without making a copy of the array. 
Minimize the total number of operations. 
 */
