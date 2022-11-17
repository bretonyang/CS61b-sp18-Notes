package map61b;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ArrayMap<K, V> implements Map61B<K, V> {

    private K[] keys;
    private V[] values;
    int size;

    public ArrayMap() {
        keys = (K[]) new Object[100];
        values = (V[]) new Object[100];
        size = 0;
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return keyIndex(key) > -1;
    }

    /**
     * Returns the value to which the specified key is mapped. No defined
     * behavior if the key doesn't exist (ok to crash).
     */
    @Override
    public V get(K key) {
        return values[keyIndex(key)];
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     */
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

    @Test
    public void test() {
        ArrayMap<Integer, Integer> am = new ArrayMap<>();
        am.put(2, 5);
        assertEquals(5, am.get(2));
    }

    public static void main(String[] args) {
        ArrayMap<String, Integer> m = new ArrayMap<>();
        m.put("horse", 3);
        m.put("fish", 7);
        m.put("bird", 10);
    }

}
