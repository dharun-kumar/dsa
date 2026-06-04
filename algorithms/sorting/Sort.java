package sorting;

public class Sort {

    public static void merge(int[] nums, int left, int right) {

        if(left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;
        merge(nums, left, mid);
        merge(nums, mid + 1, right);
        mergeHelper(nums, left, right, mid);
    }

    private static void mergeHelper(int[] nums, int left, int right, int mid) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftNums = new int[n1];
        int[] rightNums = new int[n2];

        for(int i=0; i<n1; i++) {
            leftNums[i] = nums[left + i];
        }

        for(int j=0; j<n2; j++) {
            rightNums[j] = nums[mid + 1 + j];
        }

        int i=0, j=0, k=left;

        while(i < n1 && j < n2) {
            if(leftNums[i] < rightNums[j]) {
                nums[k] = leftNums[i];
                i++;
            } else {
                nums[k] = rightNums[j];
                j++;
            }
            k++;
        }
        while(i < n1) {
            nums[k] = leftNums[i];
            i++; k++;
        }
        while(j < n2) {
            nums[k] = rightNums[j];
            j++; k++;
        }
    }

    public static void quick(int[] nums, int left, int right) {

        if(left >= right) {
            return;
        }

        int pivotIndex = partition(nums, left, right);
        quick(nums, left, pivotIndex-1);
        quick(nums, pivotIndex+1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int index = left;

        for(int i=left; i<right; i++) {
            if(nums[i] <= pivot) {
                swap(nums, i, index);
                index++;
            }
        }

        swap(nums, index, right);
        return index;
    }


    public static void bubble(int[] nums) {

        for(int i=0; i<nums.length-1; i++) {
            boolean swap = false;
            for(int j=0; j<nums.length-i-1; j++) {
                if(nums[j] >=  nums[j+1]) {
                    swap(nums, j, j+1);
                    swap = true;
                }
            }
            if(!swap) { break; }
        }

    }

    public static void selection(int[] nums) {

        for(int i=0; i<nums.length; i++) {
            int min = i;

            for(int j=i+1; j<nums.length; j++) {

                if(nums[j] < nums[min]) {
                    min = j;
                }
            }

            if(i != min) {
                swap(nums, min, i);
            }

        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
