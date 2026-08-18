package twopointers;

public class TwoPointers {

    // 125. Valid Palindrome — left-right
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

    // 141. Linked List Cycle — fast and slow
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

    // 283. Move Zeroes — same direction
    public static void moveZeros(int[] nums) {

        int write = 0;

        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                nums[write++] = nums[read];
            }
        }

        while (write < nums.length) {
            nums[write++] = 0;
        }

    }

}
