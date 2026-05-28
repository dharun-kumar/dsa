## Tree Traversals

### Recursive

#### Preorder (Root → Left → Right)
- If node is null → return
- Visit current node
- Recurse into left child
- Recurse into right child

#### Inorder (Left → Root → Right)
- If node is null → return
- Recurse into left child
- Visit current node
- Recurse into right child

#### Postorder (Left → Right → Root)
- If node is null → return
- Recurse into left child
- Recurse into right child
- Visit current node

---

### Iterative

#### Preorder — Stack (Root → Left → Right)
- Initialize stack, set current = root
- While current is not null or stack is not empty
  - If current is not null → visit current, push current to stack, move to current.left
  - Else → pop from stack, move to popped node's right child

#### Inorder — Stack (Left → Root → Right)
- Initialize stack, set current = root
- While current is not null or stack is not empty
  - If current is not null → push current to stack, move to current.left
  - Else → pop from stack into current, visit current, move to current.right

#### Postorder — Stack (Left → Right → Root)
- Initialize stack, set current = root
- Set visited = null (tracks last visited node — needed to detect if right child is already processed)
- While current is not null or stack is not empty
  - If current is not null → push current to stack, move to current.left
  - Else → peek top of stack
    - If peek.right is not null and peek.right != visited → move to peek.right
    - Else → pop from stack into visited, visit visited

#### Level Order — Queue
- If root is not null → add root to queue
- While queue is not empty
  - Poll from queue into current, visit current
  - If current.left is not null → add to queue
  - If current.right is not null → add to queue