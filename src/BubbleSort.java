public class BubbleSort {
    public static void main(String[] args) {
        int[] numbers = {10, 20, 5, 9, 35, 94, 30, 44, 435, 6467};
        int comparisons = 0;

        for (int i = 1; i <= numbers.length - 1; i++){
            for(int j = 0; j < numbers.length - 1 - i; j++){
                if(numbers[j+1] < numbers[j]){
                    int temp = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = temp;
                }
                comparisons++;
            }
            comparisons++;
        }
        for(int i = 0; i < numbers.length; i++){
            System.out.println(numbers[i]);
        }
        System.out.println("Bubble sorted within " + comparisons + " comparisons");
    }
}