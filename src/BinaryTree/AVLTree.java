package BinaryTree;

public class AVLTree {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // new node is initially at height 1
        }
    }
    
}
