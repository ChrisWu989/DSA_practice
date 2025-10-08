package Searching;

public class LinearSearch {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 5, 9, 35, 94, 30, 44, 435, 6467, 11, 30, 40, 50};
        int target = 30;

        int comparisons = 0;
        for (int i = 0; i < numbers.length; i++) {
            comparisons++;
            if (numbers[i] == target) {
                System.out.println("Element found at index " + i + " within " + comparisons +" searches");
                break;
            }
        }

        if (comparisons == numbers.length){
            System.out.println("Element not found in array within " + comparisons + " searches");
        }
    }
}
