package LinkedLists;

class dNode {
    int data;
    dNode next;
    dNode prev; // extra pointer for doubly linked list

    dNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoubleLinkedList {
    dNode head; // head/start of doubly linked list
    dNode tail;

    public void insert_node_end(int data){
        dNode n = new dNode(data);

        //empty list
        if (head == null){
            head = n;
            tail = n;
        }
        else{
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
    }

    public void insert_node_begin(int data){
        dNode n = new dNode(data);

        //empty list
        if (head == null){
            head = n;
            tail = n;
        }
        else{
            n.next = head;
            head.prev = n;
            head = n;
        }
    }

    public void insert_between(int data){
        // Case 1: empty list OR insert at front
        if (head == null || head.data >= data) {
            insert_node_begin(data);
            return;
        }

        // Case 2: Insert at end
        if(tail.data <= data){
            insert_node_end(data);
            return;
        }

        // Case 3: Insert in middle (Everything else)
        dNode n = new dNode(data);
        dNode curr = head;
        //as long as next element of head isnt null and its less then our insertion
        while(curr != null && curr.data < data){
            curr = curr.next;
        }
        dNode prevNode = curr.prev;
        prevNode.next = n;
        n.prev = prevNode;
        n.next = curr;
        curr.prev = n;
    }

    public void delete_node_end(){
        // empty list
        if (head == null){
            return;
        }

        // one node
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        // end of list
        tail = tail.prev;
        tail.next = null;
    }

    public void delete_node_begin(){
        // empty list
        if (head == null){
            return;
        }

        // one node
        if (head == tail) {
            head = null;
            tail = null;
            return;
        }

        // begin of list
        head = head.next;
        head.prev = null;
    }

    public void delete_between(int data){
        // Case 1: empty list
        if (head == null) {
            return;
        }

        // Case 2: Head / Begining
        if (head.data == data){
            delete_node_begin();
            return;
        }

        // Case 3: Tail / Ending
        if (tail.data == data){
            delete_node_end();
            return;
        }
        // traverse until next node is data we are deleting
        dNode curr = head;
        while(curr != null && curr.data != data){
            curr = curr.next;
        }

        // No number to delete
        if(curr == null){
            System.out.println("No key");
            return;
        }

        // Case 4: Everything Else
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
    }

    public void displayF() {
        dNode current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public void displayB() {
        dNode current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        list.insert_node_begin(5);
        list.insert_node_end(60);
        list.insert_between(50);
        list.insert_between(20);
        list.insert_between(22);
        list.displayF();
        list.displayB();

        list.delete_node_begin();
        list.delete_node_end();
        list.delete_between(22);
        list.displayF();
        list.displayB();
    }
}
