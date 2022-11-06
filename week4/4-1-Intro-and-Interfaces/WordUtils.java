public class WordUtils {
    /** Returns the length of the longest word. */
    public static String longest(List61B<String> list) {
        int maxIdx = 0;
        for (int i = 0; i < list.size(); i += 1) {
            String longestString = list.get(maxIdx);
            String thisString = list.get(i);
            if (thisString.length() > longestString.length()) {
                maxIdx = i;
            }
        }
        return list.get(maxIdx);
    }

    public static void main(String[] args) {
        SLList<String> someList = new SLList<>();
        someList.addLast("elk");
        someList.addLast("are");
        someList.addLast("watching");
        System.out.println(longest(someList));
        //someList.print();
    }
}