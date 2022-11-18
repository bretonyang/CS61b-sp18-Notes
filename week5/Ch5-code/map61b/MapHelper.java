package map61b;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

public class MapHelper {

    /**
     * Returns the value corresponding to the given key in the map
     * if it exists, otherwise null.
     */
    public static <K, V> V get(Map61B<K, V> map, K key) {
        if (map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    /**
     * Returns the maximum of all keys in the given ArrayMap.
     * Works only if keys can be compared.
     */
    public static <K extends Comparable<K>, V> K maxKey(Map61B<K, V> map) {
        List<K> keyList = map.keys();
        K largest = keyList.get(0);
        for (K k : keyList) {
            if (k.compareTo(largest) > 0) {
                largest = k;
            }
        }
        return largest;
    }

    @Test
    public void testGet() {
        Map61B<String, Integer> m = new ArrayMap<>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);
        Integer expected = 9;
        Integer actual = MapHelper.get(m, "fish");
        Integer actual2 = MapHelper.get(m, "aewriojoa");

        assertEquals(expected, actual);
        assertNull(actual2);
    }

    @Test
    public void testMaxKey() {
        Map61B<String, Integer> m = new ArrayMap<>();
        m.put("horse", 3);
        m.put("fish", 9);
        m.put("house", 10);

        String expected = "house";
        String actual = MapHelper.maxKey(m);
        assertEquals(expected, actual);
    }

}
