package Stacks;

public class infix_postfix {
    class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node top; // pointer to top of stack

    public infix_postfix() {
        this.top = null;
    }

    public void push(char data){
        Node n = new Node(data);
        n.next = top;
        top = n;
    }

    public char pop(){
        char popped = top.data;
        top = top.next;
        return popped;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public char peek(){
        if (isEmpty()){
            return '\0';
        }
        else{
            return top.data;
        }
    }

    public static String convert(String str) {
        infix_postfix stack = new infix_postfix();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == ' ') continue; // Skip spaces

            // Operand Check
            if (Character.isLetterOrDigit(c)) {
                res.append(c);
            }
            // If '(' push
            else if (c == '(') {
                stack.push(c);
            }
            // If ')' pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    res.append(stack.pop());
                }
                stack.pop(); // REMOVE '('
            }
            // Other Operators * / + - ^
            else{
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                    res.append(stack.pop());
                }
                stack.push(c);
            }
        }
        // Pop remaining operators
        while (!stack.isEmpty()) {
            res.append(stack.pop());
        }

        return res.toString();
    }

    public static int eval(String str) {
        int[] stack = new int[100];
        int top = -1;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // Operand Check
            if (Character.isLetterOrDigit(c)) {
                stack[++top] = c - '0';
            }
            // Operator encountered 
            else{
                int b = stack[top--]; //top (right)
                int a = stack[top--]; //2nd top (left)

                switch (c) {
                    case '+': stack[++top] = a + b; break;
                    case '-': stack[++top] = a - b; break;
                    case '*': stack[++top] = a * b; break;
                    case '/': stack[++top] = a / b; break;
                    case '^': stack[++top] = (int)Math.pow(a, b); break;
                }
            }
        }
        return stack[top];
    }

    // Helper to check PEMDAS
    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    public static void main(String[] args) {
        String str = "(5 + 7) * (3 + 2) + (1 * 4)";
        String postfix = convert(str);
        System.out.println(postfix);
        System.out.println(eval(postfix));
    }
}
