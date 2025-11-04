package BinaryTree;

import java.util.Queue;
import java.util.LinkedList;

public class AVLTree {

    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1; // new node is initially at height 1
        }
    }
    Node root;

    // Utility: get height of node
    int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Utility: get balance factor
    int getBalance(Node node) {
        if (node == null)
            return 0;
        return height(node.left) - height(node.right);
    }

    // Right rotation
    Node rightRotate(Node y) {
        Node x = y.left;
        Node temp = x.right;

        // Perform rotation
        x.right = y;
        y.left = temp;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // Left rotation
    Node leftRotate(Node x) {
        Node y = x.right;
        Node temp = y.left;

        // Perform rotation
        y.left = x;
        x.right = temp;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // INSERT operation
    Node insert(Node node, int key) {
        // 1. Perform normal BST insertion
        if (node == null)
            return new Node(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node; // no duplicate keys

        // 2. Update height of this ancestor node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // 3. Get the balance factor
        int balance = getBalance(node);

        // 4. Balance the node if unbalanced

        // Left Left Case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the unchanged node pointer
        return node;
    }

    // GET minimum value node (used in delete)
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    // DELETE operation
    Node delete(Node root, int key) {
        // 1. Perform standard BST delete
        if (root == null)
            return root;

        if (key < root.key)
            root.left = delete(root.left, key);
        else if (key > root.key)
            root.right = delete(root.right, key);
        else {
            // Node with one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = (root.left != null) ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                // Node with two children: Get inorder successor
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.right = delete(root.right, temp.key);
            }
        }

        // If the tree had only one node
        if (root == null)
            return root;

        // 2. Update height
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // 3. Balance the node
        int balance = getBalance(root);

        // 4. Handle 4 imbalance cases

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // INORDER traversal (sorted order)
    void levelOrder(Node node) {
        if (node == null)
            return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Print one level
            for (int i = 0; i < levelSize; i++) {
                Node current = queue.poll();
                System.out.print(current.key + " ");

                if (current.left != null)
                    queue.add(current.left);
                if (current.right != null)
                    queue.add(current.right);
            }
            System.out.println(); // new line for each level
        }
    }

    // MAIN method for testing
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] keys = {10, 20, 30, 40, 50, 25};

        for (int key : keys)
            tree.root = tree.insert(tree.root, key);

        tree.levelOrder(tree.root);
        System.out.println();

        // Delete a node
        tree.root = tree.delete(tree.root, 40);

        tree.levelOrder(tree.root);
        System.out.println();
    }
}