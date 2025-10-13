package Stacks;
/*
Problem 9
Find the sum of the ranges of all the pairs that are present in the stack.
-    An array of number pairs represents a range in the number line.
-    The pairs are sorted in the ascending order of the first number in the pairs.
-    The pairs are added to stack by checking the following conditions.

Case 1. There is only partial overlap (for e.g. (2, 10) and (6, 12) overlaps partially). For such cases, we update the second value of the top pair in the stack with the second value of the current pair. E.g. 10 in (2, 10) is replaced with 12 from (6, 12) and the top pair in the stack becomes (2, 12).

Case 2. When there is complete overlap, (2, 10) and (4, 8). The second pair range is within the first range. For such cases, we skip the current pair.
 
If there is no overlapping with the top pair in the stack, push the current pair into the stack.
   int[][] pairs={{120 ,199},{120,233},{121,233},{120,235},{238,270}};

So sum of the ranges of pairs in stack are
Range: 235 – 120 = 115 and
Range: 270 – 238 = 32

So 115 + 32 = 147
Hence the output is 147.
 */
class PairStackLinkedList {
    class Node {
        int start, end;
        Node next;
        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.next = null;
        }
    }

    private Node top;

    public PairStackLinkedList() {
        top = null;
    }

    public void push(int start, int end) {
        Node n = new Node(start, end);
        n.next = top;
        top = n;
    }

    public Node pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return null;
        }
        Node temp = top;
        top = top.next;
        return temp;
    }

    public Node peek() {
        return top;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void display() {
        Node curr = top;
        System.out.print("Stack (top → bottom): ");
        while (curr != null) {
            System.out.print("(" + curr.start + "," + curr.end + ") ");
            curr = curr.next;
        }
        System.out.println();
    }

    public int sumOfRanges() {
        int sum = 0;
        Node curr = top;
        while (curr != null) {
            sum += (curr.end - curr.start);
            curr = curr.next;
        }
        return sum;
    }

    public static int sumOfMergedRanges(int[][] pairs) {
        PairStackLinkedList stack = new PairStackLinkedList();

        for (int i = 0; i < pairs.length; i++) {
            int start = pairs[i][0];
            int end = pairs[i][1];

            // If stack empty, push first pair
            if (stack.isEmpty()) {
                stack.push(start, end);
            } else {
                PairStackLinkedList.Node top = stack.peek();

                // Case 1: complete overlap → skip
                if (start >= top.start && end <= top.end) {
                    continue;
                }

                // Case 2: partial overlap → merge by updating end
                else if (start <= top.end) {
                    top.end = end;
                }

                // Case 3: no overlap → push new pair
                else {
                    stack.push(start, end);
                }
            }
        }

        return stack.sumOfRanges();
    }

    public static void main(String[] args) {
        int[][] pairs = {
            {120, 199},
            {120, 233},
            {121, 233},
            {120, 235},
            {238, 270}
        };

        System.out.println("Sum of merged ranges: " + sumOfMergedRanges(pairs)); // 147
    }
}
