package twopointers;

public class TwoPointers {

    //Left-Right - valid palindrome
    public static boolean isPalindrome(String s) {

        int left = 0, right = s.length() - 1;

        while(left < right) {

            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;

        }

        return true;
    }

    // Fast-Slow: detect cycle in linked list
    public static boolean hasCycle(ListNode head) {

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                return true;
            }

        }
        return false;
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    // Same Direction: move zeroes to end — read scans, write places
    public static void moveZeros(int[] nums) {

        int write = 0;

        for(int read=0; read<nums.length; read++) {

            if(nums[read] != 0 && read != write) {
                nums[write] = nums[read];
                write++;
            }

        }

        while(write < nums.length) {
            nums[write] = 0;
            write++;
        }

    }


}
