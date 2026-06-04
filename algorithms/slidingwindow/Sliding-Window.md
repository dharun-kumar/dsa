## Sliding Window

Sliding window maintains a window of elements over a data structure and slides it to avoid redundant computation. Reduces O(n²) brute force to O(n).

- **Fixed Size** → window size is constant, slide by adding right element and removing left element
- **Variable Size** → window expands and shrinks based on a condition — used for subarray/substring problems

> **Sliding Window vs Two Pointers** — if the window tracks a running aggregate (sum, frequency map), it's Sliding Window. If movement is driven purely by index comparison, it's Two Pointers.

### Implementation

#### Fixed Size Window
- Init window by processing first `k` elements
- Slide — add incoming element at `right`, remove outgoing element at `left`
- Track result at each position

#### Variable Size Window
- Expand window by moving `right` pointer — add element to window state
- When window condition is violated → shrink by moving left pointer until condition is restored
- Track result when window is valid

### Complexity

| Time | Space                          |
|------|--------------------------------|
| O(n) | O(1) or O(k) for frequency map |

### Problems

- [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/)
- [3. Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters/)
- [424. Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement/)
- [76. Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring/)