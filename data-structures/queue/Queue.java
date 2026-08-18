package queue;

import java.util.Arrays;

public class Queue<T> {

    private Object[] data;
    private int front;
    private int rear;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public Queue() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enQueue(T item) {
        resizeIfRequired();
        rear = (rear + 1) % data.length; // circular wrap
        data[rear] = item;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T deQueue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        T item = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length; // circular wrap
        size--;
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return (T) data[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resizeIfRequired() {
        if (size == data.length) {
            data = Arrays.copyOf(data, 2 * data.length);
        }
    }
}