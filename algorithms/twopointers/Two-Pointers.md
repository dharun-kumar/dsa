## Two Pointers

Two pointer pattern involves using two pointers to iterate through a data structure, eliminating the need for nested loops.

- **Left-Right** (start at each end, converge toward center) → Sorted array with pair/triplet condition
- **Fast & Slow** (move at different speeds) → Cycle detection, midpoint finding on linked lists and arrays
- **Same Direction** (one pointer reads, other writes in-place) → In-place filtering or compression

> **Two Pointers vs Sliding Window** — if movement is driven by index comparison, it's Two Pointers. If the window tracks a running aggregate (sum, frequency), it's Sliding Window.

### Implementation

#### Left-Right
- Init `left = 0`, `right = length - 1`
- Move pointers toward center based on comparison with target condition
- Stop when `left >= right`

#### Fast & Slow
- Init both pointers at head
- Move `slow` by 1 step, `fast` by 2 steps each iteration
- If `fast` meets `slow` → cycle exists

#### Same Direction (Read-Write)
- Init `write = 0`, `read` traverses from 0 to end
- When `read` finds a valid element → write it at `write` index, increment `write`
- Elements before `write` are the filtered result

### Complexity

| Time | Space |
|------|-------|
| O(n) | O(1)  |

### Problems

- [125. Valid Palindrome](https://leetcode.com/problems/valid-palindrome/)
- [15. 3Sum](https://leetcode.com/problems/3sum/)
- [11. Container With Most Water](https://leetcode.com/problems/container-with-most-water/)
- [42. Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water/)
- [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/)