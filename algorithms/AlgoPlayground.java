import backtracking.Backtracking;
import search.BinarySearch;
import slidingwindow.SlidingWindow;
import sorting.Sorting;
import twopointers.TwoPointers;

import java.util.Arrays;

public class AlgoPlayground {

    public static void main(String[] args) {

        binarySearch();
        separator();

        sort();
        separator();

        twoPointers();
        separator();

        slidingWindow();
        separator();

        backtracking();
        separator();

    }

    private static void separator() {
        System.out.println("\n---------------------------- \n");
    }

    private static void binarySearch() {

        int[] sorted = {1, 3, 5, 7, 9, 11};
        System.out.println("Search 7 :: " + BinarySearch.search(sorted, 7));   // 3
        System.out.println("Search 6 :: " + BinarySearch.search(sorted, 6));   // -1

        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Search 6 :: " + BinarySearch.searchRotated(rotated, 6)); // 2
        System.out.println("Search 3 :: " + BinarySearch.searchRotated(rotated, 3));

    }

    private static void sort() {
        int[] arr1 = {5, 2, 8, 1, 9, 3};
        Sorting.mergeSort(arr1, 0, arr1.length - 1);
        System.out.println("Merge Sort :: " + Arrays.toString(arr1)); // [1,2,3,5,8,9]

        int[] arr2 = {5, 2, 8, 1, 9, 3};
        Sorting.quickSort(arr2, 0, arr2.length - 1);
        System.out.println("Quick Sort :: " + Arrays.toString(arr2)); // [1,2,3,5,8,9]
    }

    private static void twoPointers() {
        System.out.println("isPalindrome racecar :: " + TwoPointers.isPalindrome("racecar")); // true

        TwoPointers.ListNode head = new TwoPointers.ListNode(1);
        head.next = new TwoPointers.ListNode(2);
        head.next.next = new TwoPointers.ListNode(3);
        head.next.next.next = head.next;
        System.out.println("hasCycle :: " + TwoPointers.hasCycle(head)); // true

        int[] zeroes = {0, 1, 0, 3, 12};
        TwoPointers.moveZeros(zeroes);
        System.out.println("moveZeroes :: " + Arrays.toString(zeroes)); // [1, 3, 12, 0, 0]

    }

    private static void slidingWindow() {
        int[] nums = {2, 1, 5, 1, 3, 2};
        System.out.println("Max sum subarray k=3 :: " + SlidingWindow.maxSumSubarray(nums, 3)); // 9

        System.out.println("Longest substring :: " + SlidingWindow.lengthOfLongestSubstring("abcabcbb")); // 3

        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println("Max profit :: " + SlidingWindow.maxProfit(prices)); // 5

    }

    private static void backtracking() {
        int[] nums = {1, 2, 3};
        System.out.println("Subsets :: " + Backtracking.subsets(nums));

        int[] cNums = {2, 3, 6, 7};
       System.out.println("Combination Sum target=7 :: " + Backtracking.combinationSum(cNums, 7));

        int[] pNums = {1, 2, 3};
        System.out.println("Permutations :: " + Backtracking.permutations(pNums));
    }

}
