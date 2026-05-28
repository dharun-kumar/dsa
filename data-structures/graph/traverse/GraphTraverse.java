package graph.traverse;

import graph.Graph;
import java.util.*;

public class GraphTraverse {

    // DFS — iterative
    public static <T> void dfs(Graph<T> graph, T vertex) {
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new Stack<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (visited.contains(current)) {
                continue;
            }
            visited.add(current);
            System.out.print(current + " -> ");
            for (Graph.Edge<T> neighbor : graph.getNeighbors(current)) {
                stack.push(neighbor.vertex);
            }
        }
    }

    // BFS — iterative
    public static <T> void bfs(Graph<T> graph, T vertex) {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedList<>();
        queue.offer(vertex);
        visited.add(vertex);

        while (!queue.isEmpty()) {
            T current = queue.poll();
            System.out.print(current + " -> ");
            for (Graph.Edge<T> neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor.vertex)) {
                    visited.add(neighbor.vertex);
                    queue.offer(neighbor.vertex);
                }
            }
        }
    }

    // DFS — recursive
    public static <T> void dfsRecursive(Graph<T> graph, T vertex) {
        Set<T> visited = new HashSet<>();
        dfsHelper(graph, vertex, visited);
    }

    private static <T> void dfsHelper(Graph<T> graph, T current, Set<T> visited) {
        if (visited.contains(current)) {
            return;
        }
        visited.add(current);
        System.out.print(current + " -> ");
        for (Graph.Edge<T> neighbor : graph.getNeighbors(current)) {
            dfsHelper(graph, neighbor.vertex, visited);
        }
    }

}