package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    // Fixed size — maximum sum subarray of size k
    public static int maxSumSubarray(int[] nums, int k) {

        int windowSum = 0;
        for(int i=0; i<k; i++) {
            windowSum += nums[i];
        }

        int maxSum = windowSum;
        for(int i=k; i<nums.length; i++) {
            windowSum += nums[i] - nums[i-k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;

    }

    // Variable size — longest substring without repeating characters
    public static int lengthOfLongestSubstring(String s) {

        Map<Character, Integer> freq = new HashMap<>();
        int left = 0, maxLen = 0;

        for(int right=0; right < s.length(); right++) {

            char c = s.charAt(right);
            freq.put(c, freq.getOrDefault(c, 0) + 1);

            // shrink window while duplicate exits, using while to remove until no more duplicates ("abba")
            while(freq.get(c) > 1) {
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    // Variable size — best time to buy and sell stock
    public static int maxProfit(int[] prices) {

        int left = 0, maxProfit = 0;

        for(int right=1; right<prices.length; right++) {
            if(prices[right] < prices[left]) {
                left = right;                   // found new minimum, move buy pointer to have max profit
            }
            maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
        }
        return maxProfit;
    }

}
