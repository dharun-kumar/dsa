## Dynamic Programming

Dynamic Programming solves problems by breaking them into overlapping subproblems, solving each once, and storing results to avoid redundant computation. It applies when a problem has **optimal substructure** — the best solution to the whole is built from best solutions to its parts — and **overlapping subproblems** — the same subproblem recurs across different branches of the recursion tree.

- **Memoization** (Top-Down) → keep recursion, cache each result on first compute
- **Tabulation** (Bottom-Up) → iterative, fill a dp table from base cases up

#### Why DP is needed

Without DP, a recursive solution recomputes the same subproblems repeatedly. For a recurrence like `f(n) = f(n-1) + f(n-2)`, plain recursion expands into a tree where the same calls appear multiple times:

    f(5) → f(4) + f(3)
            f(4) → f(3) + f(2)
                    f(3) → f(2) + f(1)     ← f(3) computed again
                            f(2) → f(1) + f(0)  ← f(2) computed again

This grows exponentially — O(2ⁿ). DP eliminates this by solving each subproblem once and storing its result.

#### Memoization (Top-Down)

Keep the recursive structure, but add a cache. Before computing any subproblem, check if it's already stored — return it instead of recursing.

    f(5): not in cache → recurse f(4) + f(3)
      f(4): not in cache → recurse f(3) + f(2)
        f(3): not in cache → compute → store in cache
        f(2): not in cache → compute → store in cache
      f(4) = 5, store in cache
      f(3): found in cache → return 3           ← skipped entire subtree
    f(5) = 8

Each subproblem computed once and cached. Time drops from O(2ⁿ) to O(n).

#### Tabulation (Bottom-Up)

No recursion. Fill a dp array from base cases forward — each entry depends only on already-filled entries.

    dp[0] = 0, dp[1] = 1                      ← base cases
    dp[2] = dp[1] + dp[0] = 1
    dp[3] = dp[2] + dp[1] = 2
    dp[4] = dp[3] + dp[2] = 3
    dp[5] = dp[4] + dp[3] = 5                 ← answer

Same O(n) time, no recursion stack. When dp[i] only depends on the last 1-2 entries, the array can be replaced with rolling variables for O(1) space.

> Both approaches give the same result. Memoization is easier to write (add cache to existing recursion), tabulation is faster in practice (no recursion stack) and easier to space-optimize.

---

### 1D DP

`dp[i]` represents the optimal answer considering the first `i` elements. Each state depends on one or more previous entries in the same array.

#### How to identify
- The problem asks for an optimal value (max, min, count) over a sequence
- At each element, you make a choice — include it or skip it, extend or start fresh

#### Take or skip

**Problem**: maximize sum of non-adjacent elements.

You have a row of values and can't pick two adjacent ones. Find the combination that gives the maximum sum.

Use tabulation to track the best sum at each position. At each element, either skip it and keep the previous best, or take it and add it to the best from two positions back.

    dp[i] = max(dp[i-1], dp[i-2] + nums[i])

nums = [2, 7, 9, 3, 1]

    dp[0] = 2                              ← only one choice
    dp[1] = max(2, 7) = 7                  ← 7 is better alone
    dp[2] = max(7, 2 + 9) = 11            ← skip 7, take 2 + 9
    dp[3] = max(11, 7 + 3) = 11           ← skip 3, keep 11
    dp[4] = max(11, 11 + 1) = 12          ← take 1, add to dp[2]

    dp[4] = 12

Since dp[i] only uses dp[i-1] and dp[i-2], the array can be replaced with two variables for O(1) space.

#### Extend subsequence

**Problem**: find the longest increasing subsequence.

Given a sequence of numbers, find the longest subsequence where each element is larger than the one before it. The elements don't need to be adjacent.

Use tabulation to track the longest increasing subsequence ending at each index. For each element, look back at all previous smaller elements and extend the best one.

    dp[i] = max(dp[i], dp[j] + 1) where nums[j] < nums[i]

nums = [1, 3, 2, 5]

    Start:    [1, 1, 1, 1]                 ← each element is length 1
    After 3:  [1, 2, 1, 1]                 ← 1 < 3, extend
    After 2:  [1, 2, 2, 1]                 ← 1 < 2, extend
    After 5:  [1, 2, 2, 3]                 ← 3 < 5, extend to length 3

    max(dp) = 3 (subsequence: 1, 3, 5)

