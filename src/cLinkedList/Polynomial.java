package cLinkedList;

public class Polynomial {
    class Node {
        int coeff;
        int exp;
        Node next;

        Node(int coeff, int exp){
            this.coeff = coeff;
            this.exp = exp;
            this.next = null;
        }
    }
    Node head; // head/start of singly linked list

    public void insert_end(int coeff, int exp){
        Node n = new Node(coeff, exp);

        //empty list
        if (head == null){
            head = n;
        }
        else{
            Node curr = head;
            while (curr.next != null){
                curr = curr.next;
            }
            curr.next = n;
        }
    }

    public void insert_between(int coeff, int exp){
        Node n = new Node(coeff, exp);
        if (head == null || head.exp < exp){
            n.next = head;
            head = n;
        }
        else {
            Node curr = head;
            while (curr.next != null && curr.next.exp > exp){
                curr = curr.next;
            }
            if (curr.next != null && curr.next.exp == exp) {
                curr.next.coeff += coeff;
            }
            else {
                n.next = curr.next;
                curr.next = n;
            }
        }
    }
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        Polynomial p3 = new Polynomial();
        Node curr1 = p1.head;
        Node curr2 = p2.head;
        while (curr1 != null && curr2 != null){
            if(curr1.exp == curr2.exp){ //same exp
                p3.insert_between(curr1.coeff + curr2.coeff, curr1.exp);
                curr1 = curr1.next;
                curr2= curr2.next;
            }
            else if (curr1.exp > curr2.exp) { // 1 greater exp
                p3.insert_between(curr1.coeff, curr1.exp);
                curr1 = curr1.next;
            }
            else { // 2 greater exp
                p3.insert_between(curr2.coeff, curr2.exp);
                curr2 = curr2.next;
            }
        }

        while(curr1 != null){
            p3.insert_between(curr1.coeff, curr1.exp);
            curr1 = curr1.next;
        }

        while(curr2 != null){
            p3.insert_between(curr2.coeff, curr2.exp);
            curr2 = curr2.next;
        }
        return p3;
    }

    public void display() {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.coeff + "x^" + curr.exp);
            if (curr.next != null) System.out.print(" + ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        p1.insert_end(4, 5);
        p1.insert_between(5, 4);
        p1.insert_between(2, 3);
        p1.insert_between(3, 2);
        p1.insert_between(7, 1);
        p1.display();

        Polynomial p2 = new Polynomial();
        p2.insert_between(9, 6);
        p2.insert_between(6, 4);
        p2.insert_between(3, 2);
        p2.display();

        Polynomial p3 = Polynomial.add(p1, p2);
        p3.display();
    }
}
