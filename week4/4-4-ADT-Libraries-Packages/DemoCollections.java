import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class DemoCollections {

    /**
     * Returns a lower case version of the string with
     * all characters except letters removed.
     */
    public static String cleanString(String s) {
        return s.toLowerCase().replaceAll("[^a-z]", "");
    }

    /**
     * Gets a list of all words in the file.
     */
    public static List<String> getWords(String inputFilename) {
        List<String> words = new ArrayList<>();
        In in = new In(inputFilename);
        while (!in.isEmpty()) {
            String nextWord = cleanString(in.readString());
            words.add(nextWord);
        }
        return words;
    }

    /**
     * Returns the count of the number of unique words in words.
     */
    public static int countUniqueWords(List<String> words) {
        /* Prof. Josh's solution */
//        Set<String> wordSet = new HashSet<>();
//        for (String word : words) {
//            wordSet.add(word);
//        }

        Set<String> wordSet = new HashSet<>(words);
        return wordSet.size();
    }

    /**
     * Returns a map (a.k.a. dictionary) that tracks the count of all specified
     * target words in words.
     */
    public static Map<String, Integer> collectWordCount(List<String> words, List<String> targets) {
        Map<String, Integer> counts = new HashMap<>();

        /* Initialize counts of targets in the map to 0 */
        for (String t : targets){
            counts.put(t, 0);
        }

        for (String s : words) {
            if (counts.containsKey(s)) {
                counts.put(s, counts.get(s) + 1);
            }
        }
        return counts;
    }

    public static void main(String[] args) {
        List<String> w = getWords("lotteryOfBabylon.txt");
        System.out.println(countUniqueWords(w));

		List<String> targets = new ArrayList<>();
		targets.add("lottery");
		targets.add("the");
		targets.add("babylon");

		System.out.println(collectWordCount(w, targets));
    }
}
