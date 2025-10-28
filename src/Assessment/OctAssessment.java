package Assessment;

public class OctAssessment {
    /*
    Write  a program to remove the given  Node (n) From End of List 
    Example 1: 
    Input: head = [1,2,3,4,5], n = 2 
    Output: [1,2,3,5] 
    Example 2: 
    Input: head = [1], n = 1 
    Output: [] 
    Example 3: 
    Input: head = [1,2], n = 1 
    Output: [1] 
    */
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public static Node removeNthFromEnd(Node head, int n){
        Node dummy = new Node(0);
        dummy.next = head;
        
        Node fast = head;
        Node slow = head;
        // Will iterate fast node to be n + 1 in linkedlist
        for (int i = 0; i <= n; i++){
            if (fast == null) return head; // fast is out of bounds
            fast = fast.next;
        }
        
        // Move fast to end with the gap of slow 
        while(fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        // we are skipping this node which is the nth node 
        slow.next = slow.next.next;
        return dummy.next;
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Example 1
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);

        head1 = removeNthFromEnd(head1, 2);
        System.out.print("Output 1: ");
        printList(head1); // 1 2 3 5

        // Example 2
        Node head2 = new Node(1);
        head2 = removeNthFromEnd(head2, 1);
        System.out.print("Output 2: ");
        printList(head2); // []

        // Example 3
        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3 = removeNthFromEnd(head3, 1);
        System.out.print("Output 3: ");
        printList(head3); // 1
    }
}
