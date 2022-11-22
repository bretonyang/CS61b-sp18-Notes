import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* Naive implementation of ArraySet, where resizing is not
* taken into account. */
public class ArraySet<T> implements Iterable<T> {

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

    /**
     * Returns an iterator (a.k.a. seer) over the elements in this set.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int curIdx;

        public ArraySetIterator() {
            curIdx = 0;
        }

        @Override
        public boolean hasNext() {
            return curIdx < size;
        }

        @Override
        public T next() {
            T returnItem = items[curIdx];
            curIdx++;
            return returnItem;
        }
    }

    public static void main(String[] args) {
        /* Java Set example */
        Set<String> javaset = new HashSet<>();
        javaset.add("Tokyo");
        javaset.add("Taiwan");

//        Iterator<String> seer = javaset.iterator();
//        while (seer.hasNext()) {
//            String city = seer.next();
//            System.out.println(city);
//        }

        for (String city : javaset) {
            System.out.println(city);
        }

        /* ArraySet example */
        ArraySet<String> aset = new ArraySet<>();
//        s.add(null);
        aset.add("horse");
        aset.add("fish");
        aset.add("cow");
        aset.add("cow");

//        Iterator<String> seer = aset.iterator();
//        while(seer.hasNext()) {
//            String animal = seer.next();
//            System.out.println(animal);
//        }

        for (String animal : aset) {
            System.out.println(animal);
        }
    }

}
