package trees;

public class BSTree<T extends Comparable<T>> {

    private Node<T> root;

    public static class Node<T> {
        public T data;
        public Node<T> left, right;

        Node(T data) {
            this.data = data;
        }
    }

    public void insert(T data) {
        root = insert(root, data);
    }

    // Preorder — visit node first, then recurse left and right
    private Node<T> insert(Node<T> node, T data) {
        if (node == null) {
            return new Node<>(data);
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } else if (cmp > 0) {
            node.right = insert(node.right, data);
        }
        return node;
    }

    public boolean search(T data) {
        return search(root, data);
    }

    // Preorder — visit node first, then recurse left and right
    private boolean search(Node<T> node, T data) {
        if (node == null) {
            return false;
        }
        int cmp = data.compareTo(node.data);
        if (cmp == 0) {
            return true;
        }
        if (cmp < 0) {
            return search(node.left, data);
        }
        return search(node.right, data);
    }

    public void delete(T data) {
        root = delete(root, data);
    }

    // Preorder — visit node first, then recurse left and right
    private Node<T> delete(Node<T> node, T data) {
        if (node == null) {
            return null;
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            node.right = delete(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<T> successor = findMin(node.right); // inorder successor
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    public Node<T> findMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Node<T> findMax(Node<T> node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public Node<T> lca(Node<T> node, T val1, T val2) {
        if (node == null) {
            return null;
        }
        int cmp1 = val1.compareTo(node.data);
        int cmp2 = val2.compareTo(node.data);
        if (cmp1 < 0 && cmp2 < 0) {
            return lca(node.left, val1, val2);
        }
        if (cmp1 > 0 && cmp2 > 0) {
            return lca(node.right, val1, val2);
        }
        return node;
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public Node<T> getRoot() {
        return root;
    }
}