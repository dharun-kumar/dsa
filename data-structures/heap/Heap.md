## Heap

A heap is a complete binary tree stored as an array where each parent satisfies the heap property relative to its children.

- **Min Heap** → parent is always smaller than children — root is the minimum element
- **Max Heap** → parent is always larger than children — root is the maximum element

#### Array Representation
- Root is stored at index 0
- For node at index `i` → left child at `2i + 1`, right child at `2i + 2`, parent at `(i - 1) / 2`

#### Insert
- When insert is called, add the new element at the end of the array
- The heap property may now be violated between the new element and its parent
- Heapify up to restore — compare new element with its parent at `(index - 1) / 2`
- Swap with parent if heap property is violated, move up to parent index
- Repeat until root is reached or no violation is found

#### Poll
- When poll is called, return element at index 0 — it is the highest priority element
- Replace index 0 with last element and remove the last slot
- The heap property is now violated since the last element is placed at root
- Heapify down to restore — compare root with left child at `2i + 1` and right child at `2i + 2`
- Swap root with the higher priority child, move down to that index
- Repeat until a leaf node is reached or no violation is found

### Complexity

| Operation  | Time     | Space |
|------------|----------|-------|
| Insert     | O(log n) | O(1)  |
| Poll       | O(log n) | O(1)  |
| Peek       | O(1)     | O(1)  |
| Build Heap | O(n)     | O(1)  |

### Problems

- [215. Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array/)
- [973. K Closest Points to Origin](https://leetcode.com/problems/k-closest-points-to-origin/)
- [621. Task Scheduler](https://leetcode.com/problems/task-scheduler/)
- [295. Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream/)