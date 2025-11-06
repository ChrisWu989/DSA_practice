package Graph;
/*
Problem 27
There is an array A of N integers and two types of opeartions that can be performed on the elements of the array
    - Increment a single element A, which costs C1
    - Increment two elements of A, which costs C2. Chosen elements need to be in different positions
What is the minimum total cost of operations that will make all elements of A equal?
Return its 9 last digits without leading zeros (in other words return the result modulo 10^9)

class Solution { public int solution(int[] A, int C1, int C2) }
Write a function that given an array A of N integers and two integers C1 and C2,
returns the minimum cost of equalizing the array (modulo 10^9)

Examples
1. Given A = [1, 4], C1 = 15 and C2 = 3, the function should return 45. Increment first element 3 times

2. Given A = [2, 11, 11, 11, 12], C1 = 10 and C2 = 4, the function should return 54
    - Increment first and second element using second operation three times A = [5, 14, 11, 11, 12]
    - Increment first and third element using second operation three times A = [8, 14, 14, 11, 12]
    - Increment first and fourth element using second operation three times A = [11, 14, 14, 14, 12]
    - Increment first and fifth element using second operation twice A = [13, 14, 14, 14, 14]
    - Increment first element using first opeartion once A = [14, 14, 14, 14, 14]

3. Given A = [1000000, 2, 1, 2, 1000000], C1= 10000 and C2 = 3000, the function should return 999998000
    - Increment second and third element using the second operation 499,999 times A = [1000000, 500001, 500000, 2, 1000000]
    - Increment third and fourth element using the second operation 499,999 times A = [1000000, 500001, 999999, 500001, 1000000]
    - Increment second and fourth element using the second operation 499,999 times A = [1000000, 1000000, 999999, 1000000, 1000000]
    - Increment the third element using the first operation once A = [1000000, 1000000, 1000000, 1000000, 1000000]
    - Total cost is 5999998000 but its returned modulo 10^9

4. Given A = [500000, 0, 500000, 500000, 500000], C1 = 10000 and C2 = 3000, the function should return 10000
    - Increment first and second element using the second operation 166,667 times A = [666667, 166667, 500000, 500000, 500000]
    - Increment second and third element using the second operation 166,667 times A = [666667, 333334, 666667, 500000, 500000]
    - Increment second and fourth element using the second operation 166,667 times A = [666667, 500001, 666667, 666667, 500000]
    - Increment second and fifth element using the second operation 166,667 times A = [666667, 666668, 666667, 666667, 666667]
    - Increment first and third element using the second operation once A = [666668, 666668, 666668, 666667, 666667]
    - Increment courth and fifth element using the second operation once times A = [666668, 666668, 666668, 666668, 666668]
    - Total cost is 2000010000 but its returned modulo 10^9
*/
public class Problem27 {
    private long MOD = 1000000000; //10^9
    public int solution(int[] A, int C1, int C2) {
        // largest number in array A
        int maxNum = 0;
        for (int num : A) {
            if (num > maxNum){
                maxNum = num;
            }
        }

        // Sum of differences of each element
        // diff = maxNum - A[i]
        // totalDiff = sum(diff)
        long totalDiff = 0;
        for(int num : A) {
            totalDiff += (maxNum - num);
        }
        return (int) result;
    }
}
