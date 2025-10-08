package Sorting;

public class InsertionSort {
    public static void insertionSort(int[] arr) {
        int comparisons = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
        }

        System.out.println("Comparisons made: " + comparisons);
    }

    public static void main(String[] args) {
        int[] numbers = {12, 11, 13, 5, 6, 12, 54, 2};

        insertionSort(numbers);

        System.out.println("\nAfter sorting:");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}
