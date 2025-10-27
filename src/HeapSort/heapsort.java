package HeapSort;

public class heapsort {
    // Recursive function that makes a max heap
    private void heapifyMax(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapifyMax(arr, n, largest);
        }
    }

    // Recursive function that makes a min heap
    private void heapifyMin(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] < arr[smallest])
            smallest = left;

        if (right < n && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            heapifyMin(arr, n, smallest);
        }
    }

    // --- Convert Array to Max Heap ---
    public void toMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyMax(arr, n, i);
        }
    }

    // --- Convert Array to Min Heap ---
    public void toMinHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyMin(arr, n, i);
        }
    }

    // --- Max Heap Sort (Ascending Order) ---
    public void maxHeapSort(int[] arr) {
        toMaxHeap(arr);
        int n = arr.length;

        for (int i = n - 1; i > 0; i--) {
            // swap arr[0] and arr[i]
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifyMax(arr, i, 0); //making sure maxheap is kept
        }
    }

    // --- Min Heap Sort (Descending Order) ---
    public void minHeapSort(int[] arr) {
        toMinHeap(arr);
        int n = arr.length;

        for (int i = n - 1; i > 0; i--) {
            // swap arr[0] and arr[i]
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapifyMin(arr, i, 0); //making sure minheap is kept
        }
    }

    public void printArray(int[] arr) {
        for (int val : arr)
            System.out.print(val + " ");
        System.out.println();
    }

    // --- Test the Implementation ---
    public static void main(String[] args) {
        heapsort heapConverter = new heapsort();

        int[] arr1 = {5, 3, 8, 2, 4, 10};
        int[] arr2 = arr1.clone();

        System.out.println("Original Array:");
        heapConverter.printArray(arr1);

        heapConverter.maxHeapSort(arr1);
        System.out.println("After MaxHeapSort (Ascending):");
        heapConverter.printArray(arr1);

        heapConverter.minHeapSort(arr2);
        System.out.println("After MinHeapSort (Descending):");
        heapConverter.printArray(arr2);
    }
}