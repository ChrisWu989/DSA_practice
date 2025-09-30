public class problem_9_26 {

    /*
    Utilize binary search to get O(logn)
    Given a sorted array with possibly duplicate elements, the task is to
    find indexes of first and last occurrences of an element x in the given
    array. Examples: Input : arr[] = {1, 3, 5, 5, 5, 5, 67, 123, 125} x = 5
    Output : First Occurrence = 2 Last Occurrence = 5
    */
    public static int firstBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int first = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                first = mid; //might be first occurence
                right = mid - 1; //first occurence could be left of mid still
            }
            else if (arr[mid] < target) {
                left = mid + 1;
            } 
            else {
                right = mid - 1;
            }
        }
        return first;
    }

    public static int lastBinarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int last = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                last = mid; //might be last occurence
                left = mid + 1; //last occurence could be right of mid still
            }
            else if (arr[mid] < target) {
                left = mid + 1;
            } 
            else {
                right = mid - 1;
            }
        }
        return last;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 5, 5, 5, 67, 123, 125};
        int target = 67;

        int first = firstBinarySearch(numbers, target);
        int last = lastBinarySearch(numbers, target);
        System.out.println("First Occurence = " + first);
        System.out.println("Second Occurence = " + last);
    }
}
