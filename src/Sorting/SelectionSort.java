package Sorting;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        int comparisons = 0;

        for (int i = 0; i < arr.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < arr.length; j++) {

                comparisons++;
                
                if (arr[j] < arr[minIndex]) {
                    minIndex = j; 
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        int[] numbers = {1, 9, 7, 93, 11, 23};

        selectionSort(numbers);

        System.out.println("\nAfter sorting:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}
