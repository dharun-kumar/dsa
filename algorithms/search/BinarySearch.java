package search;

public class BinarySearch {

    public static int search(int[] nums, int target) {

        int left = 0, right = nums.length - 1;  //avoid integer overflow

        while(left <= right) {

            int mid = left + (right - left) / 2;

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

    public static int searchRotated(int[] nums, int target) {

        int left = 0, right = nums.length - 1;

        while(left <= right) {

            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                return mid;
            } else if(nums[left] <= nums[mid]) { //left side is sorted

                if(target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            } else {    //right side is sorted

                if(target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid -1;
                }

            }

        }

        return -1;
    }

    public static int findMin(int[] nums) {

        int left = 0, right = nums.length - 1;

        while(left < right) {   //until left == right =, both pointers joins at min element index

            int mid = left + (right - left) / 2;

            if(nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }

        }
        return nums[left];
    }

}
