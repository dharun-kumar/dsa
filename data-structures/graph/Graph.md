## Graphs

A graph is a non-linear data structure consisting of nodes (vertices) connected by edges. Unlike trees, graphs can have cycles and disconnected components.

> Multiple edges can exist between the same pair of vertices.

#### Types
- **Directed** → edges have direction (A → B does not imply B → A)
- **Undirected** → edges are bidirectional (A — B implies B — A)
- **Weighted** → edges have a cost or distance
- **Cyclic** → contains at least one cycle
- **Acyclic** → no cycles. A directed acyclic graph is called a DAG

#### Key Terms
- **Adjacent** → two vertices directly connected by an edge
- **Path** → sequence of vertices connected by edges
- **Cycle** → path that starts and ends at the same vertex
- **Degree** → number of edges connected to a vertex. In directed graphs — in-degree (incoming) and out-degree (outgoing)
- **Component** → a subgraph where all vertices are reachable from each other but disconnected from other subgraphs

#### Representations
- **Adjacency List** → map of node to list of neighbors, supports any object type. Space efficient for sparse graphs — O(V + E)
- **Adjacency Matrix** → 2D array where `matrix[i][j] = weight` if edge exists, supports only integer vertices (0 to n-1). Space efficient for dense graphs — O(V²)

#### Traversals
- **DFS** → explores as deep as possible before backtracking. Uses a stack (iterative) or call stack (recursive). Time: O(V + E)
- **BFS** → explores all neighbors level by level before going deeper. Uses a queue. Time: O(V + E)

### Implementation

#### Adjacency List
- Use `Map<T, List<Edge<T>>>` where each `Edge` holds destination node and weight
- `addVertex(T)` → add node to map if absent
- `addEdge(T from, T to, int weight)` → add edge from source to destination. For undirected graphs add reverse edge too
- `removeEdge(T from, T to)` → remove edge from source neighbors list. For undirected graphs remove reverse edge too
- `hasEdge(T from, T to)` → check if destination exists in source neighbors list
- `getWeight(T from, T to)` → find edge to destination and return its weight
- `getNeighbors(T)` → return list of edges for given node

#### Adjacency Matrix
- Use `int[vertices][vertices]` where `matrix[i][j]` stores edge weight, 0 means no edge
- Vertices are integers from 0 to n-1
- `addEdge(int from, int to, int weight)` → set `matrix[from][to] = weight`. For undirected graphs set reverse too
- `removeEdge(int from, int to)` → set `matrix[from][to] = 0`. For undirected graphs set reverse too
- `hasEdge(int from, int to)` → return true if `matrix[from][to] != 0`
- `getWeight(int from, int to)` → return `matrix[from][to]`
- `getNeighbors(int vertex)` → collect all indices where `matrix[vertex][i] != 0`

#### Grid as Graph
- A 2D grid treated as a graph where each cell is a node and 4 adjacent cells are edges
- **4-directional neighbors** → up `(row-1, col)`, down `(row+1, col)`, left `(row, col-1)`, right `(row, col+1)`
- **Boundary check before visiting** → `row >= 0 && row < rows && col >= 0 && col < cols`
- Use `boolean[][]` visited array to avoid revisiting cells

### Complexity

| Operation     | Adjacency List | Adjacency Matrix |
|---------------|----------------|------------------|
| Add Vertex    | O(1)           | O(V²)            |
| Add Edge      | O(1)           | O(1)             |
| Remove Edge   | O(E)           | O(1)             |
| Has Edge      | O(E)           | O(1)             |
| Get Neighbors | O(1)           | O(V)             |
| DFS / BFS     | O(V + E)       | O(V²)            |
| Space         | O(V + E)       | O(V²)            |

### Problems

- [200. Number of Islands](https://leetcode.com/problems/number-of-islands/)
- [417. Pacific Atlantic Water Flow](https://leetcode.com/problems/pacific-atlantic-water-flow/)
- [207. Course Schedule](https://leetcode.com/problems/course-schedule/)
- [210. Course Schedule II](https://leetcode.com/problems/course-schedule-ii/)
- [994. Rotting Oranges](https://leetcode.com/problems/rotting-oranges/)
- [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/)
- [127. Word Ladder](https://leetcode.com/problems/word-ladder/)