## Stack

A stack is a LIFO (Last In, First Out) data structure. Elements are added and removed from the same end (top).

- **Simple Stack** → standard LIFO, used for matching, validation, expression evaluation
- **Monotonic Stack** → maintains increasing or decreasing order — used to find next greater/smaller element

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
- [155. Min Stack](https://leetcode.com/problems/min-stack/)
- [22. Generate Parentheses](https://leetcode.com/problems/generate-parentheses/)
- [739. Daily Temperatures](https://leetcode.com/problems/daily-temperatures/)