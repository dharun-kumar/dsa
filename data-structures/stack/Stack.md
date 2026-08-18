## Stack

A stack is a LIFO (Last In, First Out) data structure. Elements are added and removed from the same end (top).

- **Simple Stack** → standard LIFO, used for matching, validation, expression evaluation
- **Monotonic Stack** → maintains increasing or decreasing order — used to find next greater/smaller element

#### Monotonic Stack

A stack that maintains increasing or decreasing order. Finds the next greater/smaller element for each element — reduces O(n²) to O(n).

    Input:  [2, 1, 2, 4, 3]
    Next Greater: [4, 2, 4, -1, -1]

    Processing 3 → stack empty, push 3
    Processing 4 → 4 > 3, pop 3 (next greater of 3 is 4), push 4
    Processing 2 → 2 < 4, push 2 (stack: [4, 2])
    Processing 1 → 1 < 2, push 1 (stack: [4, 2, 1])
    Processing 2 → 2 > 1, pop 1 (next greater of 1 is 2), push 2

- Iterate through elements — for each element, pop from the stack while the current element breaks the monotonic order
- Each popped element has found its answer — the current element is its next greater (or smaller)
- Push the current element's index onto the stack
- Elements remaining in the stack after iteration have no next greater/smaller

### Implementation
Init an object array and `top = -1` to track the top of the stack.

`top` starts at -1 to indicate an empty stack — the first push increments it to 0, which is a valid array index. `size()` returns `top + 1` since top is zero-based.

#### Push
- When push is called, resize the array if `top` has reached capacity
- Increment `top` first using `++top`, then insert element at that index
- Resize doubles the array size — `Arrays.copyOf` copies all existing elements into the new array

#### Pop
- When pop is called, store element at `top` index
- Set that slot to null — allows garbage collection of the removed element
- Decrement `top` using `top--` and return the stored value

#### Peek
- When peek is called, return element at `top` index without removing it or changing `top`

### Complexity

| Operation | Time |
|-----------|------|
| Push      | O(1) |
| Pop       | O(1) |
| Peek      | O(1) |

Space: O(n) for n elements.

### Problems

- [20. Valid Parentheses](https://leetcode.com/problems/valid-parentheses/)
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)
- [155. Min Stack](https://leetcode.com/problems/min-stack/)