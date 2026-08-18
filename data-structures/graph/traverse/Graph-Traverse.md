## Graph Algorithms

### DFS (Depth First Search)

Explores as deep as possible along each branch before backtracking. Uses a stack (iterative) or call stack (recursive).

Use DFS when you need to explore all connected nodes from a starting point — flood fill, path finding, or cycle detection.

A **visited set** is required to track processed nodes. Without it, cycles cause infinite loops.

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

### BFS (Breadth First Search)

Explores all neighbors level by level before going deeper. Uses a queue.

Use BFS when you need shortest distance in an unweighted graph or level-by-level processing. First visit to any node is always the shortest path.

**Multi-source BFS** — start with multiple nodes in the queue. Used when multiple starting points spread simultaneously (Rotting Oranges). Each level = one time step.

- Initialize visited set, add start node to queue, mark start as visited
- While queue is not empty → poll into current, visit current
- For each neighbor edge → if not visited, mark as visited and add to queue

> Marking visited on enqueue — prevents same node from being added to queue multiple times. Required for BFS to guarantee correct level-order traversal and shortest path behavior.

### Topological Sort (Kahn's Algorithm)

A topological sort of a directed graph is an ordering of nodes such that every node appears before all the nodes it points to. Only valid for **directed acyclic graphs (DAGs)** — graphs with cycles have no topological ordering.

Kahn's algorithm uses BFS with in-degree tracking to produce this ordering:

- Initialize a map to store the in-degree of each node
- Go through all nodes and count in-degrees by iterating their neighbors
- Push all nodes with in-degree 0 into a queue
- While queue is not empty → poll into current, add current to result
- For each neighbor of current → subtract 1 from its in-degree
- If a neighbor's in-degree drops to 0 → push it into the queue
- After the queue is empty — if result size < total nodes, a cycle exists (no valid ordering)

> Kahn's algorithm is structurally similar to BFS. The key difference is that BFS pushes all unvisited neighbors, while topological sort only pushes neighbors whose in-degree has dropped to 0.

**Walkthrough: Topological Sort**

    The algorithm processes nodes with no remaining dependencies first.
    When a node is processed, its neighbors lose one dependency (in-degree - 1).
    A neighbor enters the queue only when ALL its dependencies are resolved (in-degree = 0).

    DAG: A→C, B→C, C→D
    In-degrees: A=0, B=0, C=2, D=1
    Queue: [A, B]               ← nodes with no dependencies start first

    Step 1: poll A, result=[A]
      neighbor C: in-degree 2→1  ← C still has one unresolved dependency (B)

    Step 2: poll B, result=[A, B]
      neighbor C: in-degree 1→0  ← all dependencies resolved → enters queue

    Step 3: poll C, result=[A, B, C]
      neighbor D: in-degree 1→0  ← dependency resolved → enters queue

    Step 4: poll D, result=[A, B, C, D]

    Result: [A, B, C, D] — every node appears after its dependencies

### Complexity

| Traversal        | Time     | Space |
|------------------|----------|-------|
| DFS              | O(V + E) | O(V)  |
| BFS              | O(V + E) | O(V)  |
| Topological Sort | O(V + E) | O(V)  |

V = vertices, E = edges. Space includes visited set and the stack (DFS) or queue (BFS/Topological Sort).

### Problems

- [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- [207. Course Schedule](https://leetcode.com/problems/course-schedule/)
- [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
- [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)
