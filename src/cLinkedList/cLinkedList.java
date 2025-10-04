package cLinkedList;

public class cLinkedList {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Node tail = null; // tail of list where tail.next = head

    public void insert_begin(int data){
        Node n = new Node(data);
        
        if (tail == null) {
            tail = n;
            tail.next = tail;
        }
        else {
            n.next = tail.next;
            tail.next = n;
        }
    }

    public void insert_end(int data) {
        Node n = new Node(data);

        if (tail == null) {
            tail = n;
            tail.next = tail;
        } 
        else {
            n.next = tail.next;
            tail.next = n;
            tail = n;
        }
    }

    public void insert_between(int data) {
        // Front
        if (tail == null || tail.next.data >= data) {
            insert_begin(data);
            return;
        }

        // End
        if (tail.data <= data) {
            insert_end(data);
            return;
        }

        // Everything Else
        Node n = new Node(data);
        Node curr = tail.next;
        while (curr.next != tail.next && curr.next.data < data) {
            curr = curr.next;
        }
        n.next = curr.next;
        curr.next = n;
    }

    public void delete_begin(){
        // empty list
        if (tail == null){
            return;
        }

        // one node
        if (tail == tail.next){
            tail = null;
        }
        else {
            tail.next = tail.next.next;
        }
    }

    public void delete_end(){
        // empty list
        if (tail == null){
            return;
        }

        // one node
        if (tail == tail.next){
            tail = null;
            return;
        }

        Node curr = tail.next;
        while (curr.next != tail){
            curr = curr.next;
        }
        curr.next = tail.next;
        tail = curr;
    }

    public void delete_between(int data){
        // Case 1: empty list
        if (tail == null){
            return;
        }

        // Case 2: One Node or Head
        if ( (tail == tail.next && tail.data == data) || tail.next.data == data){
            delete_begin();
            return;
        }

        // Case 2: tail node
        if (tail.data == data) {
            delete_end();
            return;
        }

        // General Case: Search for prev node
        Node prev = searchPrev(data); // node before node we are deleting
        if (prev != null) {
            prev.next = prev.next.next; //actual node deleting
        } 
        else {
            System.out.println("Value " + data + " not found.");
        }
    }

    // Searcher Method for between
    public Node searchPrev(int data) {
        if (tail == null){
            return null;
        }

        Node curr = tail;
        do { //stop when we reach head
            if (curr.next.data == data) { // head.data = data means previous node
                return curr; // prev node
            }
            curr = curr.next;
        } while (curr.next != tail.next);

        return null;
    }

    // Traversal Method
    public void traverse() {
        Node curr = tail.next;
        do {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        } while (curr != tail.next);
        System.out.println("(back to head " + tail.next.data + ")");
    }
    public static void main(String[] args) {

        cLinkedList cll = new cLinkedList();

        cll.insert_begin(10);
        cll.insert_begin(5);
        cll.insert_end(20);
        cll.insert_end(30);
        cll.insert_between(25);
        cll.insert_end(40);
        cll.traverse(); // 10 -> 20 -> 30 -> 40 -> (back to head 10)

        cll.delete_begin();
        cll.traverse(); // 20 -> 30 -> 40 -> (back to head 20)

        cll.delete_end();
        cll.traverse(); // 20 -> 30 -> (back to head 20)

        cll.delete_between(20);
        cll.traverse(); // 30 -> (back to head 30)

        cll.delete_between(30);
        cll.traverse(); // List is empty
    }
}


/*
1)implement the insertion and deletion on the circular linked list ( beginning ,end ,middle)
2) search and traversal
3) implement the addition of  polynomials using a linked list.the result will be stored in the third linked list. 
eg :
    Polynomial    obj=new Polynomial();
        //4x5 + 5x4 + 2x3 + 3x2 + 7x
        //9x6 + 6x4 + 3x2
        Polynomial list1=new Polynomial();
        list1.insertbetween(2, 3);
        list1.insertbetween(4, 5);
        list1.insertbetween(5, 4);
        list1.insertbetween(7, 1);
        list1.insertbetween(3, 2);
        #1-10
 */
