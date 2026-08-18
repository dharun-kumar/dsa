package dynamicprogramming;

import java.util.Arrays;

public class DynamicProgramming {

    // 198. House Robber — 1D DP (take or skip)
    public static int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]); // skip or take
        }
        return dp[nums.length - 1];
    }

    // 300. Longest Increasing Subsequence — 1D DP (extend subsequence)
    public static int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // each element is length 1
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 1143. Longest Common Subsequence — 2D DP
    public static int longestCommonSubsequence(String text1, String text2) {
        int rows = text1.length(), cols = text2.length();
        int[][] dp = new int[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1; // match
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // skip from either
                }
            }
        }
        return dp[rows][cols];
    }

    // 322. Coin Change — Unbounded Knapsack
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int w = coin; w <= amount; w++) { // left to right: coin reusable
                dp[w] = Math.min(dp[w], dp[w - coin] + 1); // skip or use
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
