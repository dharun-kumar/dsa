import graph.Graph;
import graph.matrix.MatrixGraph;
import graph.traverse.GraphTraverse;
import hashmap.HashMap;
import heap.Heap;
import linkedlist.LinkedList;
import queue.Queue;
import stack.Stack;
import trees.BSTree;
import trees.traverse.BSTreeTraverse;
import trie.Trie;

import java.util.Comparator;
import java.util.List;

public class DSPlayground {

    public static void main(String[] args) {

        stack();
        separator();

        queue();
        separator();

        linkedList();
        separator();

        hashMap();
        separator();

        bsTree();
        separator();

        graph();
        separator();

        graphMatrix();
        separator();

        heap();
        separator();

        trie();
    }

    private static void separator() {
        System.out.println("\n---------------------------- \n");
    }

    private static void stack() {
        System.out.println("=== Stack ===");
        Stack<String> stack = new Stack<>();
        stack.push("Alice");
        stack.push("Bob");
        stack.push("Charlie");

        System.out.println("Peek :: " + stack.peek());
        System.out.println("Pop :: " + stack.pop());
        System.out.println("Pop :: " + stack.pop());
        System.out.println("Size :: " + stack.size());
    }

    private static void queue() {
        System.out.println("=== Queue ===");
        Queue<String> queue = new Queue<>();
        queue.enQueue("Alice");
        queue.enQueue("Bob");
        queue.enQueue("Charlie");

        System.out.println("Peek :: " + queue.peek());
        System.out.println("Dequeue :: " + queue.deQueue());
        System.out.println("Dequeue :: " + queue.deQueue());
        System.out.println("Size :: " + queue.size());
    }

    private static void linkedList() {
        System.out.println("=== Linked List ===");
        LinkedList<Integer> list = new LinkedList<>();
        list.insert(20);
        list.insert(25);
        list.insertAtHead(15);
        list.insertAt(1, 10);

        System.out.print("List :: ");
        list.traverse();

        System.out.println("Search 10 :: " + list.search(10));
        list.delete(10);
        System.out.println("Search 10 after delete :: " + list.search(10));

        System.out.print("List after delete :: ");
        list.traverse();
    }

    private static void hashMap() {
        System.out.println("=== HashMap ===");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Alice", 10);
        map.put("Bob", 20);

        System.out.println("Get Alice :: " + map.get("Alice"));
        System.out.println("Contains Alice :: " + map.containsKey("Alice"));

        map.remove("Alice");
        System.out.println("Get Alice after remove :: " + map.get("Alice"));
        System.out.println("Contains Alice after remove :: " + map.containsKey("Alice"));
        System.out.println("Size :: " + map.size());
    }

    private static void bsTree() {
        System.out.println("=== BSTree ===");
        BSTree<Integer> tree = new BSTree<>();
        tree.insert(5);
        tree.insert(15);
        tree.insert(8);
        tree.insert(20);
        tree.insert(3);
        tree.insert(10);

        System.out.print("Inorder (sorted) :: ");
        BSTreeTraverse.inOrder(tree.getRoot());
        System.out.println();

        System.out.print("Preorder :: ");
        BSTreeTraverse.preOrder(tree.getRoot());
        System.out.println();

        System.out.print("Postorder :: ");
        BSTreeTraverse.postOrder(tree.getRoot());
        System.out.println();

        System.out.print("Level Order :: ");
        BSTreeTraverse.levelOrder(tree.getRoot());
        System.out.println();

        System.out.print("Inorder Iterative :: ");
        BSTreeTraverse.inOrder(tree.getRoot());
        System.out.println();

        System.out.println("Height :: " + tree.height());
        System.out.println("Min :: " + tree.findMin(tree.getRoot()).data);
        System.out.println("Max :: " + tree.findMax(tree.getRoot()).data);
        System.out.println("LCA of 3 and 10 :: " + tree.lca(tree.getRoot(), 3, 10).data);
        System.out.println("Search 8 :: " + tree.search(8));

        tree.delete(8);
        System.out.println("Search 8 after delete :: " + tree.search(8));

        System.out.print("Inorder after delete :: ");
        BSTreeTraverse.inOrder(tree.getRoot());
        System.out.println();
    }

