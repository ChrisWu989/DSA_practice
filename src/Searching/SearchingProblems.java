package Searching;

public class SearchingProblems {
    /*
    Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements. 
    Example1: 
    Input: int[] nums = [0, 1, 0, 3, 12], 
    Output: [1, 3, 12, 0, 0]. 
    Note: 
    You must do this in-place without making a copy of the array. 
    Minimize the total number of operations. 
    */
    public static void problem1() {
        int[] nums = {0, 1, 0, 3, 12};
        int left = 0;

        for (int right = 0; right < nums.length; right++){
            if(nums[right] != 0){
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left++;
            }
        }

        System.out.println("After sorting:");
        for (int num : nums) {
            System.out.print(num + " ");
        }

    }
    
    /*
    Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right. 
    Note that elements beyond the length of the original array are not written. 
    Do the above modifications to the input array in place, do not return anything from your function. 
    
    Example 1: 
    Input: [1,0,2,3,0,4,5,0] 
    Output: null 
    Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4] 
    Example 2: 
    Input: [1,2,3] 
    Output: null 
    Explanation: After calling your function, the input array is modified to: [1,2,3] 
    */
    public static void problem2() {
        /*
         * Every time we see a 0 we want to insert a 0 and push array elements back
         */
        int[] nums = {1,0,2,3,0,4,5,0};
        int len = nums.length, zeroes = 0;

        // counts how many zeros are in array
        for (int num : nums) {
            if (num == 0) {
                zeroes++;
            }
        }
        //Lastindex = i
        //newindex = index with extra 0's
        int i = len - 1;
        int j = len + zeroes - 1;

        while (i >= 0) {
            if (j < len) {
                nums[j] = nums[i];
            }

            if (nums[i] == 0) {
                j--;

                if (j < len) {
                    nums[j] = 0;
                }
            }

            i--;
            j--;
        }

        System.out.println("After sorting:");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    /*
    Write a java program  to find maximum 3 elements from a array with linear time complexity
    int[] numArray = {10, 5, 8, 20, 15, 3, 25, 30};
    output:30,25,20
    */
    public static void problem3() {
        int[] nums = {10, 5, 8, 20, 15, 3, 25, 30};
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];

            // If current element is greater than first
            if (curr > first) {
                third = second;
                second = first;
                first = curr;
            } 
            // If current element is between first and second
            else if (curr > second) {
                third = second;
                second = curr;
            } 
            // If current element is between second and third
            else if (curr > third) {
                third = curr;
            }
        }
        System.out.println("Three biggest elements are " + first + " " + second + " " + third);
    }
    
    /*
    find maximum product of two integers in an array without sorting
    eg:
    int[] A = { 11, 23, 15, 6, -2 ,34};
    output:Pair is (23, 34)
    int[] A = { 11, 23, 15, 6, -35,-35 ,34};
    Pair is (-35, -35)
    */
    public static void problem4() {
        int[] nums = {10, 5, 8, 20, 15, 3, 25, 30};
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;

        for (int num : nums) {
            // Update max1 and max2
            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }

            // Update min1 and min2
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
        }
        
        if ((min1 * min2) > (max1 * max2)){
            System.out.println("Maximum product results from " + min1 + " " + min2);
        }
        else{
            System.out.println("Maximum product results from " + max1 + " " + max2);
        }
    }

    /*
    Leetcode #33: Search in Rotated Sorted Array (Medium)

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
    public static int problem5() {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            //check if left sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            //right half sorted
            else {
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } 
                else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        //problem1();
        //problem2();
        //problem3();
        //problem4();
        System.out.println("Index " + problem5());
    }
}