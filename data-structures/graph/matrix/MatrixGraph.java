package graph.matrix;

import java.util.stream.IntStream;

public class MatrixGraph {

    private final int vertices;
    private final boolean directed;
    private final int[][] matrix;

    public MatrixGraph(int vertices, boolean directed) {
        this.vertices = vertices;
        this.directed = directed;
        this.matrix = new int[vertices][vertices];
    }

    public void addEdge(int srcVertex, int destVertex, int weight) {
        if (srcVertex >= vertices || destVertex >= vertices) {
            throw new RuntimeException("Vertex ID must be less than vertices count");
        }
        matrix[srcVertex][destVertex] = weight;
        if (!directed) {
            matrix[destVertex][srcVertex] = weight;
        }
    }

    public void removeEdge(int srcVertex, int destVertex) {
        if (srcVertex >= vertices || destVertex >= vertices) {
            throw new RuntimeException("Vertex ID must be less than vertices count");
        }
        matrix[srcVertex][destVertex] = 0;
        if (!directed) {
            matrix[destVertex][srcVertex] = 0;
        }
    }

    public boolean hasEdge(int srcVertex, int destVertex) {
        if (srcVertex >= vertices || destVertex >= vertices) {
            throw new RuntimeException("Vertex ID must be less than vertices count");
        }
        return matrix[srcVertex][destVertex] != 0;
    }

    public int[] getNeighbors(int vertex) {
        if (vertex >= vertices) {
            throw new RuntimeException("Vertex ID must be less than vertices count");
        }
        return IntStream.range(0, vertices)
                .filter(i -> matrix[vertex][i] != 0)
                .toArray();
    }

    public int getWeight(int srcVertex, int destVertex) {
        return hasEdge(srcVertex, destVertex) ? matrix[srcVertex][destVertex] : 0;
    }

    public void print() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.println();
        }
    }
}