    private static void graph() {
        System.out.println("=== Graph (Adjacency List) ===");
        Graph<String> graph = new Graph<>(true);

        graph.addEdge("Chennai", "Bengaluru", 310);
        graph.addEdge("Chennai", "Hyderabad", 550);
        graph.addEdge("Bengaluru", "Chennai", 290);
        graph.addEdge("Bengaluru", "Hyderabad", 620);
        graph.addEdge("Bengaluru", "Mysore", 145);
        graph.addEdge("Hyderabad", "Bengaluru", 600);
        graph.addEdge("Hyderabad", "Visakhapatnam", 620);
        graph.addEdge("Mysore", "Wayanad", 120);
        graph.addEdge("Visakhapatnam", "Chennai", 800);

        graph.print();

        System.out.println("Weight Chennai -> Bengaluru :: " + graph.getWeight("Chennai", "Bengaluru"));
        System.out.println("Has edge Chennai -> Bengaluru :: " + graph.hasEdge("Chennai", "Bengaluru"));

        graph.removeEdge("Chennai", "Bengaluru");
        System.out.println("Has edge Chennai -> Bengaluru after remove :: " + graph.hasEdge("Chennai", "Bengaluru"));

        graph.addEdge("Chennai", "Bengaluru", 310);

        System.out.print("DFS from Chennai :: ");
        GraphTraverse.dfs(graph, "Chennai");

        System.out.print("BFS from Chennai :: ");
        GraphTraverse.bfs(graph, "Chennai");

        // Topological Sort — using a DAG
        Graph<String> dag = new Graph<>(true);
        dag.addEdge("A", "C", 1);
        dag.addEdge("B", "C", 1);
        dag.addEdge("C", "D", 1);

    }

    private static void graphMatrix() {
        System.out.println("=== Graph (Adjacency Matrix) ===");
        MatrixGraph matrix = new MatrixGraph(3, true);

        matrix.addEdge(0, 1, 1);
        matrix.addEdge(1, 0, 1);
        matrix.addEdge(1, 2, 1);
        matrix.addEdge(2, 1, 1);
        matrix.addEdge(2, 0, 1);
        matrix.addEdge(0, 2, 1);

        matrix.print();

        System.out.println("Weight 0 -> 1 :: " + matrix.getWeight(0, 1));
        System.out.println("Has edge 0 -> 1 :: " + matrix.hasEdge(0, 1));

        matrix.removeEdge(0, 1);
        System.out.println("Has edge 0 -> 1 after remove :: " + matrix.hasEdge(0, 1));

        System.out.print("Neighbors of 0 :: ");
        for (int neighbor : matrix.getNeighbors(0)) {
            System.out.print(neighbor + " ");
        }
        System.out.println();
    }

    private static void heap() {
        System.out.println("=== Heap ===");

        Heap<Integer> minHeap = new Heap<>(Comparator.<Integer>naturalOrder());
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(8);
        minHeap.insert(1);

        System.out.println("Min Heap poll :: " + minHeap.poll()); // 1
        System.out.println("Min Heap poll :: " + minHeap.poll()); // 3

        Heap<Integer> maxHeap = new Heap<>(Comparator.<Integer>reverseOrder());
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(8);
        maxHeap.insert(1);

        System.out.println("Max Heap poll :: " + maxHeap.poll()); // 8
        System.out.println("Max Heap poll :: " + maxHeap.poll()); // 5
    }

    private static void trie() {
        System.out.println("=== Trie ===");
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");

        System.out.println("Search app :: " + trie.search("app"));
        System.out.println("Search ap :: " + trie.search("ap"));
        System.out.println("StartsWith ap :: " + trie.startsWith("ap"));

        trie.delete("app");
        System.out.println("Search app after delete :: " + trie.search("app"));
        System.out.println("Search apple after deleting app :: " + trie.search("apple"));
    }
}