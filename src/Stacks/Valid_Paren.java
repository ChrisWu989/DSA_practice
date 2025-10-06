package Stacks;

public class Valid_Paren {
    class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public Valid_Paren() {
        this.top = null;
    }

    public void push(char data){
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public char pop(){
        if (isEmpty()){
            return '\0';
        }
        else {
            char popped = top.data;
            top = top.next;
            return popped;
        }
    }

    public static boolean isValid(String str) {
        Valid_Paren stack = new Valid_Paren();

        for (int i = 0; i < str.length(); i++) {
            char s = str.charAt(i);

            // Push opening brackets
            if (s == '(' || s == '[' || s == '{') {
                stack.push(s);
            }

            // Handle closing brackets
            else if (s == ')' || s == ']' || s == '}') {
                if (stack.isEmpty()){
                    return false; // no match
                }

                if (!isMatchingPair(stack.pop(), s)) {
                    return false; // mismatch
                }
            }
        }

        // Stack should be empty at the end
        return stack.isEmpty();
    }

    public boolean isEmpty(){
        return top == null;
    }

    //check every ({[ matches corresponding ]})
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    

    public static void main(String[] args) {
        String str = "{(a + b) * (c + d) + (c * d)]}";
        if (isValid(str)) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
        }
    }
}
