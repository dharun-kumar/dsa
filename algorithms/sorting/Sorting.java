package sorting;

public class Sorting {

    // Merge Sort — divide and conquer, O(n log n) guaranteed
    public static void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while(i <= mid && j <= right) {
            if(nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
            }
        }

        while(i <= mid) {
            temp[k++] = nums[i++];
        }

        while(j <= right) {
            temp[k++] = nums[j++];
        }

        for(int l=0; l<temp.length; l++) {
            nums[left + l] = temp[l];
        }
    }

    // Quick Sort — in-place, O(n log n) avg
    public static void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(nums, left, right);
        quickSort(nums, left, pivot - 1);
        quickSort(nums, pivot + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right]; // last element as pivot
        int index = left;       // index tracks boundary of smaller elements

        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                swap(nums, index, j);
                index++;
            }
        }

        swap(nums, index, right); // place pivot at correct position
        return index;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}