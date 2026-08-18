## Sorting

Sorting arranges elements in a defined order. Choice of algorithm depends on input size, space constraints, and whether stability is required.

> **Stable sort** — preserves relative order of equal elements. Merge Sort is stable, Quick Sort is not.

#### Bubble Sort

1. Start at the beginning of the array
2. Compare the current element with the next element — if current is greater, swap them
3. Repeat — each pass bubbles the largest unsorted element to its correct position
4. Outer loop runs `n - 1` times, inner loop runs `n - i - 1` — last i elements are already sorted
5. Optimization — track swaps with a boolean flag, break early if no swap occurs in a pass

Time: O(n²) — Space: O(1)

#### Selection Sort

1. Start at the beginning of the array
2. Find the minimum element in the unsorted portion by comparing current with all remaining elements — track `minIndex`
3. Swap minimum with the first unsorted element
4. Outer loop runs `n - 1` times — shrinks unsorted portion by one each pass
5. Inner loop runs from `i + 1` to end — first i elements are already in correct position

Time: O(n²) — Space: O(1)

#### Merge Sort

1. Divide array into two halves, recurse until subarray size is 1
2. Merge sorted halves by comparing elements one by one and placing the smaller first
3. Uses `left, mid` and `mid + 1, right` for the two halves — mid is included in the left half

Time: O(n log n) — log n levels of recursion, O(n) merge work at each level — Space: O(n)

**Walkthrough: Merge Sort [5, 2, 8, 1]**

    Split phase — keep dividing until each subarray has 1 element (already sorted):
      [5, 2, 8, 1] → [5, 2] and [8, 1]
      [5, 2]       → [5] and [2]         ← size 1, stop splitting
      [8, 1]       → [8] and [1]

    Merge phase — combine sorted halves by comparing front elements:
      [5] + [2] → compare front elements → 2 < 5 → [2, 5]
      [8] + [1] → compare front elements → 1 < 8 → [1, 8]
      [2, 5] + [1, 8] → 1<2 take 1; 2<8 take 2; 5<8 take 5; take 8
                       → [1, 2, 5, 8]

    Each merge produces a sorted result from two sorted inputs.
    log₂(4) = 2 levels of splitting, O(n) comparisons per merge level.

#### Quick Sort

1. Pick a pivot element (typically last element)
2. Partition — place elements smaller than pivot to its left, larger to its right
3. Pivot is now at its correct final position (`pivotIndex`)
4. Recursively sort `left, pivotIndex - 1` and `pivotIndex + 1, right`
5. Uses `pivotIndex - 1` and `pivotIndex + 1` — unlike merge sort which uses `mid` and `mid + 1`, pivot already occupies its final position so it is excluded from both sides

Time: O(n log n) avg, O(n²) worst (sorted input with bad pivot) — Space: O(log n)

> Worst case O(n²) occurs when pivot is always the smallest or largest element — one partition has n-1 elements, the other has 0. Randomizing pivot selection avoids this.

### Comparison

| Algorithm      | Time (avg) | Time (worst) | Space    | Stable |
|----------------|------------|--------------|----------|--------|
| Bubble Sort    | O(n²)      | O(n²)        | O(1)     | Yes    |
| Selection Sort | O(n²)      | O(n²)        | O(1)     | No     |
| Merge Sort     | O(n log n) | O(n log n)   | O(n)     | Yes    |
| Quick Sort     | O(n log n) | O(n²)        | O(log n) | No     |

