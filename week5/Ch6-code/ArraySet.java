import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            throw new IllegalArgumentException("Can't add null");
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

//    @Override
//    public String toString() {
//        StringBuilder strB = new StringBuilder("{");
//        for (int i = 0; i < size - 1; i++) {
//            strB.append(items[i].toString()).append(", ");
//        }
//        if (size != 0) {
//            strB.append(items[size - 1].toString());
//        }
//        strB.append("}");
//        return strB.toString();
//    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T item : this) {
            listOfItems.add(item.toString());
        }
        return "{" + String.join(", ", listOfItems) + "}";
    }

    public static <E> ArraySet<E> of(E... elements) {
        ArraySet<E> returnSet = new ArraySet<>();
        for (E el : elements) {
            returnSet.add(el);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ArraySet otherAset) {
            if (otherAset.size() != this.size()) {
                return false;
            }
            for (T item : this) {
                if (!otherAset.contains(item)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        /* ArraySet example */
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(1);
        aset.add(2);
        aset.add(3);

        // iteration
        for (int num : aset) {
            System.out.println(num);
        }

        // toString
        System.out.println(aset); // ArraySet@5f184fc6

        // equals
        ArraySet<Integer> aset2 = ArraySet.of(1, 2, 3);
        ArraySet<Integer> aset3 = ArraySet.of(1, 2);
        ArraySet<Integer> aset4 = aset;
        System.out.println(aset.equals(aset2));
        System.out.println(aset.equals(null));
        System.out.println(aset.equals(aset3));
        System.out.println(aset.equals(aset4));

        // .of() method
        ArraySet<Integer> asetOfInts = ArraySet.<Integer>of(1, 2, 3);
        System.out.println(asetOfInts);
    }

}
