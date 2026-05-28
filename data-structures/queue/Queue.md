## Queue

A queue is a FIFO (First In, First Out) data structure. New elements are added at the rear and removed from the front.

- **Simple Queue** → standard FIFO, used in BFS traversal
- **Deque** → insert/remove from both ends, used in sliding window problems
- **Priority Queue** → dequeue by priority, used in Top-K and shortest path problems

### Implementation
Init an object array with `front = 0`, `rear = -1`, `size = 0` to track enqueue and dequeue positions.

`size` is tracked separately — using `rear - front` breaks after circular wrap since `rear` can be less than `front`. `size` always reflects the correct count regardless of wrap.

Use circular wrap `% length` on `front` and `rear` — after dequeue, the freed slots at the front would be wasted without wrapping. Circular wrap allows `rear` to wrap back to the beginning and reuse those slots.

#### Enqueue
- When enqueue is called, resize if array is full
- Increment `rear` using `(rear + 1) % length` — circular wrap ensures rear wraps to 0 after reaching the end
- Insert element at `rear` index and increment `size`

#### Dequeue
- When dequeue is called, return element at `front` index and set that slot to null
- Increment `front` using `(front + 1) % length` — circular wrap moves front forward, reusing slots freed by previous dequeues
- Decrement `size`

#### Peek
- When peek is called, return element at `front` index without removing it or changing any pointers

### Complexity

| Operation | Time |
|-----------|------|
| Enqueue   | O(1) |
| Dequeue   | O(1) |
| Peek      | O(1) |

Space: O(n) for n elements.