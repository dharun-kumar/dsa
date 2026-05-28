package trees.traverse;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import trees.BSTree.Node;

public class BSTreeTraverse {

    public static <T> void preOrder(Node<T> current) {

        Stack<Node<T>> stack = new Stack<>();

        while(current != null || !stack.isEmpty()) {

            if(current != null) {
                System.out.print(current.data + " -> ");
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop().right;
            }
        }
    }

    public static <T> void inOrder(Node<T> current) {

        Stack<Node<T>> stack = new Stack<>();

        while(current != null || !stack.isEmpty()) {

            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                current = stack.pop();
                System.out.print(current.data + " -> ");
                current = current.right;
            }
        }
    }

    public static <T> void postOrder(Node<T> current) {

        Stack<Node<T>> stack = new Stack<>();
        Node<T> visited = null;

        while (current != null || !stack.isEmpty()) {

            if(current != null) {
                stack.push(current);
                current = current.left;
            } else {
                Node<T> peek = stack.peek();

                if(peek.right != null && peek.right != visited) {
                    current = peek.right;
                } else {
                    visited = stack.pop();
                    System.out.print(visited.data +" -> ");
                }
            }
        }
    }

    public static <T> void levelOrder(Node<T> current) {

        Queue<Node<T>> queue = new LinkedList<>();

        if(current != null) {
            queue.offer(current);
        }

        while(!queue.isEmpty()) {

            current = queue.poll();
            System.out.print(current.data + " -> ");

            if(current.left != null) {
                queue.offer(current.left);
            }
            if(current.right != null) {
                queue.offer(current.right);
            }

        }

    }

    public static <T> void preOrderRecursive(Node<T> current) {

        if(current == null) {
            return;
        }

        System.out.print(current.data + " -> ");
        preOrderRecursive(current.left);
        preOrderRecursive(current.right);
    }

    public static <T> void inOrderRecursive(Node<T> current) {

        if(current == null) {
            return;
        }

        inOrderRecursive(current.left);
        System.out.print(current.data + " -> ");
        inOrderRecursive(current.right);
    }


    public static <T> void postOrderRecursive(Node<T> current) {

        if(current == null) {
            return;
        }

        postOrderRecursive(current.left);
        postOrderRecursive(current.right);
        System.out.print(current.data + " -> ");
    }

}
