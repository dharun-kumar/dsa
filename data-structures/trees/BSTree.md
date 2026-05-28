## Trees

A tree is a hierarchical data structure where each node has a value and references to child nodes. Binary tree — each node has at most two children (left, right).

- **Binary Search Tree (BST)** → left < root < right, enables O(log n) search on balanced trees
- **Balanced BST** → keeps height as small as possible by rebalancing on insert/delete (AVL, Red-Black)

#### Height vs Depth
- **Height** → number of edges from a node to the deepest leaf. Height of tree = height of root.
- **Depth** → number of edges from root to a node. Root depth = 0.

#### Traversals
- **Preorder (Root → Left → Right)** → used in search, insert, serialize/clone
- **Inorder (Left → Root → Right)** → sorted order in BST
- **Postorder (Left → Right → Root)** → used in evaluate expression trees
- **Level Order (BFS)** → processes nodes level by level using a queue

### Implementation

Each node holds `data`, `left` and `right` references. Maintain a `root` pointer to the top node.

#### Insert
- When insert is called, compare data with current node — recurse left if smaller, right if larger
- When a null position is found, create a new node and return it — this is always a leaf position
- BST does not restructure on insert. AVL and Red-Black trees rebalance after insert to maintain height.

#### Search
- When search is called, compare data with current node
- If data matches — return true. If node is null — return false
- If smaller → recurse left, if larger → recurse right
- At each step half the tree is eliminated — this is what makes BST search O(log n) on balanced trees

#### Delete
- When delete is called, locate the node by comparing data — recurse left if smaller, right if larger
- Once found, three cases apply:
  - Leaf node → simply remove by returning null
  - One child → replace node with its only child
  - Two children → find inorder successor (minimum in right subtree), copy its data into current node, delete the successor from right subtree — this maintains BST ordering

#### Height
- When height is called, recursively compute height of left and right subtree
- Return 1 + max of both heights — the extra 1 accounts for the current node
- A null node returns 0 — base case

#### Find Minimum
- When findMin is called, traverse left until null is reached
- The last non-null node is the minimum — leftmost node in BST

#### Find Maximum
- When findMax is called, traverse right until null is reached
- The last non-null node is the maximum — rightmost node in BST

#### Lowest Common Ancestor (LCA)
- When lca is called, compare both values with current node
- If both values are smaller → LCA is in left subtree, recurse left
- If both values are larger → LCA is in right subtree, recurse right
- Otherwise → current node is the LCA — one value is on each side

### Complexity

| Operation | Balanced | Unbalanced |
|-----------|----------|------------|
| Search    | O(log n) | O(n)       |
| Insert    | O(log n) | O(n)       |
| Delete    | O(log n) | O(n)       |
| Height    | O(log n) | O(n)       |

### Problems

- [226. Invert Binary Tree](https://leetcode.com/problems/invert-binary-tree/)
- [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
- [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
- [199. Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view/)
- [235. Lowest Common Ancestor of BST](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/)
- [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- [230. Kth Smallest Element in BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst/)
- [105. Construct Binary Tree from Preorder and Inorder](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)
- [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
- [297. Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)