package map61b;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayMap<K, V> implements Map61B<K, V>, Iterable<K> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int curIndex;

        public KeyIterator() {
            curIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return curIndex < size;
        }

        @Override
        public K next() {
            K returnKey = keys[curIndex];
            curIndex++;
            return returnKey;
        }
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return keyIndex(key) > -1;
    }

    /**
     * Returns the value to which the specified key is mapped.
     */
    @Override
    public V get(K key) {
        int index = keyIndex(key);
        if (index == -1) {
            return null;
        }
        return values[index];
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     */
    @Override
    public void put(K key, V value) {
        int idx = keyIndex(key);
        if (idx == -1) {
            // new key-value pair
            keys[size] = key;
            values[size] = value;
            size++;
        } else {
            values[idx] = value;
        }
    }

    /**
     * Returns a list of the keys in this map.
     */
    @Override
    public List<K> keys() {
        List<K> keyList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keyList.add(keys[i]);
        }
        return keyList;
    }

    /**
     * Returns the index of the key, if it exists. Otherwise returns -1.
     */
    private int keyIndex(K key) {
        for (int i = 0; i < size; i++) {
            if (keys[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }

}
