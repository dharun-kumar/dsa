package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Backtracking {

   public static List<List<Integer>> subsets(int[] nums) {
       List<List<Integer>> res = new ArrayList<>();
       backtrackSubsets(nums, 0, new ArrayList<>(), res);
       return res;
   }

   private static void backtrackSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> res) {
       res.add(new ArrayList<>(current));

       for(int i=index; i<nums.length; i++) {
           current.add(nums[i]);
           backtrackSubsets(nums, i + 1, current, res);
           current.removeLast();
       }

   }

    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackCombinationSum(nums, target, 0, new ArrayList<>(), res);
        return res;
    }

    private static void backtrackCombinationSum(int[] nums, int target, int index, List<Integer> current, List<List<Integer>> res) {

       if(target == 0) {
           res.add(new ArrayList<>(current));
           return;
       }

       for(int i=index; i<nums.length; i++) {
           current.add(nums[i]);
           backtrackCombinationSum(nums, target - nums[i], i + 1, current, res); // i + 1, right after the one which we processed
           current.removeLast();
       }

    }

    public static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackPermutations(nums, 0, res);
        return res;
    }

    private static void backtrackPermutations(int[] nums, int index, List<List<Integer>> res) {

       if(index == nums.length) {
           res.add(new ArrayList<>(Arrays.stream(nums).boxed().toList()));
           return;
       }

       for(int i=index; i<nums.length; i++) {
           swap(nums, index, i);
           backtrackPermutations(nums, index + 1, res);
           swap(nums, index, i);
       }

    }

    private static void swap(int[] nums, int i, int j) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }

}
