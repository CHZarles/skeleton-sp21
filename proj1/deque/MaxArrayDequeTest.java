package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Optional;

public class MaxArrayDequeTest {

    private static class IntegerComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void testMaxWithDefaultComparator() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(new IntegerComparator());
        mad.addLast(5);
        mad.addLast(100);
        mad.addLast(10);
        mad.addLast(50);
        assert (mad.max() == 100);
    }

    @Test
    public void testMaxWithCustomComparator() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(String::compareTo);
        mad.addLast("apple");
        mad.addLast("banana");
        mad.addLast("kiwi");

        // Default comparator would return "kiwi"
        // Custom comparator (by length) should return "banana"
        assertEquals("banana", mad.max(new StringLengthComparator()));
    }

    @Test
    public void testMaxOnEmptyDeque() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        assertNull(mad.max());
    }

    @Test
    public void testMaxWithOneElement() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<Integer>(new IntegerComparator());
        mad.addFirst(42);
        assert(42 ==mad.max());
    }

    @Test
    public void testMaxWithNegativeNumbers() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntegerComparator());
        mad.addLast(-5);
        mad.addLast(-100);
        mad.addLast(-1);
        mad.addLast(-50);

        assert(-1 == mad.max());
    }

    @Test
    public void testMaxWithMixedElementsAndCustomComparator() {
        // Default comparator is natural string order
        MaxArrayDeque<String> mad = new MaxArrayDeque<String>(Comparator.naturalOrder());
        mad.addLast("a");
        mad.addLast("long_string");
        mad.addLast("medium");

        // Using default comparator
        assertEquals("medium", mad.max());

        // Using custom length comparator
        assertEquals("long_string", mad.max(new StringLengthComparator()));
    }
}