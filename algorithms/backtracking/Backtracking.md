## Backtracking

Backtracking tries all possible solutions and picks the valid ones. It follows DFS — go deep into one path, if it leads to a dead end, undo the last choice and try a different path. This continues until a valid solution is found or all possibilities are exhausted.

#### State Space Tree
Every backtracking problem can be visualized as a tree where each node represents a state of the solution being built. Each path from root to leaf represents one complete candidate.

#### Pruning
Pruning means abandoning a path early when it already violates the problem constraints, without exploring further. This avoids unnecessary computation and is what separates backtracking from plain brute force.

#### Template
- If base condition is met → add current result to output and return
- For each choice available at current state:
  - Make the choice — add to current result
  - Recurse into next state
  - Undo the choice — remove from current result (backtrack)

> Always visualize the problem as a State Space Tree before coding — it makes it clear what choices exist at each level, where to prune, and what the base case looks like.

#### Subsets

**Problem**: generate all possible selections from a set.

At each index, include or skip the element and recurse forward. Every node is a valid subset.

    [1, 2, 3] → [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
    total = 2ⁿ = 2³ = 8

**Walkthrough: Subsets([1, 2, 3])**

    At each index: include or skip, recurse to next (never backward).
    Every leaf is a valid subset.

    index=0, element 1:
      include 1 → index=1, element 2:
        include 2 → index=2, element 3:
          include 3 → base → add [1,2,3]
          skip 3   → base → add [1,2]
        skip 2 → index=2, element 3:
          include 3 → base → add [1,3]
          skip 3   → base → add [1]
      skip 1 → index=1, element 2:
        include 2 → index=2, element 3:
          include 3 → base → add [2,3]
          skip 3   → base → add [2]
        skip 2 → index=2, element 3:
          include 3 → base → add [3]
          skip 3   → base → add []

    n elements × 2 choices = 2ⁿ = 8 subsets

#### Permutations

**Problem**: generate all orderings of elements.

At each step, try each unused element. Mark used before recursing, unmark after (backtrack). Base case: permutation reaches size r.

    [A, B, C], r=2 → AB, BA, BC, CB, AC, CA
    formula: n! / (n - r)!  →  3! / (3-2)!  =  6 / 1  =  6

#### Combinations

**Problem**: select r elements regardless of order.

At each index, include and recurse forward, then remove (backtrack). Start index prevents reuse. Base case: combination reaches size r.

    [A, B, C], r=2 → AB, BC, AC
    formula: n! / r!(n - r)!  →  3! / 2!(3-2)!  =  6 / 2  =  3

#### Key Difference — index vs start

|              | Order matters | Recurse with                               |
|--------------|---------------|--------------------------------------------|
| Subsets      | No            | `i + 1` — only look forward                |
| Combinations | No            | `i + 1` — only look forward                |
| Permutations | Yes           | `0` every time — all elements reconsidered |

Subsets and Combinations pass `i + 1` to avoid counting `[1,2]` and `[2,1]` as different.
Permutations loop from 0 every time because order is significant and both are valid distinct results.

### Complexity

| Problem      | Time  | Space |
|--------------|-------|-------|
| Subsets      | O(2ⁿ) | O(n)  |
| Combinations | O(2ⁿ) | O(n)  |
| Permutations | O(n!) | O(n)  |

> n! grows faster than 2ⁿ — even at n=5, 5! = 120 vs 2⁵ = 32.
> The gap widens as n increases, making permutation problems the most expensive of the three.

### Problems

- [78. Subsets](https://leetcode.com/problems/subsets/)
- [39. Combination Sum](https://leetcode.com/problems/combination-sum/)
- [46. Permutations](https://leetcode.com/problems/permutations/)
- [79. Word Search](https://leetcode.com/problems/word-search/)