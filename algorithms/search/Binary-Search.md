## Binary Search

Binary search finds a target in a sorted array by comparing against the midpoint and eliminating half the search space at each step.

- **Standard** → sorted array, direct target search
- **Rotated Array** → sorted array pivoted at an unknown index, one half is always sorted

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

### Complexity

| Time     | Space |
|----------|-------|
| O(log n) | O(1)  |

### Problems

- [74. Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix/)
- [153. Find Minimum in Rotated Sorted Array](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/)
- [33. Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array/)
- [875. Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas/)