package Searching;
public class BinarySearch {

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int comparisons = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Comparisons made: " + comparisons);
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println("Comparisons made: " + comparisons);
        return -1;
    }

    public static void main(String[] args) {
        int[] numbers = {10, 20, 30, 40, 50, 60, 70};
        int target = 70;

        int result = binarySearch(numbers, target);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found in array.");
        }
    }
}