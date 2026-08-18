package hashmap;

public class HashMap<K, V> {

    private final Entry<K, V>[] dataMap;
    private int size;
    private static final int DEFAULT_CAPACITY = 100;

    @SuppressWarnings("unchecked")
    public HashMap() {
        dataMap = new Entry[DEFAULT_CAPACITY];
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % dataMap.length);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> current = dataMap[index];

        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value; // update existing key
                return;
            }
            current = current.next;
        }

        Entry<K, V> entry = new Entry<>(key, value);
        entry.next = dataMap[index]; // insert at head — O(1), no traversal needed
        dataMap[index] = entry;
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = dataMap[index];

        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> current = dataMap[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    dataMap[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }
}