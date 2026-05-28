package heap;

import java.util.Comparator;

public class Heap<T> {

    private final Object[] data;
    private final Comparator<T> comparator;
    private final int DEFAULT_CAPACITY = 10;
    private int size;

    public Heap(Comparator<T> comparator) {
        this.data = new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
        this.size = 0;
    }

    public void insert(T value) {
        data[size] = value;
        heapifyUp(size);
        size++;
    }

    private void heapifyUp(int index) {
        int parentIndex = parentIndex(index);

        // compare(index, parent) < 0 → index lesser than parent in naturalOrder (min heap)
        // greater than parent in reverseOrder (max heap)
        while (hasParent(index) && comparator.compare((T) data[index], (T) data[parentIndex]) < 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parentIndex(index);
        }
    }

    public T poll() {
        T value = get(0);
        data[0] = data[--size]; // move last element to root, decrement size
        data[size] = null;
        heapifyDown(0);
        return value;
    }

    private void heapifyDown(int index) {
        // tree cannot have a right child without a left one in heap
        while (hasLeft(index)) {
            int targetChild = leftIndex(index);

            if (hasRight(index) && comparator.compare(get(rightIndex(index)), get(leftIndex(index))) < 0) {
                targetChild = rightIndex(index);
            }

            if (comparator.compare(get(index), get(targetChild)) < 0) {
                break;
            }

            swap(index, targetChild);
            index = targetChild;
        }
    }

    private T get(int index) {
        return (T) data[index];
    }

    private boolean hasParent(int index) {
        return index > 0;
    }

    private boolean hasLeft(int index) {
        return leftIndex(index) < size;
    }

    private boolean hasRight(int index) {
        return rightIndex(index) < size;
    }

    private int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private int leftIndex(int parentIndex) {
        return (2 * parentIndex) + 1;
    }

    private int rightIndex(int parentIndex) {
        return (2 * parentIndex) + 2;
    }

    private void swap(int i, int j) {
        T temp = (T) data[i];
        data[i] = data[j];
        data[j] = temp;
    }
}