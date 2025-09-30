class Node{
    int data;
    Node next;

    Node(int data){
        this.data = data;
        this.next = null;
    }
}

public class LinkedList {
    Node head; // head/start of singly linked list
    Node tail; // tail/last of singly linked list
    boolean isReversed = false;

    public void insert_node_end(int data){
        Node n = new Node(data);

        //empty list
        if (head == null){
            head = n;
            tail = n;
        }
        else{
            tail.next = n;
            tail = n;
        }
    }

    public void insert_node_begin(int data){
        Node n = new Node(data);

        //empty list
        if (head == null){
            head = n;
            tail = n;
        }
        else{
            n.next = head;
            head = n;
        }
    }

    public void insert_between(int data){
        if (isReversed) {
            // Case 1: empty list OR insert at front
            if (head == null || head.data <= data) {
                insert_node_begin(data);
                return;
            }

            // Case 2: Insert at end
            if(tail.data >= data){
                insert_node_end(data);
                return;
            }

            // Case 3: Insert in middle (Everything else)
            Node n = new Node(data);
            Node curr = head;
            //as long as next element of head isnt null and its less then our insertion
            while(curr.next != null && curr.next.data < data){
                curr = curr.next;
            }
            n.next = curr.next; //pushing numbers back one spot for insertion 
            curr.next = n; //setting current val to inserted value
        }
        else {
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
            Node n = new Node(data);
            Node curr = head;
            //as long as next element of head isnt null and its less then our insertion
            while(curr.next != null && curr.next.data < data){
                curr = curr.next;
            }
            n.next = curr.next; //pushing numbers back one spot for insertion 
            curr.next = n; //setting current val to inserted value
        }
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

        Node curr = head;
        while (curr.next != tail){
            curr = curr.next;
        }
        curr.next = null;
        tail = curr;
    }

    public void delete_node_begin(){
        // empty list
        if (head == null){
            return;
        }

        head = head.next;
        
        // if turned into empty list
        if (head == null){
            tail= null;
        }
    }

    public void delete_between(int data){
        // Case 1: empty list OR one node
        if (head == null || head == tail) {
            delete_node_begin();
            return;
        }

        // traverse until next node is data we are deleting
        Node curr = head;
        while(curr.next != null && curr.next.data != data){
            curr = curr.next;
        }

        // No number to delete
        if(curr.next == null){
            System.out.println("No key");
            return;
        }

        // Case 2: delete at end
        if (curr.next == tail){
            delete_node_end();
            return;
        }

        // Case 3: Everything Else
        curr.next = curr.next.next;
    }

    public void reverse(){
        Node prev = null;
        Node curr = head;
        tail = head;

        while (curr != null) {
            Node temp = curr.next; //   store next element in reverse list
            curr.next = prev;      //   reversing pointwe
            prev = curr;           //   move prev forward
            curr = temp;           //   move curr forward
        }
        head = prev;               //   new head of reversed list
        isReversed = !isReversed;  //   boolean to tell list is reversed
    }
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.insert_node_end(10);
        list.insert_node_end(20);
        list.insert_node_end(30);

        // list.delete_node_begin();
        // list.display();
        // list.delete_node_end();
        // list.display();
        // list.delete_between(20);
        // list.display();

        list.reverse();
        list.insert_between(15);
        list.display();;
    }
}