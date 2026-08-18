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

    // BST ordering — go left if smaller, right if larger
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
            // leaf or one child
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // two children — use inorder successor
            Node<T> successor = findMin(node.right);
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

    public boolean validate() {
        return validate(root, null, null);
    }

    private boolean validate(Node<T> node, T min, T max) {
        if (node == null) {
            return true;
        }
        if (min != null && node.data.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && node.data.compareTo(max) >= 0) {
            return false;
        }
        return validate(node.left, min, node.data) && validate(node.right, node.data, max);
    }

    // LCA for general binary tree — does not use BST ordering
    public Node<T> lcaBinaryTree(Node<T> node, T val1, T val2) {
        if (node == null || node.data.equals(val1) || node.data.equals(val2)) {
            return node;
        }
        Node<T> left = lcaBinaryTree(node.left, val1, val2);
        Node<T> right = lcaBinaryTree(node.right, val1, val2);
        if (left != null && right != null) {
            return node;
        }
        return left != null ? left : right;
    }

    private int maxDiameter;

    public int diameter() {
        maxDiameter = 0;
        diameterHelper(root);
        return maxDiameter;
    }

    private int diameterHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = diameterHelper(node.left);
        int rightHeight = diameterHelper(node.right);
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private int maxSum;

    public int maxPathSum() {
        maxSum = Integer.MIN_VALUE;
        maxPathSumHelper(root);
        return maxSum;
    }

    private int maxPathSumHelper(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int val = ((Number) node.data).intValue();
        int leftGain = Math.max(0, maxPathSumHelper(node.left));
        int rightGain = Math.max(0, maxPathSumHelper(node.right));
        maxSum = Math.max(maxSum, val + leftGain + rightGain);
        return val + Math.max(leftGain, rightGain);
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