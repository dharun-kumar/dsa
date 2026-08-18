## Binary Search

Binary search finds a target in a sorted array by comparing against the midpoint and eliminating half the search space at each step.

- **Standard** → sorted array, direct target search
- **Rotated Array** → sorted array pivoted at an unknown index, one half is always sorted
- **Search on Answer** → the answer itself is the search space, use a feasibility check to narrow it

### Implementation

Init `left = 0` and `right = length - 1`. Compute `mid = left + (right - left) / 2` — avoids integer overflow compared to `(left + right) / 2`.

#### Standard Binary Search
- When search is called, compare target with element at `mid`
- If target matches → return `mid`
- If target is smaller → discard right half, set `right = mid - 1`
- If target is larger → discard left half, set `left = mid + 1`
- Repeat until `left > right` — target not found, return -1

#### Search in Rotated Array
- A rotated sorted array always has one half that is fully sorted
- Compare `nums[left]` with `nums[mid]` to identify which half is sorted
- If left half is sorted — check if target falls within that range, if yes search left, else search right
- If right half is sorted — check if target falls within that range, if yes search right, else search left
- Repeat until target found or `left > right`

**Walkthrough: Search in [4, 5, 6, 7, 0, 1, 2], target=0**

    At each step: identify which half is sorted, check if target is in that half.
    If yes → search the sorted half. If no → search the other half.
    One half is ALWAYS sorted in a rotated array — this is the invariant.

    Step 1: left=0, right=6, mid=3 → nums[mid]=7
      nums[left]=4 <= nums[mid]=7 → left half [4,5,6,7] is sorted
      target=0 not in [4..7]      → target must be in unsorted half → left=4

    Step 2: left=4, right=6, mid=5 → nums[mid]=1
      nums[left]=0 <= nums[mid]=1 → left half [0,1] is sorted
      target=0 in [0..1]          → search sorted half → right=5

    Step 3: left=4, right=5, mid=4 → nums[mid]=0
      target matches → return 4

#### Search on Answer
- When the problem asks for a minimum or maximum value that satisfies a condition, binary search on the answer itself
- Set `left` to the minimum possible answer, `right` to the maximum possible answer
- Compute `mid` and check if `mid` is a feasible answer using a helper function
- If feasible → `mid` could be the answer, try a smaller value by setting `right = mid`
- If not feasible → `mid` is too small, set `left = mid + 1`
- When `left == right`, the answer is found

> Standard binary search looks for a target in the data. Search on answer defines a range of possible answers and narrows it using a feasibility check — the data is used inside the check, not as the search space.

### Complexity

| Time     | Space |
|----------|-------|
| O(log n) | O(1)  |

### Problems

- [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)