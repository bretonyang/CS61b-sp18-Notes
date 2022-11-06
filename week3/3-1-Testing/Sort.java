public class Sort {

    /**
     * Sorts strings destructively.
     */
    public static void sort(String[] x) {
        sort(x, 0);
    }

    /**
     * Sorts x starting at position start.
     */
    private static void sort(String[] x, int start) {
        if (start == x.length - 1) {
            return;
        }

        int smallestIndex = findSmallestIndex(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }

    /**
     * Returns the index of the smallest String in x, starting at start.
     */
    public static int findSmallestIndex(String[] x, int start) {
        int smallestIndex = start;
        for (int i = start + 1; i < x.length; i++) {
            if (x[i].compareTo(x[smallestIndex]) < 0) {
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }

    /**
     * Swap item at position a with item at position b.
     */
    public static void swap(String[] x, int a, int b) {
        String tmp = x[a];
        x[a] = x[b];
        x[b] = tmp;
    }
}
