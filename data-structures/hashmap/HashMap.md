## Hash Map

A hash map stores key-value pairs with O(1) average lookup using a hash function to map keys to indices.

- **HashMap** → unordered key-value pairs, O(1) avg get/put
- **HashSet** → unordered unique keys, O(1) avg lookup
- **LinkedHashMap** → maintains insertion order
- **TreeMap** → sorted by key, O(log n) operations

> **Collision** — two keys map to the same index. Resolved via chaining (linked list at each bucket). Degrades to O(n) if many keys collide at same index.

### Implementation
Init an `Entry` array with a fixed capacity. Use `key.hashCode() % length` to compute the index. Each `Entry` holds key, value and a `next` reference for chaining — when two keys hash to the same index, they are linked together as a chain at that bucket.

#### Put
- When put is called, compute the index using hash function
- Traverse the chain at that index — if the key already exists, update its value and return
- If key is not found, insert new entry at the head of the chain — inserting at head is O(1) since no traversal is needed, unlike inserting at tail which requires traversing the entire chain

#### Get
- When get is called, compute the index using hash function
- Traverse the chain at that index until a matching key is found
- Return the value if found, null otherwise

#### Remove
- When remove is called, compute the index using hash function
- Traverse the chain tracking the previous node
- When the key is found — if it is the head, point the bucket directly to next node. Otherwise rewire `prev.next` to skip the matched node

#### ContainsKey
- When containsKey is called, delegate to get
- Return true if result is not null, false otherwise

### Complexity

| Operation          | Average | Worst |
|--------------------|---------|-------|
| Put / Get / Remove | O(1)    | O(n)  |
| ContainsKey        | O(1)    | O(n)  |

Space: O(n) for n entries.

### Problems

- [1. Two Sum](https://leetcode.com/problems/two-sum/)
- [49. Group Anagrams](https://leetcode.com/problems/group-anagrams/)
- [347. Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements/)
- [238. Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self/)
- [128. Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence/)