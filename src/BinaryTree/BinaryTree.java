package BinaryTree;

public class BinaryTree {

}
/*
On the binary tree array implementation write the following methods:
     String[]arr = {"D", "A", "F", "E", "B", "R", "T", "G", "Q", null, null, "V", null, "J", "L"};


get_right_child(index)
get_left_child(index)
get_parent(index)
 preorder(index)
inorder(index)
postorder(index)


    public int left(int index) {
        //System.out.println(n);
        if(((2*index)+1) > n-1){
            return -1;
        }else {
            //System.out.println("left else");
            return (2*index)+1;
        }
    }
           public void preOrder(int index) {
        if (index >= 0) {
            System.out.print(arr[index]);
            preOrder(left(index));
            preOrder(right(index));
        }
    }

    Algorithm: Inorder (root) 
1. If (root = NULL): 
a. Exit
2. Inorder (left child of root)
3. Visit (root) 
4. Inorder (right child of root) 

Algorithm: Preorder (root) 
1. If (root = NULL): 
a. Exit
2. Visit (root) 
3. Preorder (left child of root) // Recursive call to Preorder for traversing 
 // the left subtree
4. Preorder (right child of root) // Recursive call to Preorder for traversing the 
 // right subtree
 */


/*
Linked list
 *     public Node create(int[] arr, Node root, int i) {
		if (i < arr.length) {
			Node temp = new Node(arr[i]);
			root = temp;	
			// insert left child
			root.left = create(arr, root.left, 2 * i + 1);
			// insert right child
			root.right = create(arr, root.right, 2 * i + 2);
		}
		return root;
	}

    public void preOrder(Node root) {
		if (root != null) {
			System.out.print(root.data + " ");
			preOrder(root.left);
			preOrder(root.right);
		}
 */