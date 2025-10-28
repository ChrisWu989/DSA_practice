package Assessment;

import java.util.*;

public class OctAssessment3 {
    /*
    What will be the final contents of the stack if you are given the sequence of commands? 
    Given: 
    - A pooling system uses a stack. There are commands to track what operations are performed. 
    - The commands are in below format <Type> <Value> 
    - If type = 0, Value is pushed into the stack. 
    - If type = 1, Value is popped from the stack. (If there are no values to pop, the command is ignored). The value parameter is -1. 
    - If type = 2, the entire stack is reversed, the value parameter is -1. 
    
    Input Format   
    The first line contains an integer N, denoting the number of commands 
    The next N lines each contain the command that is sent. 

    Sample Input 
    8 
    0 1 
    0 5 
    1 -1 
    1 -1 
    1 -1 
    0 1 
    0 5 
    2 -1 
    
    Output Format 
    Contents of stack (Top to Bottom) 
    
    Sample Output 
    1 
    5 
    */
    public static void processCMD(List<int[]> commands){
        Stack<Integer> stack = new Stack<>();
        // Loop through all the commands
        for (int[] cmd : commands){
            int type = cmd[0];
            int value = cmd[1];
            if (type == 0){ // Push
                stack.push(value);
            }
            else if (type == 1 && value == -1){ // Pop
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }
            else if (type == 2 && value == -1){ // Reverse
                if (stack.isEmpty()) return; // Empty stack

                //Stack is LIFO so we move to queue which is FIFO
                //If we pop stack and add that to queue we reverse the list
                Queue<Integer> queue = new LinkedList<>();

                while (!stack.isEmpty()){
                    queue.add(stack.pop());
                }

                // turn queue back into stack
                while (!queue.isEmpty()) {
                    stack.push(queue.poll());
                }
            }
        }
        System.out.println("Contents of stack (Top to Bottom):");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of commands: ");
        int n = sc.nextInt();

        List<int[]> commands = new ArrayList<>();

        System.out.println("Enter each command as '<type> <value>':");
        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();
            int value = sc.nextInt();
            commands.add(new int[]{type, value});
        }

        processCMD(commands);
        sc.close();
    }
}
