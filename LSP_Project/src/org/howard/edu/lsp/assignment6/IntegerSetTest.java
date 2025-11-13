package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test class for the {@link IntegerSet} class.
 * 
 * <p>This test suite verifies the correctness of all public methods in the
 * IntegerSet class, including edge cases, exception handling, mutation behavior,
 * and correct logical outcomes of set operations.</p>
 * 
 * <p>The tests ensure compliance with assignment requirements, such as:
 * <ul>
 *   <li>No duplicates allowed</li>
 *   <liCorrect union, intersection, difference, and complement operations</li>
 *   <li>Handling empty-set exceptions for largest() and smallest()</li>
 *   <li>Order-independent equals() behavior</li>
 * </ul>
 * </p>
 */
public class IntegerSetTest {

    /**
     * Tests the clear() method to ensure the set becomes empty.
     */
    @Test
    public void testClear() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    /**
     * Tests the length() method for empty and non-empty sets.
     */
    @Test
    public void testLength() {
        IntegerSet set = new IntegerSet();
        assertEquals(0, set.length());
        set.add(5);
        set.add(10);
        assertEquals(2, set.length());
    }

    /**
     * Tests equals() with equal sets, different sets, and order variations.
     */
    @Test
    public void testEquals() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        b.add(2);
        b.add(1);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));

        b.add(3);
        assertFalse(a.equals(b));
    }

    /**
     * Tests the contains() method for present and missing values.
     */
    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(10);

        assertTrue(set.contains(10));
        assertFalse(set.contains(5));
    }

    /**
     * Tests largest() for correct maximum value.
     */
    @Test
    public void testLargest() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(7);

        assertEquals(10, set.largest());
    }

    /**
     * Tests that largest() throws IllegalStateException when the set is empty.
     */
    @Test
    public void testLargestThrows() {
        IntegerSet empty = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> empty.largest());
    }

    /**
     * Tests smallest() for correct minimum value.
     */
    @Test
    public void testSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(10);
        set.add(7);

        assertEquals(3, set.smallest());
    }

    /**
     * Tests that smallest() throws IllegalStateException when the set is empty.
     */
    @Test
    public void testSmallestThrows() {
        IntegerSet empty = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> empty.smallest());
    }

    /**
     * Tests add() to ensure duplicates are not inserted.
     */
    @Test
    public void testAdd() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1);   // duplicate ignored

        assertEquals(1, set.length());
        assertTrue(set.contains(1));
    }

    /**
     * Tests remove() to ensure elements are properly removed.
     */
    @Test
    public void testRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.remove(1);

        assertFalse(set.contains(1));
        assertTrue(set.contains(2));
    }

    /**
     * Tests union() to ensure the current set properly absorbs elements
     * from the other set without duplicates.
     */
    @Test
    public void testUnion() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        b.add(2);
        b.add(3);

        a.union(b);

        assertTrue(a.contains(1));
        assertTrue(a.contains(2));
        assertTrue(a.contains(3));
        assertEquals(3, a.length());
    }

    /**
     * Tests intersect() to ensure only common elements are retained.
     */
    @Test
    public void testIntersect() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);
        b.add(4);

        a.intersect(b);

        assertEquals(1, a.length());
        assertTrue(a.contains(2));
        assertFalse(a.contains(1));
        assertFalse(a.contains(3));
    }

    /**
     * Tests diff() to ensure all elements found in the other set
     * are removed from the current set.
     */
    @Test
    public void testDiff() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(2);
        a.add(3);

        b.add(2);

        a.diff(b);

        assertEquals(2, a.length());
        assertTrue(a.contains(1));
        assertTrue(a.contains(3));
        assertFalse(a.contains(2));
    }

    /**
     * Tests complement() to ensure the set becomes (other \ this).
     */
    @Test
    public void testComplement() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();

        a.add(1);
        a.add(3);

        b.add(1);
        b.add(2);
        b.add(3);
        b.add(4);

        a.complement(b); // becomes b \ a = {2, 4}

        assertEquals(2, a.length());
        assertTrue(a.contains(2));
        assertTrue(a.contains(4));
        assertFalse(a.contains(1));
        assertFalse(a.contains(3));
    }

    /**
     * Tests isEmpty() for empty and non-empty sets.
     */
    @Test
    public void testIsEmpty() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());

        set.add(1);
        assertFalse(set.isEmpty());
    }

    /**
     * Tests toString() formatting.
     * Order may vary, so both valid orders are accepted.
     */
    @Test
    public void testToString() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);

        String str = set.toString();
        assertTrue(str.equals("[1, 2]") || str.equals("[2, 1]"));
    }
}
