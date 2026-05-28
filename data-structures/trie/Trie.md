## Trie

A trie (prefix tree) is a tree-like data structure used to store strings where each node represents a character. Efficient for prefix-based search operations.

- Root node is empty — does not represent any character
- Each node holds a map of children `character → TrieNode` and a boolean `isEndOfWord`
- Words are stored character by character from root to leaf
- Common prefixes are shared across words — "app" and "apple" share the path 'a' → 'p' → 'p'

#### Array vs HashMap
- **Array `[26]`** → each node holds 26 fixed slots for a-z. O(1) lookup via index, higher memory — always allocates 26 slots regardless of children count. Use when character set is limited to lowercase English letters.
- **HashMap** → each node holds only existing characters. Lower memory, flexible for any character set (digits, symbols, Unicode). Use when character set is not fixed.

#### Insert
- When insert is called, start at root and traverse character by character
- For each character — if not present in children, create a new node
- Move to the next node after each character
- After the last character is processed, mark `isEndOfWord = true` on current node

#### Search
- When search is called, start at root and traverse character by character
- For each character — if not present in children, return false immediately
- After all characters are traversed, return value of `isEndOfWord` on current node
- Returns true only if the exact word exists — a prefix match alone is not sufficient

#### StartsWith (Prefix Search)
- When startsWith is called, start at root and traverse character by character
- For each character — if not present in children, return false immediately
- After all prefix characters are traversed, return true
- Does not check `isEndOfWord` — any path match is sufficient for prefix search

#### Delete
- When delete is called, traverse recursively to the end of the word
- If character not found at any point → return false, word does not exist
- At the last character → mark `isEndOfWord = false`
- On the way back up — a node can be safely removed only if it has no children and `isEndOfWord` is false
- This ensures the character is not shared by another word — removing it would break other words' paths
- If node has children or is end of another word → keep the node, only the `isEndOfWord` flag was cleared

### Complexity

| Operation  | Time | Space |
|------------|------|-------|
| Insert     | O(m) | O(m)  |
| Search     | O(m) | O(1)  |
| StartsWith | O(m) | O(1)  |
| Delete     | O(m) | O(1)  |

m = length of word

### Problems

- [208. Implement Trie](https://leetcode.com/problems/implement-trie-prefix-tree/)
- [211. Design Add and Search Words](https://leetcode.com/problems/design-add-and-search-words-data-structure/)