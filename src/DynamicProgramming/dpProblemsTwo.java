package DynamicProgramming;
  // 37 38 39
public class dpProblemsTwo {
    /*
    Problem 36
    Below there is code that finds the maximum even element in list numbers and prints it

    def find_maximum_even(numbers):
        maximum = 0
        for x in numbers:
            if x % 2 == 0 and x > maximum:
                maximum = x
        print(maximum)
    
    write a function that given a list, prints the sum of the largest odd # and the largest even # in list numbers
    If there are no odd or no even numbers in list, you can assume that the largest one in the group is 0

    Examples
    Given numbers = [5, 3, 10, 6, 11] we print 21
    Given numbers = [20, 10, 7, 5] we print 27
    Given numbers = [7, 13, 15, 13] we print 15
    Given numbers = [2, 6, 4, 6] we print 6
    */
    public static void solution36(int[] numbers){
        System.out.println(find_max_even(numbers) + find_max_odd(numbers));
    }
    
    public static int find_max_even(int[] numbers){
        int maximum = 0;
        for (int num : numbers){
            if (num % 2 == 0 && num > maximum){
                maximum = num;
            }
        }
        return maximum;
    }

    public static int find_max_odd(int[] numbers){
        int maximum = 0;
        for (int num : numbers){
            if (num % 2 != 0 && num > maximum){
                maximum = num;
            }
        }
        return maximum;
    }

    /*
    Problem 37
    There is an array A of N integers. What is the largest sum of two numbers which do not have any common digits
    Write a function that given an array of length N, returns largest possible sum of two numbers from A which do not
    have any digits in common. If it is impossible to choose two such numbers, the funciton should return -1

    Examples
    Given A = [53, 1, 36, 103, 53, 5] we return 108
    Given A = [9, 19, 99, 29] we return -1
    Given A = [6, 17, 71, 66, 17, 6] we return 137
    Given A = [15, 0, 105] we return 15
    */
    public static int solution37(int[] A) {
        int n = A.length;
        int maxSum = -1;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!shareDigits(A[i], A[j])) {
                    maxSum = Math.max(maxSum, A[i] + A[j]);
                }
            }
        }
        return maxSum;
    }

    public static boolean shareDigits(int a, int b) {
        boolean[] seen = new boolean[10];

        // mark digits of 'a'
        if (a == 0) seen[0] = true;
        while (a > 0) {
            seen[a % 10] = true;
            a /= 10;
        }

        // check digits of 'b'
        if (b == 0 && seen[0]) return true;
        while (b > 0) {
            if (seen[b % 10]) return true;
            b /= 10;
        }

        return false;
    }
    public static void main(String[] args) {
        // Problem 36
        // int[] num1 = {5, 3, 10, 6, 11};
        // solution36(num1);   // 21

        // int[] num2 = {20, 10, 7, 5};
        // solution36(num2);   // 27

        // int[] num3 = {7, 13, 15, 13};
        // solution36(num3);   // 15

        // int[] num43 = {2, 6, 4, 6};
        // solution36(num43);  // 6

        // Problem 37
        int[] A1 = {53, 1, 36, 103, 53, 5};
        int[] A2 = {9, 19, 99, 29};
        int[] A3 = {6, 17, 71, 66, 17, 6};
        int[] A4 = {15, 0, 105};

        System.out.println("Test 1 Expected: 108   Result: " + solution37(A1));
        System.out.println("Test 2 Expected: -1    Result: " + solution37(A2));
        System.out.println("Test 3 Expected: 137   Result: " + solution37(A3));
        System.out.println("Test 4 Expected: 15    Result: " + solution37(A4));
    }
}
