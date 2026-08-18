package search;

public class BinarySearch {

    // Standard binary search
    public static int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;

        while(left <= right) {

            int mid = left + (right - left) / 2; // avoids overflow

            if(nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return -1;
    }

    // 33. Search in Rotated Sorted Array
    public static int searchRotated(int[] nums, int target) {

        int left = 0, right = nums.length - 1;

        while(left <= right) {

            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[left] <= nums[mid]) { // left half is sorted

                if(target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else { // right half is sorted

                if(target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid -1;
                }

            }

        }

        return -1;
    }

    // 875. Koko Eating Bananas — search on answer
    public static int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canFinish(piles, mid, h)) {
                right = mid; // feasible — try a smaller speed
            } else {
                left = mid + 1; // not feasible — need a faster speed
            }
        }
        return left;
    }

    private static boolean canFinish(int[] piles, int speed, int h) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + speed - 1) / speed; // ceiling division
        }
        return hours <= h;
    }

}
