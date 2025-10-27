package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

public class Problems10_23 {
    /*
    Problem 23
    Spiral order traversal of a binary tree
               1
           2         3
        4     5    6   7

    the result will be(1,2,3,7,6,5,4) or(1,3,2,4,5,6,7)
    Zig zag means alternate levels left-> right and right-> left
    */
    public static void spiralTraversal(BinarySearchTree.Node root) {
        if (root == null) return;

        // Use two stacks
        Stack<BinarySearchTree.Node> stack1 = new Stack<>(); // left to right
        Stack<BinarySearchTree.Node> stack2 = new Stack<>(); // right to left

        stack1.push(root);

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            // Print nodes from stack1 and push children into stack2
            while (!stack1.isEmpty()) {
                BinarySearchTree.Node node = stack1.pop();
                System.out.print(node.data + " ");
                // Note: right pushed first so left is processed first next level
                if (node.right != null)
                    stack2.push(node.right);
                if (node.left != null)
                    stack2.push(node.left);
            }

            // Print nodes from stack2 and push children into stack1
            while (!stack2.isEmpty()) {
                BinarySearchTree.Node node = stack2.pop();
                System.out.print(node.data + " ");
                // Note: left pushed first so right is processed first next level
                if (node.left != null)
                    stack1.push(node.left);
                if (node.right != null)
                    stack1.push(node.right);
            }
        }
    }
    /*
    Leetcode 102: Level Order Traversal
    Problem 24
    Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
    
    Example 1:
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[9,20],[15,7]]
    Example 2:
    Input: root = [1]
    Output: [[1]]
    Example 3:
    Input: root = []
    Output: []
    */
    public static List<List<Integer>> levelOrder(BinarySearchTree.Node root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) return result;

        Queue<BinarySearchTree.Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size(); // number of nodes at current level
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                BinarySearchTree.Node node = queue.poll();
                level.add(node.data);

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(level);
        }

        return result;
    }
    /*
    Problem 25
    Display the Sum of Leaf nodes of a tree .Given a binary search tree containing digits only, each root-to-leaf path could represent a number.
    Find the total sum of all leaf numbers.
    Note: A leaf is a node with no children.

    Example:
    int[] arr = {10,4,6,5,9,3,12,11,16,17};
    sum:45
    */
    public static int sumOfLeafNodes(BinarySearchTree.Node root) {
        if (root == null) return 0;

        // If it’s a leaf node, return its data
        if (root.left == null && root.right == null)
            return root.data;

        // Otherwise, sum leaves in both subtrees
        return sumOfLeafNodes(root.left) + sumOfLeafNodes(root.right);
    }

/* 
PROBLEM 26:
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).
 
Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
 
Example 2:
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

     */

    public static void main(String[] args) {
        // Problem 23
        // BinarySearchTree bst = new BinarySearchTree();

        // bst.root = new BinarySearchTree.Node(1);
        // bst.root.left = new BinarySearchTree.Node(2);
        // bst.root.right = new BinarySearchTree.Node(3);
        // bst.root.left.left = new BinarySearchTree.Node(4);
        // bst.root.left.right = new BinarySearchTree.Node(5);
        // bst.root.right.left = new BinarySearchTree.Node(6);
        // bst.root.right.right = new BinarySearchTree.Node(7);

        // System.out.print("Spiral Traversal: ");
        // spiralTraversal(bst.root);

        // Problem 24
        // BinarySearchTree bst = new BinarySearchTree();

        // bst.root = new BinarySearchTree.Node(3);
        // bst.root.left = new BinarySearchTree.Node(9);
        // bst.root.right = new BinarySearchTree.Node(20);
        // bst.root.right.left = new BinarySearchTree.Node(15);
        // bst.root.right.right = new BinarySearchTree.Node(7);

        // List<List<Integer>> result = levelOrder(bst.root);
        // System.out.println(result);

        // Problem 25
        BinarySearchTree bst = new BinarySearchTree();

        int[] arr = {10, 4, 6, 5, 9, 3, 12, 11, 16, 17};
        for (int v : arr)
            bst.root = bst.insert(bst.root, v);

        int sum = sumOfLeafNodes(bst.root);
        System.out.println("Sum of Leaf Nodes: " + sum);
    }
}
//problems generate tree from preorderinorder
//generate tree from preorderpostorder
//generate preorderorderpostorder