package graph;

import java.util.*;

public class Graph<T> {

    public static class Edge<T> {
        public T vertex;
        public int weight;

        Edge(T vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    private final Map<T, List<Edge<T>>> adjacencyList;
    private final boolean directed;

    public Graph(boolean directed) {
        this.adjacencyList = new HashMap<>();
        this.directed = directed;
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T srcVertex, T destVertex, int weight) {
        adjacencyList.putIfAbsent(srcVertex, new ArrayList<>());
        adjacencyList.putIfAbsent(destVertex, new ArrayList<>());
        adjacencyList.get(srcVertex).add(new Edge<>(destVertex, weight));
        if (!directed) {
            adjacencyList.get(destVertex).add(new Edge<>(srcVertex, weight));
        }
    }

    public void removeEdge(T srcVertex, T destVertex) {
        List<Edge<T>> edges = adjacencyList.get(srcVertex);
        if (edges != null) {
            edges.removeIf(e -> e.vertex.equals(destVertex));
        }
        if (!directed) {
            List<Edge<T>> reverseEdges = adjacencyList.get(destVertex);
            if (reverseEdges != null) {
                reverseEdges.removeIf(e -> e.vertex.equals(srcVertex));
            }
        }
    }

    public boolean hasEdge(T srcVertex, T destVertex) {
        List<Edge<T>> edges = adjacencyList.get(srcVertex);
        return edges != null && edges.stream().anyMatch(e -> e.vertex.equals(destVertex));
    }

    public List<Edge<T>> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public int getWeight(T srcVertex, T destVertex) {
        List<Edge<T>> edges = adjacencyList.get(srcVertex);
        if (edges == null) {
            return 0;
        }
        return edges.stream()
                .filter(e -> e.vertex.equals(destVertex))
                .mapToInt(e -> e.weight)
                .findFirst()
                .orElse(0);
    }

    public Map<T, List<Edge<T>>> getAdjacencyList() {
        return Collections.unmodifiableMap(adjacencyList);
    }

    public int getVertices() {
        return adjacencyList.size();
    }

    public void print() {
        for (Map.Entry<T, List<Edge<T>>> entry : adjacencyList.entrySet()) {
            System.out.print(entry.getKey() + " -> [");
            for (Edge<T> edge : entry.getValue()) {
                System.out.print(edge.vertex + " (w=" + edge.weight + "), ");
            }
            System.out.println("]");
        }
    }
}