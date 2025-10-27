package BinaryTree;

import java.util.*;

class BinarySearchTree {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    Node root;

    // INSERT
    Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data)
            root.left = insert(root.left, data);
        else if (data > root.data)
            root.right = insert(root.right, data);
        return root;
    }

    // DELETE
    Node delete(Node root, int data) {
        if (root == null)
            return null;

        if (data < root.data)
            root.left = delete(root.left, data);
        else if (data > root.data)
            root.right = delete(root.right, data);
        else {
            // Case 1: No child
            if (root.left == null && root.right == null)
                return null;

            // Case 2: One child
            else if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            // Case 3: Two children
            root.data = minValue(root.right);
            root.right = delete(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // INORDER
    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    // PREORDER
    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    // POSTORDER
    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // LEVEL ORDER
    void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null)
                queue.add(curr.left);
            if (curr.right != null)
                queue.add(curr.right);
        }
    }

    // MAIN — test everything
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert elements (must assign root manually)
        bst.root = bst.insert(bst.root, 50);
        bst.root = bst.insert(bst.root, 30);
        bst.root = bst.insert(bst.root, 70);
        bst.root = bst.insert(bst.root, 20);
        bst.root = bst.insert(bst.root, 40);
        bst.root = bst.insert(bst.root, 60);
        bst.root = bst.insert(bst.root, 80);

        System.out.print("Inorder: ");
        bst.inorder(bst.root);
        System.out.println();

        System.out.print("Preorder: ");
        bst.preorder(bst.root);
        System.out.println();

        System.out.print("Postorder: ");
        bst.postorder(bst.root);
        System.out.println();

        System.out.print("Level Order: ");
        bst.levelOrder(bst.root);
        System.out.println();

        // Delete some nodes
        System.out.println("\nDeleting 20, 30, 50...");
        bst.root = bst.delete(bst.root, 20);
        bst.root = bst.delete(bst.root, 30);
        bst.root = bst.delete(bst.root, 50);

        System.out.print("Inorder after deletions: ");
        bst.inorder(bst.root);
        System.out.println();
    }
}

//problems generate tree from preorderinorder
//generate tree from preorderpostorder
//generate preorderorderpostorder