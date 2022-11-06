import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Sort class.
 */
public class TestSort {

    /**
     * Test the Sort.sort() method
     */
    @Test
    public void testSort() {
        String[] actual = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(actual);
        assertArrayEquals(expected, actual);

        String[] actual2 = {"apple", "bed", "cat", "deck"};
        String[] expected2 = {"apple", "bed", "cat", "deck"};
        Sort.sort(actual2);
        assertArrayEquals(expected2, actual2);

        String[] actual3 = {"deck", "cat", "bed", "apple"};
        String[] expected3 = {"apple", "bed", "cat", "deck"};
        Sort.sort(actual3);
        assertArrayEquals(expected3, actual3);
    }

    /**
     * Test the Sort.findSmallest() method.
     */
    @Test
    public void testFindSmallestIndex() {
        String[] input= {"i", "have", "an", "egg"};
        int expected = 2;
        int actual = Sort.findSmallestIndex(input, 0);
        assertEquals(expected, actual);

        String[] input2 = {"i", "am", "feeling", "sleepy", "now"};
        int expected2 = 2;
        int actual2 = Sort.findSmallestIndex(input, 2);
        assertEquals(expected2, actual2);
    }

    /**
     * Test the Sort.swap() method.
     */
    @Test
    public void testSwap() {
        String[] actual = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};
        Sort.swap(actual, a, b);
        assertArrayEquals(expected, actual);
    }

}
