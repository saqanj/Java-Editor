import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test class for the methods in the ArraySequence class.
 *
 * @author Saqlain Anjum
 * @author Jonathan Johnson
 * @version 1.0.0
 */
class ArraySequenceTest {

    /**
     * Populates an ArraySequence, and returns the new collection
     * to be used for testing.
     *
     * @return The newly populated ArraySequence.
     */
    private ArraySequence<Character> getCollection() {
        ArraySequence<Character> as = new ArraySequence<>();

        // Verify addFirst() and add()
        as.addFirst('A');
        as.add(1, 'B');
        as.add(2, 'C');
        return as;
    }

    /**
     * A test for checking if the ArraySequence is empty.
     */
    @Test
    void empty() {
        ArraySequence<Character> as = new ArraySequence<>();
        assertTrue(as.isEmpty(), "Initial collection state should be empty.");
    }

    /**
     * A test for adding elements to the ArraySequence.
     */
    @Test
    void addElements() {
        ArraySequence<Character> as = getCollection();

        assertEquals(as.toString(), "{(0,A),(1,B),(2,C)}");
        assertEquals(as.size(), 3);
    }

    /**
     * A test for retrieving elements in the ArraySequence.
     */
    @Test
    void get() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.get(1), 'B', "get(1) expecting B");
    }

    /**
     * A test for setting different elements of the ArraySequence.
     */
    @Test
    void set() {
        ArraySequence<Character> as = getCollection();
        as.addLast('D');
        as.set(as.size() - 1, '!');

        assertEquals(as.toString(), "{(0,A),(1,B),(2,C),(3,!)}");
    }

    /**
     * A test for removing elements of the ArraySequence.
     */
    @Test
    void remove() {
        ArraySequence<Character> as = getCollection();
        as.addLast('D');
        as.set(as.size() - 1, '!');

        as.remove(as.size() - 1);
        assertEquals(as.toString(), "{(0,A),(1,B),(2,C)}");
    }

    /**
     * A test for retrieving the first element of the ArraySequence.
     */
    @Test
    void first() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.first().getElement(), 'A');
    }

    /**
     * A test for retrieving the last element of the ArraySequence.
     */
    @Test
    void last() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.last().getElement(), 'C');
    }

    /**
     * A test for retrieving an element before another one in an ArraySequence.
     */
    @Test
    void before() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.before(as.atIndex(1)), as.first());
    }

    /**
     * A test for retrieving an element after another one in an ArraySequence.
     */
    @Test
    void after() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.after(as.atIndex(1)), as.last());
    }

    /**
     * A test for retrieving an element at an Index in an ArraySequence.
     */
    @Test
    void atIndex() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.atIndex(0), as.first());
    }

    /**
     * A test for determining if certain elements of the ArraySequence are equal.
     */
    @Test
    void equals() {
        ArraySequence<Character> as = getCollection();
        assertEquals(as.last(), as.last());
    }

    /**
     * A test for the removal of all elements from an ArraySequence.
     */
    @Test
    void removalAll() {
        ArraySequence<Character> as = getCollection();
        int size = as.size();
        for (int i = 0; i < size; i++) {
            as.remove(0);
        }
        assertTrue(as.isEmpty(), "Initial collection state should be empty.");
    }

    /**
     * A method for testing the addFirst and addLast methods together to determine
     * the overall functionality of the addLast method.
     */
    @Test
    void addLast() {
        ArraySequence<Character> as = new ArraySequence<>();

        as.addFirst('Y');
        assertEquals("{(0,Y)}", as.toString());

        as.addFirst('X');
        assertEquals(as.first().getElement(), 'X');

        as.addLast('Z');
        assertEquals(as.toString(), "{(0,X),(1,Y),(2,Z)}");
    }
}