Unlike take or skip which checks the last 1-2 entries, this checks all previous elements — O(n²).

### 2D DP

`dp[i][j]` represents the optimal answer for a subproblem defined by two parameters — two string positions, grid coordinates, or a range.

#### How to identify
- Two inputs being compared (two strings, two sequences)
- A grid where you move in limited directions
- The answer at `(i, j)` depends on neighboring cells

**Problem**: find the longest common subsequence of two strings.

Given two strings, find the longest sequence of characters that appears in both in the same order. The characters don't need to be adjacent.

Use tabulation with a 2D matrix. Compare characters one by one — if they match, extend the diagonal. If not, keep the better of dropping a character from either string.

    match:    dp[i][j] = dp[i-1][j-1] + 1
    mismatch: dp[i][j] = max(dp[i-1][j], dp[i][j-1])

s1 = "ace", s2 = "abcde"

        ""  a  b  c  d  e
    ""   0  0  0  0  0  0
    a    0  1  1  1  1  1    ← 'a' matches 'a'
    c    0  1  1  2  2  2    ← 'c' matches 'c'
    e    0  1  1  2  2  3    ← 'e' matches 'e'

    dp[3][5] = 3 (LCS: "ace")

Each row only reads the previous row → can be space-optimized to O(n).

### Knapsack

Select items to optimize a value (maximize profit, minimize count) within a capacity constraint.

#### How to identify
- A set of items with values and costs
- A constraint (weight limit, target sum, budget)
- Decide for each item: use it or skip it

#### Unbounded Knapsack

**Problem**: find minimum coins to make a target amount.

You have coins of different values and need to make a target amount. Each coin can be reused as many times as you want — find the fewest coins needed.

Use tabulation to track the fewest coins needed for each amount from 0 to target. Process one coin at a time — for each amount, check if using this coin gives a smaller count than what we already have.

    dp[i] = min(dp[i], dp[i - coin] + 1)

coins = [1, 2, 5], amount = 6

    Start:        [0, 7, 7, 7, 7, 7, 7]     ← filled with amount + 1 (sentinel)
    After coin 1: [0, 1, 2, 3, 4, 5, 6]     ← all amounts made with 1s
    After coin 2: [0, 1, 1, 2, 2, 3, 3]     ← coin 2 improves some amounts
    After coin 5: [0, 1, 1, 2, 2, 1, 2]     ← coin 5 improves 5 and 6

    dp[6] = 2 (coin 5 + coin 1)

#### 0/1 Knapsack

**Problem**: determine if an array can be split into two equal-sum subsets.

Given an array of numbers, check if you can split them into two groups with the same total. Each number is used only once.

Use tabulation to track which sums are reachable. Process one number at a time — iterate right to left so each number is counted once.

    dp[i] = dp[i] || dp[i - num]

nums = [1, 2, 3], target = 3

    Start:    [T, F, F, F]
    After 1:  [T, T, F, F]                ← sum 1 reachable
    After 2:  [T, T, T, T]                ← sums 2, 3 reachable

    dp[3] = true (subsets: {1, 2} and {3})

**Left to right** → item reusable (unbounded). **Right to left** → item used once (0/1).

### Complexity

Complexity is problem-specific. Both approaches eliminate recomputation of overlapping subproblems.

| Approach    | Time       | Space      |
|-------------|------------|------------|
| Memoization | O(states)  | O(states)  |
| Tabulation  | O(states)  | O(states)  |

states = total unique subproblems. Space can be reduced to O(n) or O(1) when only a rolling window of prior states is needed.

### Problems

#### 1D DP
- [70. Climbing Stairs](https://leetcode.com/problems/climbing-stairs/)
- [198. House Robber](https://leetcode.com/problems/house-robber/)
- [139. Word Break](https://leetcode.com/problems/word-break/)
- [300. Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence/)

#### 2D DP
- [62. Unique Paths](https://leetcode.com/problems/unique-paths/)
- [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/)
- [72. Edit Distance](https://leetcode.com/problems/edit-distance/)

#### Knapsack
- [322. Coin Change](https://leetcode.com/problems/coin-change/) — Unbounded Knapsack
- [416. Partition Equal Subset Sum](https://leetcode.com/problems/partition-equal-subset-sum/) — 0/1 Knapsack
