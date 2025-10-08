package Sorting;

public class QuickSort {
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // where we sublist and get pivot
            int pivot = sublist(arr, low, high);

            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private static int sublist(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot in correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;

    }

    public static void main(String[] args) {
        int[] numbers = {28, 55, 46, 38, 16, 89, 83, 30};
        quickSort(numbers, 0, numbers.length - 1);

        System.out.println("After sorting: ");
        for (int num : numbers) {
            System.out.print(num + " ");
        }
    }
}