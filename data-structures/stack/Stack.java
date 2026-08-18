package stack;

import java.util.Arrays;

public class Stack<T> {

    private Object[] data;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public Stack() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.top = -1;
    }

    public void push(T value) {
        resizeIfRequired();
        data[++top] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        T value = (T) data[top];
        data[top--] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return (T) data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    private void resizeIfRequired() {
        if (top == data.length - 1) {
            data = Arrays.copyOf(data, 2 * data.length);
        }
    }
}