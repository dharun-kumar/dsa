## Linked List

A linked list is a linear data structure where each node holds data and a reference to the next node. Unlike arrays, nodes are not stored contiguously in memory — each node can be anywhere in memory, connected only by references.

- **Singly** → each node points to next, traversal in one direction only
- **Doubly** → each node points to both next and previous, used in LRU Cache

### Implementation
Each node holds `data` and a `next` reference. Maintain a `head` pointer to the first node.

#### Insert at Tail
- When insert is called, create a new node
- If the list is empty, assign the new node to head and return
- Otherwise traverse to the last node — the node whose `next` is null
- Point last node's `next` to the new node
- This is O(n) because we must traverse the entire list to find the tail

#### Insert at Head
- When insertAtHead is called, point the new node's `next` to current head
- Update head to the new node
- This is O(1) — no traversal needed since we always have a direct reference to head

#### Insert at Index
- When insertAt is called, if index is 0 delegate to insertAtHead and return
- Traverse to the node at `index - 1` — the node just before the insertion point
- Point new node's `next` to `current.next`, then point `current.next` to new node
- This wires the new node into the chain without breaking existing links

#### Delete
- When delete is called, if list is empty return immediately
- If head matches the value, update head to `head.next` and return — the old head is now unreferenced
- Otherwise traverse tracking `current` — when `current.next` matches the value, rewire `current.next` to `current.next.next` to skip the matched node

#### Search
- When search is called, traverse from head comparing each node's data
- Return true as soon as a match is found
- Return false if null is reached — value does not exist in the list

### Complexity

| Operation                   | Time |
|-----------------------------|------|
| Access                      | O(n) |
| Insert / Delete at head     | O(1) |
| Insert / Delete at position | O(n) |

Space: O(n) for n elements.

### Problems

- [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/)
- [21. Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists/)
- [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/)
- [146. LRU Cache](https://leetcode.com/problems/lru-cache/)
- [23. Merge K Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists/)