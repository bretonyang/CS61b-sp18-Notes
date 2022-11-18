import java.util.HashSet;
import java.util.Set;

/* Naive implementation of ArraySet, where resizing is not
* taken into account. */
public class ArraySet<T> {

    private T[] items;
    private int size;

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /**
     * Add the value to the set if not already present
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Can't add null to an ArraySet");
        }
        if (contains(item)) {
            return;
        }
        items[size] = item;
        size++;
    }

    /**
     * check to see if ArraySet contains the value
     */
    public boolean contains(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(items[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * return number of values
     */
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
//        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");
        System.out.println(s.contains("horse"));
        System.out.println(s.size);

        Set<String> s2 = new HashSet<>();
        s2.add("Tokyo");
        s2.add("Taiwan");
        for (String city : s2) {
            System.out.println(city);
        }
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */

}
