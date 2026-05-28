## Graph Algorithms

### DFS (Depth First Search)

Explores as deep as possible along each branch before backtracking. Uses a stack (iterative) or call stack (recursive).

#### Recursive
- Initialize visited set
- If node is already visited → return
- Mark node as visited, visit node
- For each neighbor edge → recurse into neighbor

#### Iterative
- Initialize visited set, push start node to stack
- While stack is not empty → pop into current
- If current already visited → skip and continue
- Mark current as visited, visit current
- For each neighbor edge → push neighbor to stack

> Marking visited on pop — same node can be pushed multiple times but handled by the continue check. Does not affect DFS correctness.

---

### BFS (Breadth First Search)

Explores all neighbors level by level before going deeper. Uses a queue.

- Initialize visited set, add start node to queue, mark start as visited
- While queue is not empty → poll into current, visit current
- For each neighbor edge → if not visited, mark as visited and add to queue

> Marking visited on enqueue — prevents same node from being added to queue multiple times. Required for BFS to guarantee correct level-order traversal and shortest path behavior.