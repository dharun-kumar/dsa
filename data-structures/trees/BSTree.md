## Trees

A tree is a hierarchical data structure where each node has a value and references to child nodes. Binary tree — each node has at most two children (left, right).

- **Binary Search Tree (BST)** → left < root < right, enables O(log n) search on balanced trees

#### Height vs Depth
- **Height** → number of edges from a node to the deepest leaf. Height of tree = height of root.
- **Depth** → number of edges from root to a node. Root depth = 0.

#### Traversals
- **Preorder (Root → Left → Right)** → used in search, insert, serialize/clone
- **Inorder (Left → Root → Right)** → sorted order in BST
- **Postorder (Left → Right → Root)** → used in diameter, max path sum, delete
- **Level Order (BFS)** → processes nodes level by level using a queue

### Implementation

Each node holds `data`, `left` and `right` references. Maintain a `root` pointer to the top node.

#### Insert
- When insert is called, compare data with current node — recurse left if smaller, right if larger
- When a null position is found, create a new node and return it — this is always a leaf position

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

#### Validate BST

A valid BST must satisfy: every node in the left subtree is strictly less than the current node, and every node in the right subtree is strictly greater. Checking only the immediate children is not enough — a node deep in the left subtree could violate the rule against a higher ancestor.

    [5]
    / \
  [1]  [6]
       / \
     [3]  [7]      ← Invalid: 3 is in the right subtree of 5, but 3 < 5

- When validate is called, pass the current node along with a valid range `(min, max)`
- Root starts with range `(-∞, +∞)` — no constraints initially
- If node is null → return true (an empty tree is a valid BST)
- If node's value is not within `(min, max)` → return false
- Recurse left with updated range `(min, node.data)` — all left descendants must be smaller
- Recurse right with updated range `(node.data, max)` — all right descendants must be larger
- Return true only if both subtrees are valid

#### Lowest Common Ancestor (LCA)

The lowest common ancestor of two nodes `p` and `q` is the deepest node in the tree that is an ancestor of both. A node is allowed to be an ancestor of itself — so if `p` is a direct ancestor of `q`, then `p` itself is the LCA.

        [3]
        / \
      [5]  [1]
      / \
    [6]  [2]

    LCA(6, 2) = 5  → 5 is the deepest node that contains both 6 and 2 in its subtrees
    LCA(5, 1) = 3  → 3 is the deepest node that contains both 5 and 1
    LCA(5, 2) = 5  → 5 is an ancestor of 2, and a node can be its own ancestor

- When lca is called, check if current node is null or matches either target → return current node
- Recurse into left subtree and right subtree
- If both return non-null → current node is the LCA — one target is in each subtree
- If only one returns non-null → that result is the LCA (both targets are in the same subtree)
- If both return null → neither target exists in this subtree

**Walkthrough: LCA(6, 2)**

    Step 1: node=3, not a target
            recurse left(5) and right(1)

    Step 2: node=5, not a target
            recurse left(6) and right(2)

    Step 3: node=6, matches target → return 6
    Step 4: node=2, matches target → return 2

    Step 5: back at node 5 — left=6, right=2, both non-null
            → 5 is the LCA, return 5

    Step 6: node=1, not a target
            left=null, right=null → return null

    Step 7: back at node 3 — left=5, right=null
            → only left is non-null, return 5

    Result: LCA(6, 2) = 5

> In a BST, LCA can be solved in O(log n) by comparing values — if both targets are smaller, go left; if both are larger, go right; otherwise current node is the LCA. The general binary tree approach above works for any binary tree but runs in O(n) since it must check both subtrees.

#### Diameter

The diameter of a binary tree is the length of the longest path between any two nodes. The path is measured in number of edges — not nodes. This path may or may not pass through the root.

        [1]
        / \
      [2]  [3]
      / \
    [4]  [5]

    Diameter = 3  → path is 4 → 2 → 1 → 3 (or 5 → 2 → 1 → 3)

- Use postorder traversal — compute height of left and right subtrees first
- At each node, the path through that node is `leftHeight + rightHeight`
- Track the maximum path length seen across all nodes using a global variable
- Return height (`1 + max(left, right)`) up to the parent — but update the global max with the full path (`left + right`)

**Walkthrough: Diameter**

    At every node, the algorithm computes two things:
    - path through node = leftH + rightH        ← candidate diameter (uses both sides)
    - height to return  = 1 + max(leftH, rightH) ← for parent's calculation (one side only)

    Postorder visits: 4, 5, 2, 3, 1          maxDia = 0

    Node 4 (leaf): leftH=0, rightH=0
      path = 0, maxDia = 0          ← no path through a leaf
      return height = 1             ← tell parent: 1 edge to reach me

    Node 5 (leaf): same as node 4, return height = 1

    Node 2: leftH=1 (from 4), rightH=1 (from 5)
      path = 1+1 = 2, maxDia = 2   ← path 4→2→5 has 2 edges
      return height = 2             ← tell parent: 2 edges to reach my deepest leaf

    Node 3 (leaf): return height = 1, maxDia still 2

    Node 1: leftH=2 (from 2), rightH=1 (from 3)
      path = 2+1 = 3, maxDia = 3   ← path 4→2→1→3 has 3 edges
      return height = 3             ← not used (root), but maxDia = 3 is the answer

    Result: Diameter = 3

#### Maximum Path Sum

A path in a binary tree is a sequence of connected nodes where each node appears at most once. The path can start and end at any node — it does not have to pass through the root. Maximum path sum is the highest sum among all such paths.

        [-10]
        /   \
      [9]   [20]
             / \
           [15] [7]

    Max Path Sum = 42  → path is 15 → 20 → 7

- Use postorder traversal — compute max gain from left and right subtrees first
- A subtree's gain is `max(0, subtreeSum)` — ignore negative paths (they reduce the total)
- At each node, the path through that node is `node.data + leftGain + rightGain`
- Track the maximum path sum seen across all nodes using a global variable
- Return `node.data + max(leftGain, rightGain)` up to the parent — only one branch can be part of the path going upward

**Walkthrough: Max Path Sum**

    Same postorder pattern as Diameter, with two additions:
    - Negative subtrees are ignored: gain = max(0, subtree)
    - Returns one side only: parent can only extend through one branch

    Postorder visits: 9, 15, 7, 20, -10       maxSum = -∞

    Node 9 (leaf): val=9, no children
      path = 9, maxSum = 9
      return 9                      ← gain this subtree offers to parent

    Node 15 (leaf): return 15. Node 7 (leaf): return 7

    Node 20: leftGain=max(0, 15)=15, rightGain=max(0, 7)=7
      path = 20+15+7 = 42, maxSum = 42   ← best path uses both children
      return 20 + max(15, 7) = 35         ← but parent can only use one side

    Node -10: leftGain=max(0, 9)=9, rightGain=max(0, 35)=35
      path = -10+9+35 = 34               ← worse than 42
      return -10 + max(9, 35) = 25       ← if -10 were positive, this path could extend further

    Result: Max Path Sum = 42 (path 15→20→7, never goes through -10)

### Complexity

| Operation | Balanced | Unbalanced |
|-----------|----------|------------|
| Search    | O(log n) | O(n)       |
| Insert    | O(log n) | O(n)       |
| Delete    | O(log n) | O(n)       |
| Height    | O(n)     | O(n)       |

### Problems

- [104. Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree/)
- [102. Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal/)
- [98. Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree/)
- [236. Lowest Common Ancestor of Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/)
- [543. Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree/)
- [124. Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum/)
