import map61b.ArrayMap;
import java.util.Iterator;

public class IterationDemo {
    public static void main(String[] args) {
        ArrayMap<String, Integer> aMap = new ArrayMap<>();

        aMap.put("Breton", 105);
        aMap.put("Alex", 85);
        aMap.put("Eric", 70);

        // behind the scenes of enhanced for-loop
        Iterator<String> seer = aMap.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }

        // enhanced for-loop
        for (String s : aMap) {
            System.out.println(s);
        }
    }
}
