class Node {
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class problem_9_29 {
    Node head;

    // Insert a value into the list in sorted order, skipping duplicates
    private void insertSortedUnique(int data) {
        Node n = new Node(data);

        // Case 1: empty list
        if (head == null) {
            head = n;
            return;
        }

        // Case 2: insert before head
        if (data < head.data) {
            n.next = head;
            head = n;
            return;
        }

        // Traverse to find position
        Node curr = head;
        while (curr.next != null && curr.next.data < data) {
            curr = curr.next;
        }

        // Duplicate check
        if (curr.data == data || (curr.next != null && curr.next.data == data)) {
            return; // skip duplicates
        }

        // Insert newNode
        n.next = curr.next;
        curr.next = n;
    }

    // Build the linked list from an array
    public void buildFromArray(int[] arr) {
        for (int val : arr) {
            insertSortedUnique(val);
        }
    }

    // Print the linked list
    public void printList() {
        Node curr = head;
        System.out.print("{");
        while (curr != null) {
            System.out.print(curr.data);
            curr = curr.next;
            if (curr != null) System.out.print(", ");
        }
        System.out.println("}");
    }

    // Driver test
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 2, 0, 2, 0, 3, 4, 4, 3, 5};
        problem_9_29 list1 = new problem_9_29();
        list1.buildFromArray(arr1);
        list1.printList(); // {0, 1, 2, 3, 4, 5}

        int[] arr2 = {24, 27, 30, 24, 12, 17, 27, 1, 5, 1, 3};
        problem_9_29 list2 = new problem_9_29();
        list2.buildFromArray(arr2);
        list2.printList(); // {1, 3, 5, 12, 17, 24, 27, 30}
    }
}
/* 
create a singly linked list from unsorted array ,the resultant list is in sorted order and does not contain any duplicate values 
Example1: input:int[] arr= {1, 2, 2, 0,2,0, 3, 4, 4,3, 5}; 
output:{0,1,2,3,4,5} 
Example 2: Input :int[] arr = {24,27,30,24,12,17,27,1,5,1,3}; 
output:{1, 3, 5, 12, 17, 24, 27,30} 
Note: do it without sorting the array 
*/