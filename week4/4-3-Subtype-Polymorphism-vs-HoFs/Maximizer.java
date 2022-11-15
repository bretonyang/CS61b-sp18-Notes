public class Maximizer {
    public static Comparable max(Comparable[] items) {
        int maxIdx = 0;
        for (int i = 1; i < items.length; i++) {
            if (items[i].compareTo(items[maxIdx]) > 0) {
                maxIdx = i;
            }
        }
        return items[maxIdx];
    }
}
