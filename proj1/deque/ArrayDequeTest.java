package deque;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ArrayDeque.
 */
public class ArrayDequeTest {

    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue("A newly initialized ArrayDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        assertEquals(1, ad1.size());
        assertFalse("ad1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    public void addFirstAndGetTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addFirst(10);
        assertEquals(1, ad.size());
        assertEquals(Integer.valueOf(10), ad.get(0));

        ad.addFirst(20);
        assertEquals(2, ad.size());
        assertEquals(Integer.valueOf(20), ad.get(0));
        assertEquals(Integer.valueOf(10), ad.get(1));
    }

    @Test
    public void addLastAndGetTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("a");
        assertEquals(1, ad.size());
        assertEquals("a", ad.get(0));

        ad.addLast("b");
        assertEquals(2, ad.size());
        assertEquals("a", ad.get(0));
        assertEquals("b", ad.get(1));
    }

    @Test
    public void addFirstAndLastTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(10);
        ad.addFirst(5);
        ad.addLast(15);
        ad.addFirst(0);
        // Deque should be: 0, 5, 10, 15
        assertEquals(4, ad.size());
        assertEquals(Integer.valueOf(0), ad.get(0));
        assertEquals(Integer.valueOf(5), ad.get(1));
        assertEquals(Integer.valueOf(10), ad.get(2));
        assertEquals(Integer.valueOf(15), ad.get(3));
    }

    @Test
    public void removeFirstTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(5);
        ad.addLast(10);
        Integer removed = ad.removeFirst();
        assertEquals(Integer.valueOf(5), removed);
        assertEquals(1, ad.size());
        assertEquals(Integer.valueOf(10), ad.get(0));
    }

    @Test
    public void removeLastTest() {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addLast("a");
        ad.addLast("b");
        String removed = ad.removeLast();
        assertEquals("b", removed);
        assertEquals(1, ad.size());
        assertEquals("a", ad.get(0));
    }

    @Test
    public void removeFirstAndLastTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        ad.addLast(10);
        ad.addFirst(5);
        ad.addLast(15);
        ad.addFirst(0); // Deque: 0, 5, 10, 15

        assertEquals(Integer.valueOf(0), ad.removeFirst()); // Deque: 5, 10, 15
        assertEquals(Integer.valueOf(15), ad.removeLast()); // Deque: 5, 10
        assertEquals(2, ad.size());
        assertEquals(Integer.valueOf(5), ad.get(0));
        assertEquals(Integer.valueOf(10), ad.get(1));
    }

    @Test
    public void getTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull(ad.get(0));
        ad.addLast(5);
        ad.addLast(10);
        ad.addLast(15);
        assertEquals(Integer.valueOf(5), ad.get(0));
        assertEquals(Integer.valueOf(10), ad.get(1));
        assertEquals(Integer.valueOf(15), ad.get(2));
        assertNull(ad.get(3));
        assertNull(ad.get(-1));
    }

    @Test
    public void resizeGrowTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            ad.addLast(i);
        }
        assertEquals(10, ad.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(Integer.valueOf(i), ad.get(i));
        }
    }

    @Test
    public void resizeShrinkTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        for (int i = 0; i < 20; i++) {
            ad.addLast(i);
        }
        for (int i = 0; i < 18; i++) {
            ad.removeLast();
        }
        assertEquals(2, ad.size());
        assertEquals(Integer.valueOf(0), ad.get(0));
        assertEquals(Integer.valueOf(1), ad.get(1));
    }

    @Test
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> ad = new ArrayDeque<>();
        assertNull("Should return null when removeFirst is called on an empty Deque,", ad.removeFirst());
        assertNull("Should return null when removeLast is called on an empty Deque,", ad.removeLast());
    }
}